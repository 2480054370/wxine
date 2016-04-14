package com.wxine.android.utils;

import java.util.ArrayList;
import java.util.List;

import com.wxine.android.model.Community;

public class CommunityPage {
	private int pageSize = 20;
	private List<Community> items = new ArrayList<Community>();
	private int totalCount = 0;
	private int pageCount = 0;
	private int currentPage = 1;
	private int[] pages = new int[0];
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public List<Community> getItems() {
		return items;
	}

	public void setItems(List<Community> items) {
		this.items = items;
	}

	public int getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public int getPageCount() {
		return pageCount;
	}
	
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public int[] getPages() {
		return pages;
	}
	
	public void setPages(int[] pages) {
		this.pages = pages;
	}
}
