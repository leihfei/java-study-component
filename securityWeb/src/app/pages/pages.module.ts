import { SharedModule } from './../shared/shared.module';
import { NgModule } from '@angular/core';
import { PagesComponent } from './pages.component';
import { CoreModule } from '../core/core.module';
import { PagesRoutingModule } from './pages-routing.module';
import { IndexComponent } from './index/index.component';
import { LoginModule } from './login/login.module';

@NgModule({
  imports: [
    CoreModule,
    SharedModule,
    LoginModule,
    PagesRoutingModule,
  ],
  declarations: [PagesComponent, IndexComponent]
})
export class PagesModule { }
