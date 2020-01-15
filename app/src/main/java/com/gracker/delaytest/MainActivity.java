package com.gracker.delaytest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.MessageQueue;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.TraceCompat;

public class MainActivity extends AppCompatActivity {
    private static final int DELAY_TIME = 300;

    private ImageView zhihuImg;
    private TextView imageWidthTxt;
    private TextView imageHeightTxt;

    private Handler myHandler = new Handler();
    private Runnable mLoadingRunnable = new Runnable() {

        @Override
        public void run() {
            updateText();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zhihuImg = findViewById(R.id.zhihu_img);

        imageWidthTxt = findViewById(R.id.img_width_txt);
        imageHeightTxt = findViewById(R.id.img_height_txt);

//      第一种写法:直接Post
//      myHandler.post(mLoadingRunnable);

//      第二种写法:直接PostDelay 300ms.
//      myHandler.postDelayed(mLoadingRunnable, DELAY_TIME);

//      第三种写法:
//      优化的DelayLoad
//        getWindow().getDecorView().post(new Runnable() {
//            @Override
//            public void run() {
//                myHandler.post(mLoadingRunnable);
//            }
//        });


        //第四种写法
        // 利用 IdleHandler
        MessageQueue.IdleHandler idleHandler = new MessageQueue.IdleHandler() {
            @Override
            public boolean queueIdle() {
                updateText();
                return false;
            }
        };
        Looper.myQueue().addIdleHandler(idleHandler);
    }

    private void updateText() {
        TraceCompat.beginSection("updateText");
        imageWidthTxt.setText("image : w=" + zhihuImg.getWidth());
        imageHeightTxt.setText("image : h=" + zhihuImg.getWidth());
        TraceCompat.endSection();
    }
}
