import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';

/**
 * 路由守卫
 */
@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private router: Router) {
  }

  /**
   *  检查是否登录
   * @param route
   * @param state
   */
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    const userStr = sessionStorage.getItem('user');
    console.log(userStr);
    if (userStr !== null) {
      return true;
    } else {
      this.router.navigate(['/login']);
    }
    return false;
  }

}
