import { IExamQuestionAnswers } from 'app/shared/model/exam-question-answers.model';
import { IExam } from 'app/shared/model/exam.model';

export interface IExamQuestions {
  id?: number;
  descAr?: string;
  descEn?: string;
  code?: string;
  imgPath?: string;
  timeInSec?: number;
  type?: number;
  weight?: number;
  score?: number;
  status?: number;
  seq?: number;
  categoryId?: number;
  questionId?: number;
  examQuestionAnswers?: IExamQuestionAnswers[];
  exam?: IExam;
}

export class ExamQuestions implements IExamQuestions {
  constructor(
    public id?: number,
    public descAr?: string,
    public descEn?: string,
    public code?: string,
    public imgPath?: string,
    public timeInSec?: number,
    public type?: number,
    public weight?: number,
    public score?: number,
    public status?: number,
    public seq?: number,
    public categoryId?: number,
    public questionId?: number,
    public examQuestionAnswers?: IExamQuestionAnswers[],
    public exam?: IExam
  ) {}
}
