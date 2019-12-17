import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { DlsReqTrainings } from 'app/shared/model/dls-req-trainings.model';
import { DlsReqTrainingsService } from './dls-req-trainings.service';
import { DlsReqTrainingsComponent } from './dls-req-trainings.component';
import { DlsReqTrainingsDetailComponent } from './dls-req-trainings-detail.component';
import { DlsReqTrainingsUpdateComponent } from './dls-req-trainings-update.component';
import { IDlsReqTrainings } from 'app/shared/model/dls-req-trainings.model';

@Injectable({ providedIn: 'root' })
export class DlsReqTrainingsResolve implements Resolve<IDlsReqTrainings> {
  constructor(private service: DlsReqTrainingsService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDlsReqTrainings> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((dlsReqTrainings: HttpResponse<DlsReqTrainings>) => dlsReqTrainings.body));
    }
    return of(new DlsReqTrainings());
  }
}

export const dlsReqTrainingsRoute: Routes = [
  {
    path: '',
    component: DlsReqTrainingsComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'dlssyncApp.dlsReqTrainings.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DlsReqTrainingsDetailComponent,
    resolve: {
      dlsReqTrainings: DlsReqTrainingsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.dlsReqTrainings.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DlsReqTrainingsUpdateComponent,
    resolve: {
      dlsReqTrainings: DlsReqTrainingsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.dlsReqTrainings.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DlsReqTrainingsUpdateComponent,
    resolve: {
      dlsReqTrainings: DlsReqTrainingsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.dlsReqTrainings.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
