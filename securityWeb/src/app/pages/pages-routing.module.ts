import {IndexComponent} from './index/index.component';
import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CommonModule} from '@angular/common';
import {PagesComponent} from './pages.component';
import {AuthGuard} from '../core/guard/auth-guard';

const routes: Routes = [
  {
    path: 'pages', component: PagesComponent,
    children: [
      {path: '', redirectTo: 'index', pathMatch: 'full'},
      {path: 'index', component: IndexComponent, canActivate: [AuthGuard]}
    ]
  }
];

@NgModule({
  imports: [CommonModule, RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PagesRoutingModule {
}
