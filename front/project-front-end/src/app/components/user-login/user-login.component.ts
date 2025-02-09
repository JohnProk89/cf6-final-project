import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import { MatInputModule} from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule} from '@angular/material/button';
import { UserserviceService } from '../../shared/services/userservice.service';

@Component({
  selector: 'app-user-login',
  imports: [
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ],
  templateUrl: './user-login.component.html',
  styleUrl: './user-login.component.css'
})
export class UserLoginComponent {

    userService = inject(UserserviceService);

    userLoginForm = new FormGroup({
        username: new FormControl(),
        password: new FormControl()
    })

    onSubmit(value:any){
        console.log(value);
        const user = {
            username : this.userLoginForm.get('username')?.value,
            password : this.userLoginForm.get('password')?.value
        }
        this.userService.loginUser(user).subscribe({
            next: (response) => {
                console.log(response)
            },
            error: (response) => {
                console.log(response)

            }
        })
        this.userLoginForm.reset();
    }
}
