import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'app-changeprofile',
  templateUrl: './changeprofile.component.html',
  styleUrls: ['./changeprofile.component.less']
})
export class ChangeprofileComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
    if (!localStorage.getItem('currentUser')) {
      this.router.navigate(['/login']);
    }
  }

}
