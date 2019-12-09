export interface IDlsExams {
  id?: number;
  code?: string;
  nameAr?: string;
  nameEn?: string;
  testId?: string;
  qroupId?: string;
  passPercentage?: number;
}

export class DlsExams implements IDlsExams {
  constructor(
    public id?: number,
    public code?: string,
    public nameAr?: string,
    public nameEn?: string,
    public testId?: string,
    public qroupId?: string,
    public passPercentage?: number
  ) {}
}
