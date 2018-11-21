package com.liupeng3.listviewtest20180813;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * create by liupeng/20180813
 */
public class MainActivity extends AppCompatActivity {
    private String[] data = {"Apple", "Banana", "Orange", "Watermelon",
            "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango"};
    private List<Fruit> fruitList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * ArraryAdapter 适配器，用泛型来指定要适配的数据类型
         * simple_list_item_1 作为listview子项布局的id
         * setAdapter 将构建好的适配器对象传递出去，使得listView和数据之间关联
         */
        ArrayAdapter<String> adapter_01 = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1, data);
        ListView listView = findViewById(R.id.list_view);
        //listView.setAdapter(adapter_01);

        //初始化水果数据
        initFruits();
        FruitAdapter adapter_02 = new FruitAdapter(MainActivity.this, R.layout.fruit_item, fruitList);
        listView.setAdapter(adapter_02);

        /**
         * listView子项的点击事件
         * 通过setOnItemClickListener为listView注册一个监听器，当用户点击listView中的子项时
         * 就会回调onItemClick方法
         * 这个方法，可以通过position参数判断用户点击的是哪一个子项
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = fruitList.get(position);
                Toast.makeText(MainActivity.this, " You click is :" + fruit.getName(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initFruits() {
        for (int i = 0; i < 2; i++) {
            Fruit apple = new Fruit("apple", R.drawable.apple_pic);
            fruitList.add(apple);
            Fruit banana = new Fruit("Banana", R.drawable.banana_pic);
            fruitList.add(banana);
            Fruit orange = new Fruit("Orange", R.drawable.orange_pic);
            fruitList.add(orange);
            Fruit watermelon = new Fruit("Watermelon", R.drawable.watermelon_pic);
            fruitList.add(watermelon);
            Fruit pear = new Fruit("Pear", R.drawable.pear_pic);
            fruitList.add(pear);
            Fruit grape = new Fruit("Grape", R.drawable.grape_pic);
            fruitList.add(grape);
            Fruit pineapple = new Fruit("Pineapple", R.drawable.pineapple_pic);
            fruitList.add(pineapple);
            Fruit strawberry = new Fruit("Strawberry", R.drawable.strawberry_pic);
            fruitList.add(strawberry);
            Fruit cherry = new Fruit("Cherry", R.drawable.cherry_pic);
            fruitList.add(cherry);
            Fruit mango = new Fruit("Mango", R.drawable.mango_pic);
            fruitList.add(mango);
        }
    }
}
