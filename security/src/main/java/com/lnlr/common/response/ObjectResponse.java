package com.lnlr.common.response;

/**
 * 返回Object结果
 *
 * @param <T>
 * @author 雷洪飞
 */
public class ObjectResponse<T> implements Response {
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
	private T datas;


	public ObjectResponse(T datas) {
		this.status = CODE_SUCCESS;
		this.message = MSG_SUCCESS;
		this.datas = datas;
	}

	public ObjectResponse(String message, T datas) {
		this.status = CODE_SUCCESS;
		this.message = message;
		this.datas = datas;
	}

	public ObjectResponse(String responseCode, String responseMsg, T datas) {
		this.status = status;
		this.message = message;
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
	public T getDatas() {
		return datas;
	}

	/**
	 * @param datas the dateUtils to set
	 */
	public void setDatas(T datas) {
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
