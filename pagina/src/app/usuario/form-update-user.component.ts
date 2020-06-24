import { Component, OnInit } from '@angular/core';
import { UsuarioService } from './usuario.service';
import { Router, ActivatedRoute} from '@angular/router';
import { Usuario } from './usuario';
import Swal from 'sweetalert2'
import { JwtConstants } from '../jwt/jwt-constants';
import { JwtService } from '../jwt/jwt.service';

@Component({
  selector: 'app-form-update-user',
  templateUrl: './form-update-user.component.html',
  styleUrls: ['./form-update-user.component.css']
})
export class FormUpdateUserComponent implements OnInit {

  usuario: Usuario = new Usuario();

  constructor(private userService: UsuarioService, private router : Router, private jwtService : JwtService, private activatedRoute : ActivatedRoute) { }

  ngOnInit(): void {
    if (!this.jwtService.isAuth()) {
      this.router.navigate(["/"])

    }else{

      this.activatedRoute.params.subscribe(params => {
        let id = params['id']
        if (id) {
          this.userService.getUsuario(id).subscribe((usuario) => this.usuario = usuario)
        }
      })

    }
  }


  update(): void {
    console.log(this.usuario)
    this.userService.update(this.usuario).subscribe(
      usuario => {
        Swal.fire({
          title: 'Usuario Actualizado',
          text: `Usuario ${usuario.username} actualizado con éxito!`,
          icon: 'success',
          confirmButtonText: 'Cool'
        })
        localStorage.removeItem(JwtConstants.TOKEN_KEY);
        window.location.reload();
      }
    )
  }

}
