import { Injectable } from '@angular/core';
import { JwtConstants } from './jwt-constants'
import { HttpClient } from '@angular/common/http';
import { JwtRequest } from './jwt-request';
import { throwError, Observable } from 'rxjs';
import { JwtResponse } from './jwt-response';
import { Payload } from './payload';
import Swal from 'sweetalert2'
import { catchError } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class JwtService {

  private urlEndPoint: String = "http://192.168.0.17:8080/api";

  constructor(private http: HttpClient) { }

  auth(jwtRequest: JwtRequest): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(this.urlEndPoint.concat("/auth"), jwtRequest, { headers: JwtConstants.HEADER_JSON }).pipe(
      catchError(e => {
        Swal.fire({
          title: 'Error!',
          text: "Bad crendentials",
          icon: 'error',
          confirmButtonText: 'Ok'
        })
        return throwError(e);
      })

    );
  }

  logOut(): void {
    localStorage.removeItem(JwtConstants.TOKEN_KEY);
  }

  isAuth(): boolean {

    if (JwtConstants.TOKEN != null) {
      return true;
    }
    return false;
  }

  payload(): Payload {
    let payload: string = window.atob(JwtConstants.TOKEN.split(".")[1]);

    let jsonPayload: Payload = JSON.parse(payload);

    return jsonPayload;
  }



}
