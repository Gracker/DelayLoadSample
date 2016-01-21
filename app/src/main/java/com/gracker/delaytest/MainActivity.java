package com.gracker.delaytest;

import android.os.Handler;
import android.support.v4.os.TraceCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

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

        zhihuImg = (ImageView) findViewById(R.id.zhihu_img);

        imageWidthTxt = (TextView) findViewById(R.id.img_width_txt);
        imageHeightTxt = (TextView) findViewById(R.id.img_height_txt);

//      第一种写法:直接Post
//      myHandler.post(mLoadingRunnable);

//      第二种写法:直接PostDelay 300ms.
//      myHandler.postDelayed(mLoadingRunnable, DELAY_TIME);

//      第三种写法:
//      优化的DelayLoad
        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                myHandler.post(mLoadingRunnable);
            }
        });
    }

    private void updateText() {
        TraceCompat.beginSection("updateText");
        imageWidthTxt.setText("image : w=" + zhihuImg.getWidth());
        imageHeightTxt.setText("image : h=" + zhihuImg.getWidth());
        TraceCompat.endSection();
    }
}
