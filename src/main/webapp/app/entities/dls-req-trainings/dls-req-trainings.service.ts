import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDlsReqTrainings } from 'app/shared/model/dls-req-trainings.model';

type EntityResponseType = HttpResponse<IDlsReqTrainings>;
type EntityArrayResponseType = HttpResponse<IDlsReqTrainings[]>;

@Injectable({ providedIn: 'root' })
export class DlsReqTrainingsService {
  public resourceUrl = SERVER_API_URL + 'api/dls-req-trainings';

  constructor(protected http: HttpClient) {}

  create(dlsReqTrainings: IDlsReqTrainings): Observable<EntityResponseType> {
    return this.http.post<IDlsReqTrainings>(this.resourceUrl, dlsReqTrainings, { observe: 'response' });
  }

  update(dlsReqTrainings: IDlsReqTrainings): Observable<EntityResponseType> {
    return this.http.put<IDlsReqTrainings>(this.resourceUrl, dlsReqTrainings, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDlsReqTrainings>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDlsReqTrainings[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
