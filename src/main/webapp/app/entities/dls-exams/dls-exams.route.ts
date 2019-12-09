import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { DlsExams } from 'app/shared/model/dls-exams.model';
import { DlsExamsService } from './dls-exams.service';
import { DlsExamsComponent } from './dls-exams.component';
import { DlsExamsDetailComponent } from './dls-exams-detail.component';
import { DlsExamsUpdateComponent } from './dls-exams-update.component';
import { IDlsExams } from 'app/shared/model/dls-exams.model';

@Injectable({ providedIn: 'root' })
export class DlsExamsResolve implements Resolve<IDlsExams> {
  constructor(private service: DlsExamsService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDlsExams> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((dlsExams: HttpResponse<DlsExams>) => dlsExams.body));
    }
    return of(new DlsExams());
  }
}

export const dlsExamsRoute: Routes = [
  {
    path: '',
    component: DlsExamsComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.dlsExams.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DlsExamsDetailComponent,
    resolve: {
      dlsExams: DlsExamsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.dlsExams.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DlsExamsUpdateComponent,
    resolve: {
      dlsExams: DlsExamsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.dlsExams.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DlsExamsUpdateComponent,
    resolve: {
      dlsExams: DlsExamsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.dlsExams.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
