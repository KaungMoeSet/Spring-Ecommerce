import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {NavigationComponent} from './components/navigation/navigation.component';
import {RegisterComponent} from './components/register/register.component';
import {ReactiveFormsModule} from "@angular/forms";
import {NotFoundComponent} from './components/not-found/not-found.component';
import { HomePageComponent } from './page/home-page/home-page.component';
import { LogoutComponent } from './components/logout/logout.component';
import { LoginComponent } from './components/login/login.component';
import { CartComponent } from './components/cart/cart.component';
import { OrderComponent } from './components/order/order.component';
import { ProfileComponent } from './components/profile/profile.component';
import { AdminPageComponent } from './page/admin-page/admin-page.component';
import { CustomerAccountPageComponent } from './page/customer-account-page/customer-account-page.component';

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    RegisterComponent,
    NotFoundComponent,
    HomePageComponent,
    LogoutComponent,
    LoginComponent,
    CartComponent,
    OrderComponent,
    ProfileComponent,
    AdminPageComponent,
    CustomerAccountPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
