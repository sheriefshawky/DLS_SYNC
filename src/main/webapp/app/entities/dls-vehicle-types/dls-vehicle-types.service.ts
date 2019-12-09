import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDlsVehicleTypes } from 'app/shared/model/dls-vehicle-types.model';

type EntityResponseType = HttpResponse<IDlsVehicleTypes>;
type EntityArrayResponseType = HttpResponse<IDlsVehicleTypes[]>;

@Injectable({ providedIn: 'root' })
export class DlsVehicleTypesService {
  public resourceUrl = SERVER_API_URL + 'api/dls-vehicle-types';

  constructor(protected http: HttpClient) {}

  create(dlsVehicleTypes: IDlsVehicleTypes): Observable<EntityResponseType> {
    return this.http.post<IDlsVehicleTypes>(this.resourceUrl, dlsVehicleTypes, { observe: 'response' });
  }

  update(dlsVehicleTypes: IDlsVehicleTypes): Observable<EntityResponseType> {
    return this.http.put<IDlsVehicleTypes>(this.resourceUrl, dlsVehicleTypes, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDlsVehicleTypes>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDlsVehicleTypes[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
