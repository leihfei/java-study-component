import {LoginRoutingModule} from './login-routing.module';
import {SharedModule} from './../../shared/shared.module';
import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {LoginComponent} from './login.component';
import {LoginService} from './login.service';

@NgModule({
  imports: [
    SharedModule,
    LoginRoutingModule
  ],
  declarations: [
    LoginComponent
  ], schemas: [
    CUSTOM_ELEMENTS_SCHEMA
  ],
  providers: [
    LoginService
  ]
})
export class LoginModule {
}
