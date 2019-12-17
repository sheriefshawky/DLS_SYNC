import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Answers } from 'app/shared/model/answers.model';
import { AnswersService } from './answers.service';
import { AnswersComponent } from './answers.component';
import { AnswersDetailComponent } from './answers-detail.component';
import { AnswersUpdateComponent } from './answers-update.component';
import { IAnswers } from 'app/shared/model/answers.model';

@Injectable({ providedIn: 'root' })
export class AnswersResolve implements Resolve<IAnswers> {
  constructor(private service: AnswersService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAnswers> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((answers: HttpResponse<Answers>) => answers.body));
    }
    return of(new Answers());
  }
}

export const answersRoute: Routes = [
  {
    path: '',
    component: AnswersComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'dlssyncApp.answers.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: AnswersDetailComponent,
    resolve: {
      answers: AnswersResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.answers.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: AnswersUpdateComponent,
    resolve: {
      answers: AnswersResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.answers.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: AnswersUpdateComponent,
    resolve: {
      answers: AnswersResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.answers.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
