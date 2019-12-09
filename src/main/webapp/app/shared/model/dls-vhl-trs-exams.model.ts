import { IDlsExams } from 'app/shared/model/dls-exams.model';
import { IDlsVehicleTypes } from 'app/shared/model/dls-vehicle-types.model';
import { IDlsTrsTypes } from 'app/shared/model/dls-trs-types.model';

export interface IDlsVhlTrsExams {
  id?: number;
  code?: string;
  nameAr?: string;
  nameEn?: string;
  exams?: IDlsExams[];
  vehicleTypes?: IDlsVehicleTypes[];
  trsTypes?: IDlsTrsTypes[];
}

export class DlsVhlTrsExams implements IDlsVhlTrsExams {
  constructor(
    public id?: number,
    public code?: string,
    public nameAr?: string,
    public nameEn?: string,
    public exams?: IDlsExams[],
    public vehicleTypes?: IDlsVehicleTypes[],
    public trsTypes?: IDlsTrsTypes[]
  ) {}
}
