import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/primeng';
import { Router } from '@angular/router';
import { AppComponent } from '../../../../app/app.component';
import { AuthenticationService } from '../../../service/authentication.service';
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.less']
})
export class NavbarComponent implements OnInit {
  items: MenuItem[];
  constructor(private authService: AuthenticationService) { }

  ngOnInit() {
    this.items = [
      {
        label: 'Home',
        icon: 'fa fa-home',
        routerLink: '/home',
      },
      {
        label: 'Browse',
        icon: 'fa-search',
        routerLink: '/browse',
      },
      {
        icon: 'fa-user-circle-o ',
        items: [
          {
            label: 'My profile',
            icon: 'fa-user-o ',
            routerLink: '/profile'
          },
          {
            label: 'My resources',
            icon: 'fa-book',
            routerLink: '/my-resources'
          },
          {
            label: 'Log out',
            icon: 'fa-sign-out',
            command: (event) => {
              this.authService.logout();
            }
          }
        ]
      },
    ];
  }
}
