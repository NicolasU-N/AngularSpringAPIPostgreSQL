import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app.routing';
import { AppComponent } from './app.component';
import { LandingComponent } from './landing/landing.component';
import { NavbarComponent } from './shared/navbar/navbar.component';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';

import { UsuarioComponent } from './usuario/usuario.component';
import { ServerComponent } from './server/server.component';
import { FormComponent } from './server/form.component';
import { JwtService } from './jwt/jwt.service';
import { UsuarioService } from './usuario/usuario.service';
import { FormUpdateUserComponent } from './usuario/form-update-user.component';
import { ServerService } from './server/server.service';

@NgModule({
  declarations: [
    AppComponent,
    SignupComponent,
    LandingComponent,
    NavbarComponent,
    LoginComponent,
    UsuarioComponent,
    ServerComponent,
    FormComponent,
    FormUpdateUserComponent
  ],
  imports: [
    BrowserModule,
    NgbModule,
    FormsModule,
    RouterModule,
    AppRoutingModule,
    HttpClientModule,
  ],
  providers: [
    JwtService, UsuarioService, ServerService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
