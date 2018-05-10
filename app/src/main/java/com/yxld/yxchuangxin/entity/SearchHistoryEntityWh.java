package com.yxld.yxchuangxin.entity;

import android.support.annotation.NonNull;

/**
 * @ClassName: SearchHistory 
 * @Description: 搜索历史
 * @author wwx
 * @date 2016年3月16日 上午11:03:47 
 */
public class SearchHistoryEntityWh implements Comparable<SearchHistoryEntityWh>{
	/** 用户Id*/
	private String u_id;
	/** 搜索字段*/
	private String u_search;
    /** 搜索时间戳*/
	private long u_time;


	public String getU_id() {
		return u_id;
	}

	public void setU_id(String u_id) {
		this.u_id = u_id;
	}

	public String getU_search() {
		return u_search;
	}

	public void setU_search(String u_search) {
		this.u_search = u_search;
	}

    public long getU_time() {
        return u_time;
    }

    public void setU_time(long u_time) {
        this.u_time = u_time;
    }


    public SearchHistoryEntityWh(String u_id, String u_search, long u_time) {
		super();

		this.u_id = u_id;
		this.u_search = u_search;
        this.u_time=u_time;
	}

	
	@Override
	public boolean equals(Object obj) {
		SearchHistoryEntityWh s = (SearchHistoryEntityWh) obj;
		return u_id.equals(s.u_id) && u_search.equals(u_search);
	}

	@Override
	public int hashCode() {
		String in = u_id + u_search;
		return in.hashCode();
	}

    @Override
    public String toString() {
        return "SearchHistoryEntityWh{" +
                "u_id='" + u_id + '\'' +
                ", u_search='" + u_search + '\'' +
                ", u_time=" + u_time +
                '}';
    }

    @Override
    public int compareTo(@NonNull SearchHistoryEntityWh o) {
        long i = this.getU_time() - o.getU_time();//按照时间排序
        if (i > 0) {
            return -1;
        } else {
            return 1;
        }
    }
}
