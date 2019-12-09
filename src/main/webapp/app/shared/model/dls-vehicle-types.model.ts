export interface IDlsVehicleTypes {
  id?: number;
  code?: string;
  nameAr?: string;
  nameEn?: string;
}

export class DlsVehicleTypes implements IDlsVehicleTypes {
  constructor(public id?: number, public code?: string, public nameAr?: string, public nameEn?: string) {}
}
