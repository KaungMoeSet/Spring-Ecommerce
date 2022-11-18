import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {NotFoundComponent} from "./components/not-found/not-found.component";
import {RegisterComponent} from "./components/register/register.component";
import {HomePageComponent} from "./page/home-page/home-page.component";
import {AuthGuard} from "./auth/auth.guard";
import {LoginComponent} from "./components/login/login.component";
import {CartComponent} from "./components/cart/cart.component";
import {ProfileComponent} from "./components/profile/profile.component";
import {OrderComponent} from "./components/order/order.component";
import {AdminPageComponent} from "./page/admin-page/admin-page.component";
import {CustomerAccountPageComponent} from "./page/customer-account-page/customer-account-page.component";
import {AccessDeniedComponent} from "./components/access-denied/access-denied.component";
import {NavigationComponent} from "./components/navigation/navigation.component";

const routes: Routes = [
  {path:  "", pathMatch:  "full",redirectTo:  "login"},
  { path: 'home',
    component: HomePageComponent,
    canActivate: [AuthGuard] },
  { path: 'cart',
    component: CartComponent,
    canActivate: [AuthGuard] },
  { path: 'profile',
    component: ProfileComponent,
    canActivate: [AuthGuard] },
  { path: 'order',
    component: OrderComponent,
    canActivate: [AuthGuard] },
  { path: 'admin',
    component: AdminPageComponent,
    canActivate: [AuthGuard] },
  { path: 'accounts',
    component: CustomerAccountPageComponent,
    canActivate: [AuthGuard] },
  { path: 'navBar',
    component: NavigationComponent,
    canActivate: [AuthGuard] },
  { path: 'login',
    component: LoginComponent },
  { path:  'register',
    component: RegisterComponent },
  { path: 'access-denied',
    component: AccessDeniedComponent },
  { path: '**',
    component: NotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
