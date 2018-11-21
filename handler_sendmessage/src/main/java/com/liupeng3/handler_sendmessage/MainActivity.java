package com.liupeng3.handler_sendmessage;

/**
 * create by liupeng/20181012
 *
 * 通过Message与Handler进行通信的步骤是：
 1. 重写Handler的handleMessage方法，根据Message的what值进行不同的处理操作

 2. 创建Message对象
 虽然Message的构造函数式public的,我们还可以通过Message.obtain()或Handler.obtainMessage()
 来获得一个Message对象（Handler.obtainMessage()内部其实调用了Message.obtain()）。

 3. 设置Message的what值
 Message.what是我们自定义的一个Message的识别码，以便于在Handler的handleMessage方法中
 根据what识别出不同的Message，以便我们做出不同的处理操作。

 4. 设置Message的所携带的数据，简单数据可以通过两个int类型的field arg1和arg2来赋值，
 并可以在handleMessage中读取。

 5. 如果Message需要携带复杂的数据，那么可以设置Message的obj字段，obj是Object类型，可以赋予任意类型的数据。
 或者可以通过调用Message的setData方法赋值Bundle类型的数据，可以通过getData方法获取该Bundle数据。

 6. 我们通过Handler.sendMessage(Message)方法将Message传入Handler中让其在handleMessage中对其进行处理。

 需要说明的是，如果在handleMessage中 不需要判断Message类型，那么就无须设置Message的what值；
 而且让Message携带数据也不是必须的，只有在需要的时候才需要让其携带数据；
 如果确实需要让Message携带数据，应该尽量使用arg1或arg2或两者，
 能用arg1和arg2解决的话就不要用obj，因为用arg1和arg2更高效。
 ---------------------
 作者：孙群
 来源：CSDN
 原文：https://blog.csdn.net/iispring/article/details/47115879?utm_source=copy
 版权声明：本文为博主原创文章，转载请附上博文链接！
 *
 *
 */

import android.app.Activity;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements Button.OnClickListener {
    private static final String TAG ="lp5800n953";
    private TextView statusTextView = null;

    //uiHandler在主线程中创建，所以自动绑定主线程
    private  Handler uiHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Log.d(TAG,"handleMessage thread id is " + Thread.currentThread().getId());
                    Log.d(TAG,"msg.arg1:" + msg.arg1);
                    Log.d(TAG,"msg.arg2:" + msg.arg2);
                    MainActivity.this.statusTextView.setText("文件下载完成");
                    Log.d(TAG,"文件下载完成");
                    break;
            }


        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        statusTextView=findViewById(R.id.statusTextView);
        Button btnDownload=findViewById(R.id.btnDownload);
        btnDownload.setOnClickListener(this);
        Log.d(TAG,"Main Thread id is " + Thread.currentThread().getId());

    }

    @Override
    public void onClick(View v) {
        DownloadThread downloadThread=new DownloadThread();
        downloadThread.start();
        Log.d(TAG,"onclick Thread id is " + Thread.currentThread().getId());

    }

    class DownloadThread extends Thread{
        @Override
        public void run() {
            //super.run();
            try{
                Log.d(TAG,"DownloadThread id is " + Thread.currentThread().getId());
                Log.d(TAG,"开始下载文件");
                //此处让线程DownloadThread休眠5秒中，模拟文件的耗时过程
                Thread.sleep(5000);
                //文件下载完成后更新UI
                Message msg=new Message();

                /**
                 * 虽然Message的构造函数式public的，我们也可以通过以下两种方式通过循环对象获取Message
                 * msg = Message.obtain(uiHandler);
                 * msg = uiHandler.obtainMessage();
                 *
                 * what是我们自定义的一个Message的识别码，以便于在Handler的handleMessage方法中
                 * 根据what识别出不同的Message，以便我们做出不同的处理操作
                 */

                //我们可以通过arg1和arg2给Message传入简单的数据
                msg.what=1;
                msg.arg1=123;
                msg.arg2=321;

                /**
                 * 我们也可以通过给obj赋值Object类型传递向Message传入任意数据
                 * msg.obj = null;
                 * 我们还可以通过setData方法和getData方法向Message中写入和读取Bundle类型的数据
                 * msg.setData(null);
                 * Bundle data = msg.getData();
                 *
                 */

                //将该Message发送给对应的Handler
                uiHandler.sendMessage(msg);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
