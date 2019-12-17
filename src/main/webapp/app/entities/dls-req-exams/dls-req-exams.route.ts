import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { DlsReqExams } from 'app/shared/model/dls-req-exams.model';
import { DlsReqExamsService } from './dls-req-exams.service';
import { DlsReqExamsComponent } from './dls-req-exams.component';
import { DlsReqExamsDetailComponent } from './dls-req-exams-detail.component';
import { DlsReqExamsUpdateComponent } from './dls-req-exams-update.component';
import { IDlsReqExams } from 'app/shared/model/dls-req-exams.model';

@Injectable({ providedIn: 'root' })
export class DlsReqExamsResolve implements Resolve<IDlsReqExams> {
  constructor(private service: DlsReqExamsService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDlsReqExams> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((dlsReqExams: HttpResponse<DlsReqExams>) => dlsReqExams.body));
    }
    return of(new DlsReqExams());
  }
}

export const dlsReqExamsRoute: Routes = [
  {
    path: '',
    component: DlsReqExamsComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'dlssyncApp.dlsReqExams.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DlsReqExamsDetailComponent,
    resolve: {
      dlsReqExams: DlsReqExamsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.dlsReqExams.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DlsReqExamsUpdateComponent,
    resolve: {
      dlsReqExams: DlsReqExamsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.dlsReqExams.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DlsReqExamsUpdateComponent,
    resolve: {
      dlsReqExams: DlsReqExamsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.dlsReqExams.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
