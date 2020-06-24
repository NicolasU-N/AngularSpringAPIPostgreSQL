import { Component, OnInit } from '@angular/core';
import { Usuario } from './usuario';
import { UsuarioService } from './usuario.service';
import Swal from 'sweetalert2'
import { JwtService } from '../jwt/jwt.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.css']
})
export class UsuarioComponent implements OnInit {

  usuarios: Usuario[];

  usuario: Usuario = new Usuario();

  flagAdming: boolean = false;

  constructor(private usuarioService: UsuarioService, private jwtService: JwtService, private router : Router) { }

  ngOnInit(): void {

    if (this.jwtService.isAuth()) {

      let username: string = this.jwtService.payload().sub;

      this.usuarioService.userByUsername(username).subscribe(
        (usuario: Usuario) => {
          this.usuario = usuario;
          this.usuario.roles.forEach(
            rol => {
              console.log(rol)

              if (rol.name == "ADMIN") {
                this.flagAdming = true;
              }

            });

          console.log(this.flagAdming);
          if (this.flagAdming) {
            this.usuarioService.getUsuarios().subscribe(
              usuarios => {
                this.usuarios = usuarios;
              })

            }


        });
    }else{
      this.router.navigate(["/"])
    }



  }


  delete(usuario: Usuario): void {
    const swalWithBootstrapButtons = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger'
      },
      buttonsStyling: false
    })

    swalWithBootstrapButtons.fire({
      title: '¿Está seguro?',
      text: `¿Seguro que desea eliminar el server ${usuario.username}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si, eliminar!',
      cancelButtonText: 'No, cancelar!',
      reverseButtons: true
    }).then((result) => {
      if (result.value) {
        this.usuarioService.delete(usuario.id).subscribe(
          response => {
            this.usuarios = this.usuarios.filter(user => user !== usuario)
            Swal.fire({
              title: 'Server Eliminado',
              text: `Server ${usuario.id} eliminado con éxito!`,
              icon: 'success',
              confirmButtonText: 'Cool'
            })
          }
        )
        swalWithBootstrapButtons.fire(
          'Deleted!',
          'Your file has been deleted.',
          'success'
        )
      }
    });
  }

}
