import { Component, OnInit } from '@angular/core';
import { FieldsetModule } from 'primeng/primeng';
import { Router } from '@angular/router';
@Component({
  selector: 'app-resource-detail',
  templateUrl: './resource-detail.component.html',
  styleUrls: ['./resource-detail.component.less']
})
export class ResourceDetailComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
    if (!localStorage.getItem('currentUser')) {
      this.router.navigate(['/login']);
    }
  }

}
