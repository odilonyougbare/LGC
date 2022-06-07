import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import {Observable, of, EMPTY, from} from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IClient, Client } from 'app/shared/model/client.model';
import { ClientService } from './client.service';
import { ClientComponent } from './client.component';
import { ClientDetailComponent } from './client-detail.component';
import { ClientUpdateComponent } from './client-update.component';
import {Agence, IAgence} from "../../shared/model/agence.model";

@Injectable({ providedIn: 'root' })
export class ClientResolve implements Resolve<IClient> {
  constructor(private service: ClientService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IClient> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((client: HttpResponse<Client>) => {
          if (client.body) {
            return of(client.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Client());
  }

}

@Injectable({ providedIn: 'root' })
export class ClientResolveAgence implements Resolve<IAgence[]> {
  constructor(private service: ClientService, private router: Router) {
  }

  resolve(route: ActivatedRouteSnapshot): Observable<IAgence[]> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.agencesOfClient(id).pipe(
        flatMap((agence: HttpResponse<Agence[]>) => {
          if (agence.body) {
            return of(agence.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Array<Agence>());
  }

}

@Injectable({ providedIn: 'root' })
export class ClientResolveId implements Resolve<any> {
  constructor( private router: Router) {
  }

  resolve(route: ActivatedRouteSnapshot): Observable<any> {
    return  route.params['id'];
  }

}

export const clientRoute: Routes = [
  {
    path: '',
    component: ClientComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'lgcApp.client.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ClientDetailComponent,
    resolve: {
      agence: ClientResolveAgence,
      id: ClientResolveId
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'lgcApp.client.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ClientUpdateComponent,
    resolve: {
      client: ClientResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'lgcApp.client.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ClientUpdateComponent,
    resolve: {
      client: ClientResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'lgcApp.client.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
