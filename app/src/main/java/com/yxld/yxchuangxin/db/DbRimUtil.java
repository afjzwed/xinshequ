package com.yxld.yxchuangxin.db;

import android.util.Log;

import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.db.green.DaoSession;
import com.yxld.yxchuangxin.db.green.SPInfo;
import com.yxld.yxchuangxin.db.green.SPInfoDao;
import com.yxld.yxchuangxin.entity.CxwyProductInfo;

import java.util.List;

import static android.R.id.list;

/**
 * @author xlei
 * @Date 2017/12/19.
 */

public class DbRimUtil {
    private final String TAG = "DBTAG";
    private DaoSession mDaoSession;
    private SPInfoDao mSPInfoDao;
    private static DbRimUtil mDbRimUtil;

    public DbRimUtil(DaoSession daoSession) {
        this.mDaoSession = daoSession;
        mSPInfoDao = this.mDaoSession.getSPInfoDao();
    }

    public static DbRimUtil getInstans() {
        if (mDbRimUtil == null) {
            mDbRimUtil = new DbRimUtil(AppConfig.getGreenDaoSession());
        }
        return mDbRimUtil;
    }

    /**
     * 增加一个商品
     */
    public void insert(CxwyProductInfo.DataBean dataBean) {
        SPInfo soInfo = mSPInfoDao.queryBuilder().where(SPInfoDao.Properties.BusinessNumber.eq(dataBean.getProductBusinessNumber()),
                SPInfoDao.Properties.ProductId.eq(dataBean.getProductId())).unique();
        if (soInfo == null) {
            SPInfo spInfo1 = new SPInfo();
            spInfo1.setBusinessNumber(dataBean.getProductBusinessNumber());
            spInfo1.setCount(1);
            spInfo1.setProductId(dataBean.getProductId());
            spInfo1.setProductImage(dataBean.getProductImage());
            spInfo1.setProductName(dataBean.getProductName());
            spInfo1.setProductPreferentialPrice(dataBean.getProductPreferentialPrice());
            spInfo1.setProductPrice(dataBean.getProductPrice());
            spInfo1.setProductNum(dataBean.getProductNum());
            mSPInfoDao.insert(spInfo1);
            Log.i(TAG, "新---增一条商品成功");
        } else {
            Log.i(TAG, "已存在该商家 已 存在该商品成功");
            soInfo.setCount(soInfo.getCount() + 1);
            Log.i(TAG, "新---商品数量加1");
            update(soInfo);
        }
        selectAllSP(dataBean.getProductBusinessNumber());
    }


    /**
     * 查询所有商品信息
     */
    public void selectAllSP() {

        List<SPInfo> spInfos = mSPInfoDao.loadAll();
        Log.i(TAG, "spInfos" + spInfos.size() + "查询所有商品信息" + spInfos.toString());
    }

    /**
     * 查询某个商家下所有商品信息
     */
    public List<SPInfo> selectAllSP(String businessNumber) {

        List<SPInfo> list = mSPInfoDao.queryBuilder().where(SPInfoDao.Properties.BusinessNumber.eq(businessNumber)).list();
        Log.i(TAG, "spInfos当前商家下" + businessNumber + "商品数量：" + getSpCount(businessNumber));
        return list;

    }

    /**
     * 查询某个商家下某个商品信息
     */
    public SPInfo selectOneSP(CxwyProductInfo.DataBean dataBean) {

        SPInfo spInfo = mSPInfoDao.queryBuilder().where(SPInfoDao.Properties.BusinessNumber.eq(dataBean.getProductBusinessNumber()),
                SPInfoDao.Properties.ProductId.eq(dataBean.getProductId())
        ).unique();
        if (spInfo != null) {
            Log.i(TAG, "spInfos" + "查询唯一商品信息" + spInfo.toString() + "数量-----" + spInfo.getCount());
        }
        selectAllSP(dataBean.getProductBusinessNumber());
        return spInfo;
    }

    /**
     * 查询当前商家下总商品数量
     *
     * @param BusinessNumber
     * @return
     */
    public int getSpCount(String BusinessNumber) {
        int count = 0;
        List<SPInfo> list = mSPInfoDao.queryBuilder().where(SPInfoDao.Properties.BusinessNumber.eq(BusinessNumber)).list();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                count = count + list.get(i).getCount();
            }
        }
        return count;
    }

    /**
     * 删除某个商家下所有商品
     */
    public void deleteAllSP(CxwyProductInfo.DataBean dataBean) {
        List<SPInfo> list = mSPInfoDao.queryBuilder().where(SPInfoDao.Properties.BusinessNumber.eq(dataBean.getProductBusinessNumber())).list();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                mSPInfoDao.delete(list.get(i));
            }
        }
        Log.i(TAG, "删除商家下所有商品成功");
    }

    /**
     * 删除某个商家下所有商品
     */
    public void deleteAllSP(String BusinessNumber) {
        List<SPInfo> list = mSPInfoDao.queryBuilder().where(SPInfoDao.Properties.BusinessNumber.eq(BusinessNumber)).list();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                mSPInfoDao.delete(list.get(i));
            }
        }
        Log.i(TAG, "删除商家下所有商品成功");
    }

    /**
     * 删除所有信息
     */
    public void deleteAll() {
        mSPInfoDao.deleteAll();
        Log.i(TAG, "删除所有商品成功清空数据库成功");
    }

    /**
     * 删除某个商家下的一个商品
     */
    public void deleteOneSP(CxwyProductInfo.DataBean dataBean) {
        SPInfo spInfo = mSPInfoDao.queryBuilder().where(SPInfoDao.Properties.BusinessNumber.eq(dataBean.getProductBusinessNumber()),
                SPInfoDao.Properties.ProductId.eq(dataBean.getProductId())).unique();
        if (spInfo != null) {
            mSPInfoDao.delete(spInfo);
        }
        Log.i(TAG, "删除一个商品品成功");
    }

    public void deleteOneSP(SPInfo spInfo) {

        mSPInfoDao.delete(spInfo);
        Log.i(TAG, "删除一个商品品成功");
    }

    /**
     * 更新某个商品的信息
     *
     * @param businessNumber 商家id
     * @param productId      商品id
     * @param type           1表示加 2 表示减
     */
    public void update(String businessNumber, String productId, int type) {
        SPInfo spInfo = mSPInfoDao.queryBuilder().where(SPInfoDao.Properties.BusinessNumber.eq(businessNumber),
                SPInfoDao.Properties.ProductId.eq(productId)).unique();
        if (spInfo != null) {
            if (type == 1) {
                spInfo.setCount(spInfo.getCount() + 1);
            } else {
                spInfo.setCount(spInfo.getCount() - 1);
                //如果购物车中商品数量为0删除商品
                if (spInfo.getCount() == 0) {
                    mSPInfoDao.delete(spInfo);
                    return;
                }
            }
            mSPInfoDao.update(spInfo);
        }
    }

    public void update(SPInfo spInfo) {
        if (spInfo.getCount() == 0) {
            Log.i(TAG, "更新一个商品品失败  商品数量" + spInfo.getCount() + "商品删除");
            mSPInfoDao.delete(spInfo);
            return;
        }
        mSPInfoDao.update(spInfo);
        Log.i(TAG, "更新一个商品品成功 商品数量" + spInfo.getCount());
    }


    /**
     * 计算购物车总价格
     */
    public double getZongJiaGe(String BusinessNumber) {
        double money = 0.00;
        List<SPInfo> list = mSPInfoDao.queryBuilder().where(SPInfoDao.Properties.BusinessNumber.eq(BusinessNumber)).list();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                money = money + list.get(i).getCount() * list.get(i).getProductPreferentialPrice();
            }
        }
        return money;
    }
}
