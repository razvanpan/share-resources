// CORE
import { AppRoutingModule } from './app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule , NgModel} from '@angular/forms';
import { NgModule } from '@angular/core';
import { RouterModule,  Router, Routes } from '@angular/router';
import { ApiService } from './service/api.service';
import { HttpClientModule } from '@angular/common/http';
import { HttpModule } from '@angular/http';
import { Injectable } from '@angular/core';
import { BusyModule } from 'angular2-busy';
import { LoadingModule, ANIMATION_TYPES } from 'ngx-loading';

// PRIME NG
import { ButtonModule } from 'primeng/primeng';
import { ChartModule } from 'primeng/primeng';
import { DataTableModule, SharedModule } from 'primeng/primeng';
import { PaginatorModule } from 'primeng/primeng';
import { PasswordModule } from 'primeng/primeng';
import { DialogModule } from 'primeng/primeng';
import { FieldsetModule } from 'primeng/primeng';
import { FileUploadModule } from 'primeng/primeng';
import { GrowlModule } from 'primeng/primeng';
import { InputMaskModule } from 'primeng/primeng';
import { InputTextareaModule } from 'primeng/primeng';
import { InputTextModule } from 'primeng/primeng';
import { MenubarModule } from 'primeng/primeng';
import { MenuModule } from 'primeng/primeng';
import { MessagesModule } from 'primeng/primeng';
import { PanelModule } from 'primeng/primeng';
import {DropdownModule} from 'primeng/primeng';
import {SelectItem} from 'primeng/primeng';
import {ChipsModule} from 'primeng/primeng';

// MY
import { AppComponent } from './app.component';
import { AuthenticationService } from './././service/authentication.service';
import { AuthGuard } from './././service/authGuard.service';
import { BrowsingComponent } from './components/pages/browsing/browsing.component';
import { ChangeprofileComponent } from './components/pages/changeprofile/changeprofile.component';
import { ForgetPasswordComponent } from './components/pages/forget-password/forget-password.component';
import { HomepageComponent } from './components/pages/homepage/homepage.component';
import { LoginComponent } from './components/pages/login/login.component';
import { MyResourcesComponent } from './components/pages/my-resources/my-resources.component';
import { NavbarComponent } from './components/layout/navbar/navbar.component';
import { ProfileComponent } from './components/pages/profile/profile.component';
import { RegisterComponent } from './components/pages/register/register.component';
import { Resource } from './models/resource.interface';
import { ResourceDetailComponent } from './components/pages/resource-detail/resource-detail.component';
import { ChangepasswordComponent } from './components/pages/changepassword/changepassword.component';

@NgModule({
  declarations: [
    AppComponent,
    BrowsingComponent,
    ChangeprofileComponent,
    ForgetPasswordComponent,
    HomepageComponent,
    LoginComponent,
    MyResourcesComponent,
    NavbarComponent,
    ProfileComponent,
    RegisterComponent,
    ResourceDetailComponent,
    ChangepasswordComponent
  ],
  imports: [
    DropdownModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    BrowserModule,
    ButtonModule,
    ChartModule,
    DataTableModule,
    DialogModule,
    FieldsetModule,
    FileUploadModule,
    FormsModule,
    FormsModule,
    GrowlModule,
    HttpClientModule,
    HttpModule,
    InputMaskModule,
    InputTextareaModule,
    InputTextModule,
    MenubarModule,
    MenuModule,
    MessagesModule,
    PaginatorModule,
    PanelModule,
    PasswordModule,
    ReactiveFormsModule,
    SharedModule,
    ChipsModule,
    BusyModule,
    LoadingModule.forRoot({
      animationType: ANIMATION_TYPES.threeBounce,
      backdropBackgroundColour: 'rgba(255,255,255,0.1)',
      backdropBorderRadius: '4px',
      primaryColour: '#ffffff',
      secondaryColour: '#ffffff',
      tertiaryColour: '#ffffff'
  })
  ],
  providers: [ApiService, AuthenticationService, AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule {}
