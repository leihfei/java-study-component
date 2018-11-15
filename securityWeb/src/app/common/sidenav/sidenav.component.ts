import { Component, OnInit } from '@angular/core';
import { getDate} from 'date-fns';
@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.scss']
})
export class SidenavComponent implements OnInit {

  constructor() { }

  today = 'day';

  ngOnInit() {
    this.today = `day${getDate(new Date())}`;
  }

}
