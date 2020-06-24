import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from '../usuario/usuario';
import { UsuarioService } from '../usuario/usuario.service';

@Component({
    selector: 'app-signup',
    templateUrl: './signup.component.html',
})
export class SignupComponent implements OnInit {
    test: Date = new Date();
    focus;
    focus1;
    focus2;

    usuario: Usuario = new Usuario();

    constructor(private usuarioService: UsuarioService, private router: Router) { }

    ngOnInit() { }

    public Registrar(): void {
        console.log(this.usuario);
        this.usuarioService.create(this.usuario).subscribe(response => {
            console.log(response)
            this.router.navigate(["/login"]);
        }
        );
    }
}
