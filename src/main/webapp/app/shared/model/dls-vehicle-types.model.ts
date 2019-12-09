import { IDlsVhlTrsExams } from 'app/shared/model/dls-vhl-trs-exams.model';

export interface IDlsVehicleTypes {
  id?: number;
  code?: string;
  nameAr?: string;
  nameEn?: string;
  dlsVhlTrsExams?: IDlsVhlTrsExams;
}

export class DlsVehicleTypes implements IDlsVehicleTypes {
  constructor(
    public id?: number,
    public code?: string,
    public nameAr?: string,
    public nameEn?: string,
    public dlsVhlTrsExams?: IDlsVhlTrsExams
  ) {}
}
