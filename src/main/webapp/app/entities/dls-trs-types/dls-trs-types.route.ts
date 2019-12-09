import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { DlsTrsTypes } from 'app/shared/model/dls-trs-types.model';
import { DlsTrsTypesService } from './dls-trs-types.service';
import { DlsTrsTypesComponent } from './dls-trs-types.component';
import { DlsTrsTypesDetailComponent } from './dls-trs-types-detail.component';
import { DlsTrsTypesUpdateComponent } from './dls-trs-types-update.component';
import { IDlsTrsTypes } from 'app/shared/model/dls-trs-types.model';

@Injectable({ providedIn: 'root' })
export class DlsTrsTypesResolve implements Resolve<IDlsTrsTypes> {
  constructor(private service: DlsTrsTypesService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDlsTrsTypes> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((dlsTrsTypes: HttpResponse<DlsTrsTypes>) => dlsTrsTypes.body));
    }
    return of(new DlsTrsTypes());
  }
}

export const dlsTrsTypesRoute: Routes = [
  {
    path: '',
    component: DlsTrsTypesComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.dlsTrsTypes.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DlsTrsTypesDetailComponent,
    resolve: {
      dlsTrsTypes: DlsTrsTypesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.dlsTrsTypes.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DlsTrsTypesUpdateComponent,
    resolve: {
      dlsTrsTypes: DlsTrsTypesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.dlsTrsTypes.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DlsTrsTypesUpdateComponent,
    resolve: {
      dlsTrsTypes: DlsTrsTypesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.dlsTrsTypes.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
