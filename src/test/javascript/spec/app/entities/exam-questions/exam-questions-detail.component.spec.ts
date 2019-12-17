import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DlssyncTestModule } from '../../../test.module';
import { ExamQuestionsDetailComponent } from 'app/entities/exam-questions/exam-questions-detail.component';
import { ExamQuestions } from 'app/shared/model/exam-questions.model';

describe('Component Tests', () => {
  describe('ExamQuestions Management Detail Component', () => {
    let comp: ExamQuestionsDetailComponent;
    let fixture: ComponentFixture<ExamQuestionsDetailComponent>;
    const route = ({ data: of({ examQuestions: new ExamQuestions(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DlssyncTestModule],
        declarations: [ExamQuestionsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ExamQuestionsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ExamQuestionsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.examQuestions).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
