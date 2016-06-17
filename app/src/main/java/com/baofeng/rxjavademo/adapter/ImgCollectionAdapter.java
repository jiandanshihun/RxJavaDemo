package com.baofeng.rxjavademo.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.baofeng.rxjavademo.R;
import com.baofeng.rxjavademo.bean.ImgFolderItem;
import com.baofeng.rxjavademo.view.MyGridView;

import java.util.ArrayList;

/**
 * Created by xuqiang on 2016/6/17.
 */
public class ImgCollectionAdapter extends BaseExpandableListAdapter {
    private ArrayList<ImgFolderItem> imgData;
    private Activity context;
    private LayoutInflater mInflater;
    private ImageGridAdapter gridAdapter;

    public ImgCollectionAdapter(ArrayList<ImgFolderItem> imgData, Activity context) {
        super();
        this.imgData = imgData;
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        // TODO Auto-generated method stub
        if (imgData == null) {
            return 0;
        }
        return imgData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        // TODO Auto-generated method stub
        if (imgData == null || imgData.get(groupPosition) == null
                || imgData.get(groupPosition).getChildImgList() == null) {
            return 0;
        }
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        // TODO Auto-generated method stub
        if (imgData == null) {
            return null;
        }
        return imgData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        if (imgData == null || imgData.get(groupPosition) == null
                || imgData.get(groupPosition).getChildImgList() == null) {
            return null;
        }
        return imgData.get(groupPosition).getChildImgList();
    }

    @Override
    public long getGroupId(int groupPosition) {
        // TODO Auto-generated method stub
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return groupPosition * 10000;
    }

    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        ImageListGroupHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(
                    R.layout.import_show_list_group_item, null);
            holder = new ImageListGroupHolder();
            holder.imgIndex = (TextView) convertView
                    .findViewById(R.id.import_list_group_index);
            convertView.setTag(holder);
        } else {
            holder = (ImageListGroupHolder) convertView.getTag();
        }

        holder.imgIndex.setText(imgData.get(groupPosition).getName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ImageListChildHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(
                    R.layout.import_show_list_child_item, null);
            holder = new ImageListChildHolder();
            holder.imgGridView = (MyGridView) convertView
                    .findViewById(R.id.groupitem_image_show);
            convertView.setTag(holder);
        } else {
            holder = (ImageListChildHolder) convertView.getTag();
        }

        gridAdapter = null;
//		LogHelper
//				.e("xq",
//						"group.getChildImgList() size is "
//								+ groupList.get(groupPosition)
//										.getChildImgList().size());
        gridAdapter = new ImageGridAdapter(context, imgData
                .get(groupPosition).getChildImgList(),null);
        holder.imgGridView.setAdapter(gridAdapter);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return false;
    }


    static class ImageListGroupHolder {

        TextView imgIndex;

    }

    static class ImageListChildHolder {

        MyGridView imgGridView;

    }



}
