import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../service/auth.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  loggedType!: string;
  constructor(private authService: AuthService) {

    switch (this.authService.getRole()){
      case 'USER':
        this.loggedType = "customer";
        break;
      case 'ADMIN':
        this.loggedType = "admin";
        break;
    }
  }

  ngOnInit(): void {
  }

  logout() {
    Swal.fire({
      title: 'Are you sure you want to logout?',
      showCancelButton: true,
      confirmButtonText: 'Confirm',
      cancelButtonText: 'Cancel'
    }).then((result) => {
      if (result.isConfirmed) {
        this.authService.logout();
      }
    });
  }
}
