import {IndexComponent} from './index/index.component';
import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CommonModule} from '@angular/common';
import {PagesComponent} from './pages.component';

const routes: Routes = [
  {
    path: 'pages', component: PagesComponent,
    children: [
      {path: '', redirectTo: 'index', pathMatch: 'full'},
      {path: 'index', component: IndexComponent}
    ]
  }
];

@NgModule({
  imports: [CommonModule, RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PagesRoutingModule {
}
