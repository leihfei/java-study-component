package com.lnlr.common.response;

/**
 * Controller 返回值顶层接口
 *
 * @author ycitss
 */
public interface Response {
	/**
	 * 500代码
	 */
	String CODE_FAILED = "500";

	/**
	 * 失败消息
	 */
	String MSG_FAILED = "failed";

	/**
	 * 200代码常量
	 */
	String CODE_SUCCESS = "200";
	/**
	 * 正常消息
	 */
	String MSG_SUCCESS = "success";

	/**
	 * 返回结果编码
	 *
	 * @return
	 */
	String getStatus();

	/**
	 * 返回结果信息
	 *
	 * @return
	 */
	String getMessage();
}
