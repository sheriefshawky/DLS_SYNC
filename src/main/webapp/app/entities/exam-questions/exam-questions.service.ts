import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IExamQuestions } from 'app/shared/model/exam-questions.model';

type EntityResponseType = HttpResponse<IExamQuestions>;
type EntityArrayResponseType = HttpResponse<IExamQuestions[]>;

@Injectable({ providedIn: 'root' })
export class ExamQuestionsService {
  public resourceUrl = SERVER_API_URL + 'api/exam-questions';

  constructor(protected http: HttpClient) {}

  create(examQuestions: IExamQuestions): Observable<EntityResponseType> {
    return this.http.post<IExamQuestions>(this.resourceUrl, examQuestions, { observe: 'response' });
  }

  update(examQuestions: IExamQuestions): Observable<EntityResponseType> {
    return this.http.put<IExamQuestions>(this.resourceUrl, examQuestions, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IExamQuestions>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IExamQuestions[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
