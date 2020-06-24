import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { JwtConstants } from '../jwt/jwt-constants';
import { Usuario } from './usuario';
import { catchError } from 'rxjs/operators';
import { throwError, Observable } from 'rxjs';
import Swal from 'sweetalert2'

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private urlEndPoint: string = "http://192.168.0.17:8080/api/usuarios";

  constructor(private http: HttpClient) { }


  public getUsuarios(): Observable<Usuario[]> {

    return this.http.get<Usuario[]>(this.urlEndPoint, { headers: JwtConstants.HEADER_TOKEN });

  }

  public getUsuario(id): Observable<Usuario> {
    return this.http.get<Usuario>(`${this.urlEndPoint}/${id}`, { headers: JwtConstants.HEADER_TOKEN });
  }

  public userByUsername(username: string): Observable<Usuario> {
    return this.http.get<Usuario>(`${this.urlEndPoint}/username/${username}`, { headers: JwtConstants.HEADER_TOKEN });
  }

  public create(usuario: Usuario): Observable<Usuario> {
    return this.http.post<Usuario>(this.urlEndPoint, usuario, { headers: JwtConstants.HEADER_JSON }).pipe(
      catchError(e => {
        Swal.fire({
          title: 'Error!',
          text: "Nombre de usuario en uso",
          icon: 'error',
          confirmButtonText: 'Ok'
        })
        return throwError(e);
      })

    );
  }

  update(usuario: Usuario): Observable<Usuario> {
    console.log(usuario)
    return this.http.put<Usuario>(`${this.urlEndPoint}/${usuario.id}`, usuario, { headers: JwtConstants.HEADER_TOKEN })
  }

  delete(id: number): Observable<Usuario> {
    return this.http.delete<Usuario>(`${this.urlEndPoint}/${id}`, { headers: JwtConstants.HEADER_TOKEN })
  }







}
