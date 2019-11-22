import { Component, OnInit } from '@angular/core';
import { User } from '../../../models/user.interface';
import { Http } from '@angular/http';
import { JsonPipe } from '@angular/common';
import { Resource } from '../../../models/resource.interface';
import { ApiService } from '../../../service/api.service';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Message } from 'primeng/primeng';
import { InputTextareaModule } from 'primeng/primeng';
import { ChipsModule } from 'primeng/primeng';
import { FileUploadModule } from 'primeng/primeng';
import { SelectItem } from 'primeng/primeng';

@Component({
  selector: 'app-my-resources',
  templateUrl: './my-resources.component.html',
  styleUrls: ['./my-resources.component.less']
})

export class MyResourcesComponent implements OnInit {

  resourceToInsert: Resource = new ResourceToInsert();
  photo: Photo = { photoString: null };
  // tslint:disable-next-line:no-inferrable-types
  private base64textString: string = '';
  // tslint:disable-next-line:no-inferrable-types
  visible: boolean = true;
  stacked: boolean;
  displayDialog: boolean;
  displayDialogAdd: boolean;
  public title: string;
  public description: string;
  resource: Resource = new AddResource();
  public submitted: boolean;
  selectedResource: Resource;
  public resourceForm: FormGroup;
  public addResource: FormGroup;
  newResource: boolean;
  serializedForm: any;
  resources: Resource[];
  msgs: Message[] = [];

  categoryId: number;
  categories: SelectItem[];
  // idCategory: number;
  ID;

  constructor(private formBuilder: FormBuilder, private apiService: ApiService) { }

  ngOnInit() {
    this.apiService.get('user/' + localStorage.getItem('idUser') + '/resource').subscribe(response => this.resources = response);
    console.log(this.resources);
    this.resourceForm = this.formBuilder.group({
      title: ['', [<any>Validators.required, <any>Validators.pattern('[A-Z]{1,1}[a-z]{1,}')]],
      type: ['', [<any>Validators.required, <any>Validators]],
      shortDescription: ['', [<any>Validators.required, <any>Validators.pattern('[A-Z]{1,1}[a-z]{5,}')]],
      idCategory: ['', [<any>Validators.required, <any>Validators.pattern('[1-5]{1,1}')]],
      tags: [, ],
      idResource: [, ],
      photo: [, ]
    });

    this.addResource = this.formBuilder.group({
      title: ['', [<any>Validators.required, <any>Validators.pattern('[A-Z]{1,1}[a-z]{1,}')]],
      idCategory: ['', [<any>Validators.required, <any>Validators.pattern('^[1-5]{1,1}$')]],
      shortDescription: ['', [<any>Validators.required, <any>Validators.pattern('[A-Z]{1,1}[a-z]{5,}')]],
      type: ['', [<any>Validators.required, <any>Validators]],
      tags: [, ],
      idResource: [, ],
      photo: [, ]
    });

    this.apiService.get('category').subscribe(categories => this.categories = this.toSelectItems(categories));
  }


  toSelectItems(cateogries: Category[]): SelectItem[] {
    const items: SelectItem[] = [];
    items.push({ label: 'No (Category)', value: null });
    for (const cat of cateogries) {
      items.push({ label: cat.name, value: cat.idCategory });
    }
    return items;
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

    const resourcePhoto: Resource = new PrimeResourcePhoto();
    resourcePhoto.idResource = this.resource.idResource;

    resourcePhoto.photo = this.base64textString;
    this.apiService.put('resource/' + this.resource.idResource + '/picture', resourcePhoto).subscribe(

      data => {
        console.log('success!', data);
        console.log('Picture in B64 is:', resourcePhoto);

        this.visible = false;
        setTimeout(() => this.visible = true, 0);
        this.displayDialog = false;
        this.apiService.get('user/' + localStorage.getItem('idUser') + '/resource').subscribe(response => this.resources = response);
      },
      error => {
      this.msgs = [];
        console.error('could not put because', error);
      }
    );
  }

  toggle() {
    this.stacked = !this.stacked;
  }

  showDialogToAdd() {
    this.newResource = true;
    this.resource = new AddResource();
    this.displayDialog = true;
  }

  displayDialogAddResource() {
    this.newResource = true;
    this.resource = new AddResource();
    this.categoryId = null;
    this.displayDialogAdd = true;
  }

  saveNewResource() {
    this.resourceToInsert = this.addResource.value;
    this.resourceToInsert.photo = this.photo.photoString;
    this.resourceToInsert.idCategory = this.categoryId;
    console.log(this.resourceToInsert);
    this.apiService.post('user/' + localStorage.getItem('idUser') + '/resource', this.resourceToInsert).subscribe(
      data => {
        console.log('success!', data);
        this.resources[this.findSelectedResourceIndex()] = this.resource;
        this.visible = false;
        setTimeout(() => this.visible = true, 0);
        this.displayDialogAdd = false;
        this.addResource.reset();
        this.apiService.get('user/' + localStorage.getItem('idUser') + '/resource').subscribe(response => this.resources = response);
      },
      error => {
      this.msgs = [];
        console.error('could not put because', error);
      }
    );
    this.photo.photoString = null;
    this.categoryId = null;
  }

  auxHandleFileSelect(evt) {
    const files = evt.target.files;
    const file = files[0];
    if (files && file) {
      const reader = new FileReader();
      reader.onload = this._auxHandleReaderLoaded.bind(this);
      reader.readAsBinaryString(file);
      console.log(this.photo.photoString);
    }
  }

  _auxHandleReaderLoaded(readerEvt) {
    const binaryString = readerEvt.target.result;
    this.base64textString = btoa(binaryString);
    // this.resourceToInsert.photo = this.base64textString;
    this.photo.photoString = this.base64textString;
    console.log(this.photo.photoString);

  }
  saveEditedResource() {
    this.resourceForm.value.idCategory = this.categoryId;
    console.log(this.resourceForm.value);
    console.log(this.categoryId);

    this.apiService.put('resource/' + this.resource.idResource, this.resourceForm.value).subscribe(
      data => {
        console.log('success!', data);
        this.resources[this.findSelectedResourceIndex()] = this.resource;
        this.visible = false;
        setTimeout(() => this.visible = true, 0);
        this.displayDialog = false;
        this.resourceForm.reset();
        this.apiService.get('user/' + localStorage.getItem('idUser') + '/resource').subscribe(response => this.resources = response);
      },
      error => {
      this.msgs = [];
        console.error('could not put because', error);
      }
    );
    this.categoryId = null;
  }

  delete() {
    const index = this.findSelectedResourceIndex();
    this.resources = this.resources.filter((val, i) => i !== index);
    this.resource = null;
    this.displayDialog = false;
  }

  onRowSelect(event) {
    this.newResource = false;
    this.resource = this.cloneresource(event.data);
    this.categoryId = this.resource.idCategory;
    this.displayDialog = true;
  }

  cancel() {
    this.displayDialog = false;
    this.displayDialogAdd = false;
  }

  cloneresource(c: Resource): Resource {
    const resource = new AddResource();
    // tslint:disable-next-line:forin
    for (const prop in c) {
      resource[prop] = c[prop];
    }
    return resource;
  }

  findSelectedResourceIndex(): number {
    return this.resources.indexOf(this.selectedResource);
  }

}

class AddResource implements Resource {
  // tslint:disable-next-line:max-line-length
  constructor(public idResource?, public title?, public type?, public shortDescription?, public idCategory?, public tags?, public photo= null) {}
}

class PrimeResourcePhoto implements Resource {
  constructor(public idResource?, public photo?) { }
}

class ResourceToInsert implements Resource {
  constructor(public title?, public idUser?, public type?, public shortDescription?, public idCategory?, public tags?, public photo?) { }
}

class Photo {
  photoString: string;
}


export interface Category {
  idCategory: number;
  name: string;
}
