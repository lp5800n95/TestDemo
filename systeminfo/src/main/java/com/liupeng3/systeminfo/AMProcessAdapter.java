package com.liupeng3.systeminfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * @author liupeng on 2018/9/7.
 */
public class AMProcessAdapter extends BaseAdapter {
    private List<AMProcessInfo> mlistData=null;
    private LayoutInflater mInfater;

    public AMProcessAdapter(Context context, List<AMProcessInfo> listData){
        mInfater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        mlistData=listData;
    }

    @Override
    public int getCount() {
        //return 0;
        return  mlistData.size();
    }

    @Override
    public Object getItem(int position) {
        //return null;
        return mlistData.get(position);
    }

    @Override
    public long getItemId(int position) {
        //return 0;
        return  position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return null;
        ViewHolder viewHolder= null;
        if(convertView==null){
            convertView=mInfater.inflate(R.layout.am_process_item, null);
            viewHolder=new ViewHolder();
            viewHolder.tvPID=convertView.findViewById(R.id.tv_pid);
            viewHolder.tvUID=convertView.findViewById(R.id.tv_uid);
            viewHolder.tvMemorySize=convertView.findViewById(R.id.tv_size);
            viewHolder.tvProcessName=convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) convertView.getTag();
        }

        AMProcessInfo processInfo=(AMProcessInfo) getItem(position);
        viewHolder.tvPID.setText("Pid :"+processInfo.getPid());
        viewHolder.tvUID.setText("Uid :"+processInfo.getUid());
        viewHolder.tvMemorySize.setText("Size:"+processInfo.getMemorySize()+"KB");
        viewHolder.tvProcessName.setText("Name:"+processInfo.getProcessName());
        return  convertView;
    }

    class ViewHolder{
        TextView tvPID;
        TextView tvUID;
        TextView tvMemorySize;
        TextView tvProcessName;
    }
}
