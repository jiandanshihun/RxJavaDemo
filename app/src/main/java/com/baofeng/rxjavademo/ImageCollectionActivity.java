package com.baofeng.rxjavademo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xuqiang on 2016/6/17.
 */
public class ImageCollectionActivity extends Activity {
    @Bind(R.id.grid_img_collection)
    GridView gridImgCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_collection);
        ButterKnife.bind(this);
    }

    private void getImagePath(){
        getImgsThead.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    Thread getImgsThead = new Thread(){
        @Override
        public void run() {
            super.run();
        }
    };
}
