import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { DlsVehicleTypes } from 'app/shared/model/dls-vehicle-types.model';
import { DlsVehicleTypesService } from './dls-vehicle-types.service';
import { DlsVehicleTypesComponent } from './dls-vehicle-types.component';
import { DlsVehicleTypesDetailComponent } from './dls-vehicle-types-detail.component';
import { DlsVehicleTypesUpdateComponent } from './dls-vehicle-types-update.component';
import { IDlsVehicleTypes } from 'app/shared/model/dls-vehicle-types.model';

@Injectable({ providedIn: 'root' })
export class DlsVehicleTypesResolve implements Resolve<IDlsVehicleTypes> {
  constructor(private service: DlsVehicleTypesService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDlsVehicleTypes> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((dlsVehicleTypes: HttpResponse<DlsVehicleTypes>) => dlsVehicleTypes.body));
    }
    return of(new DlsVehicleTypes());
  }
}

export const dlsVehicleTypesRoute: Routes = [
  {
    path: '',
    component: DlsVehicleTypesComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.dlsVehicleTypes.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DlsVehicleTypesDetailComponent,
    resolve: {
      dlsVehicleTypes: DlsVehicleTypesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.dlsVehicleTypes.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DlsVehicleTypesUpdateComponent,
    resolve: {
      dlsVehicleTypes: DlsVehicleTypesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.dlsVehicleTypes.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DlsVehicleTypesUpdateComponent,
    resolve: {
      dlsVehicleTypes: DlsVehicleTypesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'dlssyncApp.dlsVehicleTypes.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
