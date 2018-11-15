import {ChangeDetectorRef, Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  @Output()
  private toggle = new EventEmitter<void>();

  @Output()
  private toggleTheme = new EventEmitter<boolean>();

  constructor(private changeDetectorRef: ChangeDetectorRef) {

  }

  ngOnInit() {
  }


  openSidenav() {
    this.toggle.emit();
  }

  onChange(checked: boolean) {
    this.toggleTheme.emit(checked);
  }

}
