import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { ForgetPassword } from '../../../models/forgetPassword.interface';
import { ButtonModule } from 'primeng/primeng';
import { ApiService } from '../../../service/api.service';
import { Message } from 'primeng/primeng';
import { User } from '../../../models/user.interface';
import {Subscription} from 'rxjs/Rx';

@Component({
  selector: 'app-forget-password',
  templateUrl: './forget-password.component.html',
  styleUrls: ['./forget-password.component.less'],
})

export class ForgetPasswordComponent implements OnInit {
  public loading = false;
  public myForm: FormGroup;
  msgs: Message[] = [];
  public submitted: boolean;
  displayDialog: boolean;
  busy:  Subscription;
  constructor(private formBuilder: FormBuilder, private apiService: ApiService) {}

  ngOnInit() {
    this.myForm = this.formBuilder.group({
      username: ['', [<any>Validators.required, <any>Validators.pattern('^[U0-9]{5,5}$')]],
      // tslint:disable-next-line:max-line-length
      email: ['', [<any>Validators.required, <any>Validators.pattern('^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$')]],
    });
  }

  sendEmail(model: User, isValid: boolean) {
    this.loading = true;
    this.submitted = true;
    console.log(model, isValid);
    const formObj = this.myForm.value;
    console.log('The JSON file to be sent to server is:' + JSON.stringify(formObj));

    this.busy = this.apiService.post('forgot?username=' + formObj.username + '&email=' + formObj.email).subscribe(
      data => {
        this.loading = false;
        this.displayDialog = true;
        console.log('success!', data);
      },
      error => {
        this.loading = false;
        this.msgs = [];
        this.msgs.push({ severity: 'error', detail: 'Username / E-mail combination invalid' });
        console.error('could not post because', error);
      }
    );
  }
}
