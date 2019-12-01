import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDlsRequests } from 'app/shared/model/dls-requests.model';

type EntityResponseType = HttpResponse<IDlsRequests>;
type EntityArrayResponseType = HttpResponse<IDlsRequests[]>;

@Injectable({ providedIn: 'root' })
export class DlsRequestsService {
  public resourceUrl = SERVER_API_URL + 'api/dls-requests';

  constructor(protected http: HttpClient) {}

  create(dlsRequests: IDlsRequests): Observable<EntityResponseType> {
    return this.http.post<IDlsRequests>(this.resourceUrl, dlsRequests, { observe: 'response' });
  }

  update(dlsRequests: IDlsRequests): Observable<EntityResponseType> {
    return this.http.put<IDlsRequests>(this.resourceUrl, dlsRequests, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDlsRequests>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDlsRequests[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
