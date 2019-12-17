import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DlssyncTestModule } from '../../../test.module';
import { DlsReqExamsDetailComponent } from 'app/entities/dls-req-exams/dls-req-exams-detail.component';
import { DlsReqExams } from 'app/shared/model/dls-req-exams.model';

describe('Component Tests', () => {
  describe('DlsReqExams Management Detail Component', () => {
    let comp: DlsReqExamsDetailComponent;
    let fixture: ComponentFixture<DlsReqExamsDetailComponent>;
    const route = ({ data: of({ dlsReqExams: new DlsReqExams(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DlssyncTestModule],
        declarations: [DlsReqExamsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(DlsReqExamsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DlsReqExamsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.dlsReqExams).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
