import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {OverlayModule} from '@angular/cdk/overlay';
import {NzButtonModule, NzCardModule, NzFormModule, NzIconModule, NzInputModule} from 'ng-zorro-antd';

@NgModule({
  imports: [
    CommonModule,
    NzCardModule,
    NzFormModule,
    NzIconModule,
    NzButtonModule,
    NzInputModule,
    OverlayModule,
  ],
  exports: [
    CommonModule,
    NzCardModule,
    NzFormModule,
    NzInputModule,
    NzIconModule,
    NzButtonModule,
    OverlayModule,
  ],
  declarations: []
})
export class SharedModule {

}
