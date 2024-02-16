import { NgModule } from '@angular/core';

import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppLayoutModule } from './layout/app.layout.module';
import { PasswordModule } from "primeng/password";
import { CheckboxModule } from 'primeng/checkbox';
import { TableModule } from 'primeng/table';
import { DialogModule } from 'primeng/dialog';
import { FileUploadModule } from 'primeng/fileupload';
import { ToolbarModule } from 'primeng/toolbar';
import { ToastModule } from 'primeng/toast';
import { InputTextModule } from 'primeng/inputtext';
import { DropdownModule } from 'primeng/dropdown';
import { MenuModule } from 'primeng/menu';
import { ChartModule } from 'primeng/chart';
import { DataViewModule } from 'primeng/dataview';
import { RatingModule } from 'primeng/rating';
import { PaginatorModule } from 'primeng/paginator';
import { SplitButtonModule } from 'primeng/splitbutton';
import { MessagesModule } from 'primeng/messages';
import { PickListModule } from 'primeng/picklist';
import { CardModule } from 'primeng/card';
import { ProgressSpinnerModule } from 'primeng/progressspinner';

import { AppComponent } from './app.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { AuthService } from './services/auth.service';
import { UsersService } from './services/users.service';
import { MessageService } from 'primeng/api';
import { HttpClientModule } from '@angular/common/http';
import { BalancesComponent } from './pages/balances/balances.component';
import { SchedulerPageComponent } from './pages/scheduler-page/scheduler-page.component';
import { LoadingOverlayComponent } from './components/loading-overlay/loading-overlay.component';
import { SettingsPageComponent } from './pages/settings-page/settings-page.component';
import { MyProfilePageComponent } from './pages/my-profile-page/my-profile-page.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    HomePageComponent,
    BalancesComponent,
    SchedulerPageComponent,
    LoadingOverlayComponent,
    SettingsPageComponent,
    MyProfilePageComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    AppLayoutModule,
    PasswordModule,
    CheckboxModule,
    TableModule,
    DialogModule,
    FileUploadModule,
    ToolbarModule,
    ToastModule,
    InputTextModule,
    DropdownModule,
    MenuModule,
    ChartModule,
    DataViewModule,
    RatingModule,
    PaginatorModule,
    InputTextModule,
    PickListModule,
    CardModule,
    SplitButtonModule,
    MessagesModule,
    ProgressSpinnerModule
  ],
  providers: [
    MessageService,
    AuthService,
    UsersService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
