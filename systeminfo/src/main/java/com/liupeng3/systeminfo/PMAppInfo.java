package com.liupeng3.systeminfo;

import android.graphics.drawable.Drawable;

/**
 * @author liupeng on 2018/9/6.
 */
public class PMAppInfo {
    private String appLabel;
    private Drawable appIcon;
    private String  pkgName;

    public PMAppInfo(){

    }

    public String getAppLabel() {
        return appLabel;
    }

    public void setAppLabel(String appLabel) {
        this.appLabel = appLabel;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }
}
