import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DlssyncTestModule } from '../../../test.module';
import { TemplateCategoriesDeleteDialogComponent } from 'app/entities/template-categories/template-categories-delete-dialog.component';
import { TemplateCategoriesService } from 'app/entities/template-categories/template-categories.service';

describe('Component Tests', () => {
  describe('TemplateCategories Management Delete Component', () => {
    let comp: TemplateCategoriesDeleteDialogComponent;
    let fixture: ComponentFixture<TemplateCategoriesDeleteDialogComponent>;
    let service: TemplateCategoriesService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DlssyncTestModule],
        declarations: [TemplateCategoriesDeleteDialogComponent]
      })
        .overrideTemplate(TemplateCategoriesDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TemplateCategoriesDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TemplateCategoriesService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
