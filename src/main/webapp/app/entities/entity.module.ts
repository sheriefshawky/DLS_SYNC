import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'categories',
        loadChildren: () => import('./categories/categories.module').then(m => m.DlssyncCategoriesModule)
      },
      {
        path: 'questions',
        loadChildren: () => import('./questions/questions.module').then(m => m.DlssyncQuestionsModule)
      },
      {
        path: 'answers',
        loadChildren: () => import('./answers/answers.module').then(m => m.DlssyncAnswersModule)
      },
      {
        path: 'template',
        loadChildren: () => import('./template/template.module').then(m => m.DlssyncTemplateModule)
      },
      {
        path: 'template-categories',
        loadChildren: () => import('./template-categories/template-categories.module').then(m => m.DlssyncTemplateCategoriesModule)
      },
      {
        path: 'exam',
        loadChildren: () => import('./exam/exam.module').then(m => m.DlssyncExamModule)
      },
      {
        path: 'exam-questions',
        loadChildren: () => import('./exam-questions/exam-questions.module').then(m => m.DlssyncExamQuestionsModule)
      },
      {
        path: 'exam-question-answers',
        loadChildren: () => import('./exam-question-answers/exam-question-answers.module').then(m => m.DlssyncExamQuestionAnswersModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class DlssyncEntityModule {}
