import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { DlsPersons } from 'app/shared/model/dls-persons.model';
import { DlsPersonsService } from './dls-persons.service';
import { DlsPersonsComponent } from './dls-persons.component';
import { DlsPersonsDetailComponent } from './dls-persons-detail.component';
import { DlsPersonsUpdateComponent } from './dls-persons-update.component';
import { IDlsPersons } from 'app/shared/model/dls-persons.model';

@Injectable({ providedIn: 'root' })
export class DlsPersonsResolve implements Resolve<IDlsPersons> {
  constructor(private service: DlsPersonsService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDlsPersons> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((dlsPersons: HttpResponse<DlsPersons>) => dlsPersons.body));
    }
    return of(new DlsPersons());
  }
}

export const dlsPersonsRoute: Routes = [
  {
    path: '',
    component: DlsPersonsComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'dlssyncApp.dlsPersons.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DlsPersonsDetailComponent,
    resolve: {
      dlsPersons: DlsPersonsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.dlsPersons.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DlsPersonsUpdateComponent,
    resolve: {
      dlsPersons: DlsPersonsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.dlsPersons.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DlsPersonsUpdateComponent,
    resolve: {
      dlsPersons: DlsPersonsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.dlsPersons.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
