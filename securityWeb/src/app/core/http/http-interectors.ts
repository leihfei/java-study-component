import {Injectable} from '@angular/core';
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';
import {Observable, of, throwError} from 'rxjs';
import {BASE_URL, ResponseCode} from './common.properties';
import {catchError, finalize, mergeMap, retry} from 'rxjs/operators';
import {HttpService} from './http.service';
import {Router} from '@angular/router';

/**
 * 拦截器
 * 拦截http请求，补充baseUrl,补充token
 * 还需要处理返回值结果，权限，过期，自动获取新token等
 */

// 对上一次url进行备份
let lastTimeUrl = '';

@Injectable()
export class HttpInterectors implements HttpInterceptor {

  constructor(private httpService: HttpService, private router: Router) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log(req);
    console.log(next);
    let newReq: HttpRequest<any> = req.clone({
      url: `${BASE_URL}${req.url}`,
    });
    /*此处设置额外的头部，token常用于登陆令牌*/
    if (req.url.indexOf('/sys/authorizing/login') === -1) {
      /*token数据来源自己设置，我常用localStorage存取相关数据*/
      newReq = newReq.clone({setHeaders: {Authorization: localStorage.getItem('token')}});
    }
    // send cloned request with header to the next handler.
    lastTimeUrl = newReq.url;
    const handle: Observable<HttpEvent<Response>> = next.handle(newReq);
    return handle.pipe(
      /*失败时重试2次，可自由设置*/
      retry(2),
      mergeMap(this.then.bind(this)),
      catchError(this.handleError),
      finalize<any>(() => console.log('结束'))
    );
  }

  private then(evt: HttpResponse<any> | HttpErrorResponse): Observable<any> {
    if (evt instanceof HttpResponse) {
      const xhr: HttpResponse<any> = evt as HttpResponse<any>;
      if (xhr.ok) {
        const status: string = xhr.body['status'];
        console.log(status + '  return code..');
        if (status === ResponseCode.RET_OK) {
          // 成功
          console.log('数据响应成功！');
          const clonedXhr = xhr.clone({
            body: evt.body
          });
          return of(clonedXhr);
        } else if (status === ResponseCode.NO_LOGIN) {
          // 没有登录
          console.log('您未登录');
        } else if (status === ResponseCode.AGAIN_TOKEN) {
          // 已经重新生成token,处理token，再次调用接口即可
          console.log('token过期，自动生成token');
          localStorage.setItem('token', xhr.body['datas'].token);
          // 再次调用接口
        } else if (status === ResponseCode.NO_PRESSION) {
          // 没有权限
          console.log('无权限访问');
        } else if (status === ResponseCode.RET_ERROR) {
          // 请求返回后台错误
          console.error('后台错误，错误信息:', xhr.body['message']);
        }
      }
    }
    return of(event);
  }


  /**
   * 获取错误详情
   * @param error
   */
  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
      // 判断如果是404，那么提示资源不存在
      if (error.status === 404) {
        console.log('访问的资源不存在');
      }
    }
    // return an observable with a user-facing error message
    return throwError(
      'Something bad happened; please try again later.');
  }
}

