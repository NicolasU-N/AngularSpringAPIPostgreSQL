import { Component, OnInit } from '@angular/core';
import { ServerService } from '../server/server.service';
import { Server } from '../server/server';


@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
})

export class LandingComponent implements OnInit {
  focus: any;
  focus1: any;

  servers: Server[];

  constructor(private serverService: ServerService) { }

  ngOnInit() {

    this.serverService.getServers().subscribe(

      servers => this.servers = servers
    );

  }

}
