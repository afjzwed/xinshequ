package com.yxld.yxchuangxin.ui.activity.goods.presenter;

import android.support.annotation.NonNull;
import android.view.View;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.base.BaseEntity;
import com.yxld.yxchuangxin.contain.Contains;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.entity.CxwyMallCart;
import com.yxld.yxchuangxin.entity.CxwyMallProduct;
import com.yxld.yxchuangxin.entity.ShopList;
import com.yxld.yxchuangxin.entity.StateOrderNum;
import com.yxld.yxchuangxin.entity.goods.ShopCart;
import com.yxld.yxchuangxin.entity.goods.ShopNewList;
import com.yxld.yxchuangxin.ui.activity.goods.MallFragment;
import com.yxld.yxchuangxin.ui.activity.goods.MallGoodsListActivity;
import com.yxld.yxchuangxin.ui.activity.goods.contract.MallGoodsListContract;
import com.yxld.yxchuangxin.view.MallGoodsListRankView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author Yuan.Y.Q
 * @Package com.yxld.yxchuangxin.ui.activity.goods
 * @Description: presenter of MallGoodsListActivity
 * @date 2017/06/19 14:28:26
 */
public class MallGoodsListPresenter implements MallGoodsListContract.MallGoodsListContractPresenter {

    HttpAPIWrapper httpAPIWrapper;
    private MallGoodsListContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private MallGoodsListActivity mActivity;
    //单词屏蔽的数量
    public int oneremovedCOunt = 0;
    //总共屏蔽的数量
    private int allremovedCOunt = 0;

    @Inject
    public MallGoodsListPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull MallGoodsListContract.View view, MallGoodsListActivity activity) {
        mView = view;
        this.httpAPIWrapper = httpAPIWrapper;
        mCompositeDisposable = new CompositeDisposable();
        this.mActivity = activity;
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
        mActivity = null;
    }

    /**
     * 屏蔽某些特定的商品
     * @return
     */
    @Override
    public List<CxwyMallProduct> shieldGood(ShopList shopList) {
        int tempRemoveCount = 0;
        List<CxwyMallProduct> tempList = new ArrayList<>();
        for (int i = 0; i < shopList.getProductList().size(); i++) {
            //KLog.i(shopList.getProductList().get(i).getShangpinShangpName());
            if (shopList.getProductList().get(i).getShangpinShangpName().trim().contains("香烟")) {
                //KLog.i("匹配名字成功,名字为:" + shopList.getProductList().get(i).getShangpinShangpName());
                tempRemoveCount++;
                allremovedCOunt++;
            } else {
                tempList.add(shopList.getProductList().get(i));
            }
        }
        shopList.setTotal(shopList.getTotal() - allremovedCOunt);
        shopList.setProductList(tempList);
        oneremovedCOunt = tempRemoveCount > oneremovedCOunt ? tempRemoveCount : oneremovedCOunt;
        //KLog.i("商品此次拉取屏蔽后的数量:" + shopList.getProductList().size());
        //KLog.i("商品总的屏蔽后的数量:" + shopList.getTotal());
        return shopList.getProductList();
    }


    @Override
    public void loadMallGoodsFromServer(String searchName, int rankType, int sortType, int inType, int currentPage, int onePageNum) {
        Map<String, String> map = new HashMap<>();
        map.put("uuid", Contains.uuid);
        map.put("shangpinMing", searchName);
        Disposable disposable = httpAPIWrapper.getGoodsBanner(map).subscribe(new Consumer<ShopNewList>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull ShopNewList shopNewList) throws Exception {
                KLog.i("data" + shopNewList.getRows().toString());
                KLog.i("商品此次拉取的数量:" + shopNewList.getRows().size());
                KLog.i("商品总的数量:" + shopNewList.getTotal());
                mView.onMallGoodsBacked(shopNewList);
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
//        if(currentPage == 1) {
//            oneremovedCOunt = 0;
//            allremovedCOunt = 0;
//        }
//        Map<String, String> params = new HashMap<>();
//        params.put("rows", String.valueOf(onePageNum));
//        params.put("page", String.valueOf(currentPage));
//        params.put("appxiaoqu", String.valueOf(Contains.curSelectXiaoQuId));
//
//        params.put("sort", getSortType(rankType));
//        params.put("order", getOrderType(sortType));
//        params.put("uuid", Contains.uuid);
//
//        Observable<ShopList> observable = null;
//        if (inType == MallFragment.IN_TYPE_NORMAL_GOODS) {
//            params.put("keys", "所有".equals(searchName) ? "" : searchName);//通过搜索返回
//            observable = httpAPIWrapper.getGoodsListBySearchKey(params);
//        } else {
//            //通过首页推荐进来
//            params.put("product.shangpinClassicShow", "0");
//            observable = httpAPIWrapper.getMiaoShaProduct(params);
//        }
//
//        if (observable != null) {
//            Disposable disposable = observable.subscribe(new Consumer<ShopList>() {
//                @Override
//                public void accept(@io.reactivex.annotations.NonNull ShopList shopList) throws Exception {
//                    KLog.i("商品此次拉取的数量:" + shopList.getProductList().size());
//                    KLog.i("商品总的数量:" + shopList.getTotal());
//                    shopList.setProductList(shieldGood(shopList));
//                    mView.onMallGoodsBacked(shopList);
//                }
//            }, new Consumer<Throwable>() {
//                @Override
//                public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
//                    throwable.printStackTrace();
//                    mView.onLoadFailed();
//                }
//            }, new Action() {
//                @Override
//                public void run() throws Exception {
//                    KLog.debug("GoodsList Complete");
//                }
//            });
//            mCompositeDisposable.add(disposable);
//        } else {
//            mView.onLoadFailed();
//        }

    }

    @Override
    public void add2ShopCart(Map map, View view, String url) {

        Disposable disposable = httpAPIWrapper.addGood2ShopCart(map)
                .subscribe(new Consumer<BaseEntity>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull BaseEntity baseEntity) throws Exception {
                        if (baseEntity.getStatus() == 1) {
                            mView.onAdd2ShopCartSuccess(view,url);
                        } else {
                            ToastUtil.show(mActivity, baseEntity.getMsg());
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
    public void loadShopCartFromServer() {
        Map<String, String> params = new HashMap<>();
        params.put("uuid", Contains.uuid);
        Disposable disposable = httpAPIWrapper.getShopCart1(params)
                .subscribe(new Consumer<ShopCart>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull ShopCart shopCart) throws Exception {
                        if (shopCart.getStatus() == 1) {
                            Contains.shopCartNum = shopCart.getTotal();
                            Contains.shopCartList = shopCart.getRows();
                            mView.onLoadShopCartSucceed(shopCart.getTotal());
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


    private String getOrderType(int sortType) {
        /** 筛选条件 升序asc 倒序desc */
        return sortType == MallGoodsListRankView.STATUS_UP_SELECTED ? "asc" : "desc";
    }

    private String getSortType(int rankType) {
        /** 筛选条件 销量: shangpinXiaoliang 价格:shangpinRmb 人气:shangpinRenqi */
        return rankType == 0 ? "shangpinXiaoliang" : rankType == 1 ? "shangpinRmb" : "shangpinRenqi";
    }


}