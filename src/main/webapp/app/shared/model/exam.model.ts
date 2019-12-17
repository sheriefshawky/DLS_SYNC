import { Moment } from 'moment';
import { ITemplate } from 'app/shared/model/template.model';
import { IExamQuestions } from 'app/shared/model/exam-questions.model';

export interface IExam {
  id?: number;
  validfrom?: Moment;
  timeInSec?: number;
  score?: number;
  status?: number;
  passScore?: number;
  template?: ITemplate;
  examQuestions?: IExamQuestions[];
}

export class Exam implements IExam {
  constructor(
    public id?: number,
    public validfrom?: Moment,
    public timeInSec?: number,
    public score?: number,
    public status?: number,
    public passScore?: number,
    public template?: ITemplate,
    public examQuestions?: IExamQuestions[]
  ) {}
}
