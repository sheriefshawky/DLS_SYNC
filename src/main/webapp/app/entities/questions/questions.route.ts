import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Questions } from 'app/shared/model/questions.model';
import { QuestionsService } from './questions.service';
import { QuestionsComponent } from './questions.component';
import { QuestionsDetailComponent } from './questions-detail.component';
import { QuestionsUpdateComponent } from './questions-update.component';
import { IQuestions } from 'app/shared/model/questions.model';

@Injectable({ providedIn: 'root' })
export class QuestionsResolve implements Resolve<IQuestions> {
  constructor(private service: QuestionsService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IQuestions> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((questions: HttpResponse<Questions>) => questions.body));
    }
    return of(new Questions());
  }
}

export const questionsRoute: Routes = [
  {
    path: '',
    component: QuestionsComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'dlssyncApp.questions.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: QuestionsDetailComponent,
    resolve: {
      questions: QuestionsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.questions.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: QuestionsUpdateComponent,
    resolve: {
      questions: QuestionsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.questions.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: QuestionsUpdateComponent,
    resolve: {
      questions: QuestionsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.questions.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
