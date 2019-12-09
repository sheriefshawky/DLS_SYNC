import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDlsVhlTrsExams } from 'app/shared/model/dls-vhl-trs-exams.model';

type EntityResponseType = HttpResponse<IDlsVhlTrsExams>;
type EntityArrayResponseType = HttpResponse<IDlsVhlTrsExams[]>;

@Injectable({ providedIn: 'root' })
export class DlsVhlTrsExamsService {
  public resourceUrl = SERVER_API_URL + 'api/dls-vhl-trs-exams';

  constructor(protected http: HttpClient) {}

  create(dlsVhlTrsExams: IDlsVhlTrsExams): Observable<EntityResponseType> {
    return this.http.post<IDlsVhlTrsExams>(this.resourceUrl, dlsVhlTrsExams, { observe: 'response' });
  }

  update(dlsVhlTrsExams: IDlsVhlTrsExams): Observable<EntityResponseType> {
    return this.http.put<IDlsVhlTrsExams>(this.resourceUrl, dlsVhlTrsExams, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDlsVhlTrsExams>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDlsVhlTrsExams[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
