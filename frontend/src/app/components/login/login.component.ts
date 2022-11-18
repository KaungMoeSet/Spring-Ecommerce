import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthService} from "../../service/auth.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  submitted = false;
  loading = false;

  constructor(private formBuilder: FormBuilder,
              private authService: AuthService,
              private router: Router) {
  }

  loginForm = this.formBuilder.group({
    username: ['', Validators.required],
    password: ['', Validators.required,],
  });

  ngOnInit(): void {

    if (this.authService.isAuthenticated()) {
      this.router.navigate(['/home']);
    }
  }

  onSubmit(){
    this.submitted = true;

    if(this.loginForm.invalid) {
      return;
    }

    this.loading = true;
    let user: any = this.loginForm.value;
    this.authService.login(user.username, user.password).subscribe( data => {
      this.loginOk(data);
    }, error => {
      this.loginError(error);
    });

  }

  private loginOk(response: any) {
    console.log("Response Role", response);
    console.log("token ", response.token);
    localStorage.setItem("token", response.token);
    localStorage.setItem("role", response.role);
    this.authService.setAuthentication(response.token, response.role);
    if( response.role[0] == "USER"){
      this.router.navigate(['/home']);
    } else if ( response.role[0] == "ADMIN" ){
      this.router.navigate(['/admin']);
    }
  }

  private loginError(error: any) {
    console.log("Error ", error);
    this.loading = false;
    Swal.fire(
      'Invalid login!',
      'Invalid username or password',
      'warning'
    )
    this.loginForm.reset();
  }

  get loginFormControl() {
    return this.loginForm.controls;
  }
}
