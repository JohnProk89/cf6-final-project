import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

const API_URL_AUTH='/api/auth';
const API_URL_USER='/api/user';

@Injectable({
  providedIn: 'root'
})
export class UserserviceService {
    http: HttpClient = inject(HttpClient)

    registerUser(user: any) {
        return this.http.post<{msg: string}>(`${API_URL_AUTH}/angular/signup`, user)
    }

    // check_duplicate_username(username: string){
    //     return this.http.get<{msg: string}>(`${API_URL_AUTH}/this.check_duplicate_username/${username}`,)
    // }

    loginUser(user: any) {
        return this.http.post(`${API_URL_AUTH}/angular/login`, user)
    }

    getUsers() {
        return this.http.get(`${API_URL_USER}/angular`)
    }
}
