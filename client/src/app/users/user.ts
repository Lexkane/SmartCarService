export class User {

  id: number;
  username: string;
  password: string;
  email: string;
  fullName: string;
  numberPhone: string;
  role: string[];


  constructor(username: string, password: string, email: string, fullName: string, numberPhone: string, role: string []) {
    this.fullName = fullName;
    this.email = email;
    this.password = password;
    this.username = username;
    this.numberPhone = numberPhone;
    this.role = [];
  }
}
