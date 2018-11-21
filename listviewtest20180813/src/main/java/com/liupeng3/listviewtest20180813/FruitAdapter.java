package com.liupeng3.listviewtest20180813;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * create by liupeng 20180813
 */

public class FruitAdapter extends ArrayAdapter<Fruit> {
    private int resourceId;
    protected FruitAdapter(Context context,int textViewResourceId, List<Fruit> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }

    /**
     * listview 优化技巧
     * 1：利用布局缓存
     * 2：利用控件的实例缓存
     * @param position
     * @param convertView  参数convertView 用于将之前加载好的布局进行缓存
     * @param parent
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
       //return super.getView(position, convertView, parent);

        Fruit fruit = getItem(position);
        View view;
        ViewHolder viewHolder;
        //1:利用布局缓存,优化listview
        if(convertView==null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            //2:利用控件的实例缓存，优化listview
            viewHolder=new ViewHolder();
            viewHolder.fruitImage= (ImageView) view.findViewById(R.id.fruit_image);
            viewHolder.fruitName=(TextView) view.findViewById(R.id.fruit_name);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder=(ViewHolder) view.getTag();
        }

        viewHolder.fruitImage.setImageResource(fruit.getImageId());
        viewHolder.fruitName.setText(fruit.getName());
        return view;
    }

    //2:利用控件的实例缓存，优化listview
    class ViewHolder{
        ImageView fruitImage;
        TextView fruitName;
    }

}


