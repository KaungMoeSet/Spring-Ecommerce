import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  submitted = false;
  constructor(private formBuilder: FormBuilder) {
  }

  registerForm = this.formBuilder.group({
    username: ['', Validators.required],
    gender: ['', Validators.required],
    email: ['', Validators.required],
    password: ['', Validators.required],
  })

  ngOnInit(): void {
  }

  onSubmit() {
    this.submitted = true;
  }

  get registerFormControl() {
    return this.registerForm.controls;
  }
}
