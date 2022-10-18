import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {AuthService} from "../../service/auth.service";
import Swal from "sweetalert2";
import {Router} from "@angular/router";
import {UserGender} from "../../model/user-gender";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  loading = false;
  submitted = false;
  constructor(private formBuilder: FormBuilder,
              private authService: AuthService,
              private router: Router) {
  }

  registerForm = this.formBuilder.group({
    username: ['', Validators.required],
    email: ['', Validators.required],
    gender: ['', Validators.required],
    password: ['', [Validators.required, Validators.minLength(6)]],
  })

  Genders = [
    "Male",
    "Female"
  ]

  ngOnInit(): void {
  }

  onSubmit() {
    this.submitted = true;

    if(this.registerForm.invalid) {
      return;
    }

    this.loading = true;
    let user: any = this.registerForm.value;
    console.log("User Gender", user.gender.toUpperCase());
    this.authService.register(user.username, user.gender.toUpperCase(), user.email, user.password)
      .subscribe(data => {
      this.registerOk(data);
    }, error => {
      this.registerFail(error);
    });
  }

  registerOk(response: any) {
    console.log("Response", response.value);
    Swal.fire({
      icon: 'success',
      title: 'Successful',
      text: 'please login'
      }
    )
    this.router.navigate(['/login']);
  }

  registerFail(error: any) {
    this.loading = false;
    console.log("Error", error)
    Swal.fire({
      icon: 'error',
      title: 'Oops...',
      text: 'Something went wrong! Please Try again'
    })
    this.registerForm.reset();
  }

  get registerFormControl() {
    return this.registerForm.controls;
  }
}
