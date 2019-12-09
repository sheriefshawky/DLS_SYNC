import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDlsExams } from 'app/shared/model/dls-exams.model';

type EntityResponseType = HttpResponse<IDlsExams>;
type EntityArrayResponseType = HttpResponse<IDlsExams[]>;

@Injectable({ providedIn: 'root' })
export class DlsExamsService {
  public resourceUrl = SERVER_API_URL + 'api/dls-exams';

  constructor(protected http: HttpClient) {}

  create(dlsExams: IDlsExams): Observable<EntityResponseType> {
    return this.http.post<IDlsExams>(this.resourceUrl, dlsExams, { observe: 'response' });
  }

  update(dlsExams: IDlsExams): Observable<EntityResponseType> {
    return this.http.put<IDlsExams>(this.resourceUrl, dlsExams, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDlsExams>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDlsExams[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
