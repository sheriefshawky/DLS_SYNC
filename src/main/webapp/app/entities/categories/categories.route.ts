import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Categories } from 'app/shared/model/categories.model';
import { CategoriesService } from './categories.service';
import { CategoriesComponent } from './categories.component';
import { CategoriesDetailComponent } from './categories-detail.component';
import { CategoriesUpdateComponent } from './categories-update.component';
import { ICategories } from 'app/shared/model/categories.model';

@Injectable({ providedIn: 'root' })
export class CategoriesResolve implements Resolve<ICategories> {
  constructor(private service: CategoriesService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICategories> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((categories: HttpResponse<Categories>) => categories.body));
    }
    return of(new Categories());
  }
}

export const categoriesRoute: Routes = [
  {
    path: '',
    component: CategoriesComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'dlssyncApp.categories.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CategoriesDetailComponent,
    resolve: {
      categories: CategoriesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.categories.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CategoriesUpdateComponent,
    resolve: {
      categories: CategoriesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.categories.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CategoriesUpdateComponent,
    resolve: {
      categories: CategoriesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.categories.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
