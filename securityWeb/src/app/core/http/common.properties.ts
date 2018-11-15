// 请求地址
export const BASE_URL = 'http://localhost:8089/security/';

export class ResponseCode {
  /**
   * 正常请求
   */

  static RET_OK = '200';
  /**
   * 未登录状态码
   */

  static NO_LOGIN = '401';

  /**
   * 重新生成token
   */
  static AGAIN_TOKEN = '402';

  /**
   * 没有权限状态码
   */

  static NO_PRESSION = '403';

  /**
   * 错误请求
   */

  static RET_ERROR = '500';

}

