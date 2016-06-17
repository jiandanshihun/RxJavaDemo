package com.baofeng.rxjavademo.adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.baofeng.rxjavademo.R;
import com.baofeng.rxjavademo.bean.ImgItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.ArrayList;

public class ImageGridAdapter extends BaseAdapter {
	private Context context;

    private ArrayList<ImgItem> pics;

    private LayoutInflater mInflater;

    private ArrayList<String> selectedFiles;

    private RequestManager glide;
    

	public ImageGridAdapter(Context context, ArrayList<ImgItem> pics, ArrayList<String> selectedFiles) {
		super();
		this.context = context;
		this.pics = pics;
		this.selectedFiles = selectedFiles;
		mInflater = LayoutInflater.from(context);
        glide =  Glide.with(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return pics.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return pics.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		GridHolder gHolder = null;
        if (convertView == null) {
            gHolder = new GridHolder();
            convertView = mInflater.inflate(R.layout.import_show_list_griditem, null);
            gHolder.img = (ImageView) convertView.findViewById(R.id.image_show_item);
            gHolder.choose = (ImageView) convertView.findViewById(R.id.choose_img);
            convertView.setTag(gHolder);
        } else {
            gHolder = (GridHolder) convertView.getTag();
        }
        
        LayoutParams para;  
        para = gHolder.img.getLayoutParams();
		para.height = (getScreenDisplayMinWidth(context)-80)/3;
		para.width = (getScreenDisplayMinWidth(context)-80)/3;
        gHolder.img.setLayoutParams(para);
//        ImgUtil.displayImage(pics.get(position).getLocalPath(), gHolder.img);
        glide.load(pics.get(position).getLocalPath()).into(gHolder.img);
        

        return convertView;
	}
	private class GridHolder {
        ImageView img;

        ImageView choose;
    }

    public static int getScreenDisplayMinWidth(Context context) {
        try {
            WindowManager manager = ((WindowManager) context
                    .getSystemService(Context.WINDOW_SERVICE));

            DisplayMetrics dm = new DisplayMetrics();
            manager.getDefaultDisplay().getMetrics(dm);
            int h = dm.heightPixels;
            int w = dm.widthPixels;

            if (w < h) {
                return w;
            } else {
                return h;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
