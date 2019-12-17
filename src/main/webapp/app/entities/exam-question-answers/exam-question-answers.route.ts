import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ExamQuestionAnswers } from 'app/shared/model/exam-question-answers.model';
import { ExamQuestionAnswersService } from './exam-question-answers.service';
import { ExamQuestionAnswersComponent } from './exam-question-answers.component';
import { ExamQuestionAnswersDetailComponent } from './exam-question-answers-detail.component';
import { ExamQuestionAnswersUpdateComponent } from './exam-question-answers-update.component';
import { IExamQuestionAnswers } from 'app/shared/model/exam-question-answers.model';

@Injectable({ providedIn: 'root' })
export class ExamQuestionAnswersResolve implements Resolve<IExamQuestionAnswers> {
  constructor(private service: ExamQuestionAnswersService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IExamQuestionAnswers> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((examQuestionAnswers: HttpResponse<ExamQuestionAnswers>) => examQuestionAnswers.body));
    }
    return of(new ExamQuestionAnswers());
  }
}

export const examQuestionAnswersRoute: Routes = [
  {
    path: '',
    component: ExamQuestionAnswersComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'dlssyncApp.examQuestionAnswers.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ExamQuestionAnswersDetailComponent,
    resolve: {
      examQuestionAnswers: ExamQuestionAnswersResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.examQuestionAnswers.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ExamQuestionAnswersUpdateComponent,
    resolve: {
      examQuestionAnswers: ExamQuestionAnswersResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.examQuestionAnswers.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ExamQuestionAnswersUpdateComponent,
    resolve: {
      examQuestionAnswers: ExamQuestionAnswersResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.examQuestionAnswers.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
