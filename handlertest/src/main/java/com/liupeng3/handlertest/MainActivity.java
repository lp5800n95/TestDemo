package com.liupeng3.handlertest;

/**
 * create by liupeng 20181011
 * handler的基本用法
 * 前言：
 * 在Android开发中，我们经常会遇到这样一种情况：在UI界面上进行某项操作后要执行一段很耗时的代码，比如我们在界
 * 面上点击了一个”下载“按钮，那么我们需要执行网络请求，这是一个耗时操作，因为不知道什么时候才能完成。为了保证
 * 不影响UI线程，所以我们会创建一个新的线程去执行我们的耗时的代码。当我们的耗时操作完成时，我们需要更新UI界面
 * 以告知用户操作完成了。所以我们可能会写出如下的代码:
 *
 * 上面的代码演示了单击”下载“按钮后会启动一个新的线程去执行实际的下载操作，执行完毕后更新UI界面。但是在实际运
 * 行到代码MainActivity.this.statusTextView.setText(“文件下载完成”)时，会报错如下，系统崩溃退出：
 * android.view.ViewRootImpl$CalledFromWrongThreadException:
 * Only the original thread that created a view hierarchy can touch its views
 * 错误的意思是只有创建View的原始线程才能更新View。出现这样错误的原因是Android中的View不是线程安全的
 * 在Android应用启动时，会自动创建一个线程，即程序的主线程，主线程负责UI的展示、UI事件消息的派发处理等等
 * 因此主线程也叫做UI线程，statusTextView是在UI线程中创建的
 * 当我们在DownloadThread线程中去更新UI线程中创建的statusTextView时自然会报上面的错误
 * Android的UI控件是非线程安全的，其实很多平台的UI控件都是非线程安全的，
 * 比如C#的.Net Framework中的UI控件也是非线程安全的
 * 所以不仅仅在Android平台中存在从一个新线程中去更新UI线程中创建的UI控件的问题
 * 不同的平台提供了不同的解决方案以实现跨线程跟新UI控件，Android为了解决这种问题引入了Handler机制。
 *
 * 那么Handler到底是什么呢？
 * Handler是Android中引入的一种让开发者参与处理线程中消息循环的机制。
 * 每个Hanlder都关联了一个线程，每个线程内部都维护了一个消息队列MessageQueue，
 * 这样Handler实际上也就关联了一个消息队列。
 * 可以通过Handler将Message和Runnable对象发送到该Handler所关联线程的MessageQueue（消息队列）中
 * 然后该消息队列一直在循环拿出一个Message，对其进行处理，处理完之后拿出下一个Message，继续进行处理，周而复始
 * 当创建一个Handler的时候，该Handler就绑定了当前创建Hanlder的线程
 * 从这时起，该Hanlder就可以发送Message和Runnable对象到该Handler对应的消息队列中
 * 当从MessageQueue取出某个Message时，会让Handler对其进行处理
 *
 * Handler可以用来在多线程间进行通信，在另一个线程中去更新UI线程中的UI控件只是Handler使用中的一种典型案例
 * 除此之外，Handler可以做很多其他的事情
 * 每个Handler都绑定了一个线程，假设存在两个线程ThreadA和ThreadB，并且HandlerA绑定了 ThreadA
 * 在ThreadB中的代码执行到某处时，出于某些原因，我们需要让ThreadA执行某些代码
 * 此时我们就可以使用Handler，我们可以在ThreadB中向HandlerA中加入某些信息以告知ThreadA中该做某些处理了。
 * 由此可以看出，Handler是Thread的代言人，是多线程之间通信的桥梁
 * 通过Handler，我们可以在一个线程中控制另一个线程去做某事
 *
 * Handler提供了两种方式解决我们在本文一开始遇到的问题（在一个新线程中更新主线程中的UI控件），
 * 一种是通过post方法，一种是调用sendMessage方法
 */

import android.app.Activity;
import android.os.Bundle;
import android.os.Debug;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements Button.OnClickListener {
    private TextView statusTextView= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        statusTextView=findViewById(R.id.statusTextView);
        Button btnDownload=findViewById(R.id.btnDownload);
        btnDownload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        DownloadThread downloadThread = new DownloadThread();
        downloadThread.start();

    }
    class DownloadThread extends Thread{
        @Override
        public void run() {
            //super.run();
            try{
                System.out.println("开始下载文件");
                Thread.sleep(5);
                System.out.println("文件下载完成");
                MainActivity.this.statusTextView.setText("文件下载完成");

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
