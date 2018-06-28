package com.yxld.yxchuangxin.ui.activity.goods.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.GoodsKind;
import com.yxld.yxchuangxin.entity.IsNight;
import com.yxld.yxchuangxin.entity.MallClassify;
import com.yxld.yxchuangxin.entity.goods.ShopCart;
import com.yxld.yxchuangxin.entity.goods.ShopNewList;
import com.yxld.yxchuangxin.ui.activity.goods.MallFragment;
import com.yxld.yxchuangxin.ui.activity.goods.contract.MallContract;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author yuan
 * @Package .ui.activity.goods
 * @Description: presenter of MallFragment
 * @date 2017/06/14
 */
public class MallPresenter implements MallContract.MallContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private MallContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private GoodsKind mGoodsKind;
    private MallFragment mMallFragment;


    @Inject
    public MallPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull MallContract.View view, @Nullable MallFragment fragment) {
        mView = view;
        this.httpAPIWrapper = httpAPIWrapper;
        mCompositeDisposable = new CompositeDisposable();
        mMallFragment = fragment;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        if (!mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
        mView = null;
    }


    @Override
    public void getFenlei() {
        Log.e("wh", "走分类");
        Map<String, String> params = new HashMap<>();
        params.put("uuid", Contains.uuid);
        params.put("fenlei1", "");
        Disposable disposable = httpAPIWrapper.getShangchengFenlei(params)
                .subscribe(new Consumer<MallClassify>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull MallClassify mallClassify) throws Exception {
                        mView.setFenleiAdapter(mallClassify);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                });
        mCompositeDisposable.add(disposable);
    }

//    @Override
//    public void handlerShopCart(ShopCart shopCart, boolean isAdd) {
//        if (shopCart != null) {
//            Contains.shopCartList = shopCart.getRows();
//            Contains.shopCartNum = shopCart.getTotal();
//        }
//        if (Contains.shopCartList == null) {
//            return;
//        }
//        if (mGoodsKind != null) {
//            handlerGoodsKind(mGoodsKind);
//        }
//        mView.setShopCartCount(Contains.shopCartNum);
//
//    }
//
//    private void handlerGoodsKind(GoodsKind goodsKind) {
//        for (GoodsKind.RowsBean.DianzhangtuijianListsBean dianzhangtuijianListsBean : goodsKind.getRows().getDianzhangtuijianLists()) {
//            for (ShopCart.ShapCartBean shapCartBean : Contains.shopCartList) {
//                if (shapCartBean.getCartSpbianhao() == dianzhangtuijianListsBean.getTuijianShangpinId()) {
//                    KLog.i("商品编号匹配，添加数量");
//                    dianzhangtuijianListsBean.setSelectCount(shapCartBean.getCartNum());
//                }
//            }
//        }
//        for (GoodsKind.RowsBean.XinpinListsBean xinpinListsBean : goodsKind.getRows().getXinpinLists()) {
//            for (ShopCart.ShapCartBean shapCartBean : Contains.shopCartList) {
//                if (shapCartBean.getCartSpbianhao() == xinpinListsBean.getXinpinShangpinId()) {
//                    KLog.i("商品编号匹配，添加数量");
//                    xinpinListsBean.setSelectCount(shapCartBean.getCartNum());
//                }
//            }
//        }
//        mView.setShangpin(goodsKind);
//    }


    @Override
    public void getGoodsKinds() {
        Map<String, String> params = new HashMap<>();
        params.put("uuid", Contains.uuid);
        Disposable disposable = httpAPIWrapper.getGoodsKinds(params)
                .subscribe(new Consumer<GoodsKind>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull GoodsKind goodsKind) throws Exception {
                        mGoodsKind = goodsKind;
                        mView.setShangpin(goodsKind);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {

                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                });
        mCompositeDisposable.add(disposable);
    }

    /**
     * 查询购物车列表和购物车中 的数量
     */
    @Override
    public void getShopCart() {
        Map<String, String> params = new HashMap<>();
        params.put("uuid", Contains.uuid);
        Disposable disposable = httpAPIWrapper.getShopCart1(params)
                .subscribe(new Consumer<ShopCart>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull ShopCart shopCart) throws Exception {
                        if (shopCart.getStatus() == 1) {

                            mView.setShopCartCount(shopCart.getTotal());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                });
        mCompositeDisposable.add(disposable);
    }


    @Override
    public void addGood2ShopCart(Map map, View view,String imgUrl) {
        Disposable disposable = httpAPIWrapper.addGood2ShopCart(map)
                .subscribe(new Consumer<BaseEntity>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull BaseEntity baseEntity) throws Exception {
                            mView.addGood2ShopCartSuccess(baseEntity,view,imgUrl);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void isNight(boolean isFirst) {
        Map<String, String> map = new HashMap<>();
        map.put("uuid", Contains.uuid);
        Disposable disposable = httpAPIWrapper.isNight(map)
                .subscribe(new Consumer<IsNight>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull IsNight isNight) throws Exception {
                        mView.setIsNight(isFirst,isNight);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void getNightGoodList(int page, int rows) {
        Map<String, String> map = new HashMap<>();
        map.put("uuid", Contains.uuid);
        map.put("fenlei1", "");
        map.put("fenlei2", "");
        map.put("page", page + "");
        map.put("rows", rows + "");
        httpAPIWrapper.getGoodsList(map).subscribe(new Consumer<ShopNewList>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull ShopNewList shopNewList) throws Exception {
                KLog.i("data" + shopNewList.getRows().toString());
                KLog.i("商品此次拉取的数量:" + shopNewList.getRows().size());
                KLog.i("商品总的数量:" + shopNewList.getTotal());
                mView.setNightData(shopNewList);
            }

        }, new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {

            }
        }, new Action() {
            @Override
            public void run() throws Exception {

            }
        });
    }
}