import { Component } from '@angular/core';
import { MenuEntry } from '../../shared/interfaces/menu-entry';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-list-group-menu',
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './list-group-menu.component.html',
  styleUrl: './list-group-menu.component.css'
})
export class ListGroupMenuComponent {
    menu : MenuEntry[] = [
        { text : "User Register", routerLink: "user-register"},
        { text : "Login User", routerLink: "login-user"},
        { text : "Get User", routerLink: "get-user"}
    ]
}
