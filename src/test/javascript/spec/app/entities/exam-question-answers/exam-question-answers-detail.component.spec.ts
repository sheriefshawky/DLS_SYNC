import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DlssyncTestModule } from '../../../test.module';
import { ExamQuestionAnswersDetailComponent } from 'app/entities/exam-question-answers/exam-question-answers-detail.component';
import { ExamQuestionAnswers } from 'app/shared/model/exam-question-answers.model';

describe('Component Tests', () => {
  describe('ExamQuestionAnswers Management Detail Component', () => {
    let comp: ExamQuestionAnswersDetailComponent;
    let fixture: ComponentFixture<ExamQuestionAnswersDetailComponent>;
    const route = ({ data: of({ examQuestionAnswers: new ExamQuestionAnswers(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DlssyncTestModule],
        declarations: [ExamQuestionAnswersDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ExamQuestionAnswersDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ExamQuestionAnswersDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.examQuestionAnswers).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
