package com.yxld.yxchuangxin.ui.activity.ywh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.ui.activity.ywh.component.DaggerYwhMessageComponent;
import com.yxld.yxchuangxin.ui.activity.ywh.contract.YwhMessageContract;
import com.yxld.yxchuangxin.ui.activity.ywh.module.YwhMessageModule;
import com.yxld.yxchuangxin.ui.activity.ywh.presenter.YwhMessagePresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author William
 * @Package com.yxld.yxchuangxin.ui.activity.ywh
 * @Description: $description
 * @date 2018/11/09 17:04:05
 */

public class YwhMessageFragment extends BaseFragment implements YwhMessageContract.View {

    @Inject
    YwhMessagePresenter mPresenter;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;

    private int position;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ywh_message, null);
        ButterKnife.bind(this, view);
        Bundle mBundle = getArguments();

        position = getArguments().getInt("position");

        switch (position) {
            case 0:
                tvTitle.setText("1. 开始成立阶段");
                tvContent.setText("　　业主委员会，代表业主的利益，向社会各方反映业主意愿和要求，并监督物业管理公司管理运作的一个民间组织，是业主行使共同管理权的一种特殊形式。\n" +
                        "具备下列条件之一的，应当召开首次业主大会会议：\n" +
                        "1、交付使用的物业专有部分建筑面积达到建筑物总面积百分之五十以上的；\n" +
                        "2、交付使用的物业专有部分建筑面积达到建筑物总面积百分之三十以上，且首期交付的物业专有部分交付使用时间满两年的；\n" +
                        "3、交付使用的户数达到总户数百分之五十以上的。\n" +
                        "符合召开首次业主大会会议条件的，建设单位应当向物业所在地的街道办事处或乡镇人民政府提出筹备首次业主大会会议的申请，十名以上业主也可以联名申请。 \n");
                break;
            case 1:
                tvTitle.setText("2. 筹备组成立阶段");
                tvContent.setText("　　首次业主大会会议筹备组由业主代表、建设单位代表、街道办事处、乡镇人民政府代表和居民委员会代表组成。\n" +
                        "筹备组成员人数应为单数，其中业主代表人数不低于筹备组总人数的一半，筹备组组长由街道办事处、乡镇人民政府代表担任。\n" +
                        "筹备组中业主代表的产生，由街道办事处、乡镇人民政府或者居民委员会组织业主推荐。\n" +
                        "筹备组应当将成员名单以书面形式在物业管理区域内公告。业主对筹备组成员有异议的，由街道办事处、乡镇人民政府协调解决。\n");
                break;
            case 2:
                tvTitle.setText("3. 筹备组工作阶段");
                tvContent.setText("　　筹备组应当做好以下筹备工作：\n" +
                        "（一）确认并公示业主身份、业主人数以及所拥有的专有部分面积；\n" +
                        "（二）确定首次业主大会会议召开的时间、地点、形式和内容；\n" +
                        "（三）草拟管理规约、业主大会议事规则；\n" +
                        "（四）依法确定首次业主大会会议表决规则；\n" +
                        "（五）制定业主委员会委员候选人产生办法，确定业主委员会委员候选人名单；\n" +
                        "（六）制定业主委员会选举办法；\n" +
                        "（七）完成召开首次业主大会会议的其他准备工作。\n" +
                        "前款内容应当在首次业主大会会议召开15日前以书面形式在物业管理区域内公告。业主对公告内容有异议的，筹备组应当记录并作出答复。\n");
                break;
            case 3:
                tvTitle.setText("4. 候选人确定阶段");
                tvContent.setText("　　业主委员会由业主大会会议选举产生，由5至11人单数组成。业主委员会委员应当是物业管理区域内的业主，并符合下列条件：\n" +
                        "（一） 具有完全民事行为能力；\n" +
                        "（二） 遵守国家有关法律、法规；\n" +
                        "（三） 遵守业主大会议事规则、管理规约，模范履行业主义务；\n" +
                        "（四） 热心公益事业，责任心强，公正廉洁；\n" +
                        "（五） 具有一定的组织能力；\n" +
                        "（六） 具备必要的工作时间。\n" +
                        "业主委员会委员实行任期制，每届任期不超过5年，可连选连任，业主委员会委员具有同等表决权。\n" +
                        "业主委员会应当自选举之日起7日内召开首次会议，推选业主委员会主任和副主任。\n");
                break;
            case 4:
                tvTitle.setText("5. 业主大会阶段");
                tvContent.setText("第一条:业主大会决定下列事项：\n" +
                        "　　(一)制定、修改业主大会议事规则；\n" +
                        "\n" +
                        "　　(二)制定、修改管理规约；\n" +
                        "\n" +
                        "　　(三)选举业主委员会或者更换业主委员会委员；\n" +
                        "\n" +
                        "　　(四)改变和撤销业主委员会不适当的决定；\n" +
                        "\n" +
                        "　　(五)决定业主大会、业主委员会工作经费、业主委员会委员工作津贴及标准；\n" +
                        "\n" +
                        "　　(六)确定物业服务内容、标准及物业服务收费方案；\n" +
                        "\n" +
                        "　　(七)决定选聘、续聘和解聘物业服务企业；\n" +
                        "\n" +
                        "　　(八)筹集和使用专项维修资金；\n" +
                        "\n" +
                        "　　(九)改建、重建建筑物及其附属设施；\n" +
                        "\n" +
                        "　　(十)利用共用部位、共用设施设备进行经营的方式以及所得收益的分配与使用；\n" +
                        "\n" +
                        "　　(十一)有关共有和共同管理权利的其他重大事项。\n" +
                        "\n" +
                        "第二条 业主大会可以采用集体讨论或者书面征求意见的形式。\n" +
                        "采用书面征求意见形式的，应当将征求意见书送达业主，并至少提前十五日公示相关文本和信息。\n" +
                        "　　业主大会需要投票表决的，表决意见应当由业主本人签名；业主委托代理人表决的，代理人应当提交本人和业主的身份证复印件及委托书。提倡采用信息技术手段进行表决。\n" +
                        "\n" +
                        "　　业主大会投票表决的全部资料应当保存三年以上，业主可以查询、复制相关原始资料，并依法实施监督。\n" +
                        "\n" +
                        "第三条:业主委员会不按照业主大会议事规则的规定组织召开业主大会会议的，街道办事处或乡镇人民政府应当责令业主委员会限期组织召开；逾期仍不组织召开的，街道办事处或乡镇人民政府应当指导监督居(村)民委员会及时组织召开。\n" +
                        "\n" +
                        "第四条:业主委员会由五至十一人单数组成，每届任期三至五年，业主委员会委员可以连选连任。\n" +
                        "　　业主委员会委员候选人应当从奉公守法、品行良好、公道正派、热心公益、责任心强、有一定组织能力的业主中产生。\n" +
                        "\n" +
                        "　　业主委员会委员候选人的选举材料应当载明候选人是否受到过刑事处罚，是否有不良信用记录，是否违反临时管理规约、管理规约、业主大会议事规则的情况等信息，并向业主公开。\n" +
                        "\n" +
                        "第五条:业主委员会应当自选举产生之日起三十日内，将业主大会会议决定、管理规约、业主大会议事规则、业主委员会委员名单等材料报物业所在地的县(市、区)人民政府物业管理行政主管部门和街道办事处或乡镇人民政府备案，并书面告知相关居(村)民委员会。\n" +
                        "\n" +
                        "第六条:业主委员会执行业主大会的决定事项，履行下列职责\n" +
                        "\n" +
                        "　　(一)召集业主大会会议，报告物业管理的实施情况；\n" +
                        "\n" +
                        "　　(二)代表业主与业主大会选聘的物业服务企业签订物业服务合同；\n" +
                        "\n" +
                        "　　(三)及时了解业主、物业使用人的意见和建议，监督和协助物业服务企业履行物业服务合同；\n" +
                        "\n" +
                        "　　(四)监督管理规约的实施；\n" +
                        "\n" +
                        "　　(五)督促业主交纳物业服务费及其他相关费用；\n" +
                        "\n" +
                        "　　(六)组织和监督专项维修资金的筹集和使用；\n" +
                        "\n" +
                        "　　(七)业主大会赋予的其他职责。\n" +
                        "\n" +
                        "　　业主委员会会议应当有过半数委员出席，作出决定应当经全体委员过半数通过。业主委员会委员不得委托他人出席业主委员会会议。\n" +
                        "\n" +
                        "第七条:业主委员会应当建立信息公开制度，及时公示下列信息：\n" +
                        "\n" +
                        "　　(一)业主大会议事规则、管理规约；\n" +
                        "\n" +
                        "　　(二)业主大会和业主委员会决定；\n" +
                        "\n" +
                        "　　(三)物业服务合同；\n" +
                        "\n" +
                        "　　(四)经业主大会决定占用业主共有的道路或者其他场地设置的机动车停车位及其处分情况；\n" +
                        "\n" +
                        "　　(五)专项维修资金的筹集、使用情况；\n" +
                        "\n" +
                        "　　(六)物业共用部位、共用设施设备的经营收益及其分配与使用详细情况；\n" +
                        "\n" +
                        "　　(七)业主大会、业主委员会的工作经费和业主委员会委员工作津贴详细情况；\n" +
                        "\n" +
                        "　　(八)其他应当向业主、物业使用人公开的情况和资料。\n" +
                        "\n" +
                        "　　前款第一、二、三、四项规定的事项应当持续公示；第五、六、七项规定的事项，业主委员会应当于每年三月底前公布上一年度的信息，公示期不少于三十日。\n" +
                        "\n" +
                        "　　业主、物业使用人有权查询、复制本条第一款规定的信息及相关原始资料并依法实施监督，业主委员会、物业服务企业等应当予以配合。\n" +
                        "\n" +
                        "第八条:业主委员会委员有下列情形之一的，经占总人数百分之二十以上的业主或者三分之一以上业主委员会委员提议，业主大会或者业主委员会根据业主大会的授权有权终止其委员资格：\n" +
                        "\n" +
                        "　　(一)利用业主委员会委员身份牟取不正当利益，或者存在其他滥用职权行为，损害业主合法权益的；\n" +
                        "\n" +
                        "　　(二)拒不履行业主义务的；\n" +
                        "\n" +
                        "　　(三)无故缺席业主委员会会议连续三次以上的；\n" +
                        "\n" +
                        "　　(四)法律、法规、业主大会议事规则规定的其他情形。\n" +
                        "\n" +
                        "　　业主委员会委员因物业转让、灭失等原因不再是业主的，或者有丧失民事行为能力等情形的，其委员资格自行终止。\n" +
                        "\n" +
                        "　　业主委员会委员资格终止的，应当自终止之日起三日内将其保管的档案资料、印章及属于业主共有的其他财物移交业主委员会。拒不移交的，业主委员会、业主或者居(村)民委员会可以请求街道办事处或乡镇人民政府督促移交。\n" +
                        "\n" +
                        "第九条:业主委员会应当于任期届满三个月前召开业主大会会议进行换届选举；逾期未换届的，街道办事处或乡镇人民政府应当责令其限期换届选举。\n" +
                        "\n" +
                        "　　业主委员会应当自任期届满之日起十日内，将保管的档案资料、印章及业主共有的其他财物移交新一届业主委员会。逾期不移交的，新一届业主委员会可以请求街道办事处或乡镇人民政府督促移交。\n" +
                        "\n" +
                        "第十条:业主大会根据需要可以设立业主代表会议，履行业主大会赋予的职权职责；业主大会决定设立业主代表会议的，应当同时明确业主代表的产生及业主代表会议的权限及议事规则。\n" +
                        "\n" +
                        "　　业主大会可以设立业主监督委员会，负责监督业主委员会的工作，并履行业主大会赋予的其他职责。\n");
                break;
        }
        return view;
    }


    @Override
    protected void setupFragmentComponent() {
        DaggerYwhMessageComponent
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
                .ywhMessageModule(new YwhMessageModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(YwhMessageContract.YwhMessageContractPresenter presenter) {
        mPresenter = (YwhMessagePresenter) presenter;
    }

    @Override
    protected void initDataFromLocal() {

    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}