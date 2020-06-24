import { Component, OnInit } from '@angular/core';
import { JwtRequest } from '../jwt/jwt-request';
import { JwtService } from '../jwt/jwt.service';
import { JwtConstants } from '../jwt/jwt-constants';
import { Router } from '@angular/router';
import Swal from 'sweetalert2'


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  jwtRequest: JwtRequest = new JwtRequest();

  constructor(private jwtService: JwtService, private router: Router) { }

  ngOnInit(): void {

    if (this.jwtService.isAuth()) {

      this.router.navigate(["/"]);

    }

  }

  Auth(): void {

    this.jwtService.auth(this.jwtRequest).subscribe(
      jwtResponse => {

        localStorage.setItem(JwtConstants.TOKEN_KEY, jwtResponse.jwt);

        //window.location.reload();

        Swal.fire({
          title: 'Welcome!',
          text: "Welcome ".concat(this.jwtRequest.username),
          icon: 'success',
          confirmButtonText: 'Ok',
          onDestroy: () => {
            window.location.reload();
          },
        })

        this.router.navigate(["/"]);

      }
    )

  }

}