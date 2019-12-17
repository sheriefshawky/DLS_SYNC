import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DlssyncSharedModule } from 'app/shared/shared.module';
import { TemplateCategoriesComponent } from './template-categories.component';
import { TemplateCategoriesDetailComponent } from './template-categories-detail.component';
import { TemplateCategoriesUpdateComponent } from './template-categories-update.component';
import { TemplateCategoriesDeleteDialogComponent } from './template-categories-delete-dialog.component';
import { templateCategoriesRoute } from './template-categories.route';

@NgModule({
  imports: [DlssyncSharedModule, RouterModule.forChild(templateCategoriesRoute)],
  declarations: [
    TemplateCategoriesComponent,
    TemplateCategoriesDetailComponent,
    TemplateCategoriesUpdateComponent,
    TemplateCategoriesDeleteDialogComponent
  ],
  entryComponents: [TemplateCategoriesDeleteDialogComponent]
})
export class DlssyncTemplateCategoriesModule {}
