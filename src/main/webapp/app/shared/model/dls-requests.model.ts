import { IDlsReqExams } from 'app/shared/model/dls-req-exams.model';
import { IDlsReqTrainings } from 'app/shared/model/dls-req-trainings.model';
import { IDlsPersons } from 'app/shared/model/dls-persons.model';

export interface IDlsRequests {
  id?: number;
  transactionType?: number;
  requestNo?: number;
  exported?: boolean;
  exams?: IDlsReqExams[];
  trainings?: IDlsReqTrainings[];
  dlsPersons?: IDlsPersons;
}

export class DlsRequests implements IDlsRequests {
  constructor(
    public id?: number,
    public transactionType?: number,
    public requestNo?: number,
    public exported?: boolean,
    public exams?: IDlsReqExams[],
    public trainings?: IDlsReqTrainings[],
    public dlsPersons?: IDlsPersons
  ) {
    this.exported = this.exported || false;
  }
}
