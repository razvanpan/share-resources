import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../../service/api.service';
import { RegRes } from '../../../models/reg-resources.interface';
import { Router } from '@angular/router';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.less']
})
export class HomepageComponent implements OnInit {
  title = 'app';
  data: any;

  regResource: RegRes[];
  cols: any[];
  cols1: any[];
  constructor(private apiService: ApiService, private router: Router) {
    this.data = {
      labels: ['A', 'B', 'C'],
      datasets: [
        {
          data: [300, 50, 100],
          backgroundColor: [
            '#FF6384',
            '#36A2EB',
            '#FFCE56'
          ],
          hoverBackgroundColor: [
            '#FF6384',
            '#36A2EB',
            '#FFCE56'
          ]
        }]
    };
  }
  ngOnInit() {
    this.cols1 = [
      { field: 'fullname', header: 'Name' },
      { field: 'email', header: 'E-mail' }
    ];
    this.cols = [
      { field: 'title', header: 'Title' },
      { field: 'category', header: 'Category' },
      { field: 'fullname', header: 'Owner' },
      { field: 'email', header: 'E-mail' }
    ];
  }
}
