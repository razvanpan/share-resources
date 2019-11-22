import { Injectable } from '@angular/core';
import { Http, Headers, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { Router } from '@angular/router';
import { ApiService } from './api.service';
import { ReplaySubject } from 'rxjs/ReplaySubject';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';

@Injectable()
export class AuthenticationService {
  confirm: boolean;
  public loading = false;
  private isAuthenticatedSubject = new ReplaySubject<boolean>(1);
  public isAuthenticated = this.isAuthenticatedSubject.asObservable();

  constructor(private router: Router, private apiService: ApiService) { }

  login(username: string, password: string) {
    this.apiService.post('login', { username: username, password: password }).subscribe(response => {
      this.loading = false;
      localStorage.setItem('token', response.token);
      localStorage.setItem('idUser', response.idUser);
      // Set isAuthenticated to true
      this.isAuthenticatedSubject.next(true);
      this.router.navigate(['home']);
      this.confirm = true;
    }, err => {
      this.loading = false;
      if (err.status === 401) {
        this.loading = false;
        this.router.navigate(['login']);

      }
      this.confirm = false;
    });
    console.log(this.confirm);
    if (this.confirm) {
      return false;
    } else {
      return true;
    }

  }

  logout(): void {
    // clear token remove user from local storage to log user out
    // Set auth status to false
    this.isAuthenticatedSubject.next(false);
    localStorage.removeItem('token');
    localStorage.removeItem('idUser');
    this.router.navigate(['/login']);
  }
}
