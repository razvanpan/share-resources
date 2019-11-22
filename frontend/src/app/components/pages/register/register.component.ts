import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { User } from '../../../models/user.interface';
import { Http } from '@angular/http';
import { JsonPipe } from '@angular/common';
import { ApiService } from '../../../service/api.service';
import { PasswordModule } from 'primeng/primeng';
import { Message } from 'primeng/primeng';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.less']
})
export class RegisterComponent implements OnInit {
  public loading = false;
  public myForm: FormGroup;
  public submitted: boolean;
  public events: any[] = [];
  msgs: Message[] = [];

  constructor(private _fb: FormBuilder, private apiService: ApiService, private http: Http, private router: Router) { }

  ngOnInit() {

    this.myForm = this._fb.group({
      firstName: ['', [<any>Validators.required, <any>Validators.pattern('[A-Z]{1,1}[a-z]{1,}')]],
      lastName: ['', [<any>Validators.required, <any>Validators.pattern('[A-Z]{1,1}[a-z]{1,}')]],
      username: ['', [<any>Validators.required, <any>Validators.pattern('^[U0-9]{5,5}$')]],
      // tslint:disable-next-line:max-line-length
      password: ['', [<any>Validators.required, <any>Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,}')]],
      email: ['', [<any>Validators.required, <any>Validators.pattern('^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$')]],
      phone: ['', [<any>Validators.required, <any>Validators.pattern('[0]{1,1}[0-9]{9,9}')]],
      shortDescription: [,],
    });
  }

  save(model: User, isValid: boolean) {
    this.loading = true;
    this.submitted = true;
    console.log(model, isValid);

    const formObj = this.myForm.value;
    const serializedForm = formObj;
    console.log('The JSON file to be sent to server is:' + serializedForm);
    this.apiService.post('register/', serializedForm).subscribe(
      data => {
        this.loading = false;
        this.msgs = [];
        this.router.navigate(['login']);

      },
      error => {
        this.loading = false;
        this.msgs = [];
        this.msgs.push({ severity: 'error', detail: 'Registration Failed, make sure your U-Number,E-mail and Phone are unique' });
        console.error('could not post because', error);
      }
    );
  }

}
