package com.lnlr.common.response;

/**
 * 操作成功Controller返回結果
 *
 * @author 雷洪飞
 */
public class SuccessResponse implements Response {

	/**
	 * 返回消息
	 */
	private String message;

	public SuccessResponse() {
		this.message = MSG_SUCCESS;
	}

	public SuccessResponse(String message) {
		this.message = message;
	}


	/**
	 * 返回结果编码
	 *
	 * @return
	 */
	@Override
	public String getStatus() {
		return CODE_SUCCESS;
	}

	/**
	 * 返回结果信息
	 *
	 * @return
	 */
	@Override
	public String getMessage() {
		return this.message;
	}
}
