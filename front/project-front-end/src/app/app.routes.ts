import { Routes } from '@angular/router';
import { UserRegisterComponent } from './components/user-register/user-register.component';
import { UserGetuserComponent } from './components/user-getuser/user-getuser.component';
import { UserLoginComponent } from './components/user-login/user-login.component';

export const routes: Routes = [
    { path : "user-register", component: UserRegisterComponent},
    { path : "login-user", component: UserLoginComponent},
    { path : "get-user", component: UserGetuserComponent},
];
