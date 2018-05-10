package com.yxld.yxchuangxin.entity;



/**
 * CxwyOutTousu entity. @author MyEclipse Persistence Tools
 */

public class CxwyOutTousu  implements java.io.Serializable {


    // Fields    
     //id
     private Integer tousuId;
    //投诉类型
     private String tousuLeixing;
     //投诉 楼盘
     private String tousuLoupan;
     //投诉楼栋
     private String tousuLoudong;
     //投诉单元
     private String tousuDanyuan;
     //投诉房号
     private Integer tousuFanghao;
     //投诉人姓名
     private String tousuName;
     //投诉人电话
     private String tousuPhone;
     //解决方案
     private String tousuSolution;
     //投诉原因
     private String tousuNeirong;
     //投诉状态
     private String tousuStatus;
     //投诉时间
     private String tousuTime;
     //投诉审核
     private String tousuShenhe;
     //审核签字
     private String tousuShenheqianzi;
     //投诉经办人
     private String tousuJingbanren;
     //投诉处理人
     private String tousuChuliren;
     //投诉当事人（受理人）
     private String tousuDangshiren;
      // 投诉处理意见
     private String tousuChuliyijian;
     //处理意见签字
     private String tousuChuliyijianqianzi;
      //处理时间
     private String tousuChuliyijiantime;
     //公司指派项目负责人
     private String tousuDiaochajieguo;
     //调查签字
     private String tousuDiaochaqianzi;
     //投诉分类类别（根据需求后面改的）
     private String tousuDiaochariqi;
     //回执方式
     private String tousuHuizhifangshi;
     //回执信息
     private String tousuHuizhixinxi;
     //投诉单号
     private String tousuDanhao;
     //回执人签字
     private String tousuYanzhenren;
     //公司指派负责人
     private String tousuLururen;
  
     //投诉结束时间
     private String tousuEndtime;
     //投诉备注(审核时间)
     private String tousuBiezhu1;
     //备注(座机)
     private String tousuBiezhu2;
      //调查时间
     private String tousuDiaochatime;
      //投诉处理主管
     private String tousuZhuguan;
     //责任部门
     private String tousuZrbumen;
    
     
    // Constructors

    /** default constructor */
    public CxwyOutTousu() {
    }

    
    /** full constructor */
 
    public CxwyOutTousu(Integer tousuId, String tousuLeixing, String tousuLoupan, String tousuLoudong, String tousuDanyuan, Integer tousuFanghao, String tousuName, String tousuPhone, String tousuSolution, String tousuNeirong, String tousuStatus, String tousuTime, String tousuShenhe, String tousuShenheqianzi, String tousuJingbanren, String tousuChuliren, String tousuDangshiren, String tousuChuliyijian, String tousuChuliyijianqianzi, String tousuChuliyijiantime, String tousuDiaochajieguo, String tousuDiaochaqianzi, String tousuDiaochariqi, String tousuHuizhifangshi, String tousuHuizhixinxi, String tousuDanhao, String tousuYanzhenren, String tousuLururen, String tousuEndtime, String tousuBiezhu1, String tousuBiezhu2, String tousuDiaochatime, String tousuZhuguan, String tousuZrbumen) {
		
		this.tousuId = tousuId;
		this.tousuLeixing=tousuLeixing;
		this.tousuLoupan = tousuLoupan;
		this.tousuLoudong = tousuLoudong;
		this.tousuDanyuan = tousuDanyuan;
		this.tousuFanghao = tousuFanghao;
		this.tousuName = tousuName;
		this.tousuPhone = tousuPhone;
		this.tousuSolution = tousuSolution;
		this.tousuNeirong = tousuNeirong;
		this.tousuStatus = tousuStatus;
		this.tousuTime = tousuTime;
		this.tousuShenhe = tousuShenhe;
		this.tousuShenheqianzi = tousuShenheqianzi;
		this.tousuJingbanren = tousuJingbanren;
		this.tousuChuliren = tousuChuliren;
		this.tousuDangshiren = tousuDangshiren;
		this.tousuChuliyijian = tousuChuliyijian;
		this.tousuChuliyijianqianzi = tousuChuliyijianqianzi;
		this.tousuChuliyijiantime = tousuChuliyijiantime;
		this.tousuDiaochajieguo = tousuDiaochajieguo;
		this.tousuDiaochaqianzi = tousuDiaochaqianzi;
		this.tousuDiaochariqi = tousuDiaochariqi;
		this.tousuHuizhifangshi = tousuHuizhifangshi;
		this.tousuHuizhixinxi = tousuHuizhixinxi;
		this.tousuDanhao = tousuDanhao;
		this.tousuYanzhenren = tousuYanzhenren;
		this.tousuLururen = tousuLururen;
		this.tousuEndtime = tousuEndtime;
		this.tousuBiezhu1 = tousuBiezhu1;
		this.tousuBiezhu2 = tousuBiezhu2;
		this.tousuDiaochatime = tousuDiaochatime;
		this.tousuZhuguan = tousuZhuguan;
		this.tousuZrbumen = tousuZrbumen;
		
	}


	// Property accessors

    public Integer getTousuId() {
        return this.tousuId;
    }
    
    public void setTousuId(Integer tousuId) {
        this.tousuId = tousuId;
    }

    public String getTousuLoupan() {
        return this.tousuLoupan;
    }
    
    public void setTousuLoupan(String tousuLoupan) {
        this.tousuLoupan = tousuLoupan;
    }

    public String getTousuLoudong() {
        return this.tousuLoudong;
    }
    
    public void setTousuLoudong(String tousuLoudong) {
        this.tousuLoudong = tousuLoudong;
    }

    public String getTousuDanyuan() {
        return this.tousuDanyuan;
    }
    
    public void setTousuDanyuan(String tousuDanyuan) {
        this.tousuDanyuan = tousuDanyuan;
    }

    public Integer getTousuFanghao() {
        return this.tousuFanghao;
    }
    
    public void setTousuFanghao(Integer tousuFanghao) {
        this.tousuFanghao = tousuFanghao;
    }

    public String getTousuName() {
        return this.tousuName;
    }
    
    public void setTousuName(String tousuName) {
        this.tousuName = tousuName;
    }

    public String getTousuPhone() {
        return this.tousuPhone;
    }
    
    public void setTousuPhone(String tousuPhone) {
        this.tousuPhone = tousuPhone;
    }

    public String getTousuSolution() {
        return this.tousuSolution;
    }
    
    public void setTousuSolution(String tousuSolution) {
        this.tousuSolution = tousuSolution;
    }

    public String getTousuNeirong() {
        return this.tousuNeirong;
    }
    
    public void setTousuNeirong(String tousuNeirong) {
        this.tousuNeirong = tousuNeirong;
    }

    public String getTousuStatus() {
        return this.tousuStatus;
    }
    
    public void setTousuStatus(String tousuStatus) {
        this.tousuStatus = tousuStatus;
    }

    public String getTousuTime() {
        return this.tousuTime;
    }
    
    public void setTousuTime(String tousuTime) {
        this.tousuTime = tousuTime;
    }

    public String getTousuShenhe() {
        return this.tousuShenhe;
    }
    
    public void setTousuShenhe(String tousuShenhe) {
        this.tousuShenhe = tousuShenhe;
    }

    public String getTousuShenheqianzi() {
        return this.tousuShenheqianzi;
    }
    
    public void setTousuShenheqianzi(String tousuShenheqianzi) {
        this.tousuShenheqianzi = tousuShenheqianzi;
    }

    public String getTousuJingbanren() {
        return this.tousuJingbanren;
    }
    
    public void setTousuJingbanren(String tousuJingbanren) {
        this.tousuJingbanren = tousuJingbanren;
    }

    public String getTousuChuliren() {
        return this.tousuChuliren;
    }
    
    public void setTousuChuliren(String tousuChuliren) {
        this.tousuChuliren = tousuChuliren;
    }

    public String getTousuDangshiren() {
        return this.tousuDangshiren;
    }
    
    public void setTousuDangshiren(String tousuDangshiren) {
        this.tousuDangshiren = tousuDangshiren;
    }

    public String getTousuChuliyijian() {
        return this.tousuChuliyijian;
    }
    
    public void setTousuChuliyijian(String tousuChuliyijian) {
        this.tousuChuliyijian = tousuChuliyijian;
    }

    public String getTousuChuliyijianqianzi() {
        return this.tousuChuliyijianqianzi;
    }
    
    public void setTousuChuliyijianqianzi(String tousuChuliyijianqianzi) {
        this.tousuChuliyijianqianzi = tousuChuliyijianqianzi;
    }

    public String getTousuChuliyijiantime() {
        return this.tousuChuliyijiantime;
    }
    
    public void setTousuChuliyijiantime(String tousuChuliyijiantime) {
        this.tousuChuliyijiantime = tousuChuliyijiantime;
    }

    public String getTousuDiaochajieguo() {
        return this.tousuDiaochajieguo;
    }
    
    public void setTousuDiaochajieguo(String tousuDiaochajieguo) {
        this.tousuDiaochajieguo = tousuDiaochajieguo;
    }

    public String getTousuDiaochaqianzi() {
        return this.tousuDiaochaqianzi;
    }
    
    public void setTousuDiaochaqianzi(String tousuDiaochaqianzi) {
        this.tousuDiaochaqianzi = tousuDiaochaqianzi;
    }

    public String getTousuDiaochariqi() {
        return this.tousuDiaochariqi;
    }
    
    public void setTousuDiaochariqi(String tousuDiaochariqi) {
        this.tousuDiaochariqi = tousuDiaochariqi;
    }

    public String getTousuHuizhifangshi() {
        return this.tousuHuizhifangshi;
    }
    
    public void setTousuHuizhifangshi(String tousuHuizhifangshi) {
        this.tousuHuizhifangshi = tousuHuizhifangshi;
    }

    public String getTousuHuizhixinxi() {
        return this.tousuHuizhixinxi;
    }
    
    public void setTousuHuizhixinxi(String tousuHuizhixinxi) {
        this.tousuHuizhixinxi = tousuHuizhixinxi;
    }

    public String getTousuDanhao() {
        return this.tousuDanhao;
    }
    
    public void setTousuDanhao(String tousuDanhao) {
        this.tousuDanhao = tousuDanhao;
    }

    public String getTousuYanzhenren() {
        return this.tousuYanzhenren;
    }
    
    public void setTousuYanzhenren(String tousuYanzhenren) {
        this.tousuYanzhenren = tousuYanzhenren;
    }

    public String getTousuLururen() {
        return this.tousuLururen;
    }
    
    public void setTousuLururen(String tousuLururen) {
        this.tousuLururen = tousuLururen;
    }

   
    public String getTousuEndtime() {
        return this.tousuEndtime;
    }
    
    public void setTousuEndtime(String tousuEndtime) {
        this.tousuEndtime = tousuEndtime;
    }

    public String getTousuBiezhu1() {
        return this.tousuBiezhu1;
    }
    
    public void setTousuBiezhu1(String tousuBiezhu1) {
        this.tousuBiezhu1 = tousuBiezhu1;
    }

    public String getTousuBiezhu2() {
        return this.tousuBiezhu2;
    }
    
    public void setTousuBiezhu2(String tousuBiezhu2) {
        this.tousuBiezhu2 = tousuBiezhu2;
    }


	public String getTousuDiaochatime() {
		return tousuDiaochatime;
	}


	public void setTousuDiaochatime(String tousuDiaochatime) {
		this.tousuDiaochatime = tousuDiaochatime;
	}


	public String getTousuZhuguan() {
		return tousuZhuguan;
	}


	public void setTousuZhuguan(String tousuZhuguan) {
		this.tousuZhuguan = tousuZhuguan;
	}

	public String getTousuZrbumen() {
		return tousuZrbumen;
	}


	public void setTousuZrbumen(String tousuZrbumen) {
		this.tousuZrbumen = tousuZrbumen;
	}


	public String getTousuLeixing() {
		return tousuLeixing;
	}


	public void setTousuLeixing(String tousuLeixing) {
		this.tousuLeixing = tousuLeixing;
	}


	@Override
	public String toString() {
		return "CxwyOutTousu [tousuId=" + tousuId + ", tousuLeixing="
				+ tousuLeixing + ", tousuLoupan=" + tousuLoupan
				+ ", tousuLoudong=" + tousuLoudong + ", tousuDanyuan="
				+ tousuDanyuan + ", tousuFanghao=" + tousuFanghao
				+ ", tousuName=" + tousuName + ", tousuPhone=" + tousuPhone
				+ ", tousuSolution=" + tousuSolution + ", tousuNeirong="
				+ tousuNeirong + ", tousuStatus=" + tousuStatus
				+ ", tousuTime=" + tousuTime + ", tousuShenhe=" + tousuShenhe
				+ ", tousuShenheqianzi=" + tousuShenheqianzi
				+ ", tousuJingbanren=" + tousuJingbanren + ", tousuChuliren="
				+ tousuChuliren + ", tousuDangshiren=" + tousuDangshiren
				+ ", tousuChuliyijian=" + tousuChuliyijian
				+ ", tousuChuliyijianqianzi=" + tousuChuliyijianqianzi
				+ ", tousuChuliyijiantime=" + tousuChuliyijiantime
				+ ", tousuDiaochajieguo=" + tousuDiaochajieguo
				+ ", tousuDiaochaqianzi=" + tousuDiaochaqianzi
				+ ", tousuDiaochariqi=" + tousuDiaochariqi
				+ ", tousuHuizhifangshi=" + tousuHuizhifangshi
				+ ", tousuHuizhixinxi=" + tousuHuizhixinxi + ", tousuDanhao="
				+ tousuDanhao + ", tousuYanzhenren=" + tousuYanzhenren
				+ ", tousuLururen=" + tousuLururen + ", tousuEndtime="
				+ tousuEndtime + ", tousuBiezhu1=" + tousuBiezhu1
				+ ", tousuBiezhu2=" + tousuBiezhu2 + ", tousuDiaochatime="
				+ tousuDiaochatime + ", tousuZhuguan=" + tousuZhuguan
				+ ", tousuZrbumen=" + tousuZrbumen + "]";
	}

}