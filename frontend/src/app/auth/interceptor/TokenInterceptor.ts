import {Injectable} from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import {Observable} from "rxjs";
import {AuthService} from "../../service/auth.service";


@Injectable() // ဒီကောင်က module မှာ setup လုပ်ရမယ်
export class TokenInterceptor implements HttpInterceptor {

  constructor(public authService: AuthService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    console.log("Interceptor called ", request);
    request = request.clone({
      setHeaders: {
        Authorization: `Bearer ${this.authService.getToken()}`
      }
    });
    return next.handle(request);
  }
}
