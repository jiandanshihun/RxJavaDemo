package com.baofeng.rxjavademo.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class ImgItem implements Serializable, Parcelable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1987792212760365181L;

	// 拍摄照片日期
    private String photoDate;

    // 本地图片路径
    private String localPath;
    
    private int exifOrientation;// 图片角度方向
    
 // 服务器原图url
    private String originalUrl;
    
  //缩略图
    private String thumbnailUrl;
    
    private long createTime;
    
    private String name;// 唯一标识
    
    private boolean isChoosen;
    
 // 地理位置
    private String location;
    
    public ImgItem(){        
    }
    
    
	public String getPhotoDate() {
		return photoDate;
	}

	public void setPhotoDate(String photoDate) {
		this.photoDate = photoDate;
	}

	public String getLocalPath() {
		return localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

	public int getExifOrientation() {
		return exifOrientation;
	}

	public void setExifOrientation(int exifOrientation) {
		this.exifOrientation = exifOrientation;
	}

	public String getOriginalUrl() {
		return originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	public boolean isChoosen() {
		return isChoosen;
	}


	public void setChoosen(boolean isChoosen) {
		this.isChoosen = isChoosen;
	}


	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	/**
     * Parcelable 必须实现的方法
     * @param in
     */
    public ImgItem(Parcel in){
        readFromParcel(in);
    }
    /**
     * Parcelable 必须实现的方法
     * @param in
     */
    public static final Creator<ImgItem> CREATOR = new Creator<ImgItem>() {
        public ImgItem createFromParcel(Parcel in) {
            return new ImgItem(in);
        }

        public ImgItem[] newArray(int size) {
            return new ImgItem[size];
        }
    };
    
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private void readFromParcel(Parcel in) {
        photoDate = in.readString();
        
        localPath = in.readString();
        originalUrl = in.readString();
        thumbnailUrl = in.readString();
        name = in.readString();
        
        createTime = in.readLong();
        location = in.readString();
        exifOrientation = in.readInt();                
    }

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
        dest.writeString(photoDate);
        
        dest.writeString(localPath);
        dest.writeString(originalUrl);
        dest.writeString(thumbnailUrl);
        dest.writeString(name);
        dest.writeLong(createTime);
        dest.writeString(location);
        dest.writeInt(exifOrientation);
	}

}
