package com.yxld.yxchuangxin.entity;

/**
 * Created by wwx on 2016/5/24 0024.
 * 构建viewpager item 对象
 */
public class AndroidWuYeEntity {
    /** 图片路径*/
    private int menuSrc;
    /** 菜单名*/
    private String menuName;
    /** 菜单详细*/
    private String menuDestail;

    public int getMenuSrc() {
        return menuSrc;
    }

    public void setMenuSrc(int menuSrc) {
        this.menuSrc = menuSrc;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuDestail() {
        return menuDestail;
    }

    public void setMenuDestail(String menuDestail) {
        this.menuDestail = menuDestail;
    }

    public AndroidWuYeEntity() {
    }

    public AndroidWuYeEntity(int menuSrc, String menuName, String menuDestail) {
        this.menuSrc = menuSrc;
        this.menuName = menuName;
        this.menuDestail = menuDestail;
    }

    @Override
    public String toString() {
        return "AndroidWuYeEntity{" +
                "menuSrc=" + menuSrc +
                ", menuName='" + menuName + '\'' +
                ", menuDestail='" + menuDestail + '\'' +
                '}';
    }
}
