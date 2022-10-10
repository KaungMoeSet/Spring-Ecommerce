import { Injectable } from '@angular/core';
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";

export const API_URL = "http://localhost:8080/";
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  authenticated = false;
  token: string = '';
  role: string = '';

  constructor(private router: Router,
              private http: HttpClient) { }

  isAuthenticated() {
    return this.authenticated;
  }

  setAuthentication(token: string, role: string[]) {
    console.log("Role "+ role[0]);
    this.token = token;
    this.role = role[0];
    this.authenticated = true;
  }

  getToken() {
    return this.token;
  }

  getRole() {
    return this.role;
  }

  register(username: string, gender: string, email: string, password: string){
    return this.http.post(API_URL+"register",  {
      username,
      gender,
      email,
      password
    });
  }

  login(username: string, password: string) {
    return this.http.post(API_URL+"login", {
      username,
      password
    });
  }

  logout() {
    localStorage.removeItem("token");
    this.token = "";
    this.authenticated = false;
    this.router.navigate(['/login']);
  }
}
