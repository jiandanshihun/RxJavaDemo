package com.baofeng.rxjavademo.bean;

import java.util.ArrayList;

public class ImgFolderItem implements Cloneable {

	private ArrayList<ImgItem> childImgList;

    private String name;

	public ArrayList<ImgItem> getChildImgList() {
		return childImgList;
	}

	public void setChildImgList(ArrayList<ImgItem> childImgList) {
		this.childImgList = childImgList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
	@Override
    protected Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return super.clone();
    }

    
}
