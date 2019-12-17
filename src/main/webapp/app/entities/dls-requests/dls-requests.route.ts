import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { DlsRequests } from 'app/shared/model/dls-requests.model';
import { DlsRequestsService } from './dls-requests.service';
import { DlsRequestsComponent } from './dls-requests.component';
import { DlsRequestsDetailComponent } from './dls-requests-detail.component';
import { DlsRequestsUpdateComponent } from './dls-requests-update.component';
import { IDlsRequests } from 'app/shared/model/dls-requests.model';

@Injectable({ providedIn: 'root' })
export class DlsRequestsResolve implements Resolve<IDlsRequests> {
  constructor(private service: DlsRequestsService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDlsRequests> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((dlsRequests: HttpResponse<DlsRequests>) => dlsRequests.body));
    }
    return of(new DlsRequests());
  }
}

export const dlsRequestsRoute: Routes = [
  {
    path: '',
    component: DlsRequestsComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'dlssyncApp.dlsRequests.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DlsRequestsDetailComponent,
    resolve: {
      dlsRequests: DlsRequestsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.dlsRequests.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DlsRequestsUpdateComponent,
    resolve: {
      dlsRequests: DlsRequestsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.dlsRequests.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DlsRequestsUpdateComponent,
    resolve: {
      dlsRequests: DlsRequestsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.dlsRequests.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
