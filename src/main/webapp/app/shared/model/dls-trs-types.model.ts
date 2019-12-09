export interface IDlsTrsTypes {
  id?: number;
  code?: string;
  nameAr?: string;
  nameEn?: string;
}

export class DlsTrsTypes implements IDlsTrsTypes {
  constructor(public id?: number, public code?: string, public nameAr?: string, public nameEn?: string) {}
}
