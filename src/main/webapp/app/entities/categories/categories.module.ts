import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DlssyncSharedModule } from 'app/shared/shared.module';
import { CategoriesComponent } from './categories.component';
import { CategoriesDetailComponent } from './categories-detail.component';
import { CategoriesUpdateComponent } from './categories-update.component';
import { CategoriesDeleteDialogComponent } from './categories-delete-dialog.component';
import { categoriesRoute } from './categories.route';

@NgModule({
  imports: [DlssyncSharedModule, RouterModule.forChild(categoriesRoute)],
  declarations: [CategoriesComponent, CategoriesDetailComponent, CategoriesUpdateComponent, CategoriesDeleteDialogComponent],
  entryComponents: [CategoriesDeleteDialogComponent]
})
export class DlssyncCategoriesModule {}
