import { IQuestions } from 'app/shared/model/questions.model';

export interface IAnswers {
  id?: number;
  descAr?: string;
  descEn?: string;
  code?: string;
  imgPath?: string;
  isRightAnswer?: boolean;
  status?: number;
  questions?: IQuestions;
}

export class Answers implements IAnswers {
  constructor(
    public id?: number,
    public descAr?: string,
    public descEn?: string,
    public code?: string,
    public imgPath?: string,
    public isRightAnswer?: boolean,
    public status?: number,
    public questions?: IQuestions
  ) {
    this.isRightAnswer = this.isRightAnswer || false;
  }
}
