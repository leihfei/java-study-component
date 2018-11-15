import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {HttpResponse} from '../../domain/response/http-response';
import {LoginService} from './login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.less']
})
export class LoginComponent implements OnInit {
  validateForm: FormGroup;

  constructor(private fb: FormBuilder, private loginService: LoginService, private router: Router) {
  }

  ngOnInit() {
    this.validateForm = this.fb.group({
      userName: [null, [Validators.required]],
      password: [null, [Validators.required]],
      remember: [true]
    });
  }

  submitForm(): void {
    // for (const i in this.validateForm.controls) {
    //   this.validateForm.controls[i].markAsDirty();
    //   this.validateForm.controls[i].updateValueAndValidity();
    // }
    // 提交登录
    this.loginService.login({username: 'cm', password: '1234567', rememberMe: true})
      .subscribe((data: HttpResponse) => {
        // 走到这里，其实说明数据绝对返回的是200,取出数据，然后跳转
        localStorage.setItem('token', data.datas.token);
        // 保存菜单
        sessionStorage.setItem('menus', data.datas.menus);
        // 保存用户
        sessionStorage.setItem('user', JSON.stringify(data.datas.user));
        this.router.navigate(['/pages/index']);
      });
  }

  testInterface() {
    this.loginService.test({})
      .subscribe(data => {
        console.log('部门树返回结果');
        console.log(data);
      });
  }


}
