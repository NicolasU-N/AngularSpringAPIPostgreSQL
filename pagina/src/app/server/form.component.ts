import { Component, OnInit } from '@angular/core';
import { Server } from './server';
import { ServerService } from './server.service';
import { Router, ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2'
import { JwtService } from '../jwt/jwt.service';
import { UsuarioService } from '../usuario/usuario.service';
import { Usuario } from '../usuario/usuario';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  server: Server = new Server();

  usuario: Usuario = new Usuario();

  flagAdmin: boolean = false;

  constructor(private usuarioService: UsuarioService, private serverService: ServerService, private router: Router, private activatedRoute: ActivatedRoute, private jwtService: JwtService) { }

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
                this.flagAdmin = true;
              }

            });

          console.log(this.flagAdmin);

          if (this.flagAdmin) {

            this.activatedRoute.params.subscribe(
              params => {
                let id = params["id"]
                if (id) {
                  this.serverService.getServer(id).subscribe(
                    (server) => {
                      this.server = server;
                    }
                  )
                }
              }
            )


          } else {
            this.router.navigate(["/"])
          }


        });
    } else {
      this.router.navigate(["/"])
    }

  }

  public create(): void {
    this.serverService.create(this.server).subscribe(
      () => this.router.navigate(["/serverComponent"])
    )
  }

  update(): void {
    this.serverService.update(this.server).subscribe(
      server => {
        this.router.navigate(["/serverComponent"])
        Swal.fire({
          title: 'Server Actualizado',
          text: `Server ${server.id} actualizado con éxito!`,
          icon: 'success',
          confirmButtonText: 'Cool'
        })
      }
    )
  }

}
