package com.gracker.delaytest;

import android.os.Handler;
import android.support.v4.os.TraceCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final int DEALY_TIME = 300;

    private ImageView zhihuImage;
    private TextView imageWidth;
    private TextView imageHeight;

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

        zhihuImage = (ImageView) findViewById(R.id.zhihu_img);

        imageWidth = (TextView) findViewById(R.id.img_width_txt);
        imageHeight = (TextView) findViewById(R.id.img_height_txt);

        //  第一种写法:直接Post
//        myHandler.post(mLoadingRunnable);

//  第二种写法:直接PostDelay 300ms.
//  myHandler.postDelayed(mLoadingRunnable, DEALY_TIME);

//        第三种写法:
//        优化的DelayLoad
        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                myHandler.post(mLoadingRunnable);
            }
        });
    }

    private void updateText() {
        TraceCompat.beginSection("updateText");
        imageWidth.setText("image : w=" + zhihuImage.getWidth());
        imageHeight.setText("image : h=" + zhihuImage.getWidth());
        TraceCompat.endSection();
    }
}
