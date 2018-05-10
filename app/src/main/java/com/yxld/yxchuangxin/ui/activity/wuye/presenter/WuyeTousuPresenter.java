package com.yxld.yxchuangxin.ui.activity.wuye.presenter;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.qiniu.android.common.Zone;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.KeyGenerator;
import com.qiniu.android.storage.Recorder;
import com.qiniu.android.storage.UpCancellationSignal;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;
import com.qiniu.android.storage.persistent.FileRecorder;
import com.qiniu.android.utils.UrlSafeBase64;
import com.socks.library.KLog;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.QiniuToken;
import com.yxld.yxchuangxin.ui.activity.index.util.Bimp;
import com.yxld.yxchuangxin.ui.activity.index.util.FileUtils;
import com.yxld.yxchuangxin.ui.activity.index.util.ImageItem;
import com.yxld.yxchuangxin.ui.activity.wuye.WuyeTousuFragment;
import com.yxld.yxchuangxin.ui.activity.wuye.contract.WuyeTousuContract;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

import static android.app.Activity.RESULT_OK;
import static com.yxld.yxchuangxin.base.BaseActivity.STATUS_CODE_OK;

/**
 * @author hu
 * @Package com.yxld.yxchuangxin.ui.activity.wuye
 * @Description: presenter of WuyeTousuFragment
 * @date 2017/06/20 11:11:21
 */
public class WuyeTousuPresenter implements WuyeTousuContract.WuyeTousuContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private WuyeTousuContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private WuyeTousuFragment mFragment;

    public static final int PHOTOZOOM = 0; // 相册/拍照
    public static final int PHOTOTAKE = 1; // 相册/拍照
    public static final int IMAGE_COMPLETE = 2; // 结果

    //c操作的图片的位置
    private int opreratePosition = -1;

    //上传完成之后图片路径
    public String uploadImgUrl = "";
    public int curUploadImgIndex;

    private volatile boolean isCancelled = false;

    private static final String IMAGE_FILE_LOCATION = "file:///sdcard/temp.jpg";//temp file
    Uri imageUri = Uri.parse(IMAGE_FILE_LOCATION);//The Uri to store the big bitmap
    private String token;
    private UploadManager uploadManager;

    /**
     * 上传图片成功
     */
    public static final int uploadSuccess = 0;
    /**
     * 上传图片失败
     */
    public static final int uploadFaild = 1;

    @Inject
    public WuyeTousuPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull WuyeTousuContract.View view, WuyeTousuFragment fragment) {
        mView = view;
        this.httpAPIWrapper = httpAPIWrapper;
        mCompositeDisposable = new CompositeDisposable();
        this.mFragment = fragment;
    }

    @Override
    public void subscribe() {

    }

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case uploadSuccess:
                    //				Log.d("geek", "uploadSuccess: uploadImgUrl="+uploadImgUrl);

                    mView.onUploadOVer(uploadImgUrl);
                    break;
                case uploadFaild:
                    mView.closeProgressDialog();
                    Toast.makeText(mFragment.getActivity(), "上传失败,请稍后再试!",
                            Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void unsubscribe() {
        if (!mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
        mView = null;
    }

    @Override
    public void saveTousu(Map map) {
        mView.showProgressDialog();
        Disposable disposable = httpAPIWrapper.saveTousuAndJianYi(map)
                .subscribe(new Consumer<BaseEntity>() {
                    @Override
                    public void accept(BaseEntity user) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        mView.onTousuBack();
                        //                        mView.closeProgressDialog();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //onError
                        KLog.i("onError");
                        throwable.printStackTrace();
                        mView.closeProgressDialog();
                        //                        ToastUtil.show(mFragment.getActivity(), mFragment.getString(R.string.loading_failed_1));
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        //onComplete
                        KLog.i("onComplete");
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void fromCameraUpLoad(int position) {
        opreratePosition = position;
        AndPermission.with(mFragment.getActivity())
                .requestCode(PHOTOTAKE)
                .permission(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                )
                .rationale((requestCode, rationale) -> {
                            AndPermission.rationaleDialog(mFragment.getActivity(), rationale).show();
                        }
                )
                .callback(permissionListener)
                .start();
    }

    @Override
    public void fromAlbumUpLoad(int position) {
        opreratePosition = position;
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        mFragment.startActivityForResult(intent, PHOTOZOOM);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PHOTOTAKE://拍照
                if (resultCode == RESULT_OK) {
                    //                    startPhotoZoom(Uri.fromFile(mFile));
                    //系统判断
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Uri uri = FileProvider.getUriForFile(mFragment.getActivity(), mFragment.getActivity().getPackageName() + ".fileprovider", mFile);
                        //裁剪照片
                        startPhotoZoom(uri);
                    } else {
                        startPhotoZoom(Uri.fromFile(mFile));
                    }
                }
                break;
            case PHOTOZOOM:
                if (data == null) {
                    return;
                }
                startPhotoZoom(data.getData());
                break;
            case IMAGE_COMPLETE:
                if (Bimp.tempTousuSelectBitmap.size() < 9 && resultCode == RESULT_OK) {
                    String fileName = String.valueOf(System.currentTimeMillis());
                    Bitmap bitmap = decodeUriAsBitmap(imageUri);
                    String path = FileUtils.saveBitmap(bitmap, fileName);
                    ImageItem takePhoto = new ImageItem();
                    takePhoto.setBitmap(bitmap);
                    if (path != null && !"".equals(path)) {
                        takePhoto.setImagePath(path);
                        takePhoto.setSelected(true);
                        //Bimp.tempTousuSelectBitmap.add(takePhoto);
                        Bimp.tempTousuSelectBitmap.set(opreratePosition, takePhoto);
                        mView.onPickImgBack();
                    } else {
                        Toast.makeText(mFragment.getActivity(), "没有图片", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            default:
                break;
        }
    }

    //以bitmap返回格式解析uri
    private Bitmap decodeUriAsBitmap(Uri uri) {
        Bitmap bitmap = null;
        try {
            //解决oom 内存溢出
            BitmapFactory.Options options=new BitmapFactory.Options();
            options.inSampleSize=8;
            options.inPreferredConfig=Bitmap.Config.RGB_565;
            options.inPurgeable=true;
            options.inInputShareable=true;
            bitmap = BitmapFactory.decodeStream(mFragment.getActivity().getContentResolver().openInputStream(uri),null,options);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }

    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        }
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", true);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);  //imageurl 文件输出的位置
        intent.putExtra("noFaceDetection", true);
        mFragment.startActivityForResult(intent, IMAGE_COMPLETE);
    }

    @Override
    public void upLoadImg() {
        getQiNiuToken();
    }

    @Override
    public void init() {
        KLog.i("初始化");
        //断点上传
        String dirPath = "/storage/emulated/0/Download";
        Recorder recorder = null;
        try {
            File f = File.createTempFile("qiniu_xxxx", ".tmp");
            Log.d("qiniu", f.getAbsolutePath().toString());
            dirPath = f.getParent();
            //设置记录断点的文件的路径
            recorder = new FileRecorder(dirPath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final String dirPath1 = dirPath;
        //默认使用 key 的url_safe_base64编码字符串作为断点记录文件的文件名。
        //避免记录文件冲突（特别是key指定为null时），也可自定义文件名(下方为默认实现)：
        KeyGenerator keyGen = new KeyGenerator() {
            public String gen(String key, File file) {
                // 不必使用url_safe_base64转换，uploadManager内部会处理
                // 该返回值可替换为基于key、文件内容、上下文的其它信息生成的文件名
                String path = key + "_._" + new StringBuffer(file.getAbsolutePath()).reverse();
                Log.d("qiniu", path);
                File f = new File(dirPath1, UrlSafeBase64.encodeToString(path));
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new FileReader(f));
                    String tempString = null;
                    int line = 1;
                    try {
                        while ((tempString = reader.readLine()) != null) {
                            //                          System.out.println("line " + line + ": " + tempString);
                            Log.d("qiniu", "line " + line + ": " + tempString);
                            line++;
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            reader.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return path;
            }
        };

        Configuration config = new Configuration.Builder()
                // recorder 分片上传时，已上传片记录器
                // keyGen 分片上传时，生成标识符，用于片记录器区分是那个文件的上传记录
                .recorder(recorder, keyGen)
                .zone(Zone.zone2) // 设置区域，指定不同区域的上传域名、备用域名、备用IP。
                .build();
        // 实例化一个上传的实例
        uploadManager = new UploadManager(config);
    }

    public void getQiNiuToken() {
        mView.showProgressDialog();
        Map<String, String> map = new HashMap<>();
        Disposable disposable = httpAPIWrapper.getQiniuToken(map)
                .subscribe(new Consumer<QiniuToken>() {
                    @Override
                    public void accept(QiniuToken qinniuToken) throws Exception {
                        //isSuccesse
                        KLog.i("onSuccesse");
                        if (qinniuToken.status != STATUS_CODE_OK) {
                            //                            onError(qinniuToken.MSG,qinniuToken.status);
                            return;
                        }
                        token = qinniuToken.getUptoken();
                        Logger.d(qinniuToken.getUptoken());
                        uploadImgUrl = "";
                        curUploadImgIndex = 0;
                        for (int i = 0; i < Bimp.tempTousuSelectBitmap.size(); i++) {
                            if (!Bimp.tempTousuSelectBitmap.get(i).isSelected()) {
                                KLog.i("第" + i + "张图跳过");
                                continue;
                            }
                            KLog.i("开始传第" + i + "张图");
                            //设定需要添加的自定义变量为Map<String, String>类型 并且放到UploadOptions第一个参数里面
                            //上传策略中使用了自定义变量
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("Android", "我要投诉");
                            isCancelled = false;
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
                            String date = sdf.format(new java.util.Date());
                            //                            Log.d("...", "文件位置：" + Bimp.tempTousuSelectBitmap.get(i).getImagePath());
                            final String curUrl = "upload/tousujianyi/img/" + date + "/" + "android_" + System.currentTimeMillis() + "";
                            //put第二个参数设置文件名
                            KLog.i("图片地址::" + Bimp.tempTousuSelectBitmap.get(i).getImagePath());
                            uploadManager.put(Bimp.tempTousuSelectBitmap.get(i).getImagePath(), curUrl, token, new UpCompletionHandler() {
                                public void complete(String key, ResponseInfo info, JSONObject res) {
                                    curUploadImgIndex++;
                                    if (info.isOK() == true) {
                                        String urlkey = "";
                                        try {
                                            urlkey = res.getString("key");
                                        } catch (Exception e) {
                                            urlkey = "";
                                        }
                                        uploadImgUrl += curUrl + ";";
                                        Log.d("geek", "complete: urlkey=" + urlkey + ",uploadImgUrl=" + uploadImgUrl);
                                        Log.d("geek", "complete: curUploadImgIndex=" + curUploadImgIndex + "," + ",size=" + Bimp.tempTousuSelectBitmap.size());
                                        //判断图片是否都上传完成
                                        if ((curUploadImgIndex) == Bimp.imgTousuCount) {
                                            //										Toast.makeText(Repair.this, "图片上传成功", Toast.LENGTH_SHORT).show();
                                            mHandler.sendEmptyMessage(uploadSuccess);
                                        }
                                    } else {
                                        mHandler.sendEmptyMessage(uploadFaild);
                                    }
                                }
                            }, new UploadOptions(map, null, false,
                                    new UpProgressHandler() {
                                        public void progress(String key, double percent) {
                                            Log.i("qiniu", key + ": " + percent);
                                        }

                                    }, new UpCancellationSignal() {
                                @Override
                                public boolean isCancelled() {
                                    return isCancelled;
                                }
                            }));
                        }
                        //
                        //                        mView.closeProgressDialog();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //onError
                        KLog.i("onError");
                        throwable.printStackTrace();
                        mView.closeProgressDialog();
                        //                        ToastUtil.show(mFragment.getActivity(), mFragment.getString(R.string.loading_failed_1));
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        //onComplete
                        KLog.i("onComplete");
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    File mFile;
    private PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            // 权限申请成功回调。
            if (requestCode == PHOTOTAKE) {
                // TODO ...
                KLog.i("成功的回调");
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                /*mFile = new File(Environment.getExternalStorageDirectory(), "chuangxin/temp.jpg");
                try {
                    mFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                mFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                        + "/test/" + System.currentTimeMillis() + ".jpg");
                mFile.getParentFile().mkdirs();
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                //加载Uri型的文件路径
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mFile));
                //加载Uri型的文件路径
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,
                            FileProvider.getUriForFile(mFragment.getActivity(), mFragment.getActivity().getPackageName() + ".fileprovider", mFile));
                } else {
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mFile));
                }
                mFragment.startActivityForResult(intent, PHOTOTAKE);
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // 权限申请失败回调。
            if (requestCode == PHOTOTAKE) {
                // TODO ...
            }
        }
    };
}