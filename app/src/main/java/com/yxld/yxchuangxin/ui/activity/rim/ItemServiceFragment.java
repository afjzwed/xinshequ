package com.yxld.yxchuangxin.ui.activity.rim;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.ToastUtil;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.entity.AndroidWuYeEntity;
import com.yxld.yxchuangxin.ui.activity.rim.component.DaggerItemServiceComponent;
import com.yxld.yxchuangxin.ui.activity.rim.contract.ItemServiceContract;
import com.yxld.yxchuangxin.ui.activity.rim.module.ItemServiceModule;
import com.yxld.yxchuangxin.ui.activity.rim.presenter.ItemServicePresenter;
import com.yxld.yxchuangxin.ui.adapter.rim.ServicceMainActivityAdapter;
import com.yxld.yxchuangxin.view.MyGridView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wwx
 * @Package com.yxld.yxchuangxin.ui.activity.rim
 * @Description: 欣周边 欣周边 主页 需要更改为mvp模式
 * @date 2017/06/16
 */

public class ItemServiceFragment extends BaseFragment implements ItemServiceContract.View {

    @Inject
    ItemServicePresenter mPresenter;
    @BindView(R.id.buttomGrildview)
    MyGridView buttomGrildview;

    /**
     * 构建三级菜单实体
     */
    private List<AndroidWuYeEntity> listData = new ArrayList<AndroidWuYeEntity>();

    /**
     * 构建三级菜单适配器
     */
    private ServicceMainActivityAdapter adapter;

    private int lastActivityTag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service_item, null);
        ButterKnife.bind(this, view);
        Bundle mBundle = getArguments();
        //获取Activity传递过来的参数
        lastActivityTag = mBundle.getInt("arg");
        Logger.d(lastActivityTag+"========================================");
        listData.clear();
        switch (lastActivityTag) {
            case 0: // 到家, 家政  洗衣  外卖  快餐  果蔬生鲜  鲜花配送
                listData.add(new AndroidWuYeEntity(R.mipmap.s_1_jiazheng, "家政", "这是家政详细内容"));
                listData.add(new AndroidWuYeEntity(R.mipmap.s_1_xiyi, "洗衣", "这是洗衣详细内容"));
                listData.add(new AndroidWuYeEntity(R.mipmap.s_1_waimai, "外卖", "这是外卖详细内容详细内容"));
                listData.add(new AndroidWuYeEntity(R.mipmap.s_1_kuaican, "快餐", "这是快餐详细内容详细内容"));
                listData.add(new AndroidWuYeEntity(R.mipmap.s_1_guoshu, "果蔬生鲜", "这是果蔬生鲜详细内容"));
                listData.add(new AndroidWuYeEntity(R.mipmap.s_1_hua, "鲜花配送", "这是鲜花配送详细内容"));
                break;
            case 1: // 车主服务, 洗车  车检  汽车保养  车险   代驾  违章查询  摇号查询
                listData.add(new AndroidWuYeEntity(R.mipmap.s_2_xiche, "洗车", "这是洗车详细内容"));
                listData.add(new AndroidWuYeEntity(R.mipmap.s_2_chejian, "车检", "这是车检详细内容"));
                listData.add(new AndroidWuYeEntity(R.mipmap.s_2_chebaoyang, "汽车保养", "这是汽车保养详细内容"));
                listData.add(new AndroidWuYeEntity(R.mipmap.s_2_daijiao, "代驾", "这是代驾详细内容"));
                listData.add(new AndroidWuYeEntity(R.mipmap.s_2_weizhang, "违章查询", "这是违章查询详细内容"));
                listData.add(new AndroidWuYeEntity(R.mipmap.s_2_yaohao, "摇号查询", "这是摇号查询详细内容"));
                break;
            case 2: //支付 , 话费充值  生活缴费  Q币/游戏充值
                listData.add(new AndroidWuYeEntity(R.mipmap.s_3_huafei, "话费充值", "这是话费充值详细内容"));
                listData.add(new AndroidWuYeEntity(R.mipmap.s_3_shenghuo, "生活缴费", "这是生活缴费详细内容"));
                listData.add(new AndroidWuYeEntity(R.mipmap.s_3_youxi, "Q币/游戏充值", "这是Q币/游戏充值详细内容"));
                break;
            case 3: //生活助手,保险  丽人  代理业务  医院挂号    KTV  名品特卖  美食  团购
                listData.add(new AndroidWuYeEntity(R.mipmap.s_4_baoxian, "保险", "这是保险详细内容"));
                listData.add(new AndroidWuYeEntity(R.mipmap.s_4_liren, "丽人", "这是丽人详细内容"));
                listData.add(new AndroidWuYeEntity(R.mipmap.s_4_daili, "代理业务", "这是代理业务详细内容"));
                listData.add(new AndroidWuYeEntity(R.mipmap.s_4_guahao, "医院挂号", "这是医院挂号详细内容"));
                listData.add(new AndroidWuYeEntity(R.mipmap.s_4_ktv, "KTV", "这是KTV详细内容"));
                listData.add(new AndroidWuYeEntity(R.mipmap.s_4_mingpin, "名品特卖", "这是名品特卖详细内容"));
                listData.add(new AndroidWuYeEntity(R.mipmap.s_4_meishi, "美食", "这是美食详细内容"));
                listData.add(new AndroidWuYeEntity(R.mipmap.s_4_tuangou, "团购", "这是团购详细内容"));

                break;
            case 4: // 娱乐 电影票  演出票务  会展票务
                listData.add(new AndroidWuYeEntity(R.mipmap.s_5_dianying, "电影票", "这是详细内容详细内容"));
                listData.add(new AndroidWuYeEntity(R.mipmap.s_5_yanchu, "演出票务", "这是详细内容详细内容"));
                listData.add(new AndroidWuYeEntity(R.mipmap.s_5_huizhan, "会展票务", "这是详细内容详细内容"));
                break;
            case 5: //出行/旅行, 打车   周末游  酒店优选  机票  航班查询  租车  火车票  景点门票
                listData.add(new AndroidWuYeEntity(R.mipmap.s_6_dache, "打车", "这是打车详细内容"));
                listData.add(new AndroidWuYeEntity(R.mipmap.s_6_zhoumoyou, "周末游", "这是周末游详细内容"));
                listData.add(new AndroidWuYeEntity(R.mipmap.s_6_jiudian, "酒店优选", "这是酒店优选详细内容"));
                listData.add(new AndroidWuYeEntity(R.mipmap.s_6_jipiao, "机票", "这是机票详细内容"));
                listData.add(new AndroidWuYeEntity(R.mipmap.s_6_hangban, "航班查询", "这是航班查询详细内容"));
                listData.add(new AndroidWuYeEntity(R.mipmap.s_6_zuche, "租车", "这是租车详细内容"));
                listData.add(new AndroidWuYeEntity(R.mipmap.s_6_huoche, "火车票", "这是火车票详细内容"));
                listData.add(new AndroidWuYeEntity(R.mipmap.s_6_jinqu, "景点门票", "这是景点门票详细内容"));
                break;
        }

        adapter = new ServicceMainActivityAdapter(listData, getActivity(), lastActivityTag);
        buttomGrildview.setAdapter(adapter);
        buttomGrildview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view.findViewById(R.id.main_name);
                if (tv.getText().toString().equals("更多")) {
                    ToastUtil.show(getActivity(), "更多");
                } else {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), // context
                            WebViewActivity.class);// 跳转的activity\
                    String name = tv.getText().toString();
                    switch (name) {
                        case "家政":
                            Bundle jz = new Bundle();
                            jz.putString("name", "无忧保姆");
                            jz.putString("address", "http://www.51baomu.cn");
                            intent.putExtras(jz);
                            startActivity(intent);
                            break;
                        case "洗衣":
                           startActivity(BusinessListActivity.class);
//                            Bundle xy = new Bundle();
//                            xy.putString("name", "e袋洗");
//                            xy.putString("address","http://www.edaixi.com");
//                            intent.putExtras(xy);
//                            startActivity(intent);
                            break;
                        case "快餐":
                            Bundle kc = new Bundle();
                            kc.putString("name", "美团外卖");
                            kc.putString("address", "http://www.waimai.meituan.com");
                            intent.putExtras(kc);
                            startActivity(intent);
                            break;
                        case "外卖":
                            Bundle wm = new Bundle();
                            wm.putString("name", "饿了么");
                            wm.putString("address", "http://www.ele.me");
                            intent.putExtras(wm);
                            startActivity(intent);
                            break;
                        case "果蔬生鲜":
                            Bundle gssx = new Bundle();
                            gssx.putString("name", "绿叶水果商城");
                            gssx.putString("address", "http://www.27shuiguo.com");
                            intent.putExtras(gssx);
                            startActivity(intent);
                            break;
                        case "鲜花配送":
                            Bundle xhps = new Bundle();
                            xhps.putString("name", "鲜花网");
                            xhps.putString("address", "http://www.hua.com");
                            intent.putExtras(xhps);
                            startActivity(intent);
                            break;
                        case "洗车":
                            Bundle xc = new Bundle();
                            xc.putString("name", "大象上门洗车");
                            xc.putString("address", "http://www.ewcar.cn");
                            intent.putExtras(xc);
                            startActivity(intent);
                            break;
                        case "车检":
                            Bundle cj = new Bundle();
                            cj.putString("name", "御驾部落");
                            cj.putString("address", "http://www.yujiabuluo.com");
                            intent.putExtras(cj);
                            startActivity(intent);
                            break;
                        case "汽车保养":
                            Bundle qcby = new Bundle();
                            qcby.putString("name", "涂虎养车网");
                            qcby.putString("address", "http://www.tuhu.cn");
                            intent.putExtras(qcby);
                            startActivity(intent);
                            break;
                        case "代驾":
                            Bundle dj = new Bundle();
                            dj.putString("name", "e代驾");
                            dj.putString("address", "http://www.edaijia.cn");
                            intent.putExtras(dj);
                            startActivity(intent);
                            break;
                        case "违章查询":
                            Bundle wzcx = new Bundle();
                            wzcx.putString("name", "违章查询");
                            wzcx.putString("address", "http://www.hncsjj.gov.cn/tpfw/category.do?method=cate&&id=1306");
                            intent.putExtras(wzcx);
                            startActivity(intent);
                            break;
                        case "摇号查询":
                            Bundle yhcx = new Bundle();
                            yhcx.putString("name", "摇号网");
                            yhcx.putString("address", "http://www.bitenews.cn");
                            intent.putExtras(yhcx);
                            startActivity(intent);
                            break;
                        case "话费充值":
                            Bundle hfcz = new Bundle();
                            hfcz.putString("name", "百度钱包充值");
                            hfcz.putString("address", "http://life.baifubao.com/sj?channel_no=CHF1000002");
                            intent.putExtras(hfcz);
                            startActivity(intent);
                            break;
                        case "生活缴费":
                            Bundle shjf = new Bundle();
                            shjf.putString("name", "QQ便民");
                            shjf.putString("address", "http://chong.qq.com/home/life_v2.shtml?PTAG=31463.36.9");
                            intent.putExtras(shjf);
                            startActivity(intent);
                            break;
                        case "Q币/游戏充值":
                            Bundle yxcz = new Bundle();
                            yxcz.putString("name", "腾讯充值中心");
                            yxcz.putString("address", "http://www.pay.qq.com");
                            intent.putExtras(yxcz);
                            startActivity(intent);
                            break;
                        case "保险":
                            Bundle bx = new Bundle();
                            bx.putString("name", "慧择网");
                            bx.putString("address", "http://huize.com");
                            intent.putExtras(bx);
                            startActivity(intent);
                            break;
                        case "丽人":
                            Bundle lr = new Bundle();
                            lr.putString("name", "丽人网");
                            lr.putString("address", "http://www.liren521.com/");
                            intent.putExtras(lr);
                            startActivity(intent);
                            break;
                        case "代理业务":
                            Bundle dlyw = new Bundle();
                            dlyw.putString("name", "快递100");
                            dlyw.putString("address", "http://www.kuaidi100.com");
                            intent.putExtras(dlyw);
                            startActivity(intent);
                            break;
                        case "医院挂号":
                            Bundle yygh = new Bundle();
                            yygh.putString("name", "挂号网");
                            yygh.putString("address", "http://www.guahao.com");
                            intent.putExtras(yygh);
                            startActivity(intent);
                            break;
                        case "KTV":
                            Bundle ktv = new Bundle();
                            ktv.putString("name", "唱吧");
                            ktv.putString("address", "http://www.changba.com");
                            intent.putExtras(ktv);
                            startActivity(intent);
                            break;
                        case "名品特卖":
                            Bundle mmtm = new Bundle();
                            mmtm.putString("name", "唯品会");
                            mmtm.putString("address", "http://www.vip.com");
                            intent.putExtras(mmtm);
                            startActivity(intent);
                            break;
                        case "美食":
                            Bundle ms = new Bundle();
                            ms.putString("name", "美食杰");
                            ms.putString("address", "http://www.meishij.net");
                            intent.putExtras(ms);
                            startActivity(intent);
                            break;
                        case "团购":
                            Bundle tg = new Bundle();
                            tg.putString("name", "美团网");
                            tg.putString("address", "http://chs.meituan.com/?utm_campaign=baidu&utm_medium=organic&utm_source=baidu&utm_content=homepage&utm_term=");
                            intent.putExtras(tg);
                            startActivity(intent);
                            break;
                        case "电影票":
                            Bundle dyp = new Bundle();
                            dyp.putString("name", "猫眼电影");
                            dyp.putString("address", "http://maoyan.com");
                            intent.putExtras(dyp);
                            startActivity(intent);
                            break;
                        case "演出票务":
                            Bundle ycpw = new Bundle();
                            ycpw.putString("name", "大麦网");
                            ycpw.putString("address", "http://damai.cn");
                            intent.putExtras(ycpw);
                            startActivity(intent);
                            break;
                        case "会展票务":
                            Bundle hzpw = new Bundle();
                            hzpw.putString("name", "中演票务通");
                            hzpw.putString("address", "http://t3.com.cn");
                            intent.putExtras(hzpw);
                            startActivity(intent);
                            break;
                        case "打车":
                            Bundle dc = new Bundle();
                            dc.putString("name", "滴滴打车");
                            dc.putString("address", "http://xiaojukeji.com");
                            intent.putExtras(dc);
                            startActivity(intent);
                            break;
                        case "周末游":
                            Bundle zmy = new Bundle();
                            zmy.putString("name", "周末去哪儿");
                            zmy.putString("address", "https://m.wanzhoumo.com");
                            intent.putExtras(zmy);
                            startActivity(intent);
                            break;
                        case "酒店优选":
                            Bundle jdyx = new Bundle();
                            jdyx.putString("name", "艺龙网");
                            jdyx.putString("address", "http://elong.com");
                            intent.putExtras(jdyx);
                            startActivity(intent);
                            break;
                        case "机票":
                            Bundle jp = new Bundle();
                            jp.putString("name", "同程机票");
                            jp.putString("address", "http://www.ly.com/FlightQuery.aspx");
                            intent.putExtras(jp);
                            startActivity(intent);
                            break;
                        case "航班查询":
                            Bundle hbcx = new Bundle();
                            hbcx.putString("name", "飞常准");
                            hbcx.putString("address", "http://www.variflight.com");
                            intent.putExtras(hbcx);
                            startActivity(intent);
                            break;
                        case "租车":
                            Bundle zc = new Bundle();
                            zc.putString("name", "一嗨租车");
                            zc.putString("address", "http://www.1hai.cn");
                            intent.putExtras(zc);
                            startActivity(intent);
                            break;
                        case "火车票":
                            Bundle hcp = new Bundle();
                            hcp.putString("name", "同程火车票");
                            hcp.putString("address", "http://www.ly.com/huochepiao");
                            intent.putExtras(hcp);
                            startActivity(intent);
                            break;
                        case "景点门票":
                            Bundle jdmp = new Bundle();
                            jdmp.putString("name", "途牛网");
                            jdmp.putString("address", "http://www.menpiao.tuniu.com");
                            intent.putExtras(jdmp);
                            startActivity(intent);
                            break;
                    }
                }
            }
        });
        return view;
    }


    @Override
    protected void setupFragmentComponent() {
        DaggerItemServiceComponent
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
                .itemServiceModule(new ItemServiceModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(ItemServiceContract.ItemServiceContractPresenter presenter) {
        mPresenter = (ItemServicePresenter) presenter;
    }

    @Override
    protected void initDataFromLocal() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}