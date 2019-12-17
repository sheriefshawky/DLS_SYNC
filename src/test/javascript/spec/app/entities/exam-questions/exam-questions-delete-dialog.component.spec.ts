import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DlssyncTestModule } from '../../../test.module';
import { ExamQuestionsDeleteDialogComponent } from 'app/entities/exam-questions/exam-questions-delete-dialog.component';
import { ExamQuestionsService } from 'app/entities/exam-questions/exam-questions.service';

describe('Component Tests', () => {
  describe('ExamQuestions Management Delete Component', () => {
    let comp: ExamQuestionsDeleteDialogComponent;
    let fixture: ComponentFixture<ExamQuestionsDeleteDialogComponent>;
    let service: ExamQuestionsService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DlssyncTestModule],
        declarations: [ExamQuestionsDeleteDialogComponent]
      })
        .overrideTemplate(ExamQuestionsDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ExamQuestionsDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ExamQuestionsService);
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
