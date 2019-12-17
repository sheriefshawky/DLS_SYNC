import { IAnswers } from 'app/shared/model/answers.model';
import { ICategories } from 'app/shared/model/categories.model';

export interface IQuestions {
  id?: number;
  descAr?: string;
  descEn?: string;
  code?: string;
  imgPath?: string;
  timeInSec?: number;
  type?: number;
  weight?: number;
  status?: number;
  answers?: IAnswers[];
  categories?: ICategories;
}

export class Questions implements IQuestions {
  constructor(
    public id?: number,
    public descAr?: string,
    public descEn?: string,
    public code?: string,
    public imgPath?: string,
    public timeInSec?: number,
    public type?: number,
    public weight?: number,
    public status?: number,
    public answers?: IAnswers[],
    public categories?: ICategories
  ) {}
}
