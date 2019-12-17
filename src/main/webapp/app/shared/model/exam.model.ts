import { Moment } from 'moment';
import { ITemplate } from 'app/shared/model/template.model';
import { IExamQuestions } from 'app/shared/model/exam-questions.model';

export interface IExam {
  id?: number;
  validfrom?: Moment;
  validto?: Moment;
  timeInSec?: number;
  score?: number;
  status?: number;
  passScore?: number;
  startAt?: Moment;
  submitAt?: Moment;
  template?: ITemplate;
  examQuestions?: IExamQuestions[];
}

export class Exam implements IExam {
  constructor(
    public id?: number,
    public validfrom?: Moment,
    public validto?: Moment,
    public timeInSec?: number,
    public score?: number,
    public status?: number,
    public passScore?: number,
    public startAt?: Moment,
    public submitAt?: Moment,
    public template?: ITemplate,
    public examQuestions?: IExamQuestions[]
  ) {}
}
