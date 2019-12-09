import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { DlsVhlTrsExams } from 'app/shared/model/dls-vhl-trs-exams.model';
import { DlsVhlTrsExamsService } from './dls-vhl-trs-exams.service';
import { DlsVhlTrsExamsComponent } from './dls-vhl-trs-exams.component';
import { DlsVhlTrsExamsDetailComponent } from './dls-vhl-trs-exams-detail.component';
import { DlsVhlTrsExamsUpdateComponent } from './dls-vhl-trs-exams-update.component';
import { IDlsVhlTrsExams } from 'app/shared/model/dls-vhl-trs-exams.model';

@Injectable({ providedIn: 'root' })
export class DlsVhlTrsExamsResolve implements Resolve<IDlsVhlTrsExams> {
  constructor(private service: DlsVhlTrsExamsService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDlsVhlTrsExams> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((dlsVhlTrsExams: HttpResponse<DlsVhlTrsExams>) => dlsVhlTrsExams.body));
    }
    return of(new DlsVhlTrsExams());
  }
}

export const dlsVhlTrsExamsRoute: Routes = [
  {
    path: '',
    component: DlsVhlTrsExamsComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.dlsVhlTrsExams.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DlsVhlTrsExamsDetailComponent,
    resolve: {
      dlsVhlTrsExams: DlsVhlTrsExamsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.dlsVhlTrsExams.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DlsVhlTrsExamsUpdateComponent,
    resolve: {
      dlsVhlTrsExams: DlsVhlTrsExamsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.dlsVhlTrsExams.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DlsVhlTrsExamsUpdateComponent,
    resolve: {
      dlsVhlTrsExams: DlsVhlTrsExamsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.dlsVhlTrsExams.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
