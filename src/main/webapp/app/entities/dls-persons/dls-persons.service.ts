import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDlsPersons } from 'app/shared/model/dls-persons.model';

type EntityResponseType = HttpResponse<IDlsPersons>;
type EntityArrayResponseType = HttpResponse<IDlsPersons[]>;

@Injectable({ providedIn: 'root' })
export class DlsPersonsService {
  public resourceUrl = SERVER_API_URL + 'api/dls-persons';

  constructor(protected http: HttpClient) {}

  create(dlsPersons: IDlsPersons): Observable<EntityResponseType> {
    return this.http.post<IDlsPersons>(this.resourceUrl, dlsPersons, { observe: 'response' });
  }

  update(dlsPersons: IDlsPersons): Observable<EntityResponseType> {
    return this.http.put<IDlsPersons>(this.resourceUrl, dlsPersons, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDlsPersons>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDlsPersons[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
