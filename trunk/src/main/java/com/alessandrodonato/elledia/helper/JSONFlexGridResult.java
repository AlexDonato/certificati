/**
 * 
 */
package com.alessandrodonato.elledia.helper;

import java.util.ArrayList;

/**
 * @author Alessandro Donato
 *
 * 20/feb/2013
 */
public class JSONFlexGridResult {
	private int page;
	private int total;
	private ArrayList rows;
	private ArrayList post;
	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}
	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	/**
	 * @return the rows
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	@SuppressWarnings("rawtypes")
	public void setRows(ArrayList rows) {
		this.rows = rows;
	}
	/**
	 * @return the post
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList getPost() {
		return post;
	}
	/**
	 * @param post the post to set
	 */
	@SuppressWarnings("rawtypes")
	public void setPost(ArrayList post) {
		this.post = post;
	}
}
