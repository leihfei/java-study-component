import {NgModule, Optional, SkipSelf} from '@angular/core';
import {HeaderComponent} from '../common/header/header.component';
import {FooterComponent} from '../common/footer/footer.component';
import {SidenavComponent} from '../common/sidenav/sidenav.component';
import {HttpClientModule} from '@angular/common/http';
import {DomSanitizer} from '@angular/platform-browser';
import {SharedModule} from '../shared/shared.module';


@NgModule({
  imports: [
    SharedModule,
    HttpClientModule,
  ],
  exports: [
    HeaderComponent, FooterComponent, SidenavComponent
  ],
  declarations: [HeaderComponent, FooterComponent, SidenavComponent,
  ],
  providers: []
})
export class CoreModule {

  constructor(@Optional() @SkipSelf() parent: CoreModule, ds: DomSanitizer
  ) {
    if (parent) {
      throw new Error('模块已存在，不允许重复加载!');
    }
  }
}
