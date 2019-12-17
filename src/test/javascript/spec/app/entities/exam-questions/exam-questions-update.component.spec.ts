import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { DlssyncTestModule } from '../../../test.module';
import { ExamQuestionsUpdateComponent } from 'app/entities/exam-questions/exam-questions-update.component';
import { ExamQuestionsService } from 'app/entities/exam-questions/exam-questions.service';
import { ExamQuestions } from 'app/shared/model/exam-questions.model';

describe('Component Tests', () => {
  describe('ExamQuestions Management Update Component', () => {
    let comp: ExamQuestionsUpdateComponent;
    let fixture: ComponentFixture<ExamQuestionsUpdateComponent>;
    let service: ExamQuestionsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DlssyncTestModule],
        declarations: [ExamQuestionsUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ExamQuestionsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ExamQuestionsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ExamQuestionsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ExamQuestions(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new ExamQuestions();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
