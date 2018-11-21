package com.liupeng3.systeminfo;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liupeng on 2018/9/6.
 */
public class PMTestActivity extends Activity {
    public static final int ALL_APP=0;
    public static final int SYSTEM_APP=1;
    public static final int THIRD_APP=2;
    public static final int SDCARD_APP=3;

    private ListView mlistView;
    private PackageManager packageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pm_main);
        mlistView=findViewById(R.id.listView_pm);
    }

    private List<PMAppInfo> getAppInfo(int flag) {
        packageManager = this.getPackageManager();
        List<ApplicationInfo> listAppliactions = packageManager.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
        List<PMAppInfo> appInfos = new ArrayList<PMAppInfo>();
        switch (flag) {
            case ALL_APP:
                appInfos.clear();
                for (ApplicationInfo appTemp : listAppliactions) {
                    appInfos.add(makeAppInfo(appTemp));
                }
                break;
            case SYSTEM_APP:
                appInfos.clear();
                for (ApplicationInfo appTemp : listAppliactions) {
                    if ((appTemp.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                        appInfos.add(makeAppInfo(appTemp));
                    }
                }
                break;
            case THIRD_APP:
                appInfos.clear();
                for (ApplicationInfo appTemp : listAppliactions) {
                    if ((appTemp.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
                        appInfos.add(makeAppInfo(appTemp));
                    } else if ((appTemp.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
                        appInfos.add(makeAppInfo(appTemp));
                    }
                }
                break;
            case SDCARD_APP:
                appInfos.clear();
                for (ApplicationInfo appTemp : listAppliactions) {
                    if ((appTemp.flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) != 0) {
                        appInfos.add(makeAppInfo(appTemp));
                    }
                }
                break;
            default:
                return null;
        }
        return appInfos;
    }


    private PMAppInfo makeAppInfo(ApplicationInfo app){
        PMAppInfo appInfo=new PMAppInfo();
        appInfo.setAppLabel((String) app.loadLabel(packageManager));
        appInfo.setAppIcon(app.loadIcon(packageManager));
        appInfo.setPkgName(app.packageName);
        return appInfo;

    }

    public void setListData(int flag){
        PMTestAdapter pmTestAdapter=new PMTestAdapter(this,getAppInfo(flag));
        mlistView.setAdapter(pmTestAdapter);
    }

    public void btnAllApp(View view){
        setListData(ALL_APP);
    }

    public void btnSystemApp(View view){
        setListData(SYSTEM_APP);
    }

    public void btn3rdApp(View view){
        setListData(THIRD_APP);
    }

    public void btnSdcardApp(View view){
        setListData(SDCARD_APP);
    }
}
