import { Injectable } from '@angular/core';
import { AuthenticationService } from './authentication.service';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { ApiService } from './api.service';

@Injectable()
export class AuthGuard implements CanActivate {
  constructor(
    private router: Router,
    private authService: AuthenticationService,
    private apiService: ApiService

  ) { }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> {
    return new Observable<boolean>(observer => {
      let authcheck = false;
      console.log('Checking Auth');
      if (localStorage.getItem('token') === null) {
        this.router.navigate(['login']);
        observer.next(false);
      } else {
        this.authService.isAuthenticated.subscribe({
          next: (value) => {
            authcheck = true;
            if (value) {
              observer.next(true);
            } else {
              observer.next(false);
            }
          }
        });
        if (!authcheck) {
          this.apiService.get('checkIsAuthenticated').subscribe(response => {
            observer.next(true);
          }, err => {
            this.router.navigate(['login']);
            observer.next(false);
          });
        }
      }
    });

  }
}
