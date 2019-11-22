import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { User } from '../../../models/user.interface';
import { ApiService } from '../../../service/api.service';
import { Message } from 'primeng/primeng';

@Component({
  selector: 'app-changepassword',
  templateUrl: './changepassword.component.html',
  styleUrls: ['./changepassword.component.less']
})
export class ChangepasswordComponent implements OnInit {

  public myForm: FormGroup;
  displayDialog: boolean;
  public submitted: boolean;
  msgs: Message[] = [];
  constructor(private formBuilder: FormBuilder, private apiService: ApiService) { }

  ngOnInit() {
    this.myForm = this.formBuilder.group({
      // tslint:disable-next-line:max-line-length
      newpassword: ['', [<any>Validators.required, <any>Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,}')]],
      repeatnewpassword: ['', [<any>Validators.required, <any>Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,}')]],
    });
  }

  submit(model: User, isValid: boolean) {
    this.displayDialog = true;
    this.submitted = true;
    console.log(model, isValid);
    const formObj = this.myForm.value;
    console.log('The JSON file to be sent to server is:' + JSON.stringify(formObj));

    if (formObj.newpassword !== formObj.repeatnewpassword) {
      this.msgs = [];
      this.msgs.push({ severity: 'error', detail: 'Fields must be the same' });
    } else {
      this.apiService.post('reset?password=' + formObj.newpassword).subscribe(
        data => {
          console.log('success!', data);
        },
        error => {
          console.error('could not post because', error);
        }
      );
    }
  }

}
