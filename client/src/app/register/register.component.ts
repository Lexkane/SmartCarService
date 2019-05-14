import { Component, OnInit } from '@angular/core';
 
import { AuthService } from '../auth/auth.service';
import { SignUpInfo } from '../auth/signup-info';
import { ROLES } from '../roles/mock-roles';
import { HttpResponse } from '@angular/common/http';
import { Role } from '../roles/role';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  
  roleArray = ROLES;
  selectedRole: Role = null;
  registerForm: any = {};
  signupInfo: SignUpInfo;
  isSignedUp = false;
  isSignUpFailed = false;
  errorMessage = '';
  
  
 
  constructor(private authService: AuthService) { }

  ngOnInit() {
  }

  chooseRole(selected: Role) {
    this.selectedRole = selected;
  }


  onSubmit() {
    console.log(this.registerForm);
    console.log(this.selectedRole);
    this.signupInfo = new SignUpInfo(
      this.registerForm.username,
      this.registerForm.password,
      this.registerForm.fullName,
      this.registerForm.email,
      this.registerForm.numberPhone,
      this.selectedRole.name);
      console.log(this.signupInfo);
    this.authService.signUp(this.signupInfo).subscribe(
      data => {
        console.log(data);
        this.isSignUpFailed = false;
        this.isSignedUp = true;
        this.reloadPage();
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isSignUpFailed = true;
      }
    );
  }

  reloadPage() {
    window.location.href='ui/auth/login';
  }
}
