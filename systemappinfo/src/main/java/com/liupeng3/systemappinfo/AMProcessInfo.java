package com.liupeng3.systemappinfo;

/**
 * @author liupeng on 2018/9/7.
 */
public class AMProcessInfo {
    private String pid;
    private String uid;
    private String memorySize;
    private String processName;

    public AMProcessInfo(){
        //构造函数
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(String memorySize) {
        this.memorySize = memorySize;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }
}
