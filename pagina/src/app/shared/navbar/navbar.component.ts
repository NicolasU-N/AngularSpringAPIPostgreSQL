import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { JwtService } from '../../jwt/jwt.service';
import { Usuario } from '../../usuario/usuario';
import { UsuarioService } from '../../usuario/usuario.service';


@Component({
    selector: 'app-navbar',
    templateUrl: './navbar.component.html',
    styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

    usuario: Usuario = new Usuario();

    flag: boolean = false;

    constructor(private jwtService: JwtService, private router: Router, private usuarioService: UsuarioService) { }

    ngOnInit(): void {

        if (this.jwtService.isAuth()) {

            let username: string = this.jwtService.payload().sub;

            this.usuarioService.userByUsername(username).subscribe(
                (usuario) => {
                    this.usuario = usuario;
                   this.usuario.roles.forEach(
                       (rol) => {

                        if(rol.name == "ADMIN"){
                            this.flag = true;

                        }

                       }
                   );
                });
        }


    }

    logOut(): void {
        this.jwtService.logOut();
        this.router.navigate[("/")]
        window.location.reload()
    }

}
