import { Routes, RouterModule, Router } from '@angular/router';
import { NgModule } from '@angular/core';

import { AuthGuard } from './service/authGuard.service';
import { BrowsingComponent } from './components/pages/browsing/browsing.component';
import { ChangeprofileComponent } from './components/pages/changeprofile/changeprofile.component';
import { ForgetPasswordComponent} from './components/pages/forget-password/forget-password.component';
import { HomepageComponent } from './components/pages/homepage/homepage.component';
import { LoginComponent } from './components/pages/login/login.component';
import { MyResourcesComponent } from './components/pages/my-resources/my-resources.component';
import { ProfileComponent } from './components/pages/profile/profile.component';
import { RegisterComponent } from './components/pages/register/register.component';
import { ResourceDetailComponent } from './components/pages/resource-detail/resource-detail.component';
import { ChangepasswordComponent} from './components/pages/changepassword/changepassword.component';


const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'forget-password', component: ForgetPasswordComponent },
  { path: 'changepassword', component: ChangepasswordComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'browse', component: BrowsingComponent, canActivate: [AuthGuard] },
  { path: 'change-profile', component: ChangeprofileComponent, canActivate: [AuthGuard] },
  { path: 'home', component: HomepageComponent, canActivate: [AuthGuard] },
  { path: 'my-resources', component: MyResourcesComponent, canActivate: [AuthGuard] },
  { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard] },
  { path: 'resource-details', component: ResourceDetailComponent, canActivate: [AuthGuard] },
  {
    path: '',
    children: []
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
  constructor(router: Router) {}
}
