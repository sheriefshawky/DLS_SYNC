import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DlssyncTestModule } from '../../../test.module';
import { ExamQuestionAnswersDeleteDialogComponent } from 'app/entities/exam-question-answers/exam-question-answers-delete-dialog.component';
import { ExamQuestionAnswersService } from 'app/entities/exam-question-answers/exam-question-answers.service';

describe('Component Tests', () => {
  describe('ExamQuestionAnswers Management Delete Component', () => {
    let comp: ExamQuestionAnswersDeleteDialogComponent;
    let fixture: ComponentFixture<ExamQuestionAnswersDeleteDialogComponent>;
    let service: ExamQuestionAnswersService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DlssyncTestModule],
        declarations: [ExamQuestionAnswersDeleteDialogComponent]
      })
        .overrideTemplate(ExamQuestionAnswersDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ExamQuestionAnswersDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ExamQuestionAnswersService);
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
