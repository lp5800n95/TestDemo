package com.liupeng3.handlertest_post;
/**
 * create by liupeng/20181012
 * 使用post方法
 * 我们在Activity中创建了一个Handler成员变量uiHandler，Handler有个特点，在执行new Handler()的时候
 * 默认情况下Handler会绑定当前代码执行的线程，我们在主线程中实例化了uiHandler
 * 所以uiHandler就自动绑定了主线程，即UI线程.
 * 当我们在DownloadThread中执行完耗时代码后，我们将一个Runnable对象通过post方法传入到了Handler中
 * Handler会在合适的时候让主线程执行Runnable中的代码
 * 这样Runnable就在主线程中执行了，从而正确更新了主线程中的UI
 * 以下是输出结果
 *
 * Line 80086: 01-15 00:45:56.155 D/lp5800n95(12509): Main Thread id is 2
   Line 80159: 01-15 00:46:03.819 D/lp5800n95(12509): DownloadThread id is 1192
   Line 80161: 01-15 00:46:03.820 D/lp5800n95(12509): 开始下载文件
   Line 80299: 01-15 00:46:08.825 D/lp5800n95(12509): 文件下载完成
   Line 80300: 01-15 00:46:08.829 D/lp5800n95(12509): Runnable Thread id is 2

 *通过输出结果可以看出，Runnable中的代码所执行的线程ID与DownloadThread的线程ID不同，而与主线程的线程ID相同
 * 因此我们也由此看出在执行了Handler.post(Runnable)这句代码之后
 * 运行Runnable代码的线程与Handler所绑定的线程是一致的
 * 而与执行Handler.post(Runnable)这句代码的线程（DownloadThread）无关
 *
 *
 *
 */

import android.os.Handler;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements Button.OnClickListener {

    private static final String TAG ="lp5800n95";
    private TextView statusTextView =null;
    //uiHandler在主线程中创建，所以自动绑定主线程
    private Handler uiHandler= new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        statusTextView =findViewById(R.id.statusTextView);
        Button btnDownload=findViewById(R.id.btnDownload);
        btnDownload.setOnClickListener(this);
        Log.d(TAG,"Main Thread id is " +Thread.currentThread().getId());
    }

    @Override
    public void onClick(View v) {
        DownloadThread downloadThread= new DownloadThread();
        downloadThread.start();
    }

    class DownloadThread  extends Thread{
        @Override
        public void run() {
            //super.run();
            try{
                Log.d(TAG,"DownloadThread id is " + Thread.currentThread().getId());
                Log.d(TAG,"开始下载文件");
                //此处让线程DownloadThread休眠5秒中，模拟文件的耗时过程
                Thread.sleep(5000);
                Log.d(TAG,"文件下载完成");
                //文件下载完成后更新UI
                Runnable runnable=new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG,"Runnable Thread id is " + Thread.currentThread().getId());
                        MainActivity.this.statusTextView.setText("文件下载完成");
                    }
                };

                /**
                 *当我们在DownloadThread中执行完耗时代码后，我们将一个Runnable对象通过post方法传入到了Handler中，
                 * Handler会在合适的时候让主线程执行Runnable中的代码，这样Runnable就在主线程中执行了，
                 * 从而正确更新了主线程中的UI
                 *
                 */
                //将一个Runnable对象通过post方法传入到了Handler中
                uiHandler.post(runnable);

            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
