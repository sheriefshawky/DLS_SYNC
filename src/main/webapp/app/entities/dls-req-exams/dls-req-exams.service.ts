import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDlsReqExams } from 'app/shared/model/dls-req-exams.model';

type EntityResponseType = HttpResponse<IDlsReqExams>;
type EntityArrayResponseType = HttpResponse<IDlsReqExams[]>;

@Injectable({ providedIn: 'root' })
export class DlsReqExamsService {
  public resourceUrl = SERVER_API_URL + 'api/dls-req-exams';

  constructor(protected http: HttpClient) {}

  create(dlsReqExams: IDlsReqExams): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(dlsReqExams);
    return this.http
      .post<IDlsReqExams>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(dlsReqExams: IDlsReqExams): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(dlsReqExams);
    return this.http
      .put<IDlsReqExams>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IDlsReqExams>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IDlsReqExams[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(dlsReqExams: IDlsReqExams): IDlsReqExams {
    const copy: IDlsReqExams = Object.assign({}, dlsReqExams, {
      examDate: dlsReqExams.examDate != null && dlsReqExams.examDate.isValid() ? dlsReqExams.examDate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.examDate = res.body.examDate != null ? moment(res.body.examDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((dlsReqExams: IDlsReqExams) => {
        dlsReqExams.examDate = dlsReqExams.examDate != null ? moment(dlsReqExams.examDate) : null;
      });
    }
    return res;
  }
}
