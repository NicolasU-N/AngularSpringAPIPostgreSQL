import { HttpHeaders } from '@angular/common/http';

export class JwtConstants {
    
    public static readonly TOKEN_KEY : string = "JWT";
    public static readonly TOKEN : string = localStorage.getItem(JwtConstants.TOKEN_KEY);
    public static readonly HEADER_JSON : HttpHeaders= new HttpHeaders({'Content-Type':'application/json'})
    public static readonly HEADER_TOKEN : HttpHeaders= new HttpHeaders({'Authorization':'Bearer '.concat(JwtConstants.TOKEN)});
}
