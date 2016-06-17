package com.baofeng.rxjavademo;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.widget.ExpandableListView;

import com.baofeng.rxjavademo.bean.ImgFolderItem;
import com.baofeng.rxjavademo.bean.ImgItem;
import com.socks.library.KLog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xuqiang on 2016/6/17.
 */
public class ImageCollectionActivity extends Activity {

    @Bind(R.id.grid_img_collection)
    ExpandableListView gridImgCollection;

    private ArrayList<ImgFolderItem> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_collection);
        ButterKnife.bind(this);
        getImagePath();
    }

    private void getImagePath() {
        getImgsThead.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    Thread getImgsThead = new Thread() {
        @Override
        public void run() {
            super.run();
            list.clear();
            list = cursor2GroupItem(ImageCollectionActivity.this, null);

            for (ImgFolderItem dir : list) {
                KLog.a("jiandanshihun", "ImageFolder is " + dir.getName());
                for (ImgItem img : dir.getChildImgList()) {
                    KLog.a("jiandanshihun", "Img path is " + img.getLocalPath());
                }
            }
        }
    };

    /**
     * 获取数据库图片和视频文件
     */
    public static ArrayList<ImgFolderItem> cursor2GroupItem(Context context, String dir) {
        ArrayList<ImgItem> childList = null;
        ArrayList<ImgFolderItem> groupList = new ArrayList<ImgFolderItem>();
        ImgFolderItem group = null;
        String preName = "";
        ArrayList<ImgItem> mediaList = new ArrayList<ImgItem>();
        mediaList.addAll(getImageList(context, dir));
        Collections.sort(mediaList, new Comparator<ImgItem>() {
            @Override
            public int compare(ImgItem lhs, ImgItem rhs) {
                int ret = rhs.getPhotoDate().compareTo(lhs.getPhotoDate());
                if (ret > 0) {
                    return 1;
                } else if (ret < 0) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        for (ImgItem myChildItem : mediaList) {
            String takenTime = myChildItem.getPhotoDate();
            if (!preName.equals(takenTime)) {
                preName = takenTime;
                childList = new ArrayList<ImgItem>();
                group = new ImgFolderItem();
                group.setName(takenTime);
                group.setChildImgList(childList);
                groupList.add(group);
            }
            childList.add(myChildItem);
        }
        return groupList;
    }

    public static ArrayList<ImgItem> getImageList(Context context, String dir) {
        ArrayList<ImgItem> childList = new ArrayList<ImgItem>();
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null,
                MediaStore.Images.Media.DATE_TAKEN + " DESC");
        if (cursor == null) {
            return null;
        }
        boolean isMatche = true;
        while (cursor.moveToNext()) {
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            if (TextUtils.isEmpty(dir)) {
                isMatche = true;
            } else {
                isMatche = path.matches(dir + "/*[^/]*");
            }
            if (!isMatche) {
                continue;
            }
            long dateTaken = cursor.getLong(cursor
                    .getColumnIndex(MediaStore.Images.Media.DATE_TAKEN));
            String time = dateToStr(dateTaken);
            int exifOrientation = cursor.getInt(cursor
                    .getColumnIndexOrThrow(MediaStore.Images.ImageColumns.ORIENTATION));
            ImgItem myChildItem = new ImgItem();
            myChildItem.setLocalPath(path);
            myChildItem.setPhotoDate(time);
            myChildItem.setExifOrientation(exifOrientation);

            childList.add(myChildItem);
        }
        cursor.close();
        return childList;
    }

    public static String dateToStr(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(time);
        return dateString;
    }
}
