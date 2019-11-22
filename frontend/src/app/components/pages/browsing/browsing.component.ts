import { Component, OnInit } from '@angular/core';
import { User } from '../../../models/user.interface';
import { Http } from '@angular/http';
import { JsonPipe } from '@angular/common';
import { ApiService } from '../../../service/api.service';
import { SelectItem } from 'primeng/primeng';

@Component({
  selector: 'app-browsing',
  templateUrl: './browsing.component.html',
  styleUrls: ['./browsing.component.less']
})

export class BrowsingComponent implements OnInit {
  selectedResource: Resource = {
    'idResource': '',
    'title': '',
    'fullNameUser': '',
    'type': '',
    'shortDescription': '',
    'categoryName': '',
    'tags': '',
    'photo': '',
    'email': '',
    'phone': ''
  };
  displayDialog: boolean;
  resources: Resource[];
  categories: SelectItem[];
  stacked: boolean;
  data: any;
  singlePost: any = {
    email: '',
    password: ''
  };
  // tslint:disable-next-line:no-inferrable-types
  visible: boolean = true;
  categoryId: number;

  constructor(private apiService: ApiService, private http: Http) {
  }

  ngOnInit() {
    this.apiService.get('resource').subscribe(resources => this.resources = resources);
    this.apiService.get('category').subscribe(categories => this.categories = this.toSelectItems(categories));
  }

  toSelectItems(cateogries: Category[]): SelectItem[] {
    const items: SelectItem[] = [];
    items.push({ label: 'No Filter (Category)', value: null });
    for (const cat of cateogries) {
      items.push({ label: cat.name, value: cat.idCategory });
    }
    return items;
  }

  onRowSelect(event) {
    this.displayDialog = true;
    // tslint:disable-next-line:max-line-length
    this.apiService.get('resource/' + this.selectedResource.idResource).subscribe(resourceDetaild => this.selectedResource = resourceDetaild);
    console.log(this.selectedResource);
  }

  cancel() {
    this.displayDialog = false;
  }

  toggle() {
    this.stacked = !this.stacked;
  }

  performSearch(searchTerm: HTMLInputElement, categoryId: number): void {
    console.log('Selected category: ' + categoryId);
    console.log('Selected search term: ' + searchTerm.value);

    // tslint:disable-next-line:no-inferrable-types
    let url: string = 'resource';
    // tslint:disable-next-line:max-line-length
    url = url + this.genrateUrl([new QueryParam(searchTerm != null && searchTerm.value.length > 0, 'title', searchTerm.value), new QueryParam(categoryId > 0, 'idcategory', categoryId)]);
    console.log(url);

    this.apiService.get(url).subscribe(
      (resources) => {
        this.resources = resources;
        setTimeout(() => this.visible = true, 0);
      }
    );
    console.log(`User entered: ${searchTerm.value} ${categoryId}`);
  }

  genrateUrl(params: QueryParam[]): string {
    // tslint:disable-next-line:no-inferrable-types
    let url: string = '';
    // tslint:disable-next-line:no-inferrable-types
    let first: boolean = true;
    for (const param of params) {
      if (first && param.valid) {
        url += '?' + param.name + '=' + param.value;
        first = false;
      } else {
        if (!first && param.valid) {
          url += '&' + param.name + '=' + param.value;
        }
      }
    }
    return url;
  }

}

export interface Resource {
  idResource;
  title;
  fullNameUser;
  type;
  shortDescription;
  categoryName;
  tags;
  photo;
  email;
  phone;
}
export interface Category {
  idCategory: number;
  name: string;
}

export class QueryParam {
  valid: boolean;
  name: string;
  value: any;

  public constructor(valid: boolean, name: string, value: any) {
    this.valid = valid;
    this.name = name;
    this.value = value;
  }
}
