import { IQuestions } from 'app/shared/model/questions.model';

export interface ICategories {
  id?: number;
  nameAr?: string;
  nameEn?: string;
  code?: string;
  status?: number;
  questions?: IQuestions[];
}

export class Categories implements ICategories {
  constructor(
    public id?: number,
    public nameAr?: string,
    public nameEn?: string,
    public code?: string,
    public status?: number,
    public questions?: IQuestions[]
  ) {}
}
