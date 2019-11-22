import { Component, OnInit } from '@angular/core';
import { Login } from '../../../models/login-user.interface';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { ButtonModule } from 'primeng/primeng';
import { AuthenticationService } from '../../../service/authentication.service';
import { Router } from '@angular/router';
import { Message } from 'primeng/primeng';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.less']
})
export class LoginComponent implements OnInit {
  public loading = false;
  model: any = {};
  error = '';
  public form: FormGroup;
  public loginForm: FormGroup;
  // tslint:disable-next-line:no-inferrable-types
  errMesg: boolean = true;
  constructor(private _fb: FormBuilder, private router: Router, private authenticationService: AuthenticationService) { }
  msgs: Message[] = [];

  ngOnInit() {
    this.form = this._fb.group({
      username: ['', ],
      password: ['', ]
    });
  }

  login() {
    this.loading = true;
    this.errMesg = this.authenticationService.login(this.model.username, this.model.password);

    setTimeout(() => {
      if (this.errMesg === true) {
        this.loading = false;
        this.msgs = [];
        this.msgs.push({ severity: 'success', detail: 'Login Succesfull' });
        console.log('Login Succesfull');
      }
    },
      10);

    setTimeout(() => {
      if (this.errMesg === false) {
        this.loading = false;
        this.msgs = [];
        this.msgs.push({ severity: 'error', detail: 'Unauthorized' });
        console.log('Unauthorized');
      }
    },
      50);
  }

}
