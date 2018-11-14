import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {NgZorroAntdModule, NZ_I18N, NZ_ICON_DEFAULT_TWOTONE_COLOR, NZ_ICONS, zh_CN} from 'ng-zorro-antd';
import {registerLocaleData} from '@angular/common';
import zh from '@angular/common/locales/zh';
import {PagesModule} from './pages/pages.module';
import {AppRoutingModule} from './app-routing.module';
import {IconDefinition} from '@ant-design/icons-angular';
import {AccountBookFill, AlertFill, AlertOutline} from '@ant-design/icons-angular/icons';


// 引入你需要的图标，比如你需要 fill 主题的 AccountBook Alert 和 outline 主题的 Alert，推荐 ✔️
const icons: IconDefinition[] = [AccountBookFill, AlertOutline, AlertFill];
registerLocaleData(zh);

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgZorroAntdModule,
    PagesModule,
    AppRoutingModule
  ],
  providers: [{provide: NZ_I18N, useValue: zh_CN},
    {provide: NZ_ICON_DEFAULT_TWOTONE_COLOR, useValue: '#00ff00'}, // 不提供的话，即为 Ant Design 的主题蓝色
    {provide: NZ_ICONS, useValue: icons}
  ],
  bootstrap: [AppComponent]
})

export class AppModule {
}
