import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IExam } from 'app/shared/model/exam.model';

type EntityResponseType = HttpResponse<IExam>;
type EntityArrayResponseType = HttpResponse<IExam[]>;

@Injectable({ providedIn: 'root' })
export class ExamService {
  public resourceUrl = SERVER_API_URL + 'api/exams';

  constructor(protected http: HttpClient) {}

  create(exam: IExam): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(exam);
    return this.http
      .post<IExam>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(exam: IExam): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(exam);
    return this.http
      .put<IExam>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IExam>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IExam[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(exam: IExam): IExam {
    const copy: IExam = Object.assign({}, exam, {
      validfrom: exam.validfrom != null && exam.validfrom.isValid() ? exam.validfrom.toJSON() : null,
      validto: exam.validto != null && exam.validto.isValid() ? exam.validto.toJSON() : null,
      startAt: exam.startAt != null && exam.startAt.isValid() ? exam.startAt.toJSON() : null,
      submitAt: exam.submitAt != null && exam.submitAt.isValid() ? exam.submitAt.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.validfrom = res.body.validfrom != null ? moment(res.body.validfrom) : null;
      res.body.validto = res.body.validto != null ? moment(res.body.validto) : null;
      res.body.startAt = res.body.startAt != null ? moment(res.body.startAt) : null;
      res.body.submitAt = res.body.submitAt != null ? moment(res.body.submitAt) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((exam: IExam) => {
        exam.validfrom = exam.validfrom != null ? moment(exam.validfrom) : null;
        exam.validto = exam.validto != null ? moment(exam.validto) : null;
        exam.startAt = exam.startAt != null ? moment(exam.startAt) : null;
        exam.submitAt = exam.submitAt != null ? moment(exam.submitAt) : null;
      });
    }
    return res;
  }
}
