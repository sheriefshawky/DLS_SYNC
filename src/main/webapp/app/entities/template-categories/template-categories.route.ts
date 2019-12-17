import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { TemplateCategories } from 'app/shared/model/template-categories.model';
import { TemplateCategoriesService } from './template-categories.service';
import { TemplateCategoriesComponent } from './template-categories.component';
import { TemplateCategoriesDetailComponent } from './template-categories-detail.component';
import { TemplateCategoriesUpdateComponent } from './template-categories-update.component';
import { ITemplateCategories } from 'app/shared/model/template-categories.model';

@Injectable({ providedIn: 'root' })
export class TemplateCategoriesResolve implements Resolve<ITemplateCategories> {
  constructor(private service: TemplateCategoriesService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITemplateCategories> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((templateCategories: HttpResponse<TemplateCategories>) => templateCategories.body));
    }
    return of(new TemplateCategories());
  }
}

export const templateCategoriesRoute: Routes = [
  {
    path: '',
    component: TemplateCategoriesComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'dlssyncApp.templateCategories.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TemplateCategoriesDetailComponent,
    resolve: {
      templateCategories: TemplateCategoriesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.templateCategories.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TemplateCategoriesUpdateComponent,
    resolve: {
      templateCategories: TemplateCategoriesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.templateCategories.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TemplateCategoriesUpdateComponent,
    resolve: {
      templateCategories: TemplateCategoriesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.templateCategories.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
