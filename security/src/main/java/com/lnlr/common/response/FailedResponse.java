package com.lnlr.common.response;

/**
 * 操作失败Controller返回结果
 *
 * @param <T>
 * @author 雷洪飞
 */
public class FailedResponse<T> implements Response {
	/**
	 * 返回编码
	 */
	private String status;
	/**
	 * 返回信息
	 */
	private String message;

	public FailedResponse() {
		this.status = Response.CODE_FAILED;
		this.message = Response.MSG_FAILED;
	}

	public FailedResponse(String status, String message) {
		this.status = status;
		this.message = message;
	}

	public FailedResponse(String message) {
		this.status = Response.CODE_FAILED;
		this.message = message;
	}

	;

	@Override
	public String getStatus() {
		return status;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
