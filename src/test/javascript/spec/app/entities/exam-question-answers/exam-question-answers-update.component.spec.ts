import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { DlssyncTestModule } from '../../../test.module';
import { ExamQuestionAnswersUpdateComponent } from 'app/entities/exam-question-answers/exam-question-answers-update.component';
import { ExamQuestionAnswersService } from 'app/entities/exam-question-answers/exam-question-answers.service';
import { ExamQuestionAnswers } from 'app/shared/model/exam-question-answers.model';

describe('Component Tests', () => {
  describe('ExamQuestionAnswers Management Update Component', () => {
    let comp: ExamQuestionAnswersUpdateComponent;
    let fixture: ComponentFixture<ExamQuestionAnswersUpdateComponent>;
    let service: ExamQuestionAnswersService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DlssyncTestModule],
        declarations: [ExamQuestionAnswersUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ExamQuestionAnswersUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ExamQuestionAnswersUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ExamQuestionAnswersService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ExamQuestionAnswers(123);
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
        const entity = new ExamQuestionAnswers();
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
