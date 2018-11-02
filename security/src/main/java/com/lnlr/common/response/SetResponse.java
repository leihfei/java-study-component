package com.lnlr.common.response;


import java.util.Set;

/**
 * 返回Set结果
 * @author leihf
 */
public class SetResponse<T> implements Response {

	/**
	 * 返回编码
	 */
	private String status;
	/**
	 * 返回信息
	 */
	private String message;

	/**
	 * 返回数据
	 */
	private Set<T> datas;

	public SetResponse(String responseCode, String responseMsg, Set<T> datas) {
		this.status = responseCode;
		this.message = responseMsg;
		this.datas = datas;
	}

	public SetResponse(Set<T> datas) {
		this.status = CODE_SUCCESS;
		this.message = MSG_SUCCESS;
		this.datas = datas;
	}

	@Override
	public String getStatus() {
		return status;
	}


	@Override
	public String getMessage() {
		return message;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the dateUtils
	 */
	public Set<T> getDatas() {
		return datas;
	}

	/**
	 * @param datas the dateUtils to set
	 */
	public void setDatas(Set<T> datas) {
		this.datas = datas;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ListResponse [status=" + status + ", message=" + message + ", dateUtils=" + datas + "]";
	}

}
