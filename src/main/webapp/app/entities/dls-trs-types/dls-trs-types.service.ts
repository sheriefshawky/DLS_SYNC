import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDlsTrsTypes } from 'app/shared/model/dls-trs-types.model';

type EntityResponseType = HttpResponse<IDlsTrsTypes>;
type EntityArrayResponseType = HttpResponse<IDlsTrsTypes[]>;

@Injectable({ providedIn: 'root' })
export class DlsTrsTypesService {
  public resourceUrl = SERVER_API_URL + 'api/dls-trs-types';

  constructor(protected http: HttpClient) {}

  create(dlsTrsTypes: IDlsTrsTypes): Observable<EntityResponseType> {
    return this.http.post<IDlsTrsTypes>(this.resourceUrl, dlsTrsTypes, { observe: 'response' });
  }

  update(dlsTrsTypes: IDlsTrsTypes): Observable<EntityResponseType> {
    return this.http.put<IDlsTrsTypes>(this.resourceUrl, dlsTrsTypes, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDlsTrsTypes>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDlsTrsTypes[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
