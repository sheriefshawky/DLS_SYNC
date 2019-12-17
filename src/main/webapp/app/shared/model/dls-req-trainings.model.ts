import { IDlsRequests } from 'app/shared/model/dls-requests.model';

export interface IDlsReqTrainings {
  id?: number;
  trainingCode?: number;
  trainingLectures?: number;
  trainingfulfilled?: number;
  exported?: boolean;
  dlsRequests?: IDlsRequests;
}

export class DlsReqTrainings implements IDlsReqTrainings {
  constructor(
    public id?: number,
    public trainingCode?: number,
    public trainingLectures?: number,
    public trainingfulfilled?: number,
    public exported?: boolean,
    public dlsRequests?: IDlsRequests
  ) {
    this.exported = this.exported || false;
  }
}
