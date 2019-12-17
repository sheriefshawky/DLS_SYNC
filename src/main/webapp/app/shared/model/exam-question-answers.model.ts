import { IExamQuestions } from 'app/shared/model/exam-questions.model';

export interface IExamQuestionAnswers {
  id?: number;
  descAr?: string;
  descEn?: string;
  code?: string;
  imgPath?: string;
  isRightAnswer?: boolean;
  status?: number;
  answerId?: number;
  examQuestions?: IExamQuestions;
}

export class ExamQuestionAnswers implements IExamQuestionAnswers {
  constructor(
    public id?: number,
    public descAr?: string,
    public descEn?: string,
    public code?: string,
    public imgPath?: string,
    public isRightAnswer?: boolean,
    public status?: number,
    public answerId?: number,
    public examQuestions?: IExamQuestions
  ) {
    this.isRightAnswer = this.isRightAnswer || false;
  }
}
