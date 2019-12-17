import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITemplateCategories } from 'app/shared/model/template-categories.model';

type EntityResponseType = HttpResponse<ITemplateCategories>;
type EntityArrayResponseType = HttpResponse<ITemplateCategories[]>;

@Injectable({ providedIn: 'root' })
export class TemplateCategoriesService {
  public resourceUrl = SERVER_API_URL + 'api/template-categories';

  constructor(protected http: HttpClient) {}

  create(templateCategories: ITemplateCategories): Observable<EntityResponseType> {
    return this.http.post<ITemplateCategories>(this.resourceUrl, templateCategories, { observe: 'response' });
  }

  update(templateCategories: ITemplateCategories): Observable<EntityResponseType> {
    return this.http.put<ITemplateCategories>(this.resourceUrl, templateCategories, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITemplateCategories>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITemplateCategories[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
