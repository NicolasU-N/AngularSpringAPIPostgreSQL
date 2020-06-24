import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Server } from './server';
import { Observable } from 'rxjs';
import { JwtConstants } from '../jwt/jwt-constants';

@Injectable({
  providedIn: 'root'
})
export class ServerService {

  private urlEndPoint: string = "http://192.168.0.17:8080/api/servers";


  constructor(private http: HttpClient) { }


  getServers(): Observable<Server[]> {

    return this.http.get<Server[]>(this.urlEndPoint, { headers: JwtConstants.HEADER_JSON });

  }

  getServer(id): Observable<Server> {
    return this.http.get<Server>(`${this.urlEndPoint}/${id}`, { headers: JwtConstants.HEADER_JSON });
  }

  create(server: Server): Observable<Server> {
    return this.http.post<Server>(this.urlEndPoint, server, { headers: JwtConstants.HEADER_JSON });
  }

  update(server: Server): Observable<Server> {
    console.log(server)
    return this.http.put<Server>(`${this.urlEndPoint}/${server.id}`, server, { headers: JwtConstants.HEADER_JSON })
  }

  delete(id: number): Observable<Server> {
    return this.http.delete<Server>(`${this.urlEndPoint}/${id}`, { headers: JwtConstants.HEADER_JSON })
  }



}
