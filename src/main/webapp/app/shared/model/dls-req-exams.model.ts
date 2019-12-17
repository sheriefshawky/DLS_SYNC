import { Moment } from 'moment';
import { IDlsRequests } from 'app/shared/model/dls-requests.model';

export interface IDlsReqExams {
  id?: number;
  examCode?: number;
  examResult?: number;
  examDate?: Moment;
  examGrade?: number;
  isEligibile?: number;
  exported?: boolean;
  dlsRequests?: IDlsRequests;
}

export class DlsReqExams implements IDlsReqExams {
  constructor(
    public id?: number,
    public examCode?: number,
    public examResult?: number,
    public examDate?: Moment,
    public examGrade?: number,
    public isEligibile?: number,
    public exported?: boolean,
    public dlsRequests?: IDlsRequests
  ) {
    this.exported = this.exported || false;
  }
}
