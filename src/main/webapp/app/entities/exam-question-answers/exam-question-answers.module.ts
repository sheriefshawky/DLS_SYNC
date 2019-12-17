import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DlssyncSharedModule } from 'app/shared/shared.module';
import { ExamQuestionAnswersComponent } from './exam-question-answers.component';
import { ExamQuestionAnswersDetailComponent } from './exam-question-answers-detail.component';
import { ExamQuestionAnswersUpdateComponent } from './exam-question-answers-update.component';
import { ExamQuestionAnswersDeleteDialogComponent } from './exam-question-answers-delete-dialog.component';
import { examQuestionAnswersRoute } from './exam-question-answers.route';

@NgModule({
  imports: [DlssyncSharedModule, RouterModule.forChild(examQuestionAnswersRoute)],
  declarations: [
    ExamQuestionAnswersComponent,
    ExamQuestionAnswersDetailComponent,
    ExamQuestionAnswersUpdateComponent,
    ExamQuestionAnswersDeleteDialogComponent
  ],
  entryComponents: [ExamQuestionAnswersDeleteDialogComponent]
})
export class DlssyncExamQuestionAnswersModule {}
