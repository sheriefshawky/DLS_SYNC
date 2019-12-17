import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Template } from 'app/shared/model/template.model';
import { TemplateService } from './template.service';
import { TemplateComponent } from './template.component';
import { TemplateDetailComponent } from './template-detail.component';
import { TemplateUpdateComponent } from './template-update.component';
import { ITemplate } from 'app/shared/model/template.model';

@Injectable({ providedIn: 'root' })
export class TemplateResolve implements Resolve<ITemplate> {
  constructor(private service: TemplateService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITemplate> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((template: HttpResponse<Template>) => template.body));
    }
    return of(new Template());
  }
}

export const templateRoute: Routes = [
  {
    path: '',
    component: TemplateComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'dlssyncApp.template.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TemplateDetailComponent,
    resolve: {
      template: TemplateResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.template.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TemplateUpdateComponent,
    resolve: {
      template: TemplateResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.template.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TemplateUpdateComponent,
    resolve: {
      template: TemplateResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.template.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
