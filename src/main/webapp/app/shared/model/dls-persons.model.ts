import { IDlsRequests } from 'app/shared/model/dls-requests.model';

export interface IDlsPersons {
  id?: number;
  fullName?: string;
  mobileNo?: string;
  licenseCategory?: number;
  nationalId?: string;
  passportKey?: string;
  transactionType?: number;
  exported?: boolean;
  requests?: IDlsRequests[];
}

export class DlsPersons implements IDlsPersons {
  constructor(
    public id?: number,
    public fullName?: string,
    public mobileNo?: string,
    public licenseCategory?: number,
    public nationalId?: string,
    public passportKey?: string,
    public transactionType?: number,
    public exported?: boolean,
    public requests?: IDlsRequests[]
  ) {
    this.exported = this.exported || false;
  }
}
