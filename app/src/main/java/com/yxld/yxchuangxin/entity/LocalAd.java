package com.yxld.yxchuangxin.entity;

import com.yxld.yxchuangxin.base.BaseEntity;

/**
 * @author xlei
 * @Date 2018/5/11.
 */

public class LocalAd extends BaseEntity {

    /**
     * id : 1
     * gongsilogo : logo1
     * gongsikaipin : /dff.png
     * gongsijieshao : sdhjshfhsfh付了款晃了晃
     * gongsiid : 1
     * ggtime : 0
     */
    private LocalAd rows;
        private int id;
        private String gongsilogo;
        private String gongsikaipin;
        private String gongsijieshao;
        private int gongsiid;
        private int ggtime;
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGongsilogo() {
            return gongsilogo;
        }

        public void setGongsilogo(String gongsilogo) {
            this.gongsilogo = gongsilogo;
        }

        public String getGongsikaipin() {
            return gongsikaipin;
        }

        public void setGongsikaipin(String gongsikaipin) {
            this.gongsikaipin = gongsikaipin;
        }

        public String getGongsijieshao() {
            return gongsijieshao;
        }

        public void setGongsijieshao(String gongsijieshao) {
            this.gongsijieshao = gongsijieshao;
        }

        public int getGongsiid() {
            return gongsiid;
        }

        public void setGongsiid(int gongsiid) {
            this.gongsiid = gongsiid;
        }

        public int getGgtime() {
            return ggtime;
        }

        public void setGgtime(int ggtime) {
            this.ggtime = ggtime;
        }

    public LocalAd getRows() {
        return rows;
    }

    public void setRows(LocalAd rows) {
        this.rows = rows;
    }
}
