import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DlssyncSharedModule } from 'app/shared/shared.module';
import { ExamQuestionsComponent } from './exam-questions.component';
import { ExamQuestionsDetailComponent } from './exam-questions-detail.component';
import { ExamQuestionsUpdateComponent } from './exam-questions-update.component';
import { ExamQuestionsDeleteDialogComponent } from './exam-questions-delete-dialog.component';
import { examQuestionsRoute } from './exam-questions.route';

@NgModule({
  imports: [DlssyncSharedModule, RouterModule.forChild(examQuestionsRoute)],
  declarations: [ExamQuestionsComponent, ExamQuestionsDetailComponent, ExamQuestionsUpdateComponent, ExamQuestionsDeleteDialogComponent],
  entryComponents: [ExamQuestionsDeleteDialogComponent]
})
export class DlssyncExamQuestionsModule {}
