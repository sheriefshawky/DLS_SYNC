import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ExamQuestions } from 'app/shared/model/exam-questions.model';
import { ExamQuestionsService } from './exam-questions.service';
import { ExamQuestionsComponent } from './exam-questions.component';
import { ExamQuestionsDetailComponent } from './exam-questions-detail.component';
import { ExamQuestionsUpdateComponent } from './exam-questions-update.component';
import { IExamQuestions } from 'app/shared/model/exam-questions.model';

@Injectable({ providedIn: 'root' })
export class ExamQuestionsResolve implements Resolve<IExamQuestions> {
  constructor(private service: ExamQuestionsService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IExamQuestions> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((examQuestions: HttpResponse<ExamQuestions>) => examQuestions.body));
    }
    return of(new ExamQuestions());
  }
}

export const examQuestionsRoute: Routes = [
  {
    path: '',
    component: ExamQuestionsComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'dlssyncApp.examQuestions.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ExamQuestionsDetailComponent,
    resolve: {
      examQuestions: ExamQuestionsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.examQuestions.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ExamQuestionsUpdateComponent,
    resolve: {
      examQuestions: ExamQuestionsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.examQuestions.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ExamQuestionsUpdateComponent,
    resolve: {
      examQuestions: ExamQuestionsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.examQuestions.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
