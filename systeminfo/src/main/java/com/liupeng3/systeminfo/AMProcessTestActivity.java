package com.liupeng3.systeminfo;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Debug;
import android.support.annotation.Nullable;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liupeng on 2018/9/7.
 */
public class AMProcessTestActivity extends Activity{

    private ListView mlistView;
    private List<AMProcessInfo> mAmProcessInfoList;
    private ActivityManager mactivityManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.am_process_main);
        mlistView=findViewById(R.id.listView_am_process);
        mactivityManager=(ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        mlistView.setAdapter(new AMProcessAdapter(this,getRunningProcessInfo()));

    }

    private List<AMProcessInfo> getRunningProcessInfo(){
        mAmProcessInfoList=new ArrayList<AMProcessInfo>();
        List<ActivityManager.RunningAppProcessInfo> appProcessInfoList=
                mactivityManager.getRunningAppProcesses();
        for(int i=0;i<appProcessInfoList.size();i++){
            ActivityManager.RunningAppProcessInfo info=
                    appProcessInfoList.get(i);
            int pid=info.pid;
            int uid=info.uid;
            String processName=info.processName;
            int[] memoryPid =new int[]{pid};
            Debug.MemoryInfo[] memoryInfos=mactivityManager.getProcessMemoryInfo(memoryPid);
            int memorySize =memoryInfos[0].getTotalPss();
            AMProcessInfo processInfo=new AMProcessInfo();
            processInfo.setPid("pid is"+pid);
            processInfo.setUid("uid is"+uid);
            processInfo.setMemorySize("size is"+memorySize);
            processInfo.setProcessName("Name is"+processName);
            mAmProcessInfoList.add(processInfo);
        }
        return mAmProcessInfoList;
    }
}
