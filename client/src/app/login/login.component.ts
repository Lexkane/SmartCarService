import { Component, OnInit } from '@angular/core';
 
import { AuthService } from '../auth/auth.service';
import { TokenStorageService } from '../auth/token-storage.service';
import { AuthLoginInfo } from '../auth/login-info';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  private loginInfo: AuthLoginInfo;
 
  constructor(private authService: AuthService, private tokenStorage: TokenStorageService) { }
 
  ngOnInit() {

  }
 
  onSubmit() {
    console.log(this.loginForm);
 
    this.loginInfo = new AuthLoginInfo(
      this.loginForm.username,
      this.loginForm.password);
 
this.authService.attemptAuth(this.loginInfo).subscribe(
      data => {
        console.log(data);
        console.log(data.token);
        this.tokenStorage.saveToken(data.token);
        this.tokenStorage.saveUsername(data.username);
        this.tokenStorage.saveAuthorities(data.authorities);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getAuthorities();
        this.roles.every(role => {
          if (role === 'ROLE_CAR_OWNER') {
          window.location.href='/ui/cars'
          return false;
        } else if (role === 'ROLE_SALES_MANAGER') {
          window.location.href='/ui/home';
          return false;
        } else if (role === 'ROLE_TECHNICAL_MANAGER') {
          window.location.href='/ui/home';
          return false;
        } else if (role === 'ROLE_DIELER') {
          window.location.href='/ui/home';
          return false;
        } else if (role === 'ROLE_ADMIN') {
          window.location.href='/ui/home';
          return false;
        } else if (role === 'ROLE_WORKER') {
          window.location.href='/ui/home';
          return false;
        }
       })   
        },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isLoginFailed = true;
      }
    );
  }
 
  reloadPage() {
    window.location.href='/ui/home';
  }

}
