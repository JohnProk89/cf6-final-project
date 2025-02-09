import { Component } from '@angular/core';
import { RouterOutlet, RouterLink } from '@angular/router';
import { ListGroupMenuComponent } from './components/list-group-menu/list-group-menu.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterLink, ListGroupMenuComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'project-front-end';
}
