package com.yxld.yxchuangxin.entity;


import com.yxld.yxchuangxin.base.BaseEntity;

import java.util.List;

/**
 * CxwyWxxiangmu entity. @author MyEclipse Persistence Tools
 */

public class CxwyWxxiangmu extends BaseEntity implements java.io.Serializable {

	private List<CxwyWxxiangmu> rows;

    // Fields    
     private Integer id;//id
     private String name;//项目名
     private String feiyong;//费用
     private String sqname;//上传人
     private String sqtime;//上传时间
     private String xgname;//最后修改人
     private String xgtime;//最后修改时间
     private String lurutime;//
     private String biezhu1;//备注1
     private String biezhu2;//备注2


    // Constructors

    /** default constructor */
    public CxwyWxxiangmu() {
    }

    
    /** full constructor */
    public CxwyWxxiangmu(String name, String feiyong, String sqname, String sqtime, String xgname, String xgtime, String lurutime, String biezhu1, String biezhu2) {
        this.name = name;
        this.feiyong = feiyong;
        this.sqname = sqname;
        this.sqtime = sqtime;
        this.xgname = xgname;
        this.xgtime = xgtime;
        this.lurutime=lurutime;
        this.biezhu1 = biezhu1;
        this.biezhu2 = biezhu2;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getFeiyong() {
        return this.feiyong;
    }
    
    public void setFeiyong(String feiyong) {
        this.feiyong = feiyong;
    }

    public String getSqname() {
        return this.sqname;
    }
    
    public void setSqname(String sqname) {
        this.sqname = sqname;
    }

    public String getSqtime() {
        return this.sqtime;
    }
    
    public void setSqtime(String sqtime) {
        this.sqtime = sqtime;
    }

    public String getXgname() {
        return this.xgname;
    }
    
    public void setXgname(String xgname) {
        this.xgname = xgname;
    }

    public String getXgtime() {
        return this.xgtime;
    }
    
    public void setXgtime(String xgtime) {
        this.xgtime = xgtime;
    }

    public String getBiezhu1() {
        return this.biezhu1;
    }
    
    public void setBiezhu1(String biezhu1) {
        this.biezhu1 = biezhu1;
    }

    public String getBiezhu2() {
        return this.biezhu2;
    }
    
    public void setBiezhu2(String biezhu2) {
        this.biezhu2 = biezhu2;
    }


	public String getLurutime() {
		return lurutime;
	}


	public void setLurutime(String lurutime) {
		this.lurutime = lurutime;
	}


	public List<CxwyWxxiangmu> getRows() {
		return rows;
	}


	public void setRows(List<CxwyWxxiangmu> rows) {
		this.rows = rows;
	}


	@Override
	public String toString() {
		return "CxwyWxxiangmu [rows=" + rows + ", id=" + id + ", name=" + name
				+ ", feiyong=" + feiyong + ", sqname=" + sqname + ", sqtime="
				+ sqtime + ", xgname=" + xgname + ", xgtime=" + xgtime
				+ ", lurutime=" + lurutime + ", biezhu1=" + biezhu1
				+ ", biezhu2=" + biezhu2 + ", status=" + status + ", MSG="
				+ MSG + "]";
	}


}