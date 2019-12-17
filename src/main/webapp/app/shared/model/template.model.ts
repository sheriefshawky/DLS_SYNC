import { ITemplateCategories } from 'app/shared/model/template-categories.model';

export interface ITemplate {
  id?: number;
  nameAr?: string;
  nameEn?: string;
  code?: string;
  timeInSec?: number;
  passScore?: number;
  status?: number;
  categories?: ITemplateCategories[];
}

export class Template implements ITemplate {
  constructor(
    public id?: number,
    public nameAr?: string,
    public nameEn?: string,
    public code?: string,
    public timeInSec?: number,
    public passScore?: number,
    public status?: number,
    public categories?: ITemplateCategories[]
  ) {}
}
