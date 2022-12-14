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
import { LoginComponent } from './components/login/login.component';
import { CartComponent } from './components/cart/cart.component';
import { OrderComponent } from './components/order/order.component';
import { ProfileComponent } from './components/profile/profile.component';
import { AdminPageComponent } from './page/admin-page/admin-page.component';
import { CustomerAccountPageComponent } from './page/account-page/customer-account-page.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {TokenInterceptor} from "./auth/interceptor/TokenInterceptor";
import { AccessDeniedComponent } from './components/access-denied/access-denied.component';
import {ModalModule} from "ngx-bootstrap/modal";
import { ProductTableComponent } from './components/product-table/product-table.component';

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    RegisterComponent,
    NotFoundComponent,
    HomePageComponent,
    LoginComponent,
    CartComponent,
    OrderComponent,
    ProfileComponent,
    AdminPageComponent,
    CustomerAccountPageComponent,
    AccessDeniedComponent,
    ProductTableComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    ReactiveFormsModule,
    ModalModule.forRoot()
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
