import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IExamQuestionAnswers } from 'app/shared/model/exam-question-answers.model';

type EntityResponseType = HttpResponse<IExamQuestionAnswers>;
type EntityArrayResponseType = HttpResponse<IExamQuestionAnswers[]>;

@Injectable({ providedIn: 'root' })
export class ExamQuestionAnswersService {
  public resourceUrl = SERVER_API_URL + 'api/exam-question-answers';

  constructor(protected http: HttpClient) {}

  create(examQuestionAnswers: IExamQuestionAnswers): Observable<EntityResponseType> {
    return this.http.post<IExamQuestionAnswers>(this.resourceUrl, examQuestionAnswers, { observe: 'response' });
  }

  update(examQuestionAnswers: IExamQuestionAnswers): Observable<EntityResponseType> {
    return this.http.put<IExamQuestionAnswers>(this.resourceUrl, examQuestionAnswers, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IExamQuestionAnswers>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IExamQuestionAnswers[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
