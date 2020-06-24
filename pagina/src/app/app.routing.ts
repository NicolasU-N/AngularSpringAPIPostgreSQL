import { NgModule } from '@angular/core';
import { CommonModule, } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';

import { LandingComponent } from './landing/landing.component';
import { FormComponent } from './server/form.component';
import { ServerComponent } from './server/server.component';
import { SignupComponent } from './signup/signup.component';
import { LoginComponent } from './login/login.component';
import { UsuarioComponent } from './usuario/usuario.component';
import { FormUpdateUserComponent } from './usuario/form-update-user.component';


const routes: Routes = [
  { path: '', component: LandingComponent },
  { path: 'register', component: SignupComponent },
  { path: 'login', component: LoginComponent },
  { path: 'landing', component: LandingComponent },
  { path: 'formServer', component: FormComponent },
  { path: 'serverComponent/formServer/:id', component: FormComponent },
  { path: 'serverComponent', component: ServerComponent },
  { path: 'usuarioComponent', component: UsuarioComponent },
  { path: 'usuarioComponent/updateUsuario/:id', component: FormUpdateUserComponent },


];

@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    RouterModule.forRoot(routes, {

    })
  ],
  exports: [
  ],
})
export class AppRoutingModule { }
