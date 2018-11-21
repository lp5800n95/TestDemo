package com.liupeng3.systemappinfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * @author liupeng on 2018/9/6.
 */
public class PMTestAdapter extends BaseAdapter {
    private List<PMAppInfo> mAppInfoList=null;
    private LayoutInflater mInflater=null;

    public PMTestAdapter(Context context,List<PMAppInfo>appInfoList){
        mInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mAppInfoList=appInfoList;
    }

    @Override
    public int getCount() {
        //return 0;
        return mAppInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        //return null;
        return mAppInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        //return 0;
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return null;
        ViewHolder viewHolder=null;
        if(convertView ==null){
            viewHolder=new ViewHolder();
            convertView=mInflater.inflate(R.layout.pm_item,null);
            viewHolder.appIcon=(ImageView) convertView.findViewById(R.id.imageView_icon);
            viewHolder.appLabel=(TextView) convertView.findViewById(R.id.textView_label);
            viewHolder.appPackage=(TextView) convertView.findViewById(R.id.textView_package);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) convertView.getTag();
        }

        PMAppInfo appInfo=(PMAppInfo) getItem(position);
        viewHolder.appIcon.setImageDrawable(appInfo.getAppIcon());
        viewHolder.appLabel.setText(appInfo.getAppLabel());
        viewHolder.appPackage.setText(appInfo.getPkgName());
        return convertView;

    }

    class ViewHolder{
        ImageView appIcon;
        TextView appLabel;
        TextView appPackage;

    }
}
