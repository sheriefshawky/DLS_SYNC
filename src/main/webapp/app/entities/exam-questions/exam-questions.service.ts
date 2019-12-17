import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

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
    const copy = this.convertDateFromClient(examQuestions);
    return this.http
      .post<IExamQuestions>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(examQuestions: IExamQuestions): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(examQuestions);
    return this.http
      .put<IExamQuestions>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IExamQuestions>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IExamQuestions[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(examQuestions: IExamQuestions): IExamQuestions {
    const copy: IExamQuestions = Object.assign({}, examQuestions, {
      startAt: examQuestions.startAt != null && examQuestions.startAt.isValid() ? examQuestions.startAt.toJSON() : null,
      submitAt: examQuestions.submitAt != null && examQuestions.submitAt.isValid() ? examQuestions.submitAt.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.startAt = res.body.startAt != null ? moment(res.body.startAt) : null;
      res.body.submitAt = res.body.submitAt != null ? moment(res.body.submitAt) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((examQuestions: IExamQuestions) => {
        examQuestions.startAt = examQuestions.startAt != null ? moment(examQuestions.startAt) : null;
        examQuestions.submitAt = examQuestions.submitAt != null ? moment(examQuestions.submitAt) : null;
      });
    }
    return res;
  }
}
