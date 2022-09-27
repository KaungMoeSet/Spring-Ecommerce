import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {NotFoundComponent} from "./components/not-found/not-found.component";
import {RegisterComponent} from "./components/register/register.component";
import {HomePageComponent} from "./page/home-page/home-page.component";
import {LogoutComponent} from "./components/logout/logout.component";
import {AuthGuard} from "./auth/auth.guard";
import {LoginComponent} from "./components/login/login.component";
import {CartComponent} from "./components/cart/cart.component";
import {ProfileComponent} from "./components/profile/profile.component";
import {OrderComponent} from "./components/order/order.component";
import {AdminPageComponent} from "./page/admin-page/admin-page.component";
import {CustomerAccountPageComponent} from "./page/customer-account-page/customer-account-page.component";

const routes: Routes = [
  { path:  "", pathMatch:  "full",redirectTo:  "login"},
  { path: 'home',
    component: HomePageComponent },
  { path: 'cart',
    component: CartComponent },
  { path: 'profile',
    component: ProfileComponent },
  { path: 'order',
    component: OrderComponent },
  { path: 'admin',
    component: AdminPageComponent },
  { path: 'accounts',
    component: CustomerAccountPageComponent },
  { path: 'login',
    component: LoginComponent },
  { path:  'register',
    component: RegisterComponent },
  { path:  'logout',
    component: LogoutComponent},
  { path: '**',
    component: NotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
