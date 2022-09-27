import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  loggedType: string;
  constructor() {
    this.loggedType = "home";
  }

  ngOnInit(): void {
  }

}
