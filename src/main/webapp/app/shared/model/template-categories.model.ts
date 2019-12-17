import { ICategories } from 'app/shared/model/categories.model';
import { ITemplate } from 'app/shared/model/template.model';

export interface ITemplateCategories {
  id?: number;
  nameAr?: string;
  nameEn?: string;
  code?: string;
  noOfQuestions?: number;
  seq?: number;
  category?: ICategories;
  template?: ITemplate;
}

export class TemplateCategories implements ITemplateCategories {
  constructor(
    public id?: number,
    public nameAr?: string,
    public nameEn?: string,
    public code?: string,
    public noOfQuestions?: number,
    public seq?: number,
    public category?: ICategories,
    public template?: ITemplate
  ) {}
}
