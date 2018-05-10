package com.yxld.yxchuangxin.ui.adapter.wuye;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.Utils.TimeUtil;
import com.yxld.yxchuangxin.entity.CarList;

import java.util.List;

/**
 * 作者：hu on 2017/6/8
 * 邮箱：365941593@qq.com
 * 描述：
 */

//public class CarManageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//    List<Car> list;
//    Activity mContext;
//    public CarManageAdapter(List<Car> data, Activity context) {
//        this.list = data;
//        mContext = context;
//    }
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return new CarManageAdapterViewHolder(LayoutInflater.from(mContext).inflate(R.layout.rv_car_manage_item, parent, false));
//    }
//
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    public class CarManageAdapterViewHolder extends RecyclerView.ViewHolder {
//        public CarManageAdapterViewHolder(View itemView) {
//            super(itemView);
//            AutoUtils.autoSize(itemView);
//        }
//    }
//}
public class CarManageAdapter extends BaseQuickAdapter<CarList.DataBean, BaseViewHolder>{
    public CarManageAdapter(List<CarList.DataBean> data) {
        super(R.layout.rv_car_manage_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CarList.DataBean item) {
        helper.setText(R.id.tv_car_number, item.getClmediaNo())
                .setText(R.id.tv_car_location, item.getClplNo())
                .setText(R.id.tv_park_location, item.getParkWeizhi())
                .setText(R.id.tv_car_money_time, TimeUtil.timesTamp2YearMonthDay(item.getCardTimr()))
                .setText(R.id.tv_protocol_time, TimeUtil.timesTamp2YearMonthDay(item.getProtocolTime()));
        if (item.getType() == 0) {
            helper.setText(R.id.tv_car_status, "已锁定");
            helper.setText(R.id.bt_suoding, "解锁");
        } else {
            helper.setText(R.id.tv_car_status, "未锁定");
            helper.setText(R.id.bt_suoding, "锁定");
        }
        helper.addOnClickListener(R.id.bt_chongzhi)
                .addOnClickListener(R.id.bt_suoding);

    }
}
