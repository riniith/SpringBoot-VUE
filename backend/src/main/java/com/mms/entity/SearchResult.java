package com.mms.entity;

import java.util.List;

/**
 * 实体类：搜索结果
 * 
 * @author Administrator
 *
 */
public class SearchResult<E> {
	// 查到的对象列表
	private List<E> list;
	// 查到的结果总行数
	private long total;

	public SearchResult() {
	}

	public SearchResult(List<E> list, long total) {
		super();
		this.list = list;
		this.total = total;
	}

	public List<E> getList() {
		return list;
	}

	public void setList(List<E> list) {
		this.list = list;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

}
