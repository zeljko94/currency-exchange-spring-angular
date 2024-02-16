import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppLayoutComponent } from './layout/app.layout.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { BalancesComponent } from './pages/balances/balances.component';
import { AuthGuard } from './guards/auth.guard';
import { SchedulerPageComponent } from './pages/scheduler-page/scheduler-page.component';
import { SettingsPageComponent } from './pages/settings-page/settings-page.component';
import { MyProfilePageComponent } from './pages/my-profile-page/my-profile-page.component';

const routes: Routes = [
  {
    path: 'login', component: LoginPageComponent 
  },
  { 
    path: '', component: AppLayoutComponent,
    children: [
      { path: 'login', component: LoginPageComponent },
      { path: 'home', component: HomePageComponent, canActivate: [AuthGuard] },
      { path: 'scheduler', component: SchedulerPageComponent, canActivate: [AuthGuard] },
      { path: 'balances', component: BalancesComponent, canActivate: [AuthGuard] },
      { path: 'settings', component: SettingsPageComponent, canActivate: [AuthGuard] },
      { path: 'my-profile', component: MyProfilePageComponent, canActivate: [AuthGuard] },
      { path: '', redirectTo: '/login', pathMatch: 'full' },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
