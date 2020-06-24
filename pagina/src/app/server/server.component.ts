import { Component, OnInit } from '@angular/core';
import { Server } from './server';
import { ServerService } from './server.service';
import Swal from 'sweetalert2'

import { JwtService } from '../jwt/jwt.service';
import { Usuario } from '../usuario/usuario';
import { UsuarioService } from '../usuario/usuario.service';
import { Router } from '@angular/router';




@Component({
  selector: 'app-server',
  templateUrl: './server.component.html',
  styleUrls: ['./server.component.css']
})
export class ServerComponent implements OnInit {

  servers: Server[];

  serverUser : Server[] = [];

  usuario: Usuario = new Usuario();

  flag: boolean = false;

  constructor(private serverService: ServerService, private jwtService: JwtService, private usuarioService: UsuarioService, private router : Router) { }

  ngOnInit(): void {
    this.serverService.getServers().subscribe(
      servers => this.servers = servers
    );

    if (this.jwtService.isAuth()) {

      let username: string = this.jwtService.payload().sub;

      this.usuarioService.userByUsername(username).subscribe(
        usuario => {

          this.usuario = usuario;

          this.usuario.roles.forEach(
            rol => {
              if (rol.name == "ADMIN") {
                this.flag = true;

              }
            }
          );


        })
    }else{

      this.router.navigate(["/"])

    }



  }

  delete(server: Server): void {
    const swalWithBootstrapButtons = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger'
      },
      buttonsStyling: false
    })

    swalWithBootstrapButtons.fire({
      title: '¿Está seguro?',
      text: `¿Seguro que desea eliminar el server ${server.id}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si, eliminar!',
      cancelButtonText: 'No, cancelar!',
      reverseButtons: true
    }).then((result) => {
      if (result.value) {
        this.serverService.delete(server.id).subscribe(
          response => {
            this.servers = this.servers.filter(ser => ser !== server)
            Swal.fire({
              title: 'Server Eliminado',
              text: `Server ${server.id} eliminado con éxito!`,
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

  buy(server : Server):void{

    this.serverUser.push(server);


    this.usuario.servers = this.serverUser;

    this.usuarioService.update(this.usuario).subscribe(
      usuario => {
        Swal.fire({
          title: 'compra exitosa',
          text: `El usuario: ${usuario.username} ha comprado una parte del servidor ${server.dns}`,
          icon: 'success',
          confirmButtonText: 'Cool'
        })
      }
    );

    this.serverUser = [];

    this.router.navigate(["/usuarioComponent"])

  }



}
