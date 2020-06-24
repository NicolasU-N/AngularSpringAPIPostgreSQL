import { Rol } from '../rol/rol';
import { Server } from '../server/server';

export class Usuario {
    id: number;
    username: string;
    password: string;
    enable: boolean;
    createAt: string;
    updateAt: string;
    roles: Rol[];
    servers: Server[];
}
