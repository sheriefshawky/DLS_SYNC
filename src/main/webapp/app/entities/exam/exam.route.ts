import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Exam } from 'app/shared/model/exam.model';
import { ExamService } from './exam.service';
import { ExamComponent } from './exam.component';
import { ExamDetailComponent } from './exam-detail.component';
import { ExamUpdateComponent } from './exam-update.component';
import { IExam } from 'app/shared/model/exam.model';

@Injectable({ providedIn: 'root' })
export class ExamResolve implements Resolve<IExam> {
  constructor(private service: ExamService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IExam> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((exam: HttpResponse<Exam>) => exam.body));
    }
    return of(new Exam());
  }
}

export const examRoute: Routes = [
  {
    path: '',
    component: ExamComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'dlssyncApp.exam.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ExamDetailComponent,
    resolve: {
      exam: ExamResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.exam.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ExamUpdateComponent,
    resolve: {
      exam: ExamResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.exam.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ExamUpdateComponent,
    resolve: {
      exam: ExamResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.exam.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
