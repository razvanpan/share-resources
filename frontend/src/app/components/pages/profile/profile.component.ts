import { User } from '../../../models/user.interface';
import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../../service/api.service';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { GrowlModule } from 'primeng/primeng';
import  { Message, SelectItem }  from  'primeng/primeng';
import { environment } from '../../../../environments/environment';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.less']
})

export class ProfileComponent implements OnInit {
  private base64textString: String = "";

  displayDialog: boolean;
  displayDialogPassword: boolean;
  public myForm: FormGroup;
  public changePasswordForm: FormGroup;
  user: UserProfile = new PrimeUser();
  userchange;
  userchangePassword;
  msgs: Message[];
  uploadedFiles: any[] = [];

  userform: FormGroup;
  submitted: boolean;
  genders: SelectItem[];
  description: string;
  success: boolean;
  visible: boolean = true;

  constructor(private _fb: FormBuilder, private apiService: ApiService) { }


  onUpload(event) {
    for (const file of event.files) {
      this.uploadedFiles.push(file);
    }
    this.msgs = [];
    this.msgs.push({ severity: 'info', summary: 'File Uploaded', detail: '' });
  }

  ngOnInit() {
    this.apiService.get('user/' + localStorage.getItem('idUser')).subscribe(users => this.user = users);

    this.myForm = this._fb.group({
      firstName: ['', [<any>Validators.required, <any>Validators.pattern('[A-Z]{1,1}[a-z]{1,}')]],
      lastName: ['', [<any>Validators.required, <any>Validators.pattern('[A-Z]{1,1}[a-z]{1,}')]],
      username: ['', [<any>Validators.required, <any>Validators.pattern('^[U0-9]{5,5}$')]],
      // tslint:disable-next-line:max-line-length
      email: ['', [<any>Validators.required, <any>Validators.pattern('^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$')]],
      phone: ['', [<any>Validators.required, <any>Validators.pattern('[0]{1,1}[0-9]{9,9}')]],
      shortDescription: [,]
    });

    this.changePasswordForm = this._fb.group({
      oldPassword: [,],
      // tslint:disable-next-line:max-line-length
      newPassword: ['', [<any>Validators.required, <any>Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,}')]],
      repetedNewPassword: ['', [<any>Validators.required, <any>Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,}')]]
    });

  }

  handleFileSelect(evt) {
    const files = evt.target.files;
    const file = files[0];
    if (files && file) {
      const reader = new FileReader();
      reader.onload = this._handleReaderLoaded.bind(this);
      reader.readAsBinaryString(file);
      console.log('Picture in B64 is:', reader);
    }
  }

  _handleReaderLoaded(readerEvt) {
    const binaryString = readerEvt.target.result;
    this.base64textString = btoa(binaryString);

    const userPhoto: UserProfile = new PrimeUserPhoto();
    userPhoto.idUser = localStorage.getItem('idUser');

    userPhoto.photo = this.base64textString;
    this.apiService.put('user/' + localStorage.getItem('idUser') + '/picture', userPhoto).subscribe(

      data => {
        console.log('success!', data);
        console.log('Picture in B64 is:', userPhoto);

        this.visible = false;
        setTimeout(() => this.visible = true, 0);
        this.displayDialog = false;
        this.apiService.get('user/' + localStorage.getItem('idUser')).subscribe(users => this.user = users);
      },
      error => {
      this.msgs = [];
        console.error('could not put because', error);
      }
    );
  }

  edit() {
    this.displayDialog = true;
    this.userchange = new PrimeUser(this.user.firstName, this.user.lastName,
      this.user.username, this.user.email, this.user.shortDescription, this.user.phone);
  }

  changePassword() {
    this.displayDialogPassword = true;
    this.userchangePassword = new PrimeUserPassword(this.user.password);
  }

  cancel() {
    this.displayDialog = false;
  }

  cancelPopUpPassword() {
    this.displayDialogPassword = false;
  }

  save(value: any) {
    console.log(this.myForm.value);
    this.apiService.put('user/' + localStorage.getItem('idUser'), this.myForm.value).subscribe(
      data => {
        console.log('success!', data);
        this.visible = false;
        setTimeout(() => this.visible = true, 0);
        this.displayDialog = false;
        this.myForm.reset();
        this.apiService.get('user/' + localStorage.getItem('idUser')).subscribe(users => this.user = users);
      },
      error => {
      this.msgs = [];
        console.error('could not put because', error);
      }
    );

  }

  savePassword(model: User, isValid: boolean) {
    console.log(this.userchangePassword);

    this.submitted = true;
    console.log(model, isValid);
    const formObj = this.changePasswordForm.value;
    console.log('The JSON file to be sent to server is:' + JSON.stringify(formObj));
    console.log(this.userchangePassword);

    if (this.userchangePassword.newPassword !== this.userchangePassword.repetedNewPassword) {
      this.msgs = [];
      this.msgs.push({ severity: 'error', detail: 'New Password and Repeat New Passowrd  must be the same' });
    } else {
      this.apiService.put('user/' + localStorage.getItem('idUser') + '/password', this.userchangePassword).subscribe(
        data => {
          console.log('success!', data);
          this.visible = false;
          setTimeout(() => this.visible = true, 0);
          this.displayDialogPassword = false;
        },
        error => {
        this.msgs = [];
          console.error('could not put because', error);
        }
      );
    }

  }

}

export interface UserProfile {
  idUser?;
  firstName?;
  lastName?;
  username?;
  email?;
  shortDescription?;
  phone?;
  password?;
  photo?;
}

class PrimeUser implements UserProfile {

  constructor(public firstName?, public lastName?, public username?, public email?, public shortDescription?, public phone?) { }
}

class PrimeUserPassword implements UserProfile {

  constructor(public password?) { }
}

class PrimeUserPhoto implements UserProfile {
  constructor(public idUser?, public photo?) { }
}
