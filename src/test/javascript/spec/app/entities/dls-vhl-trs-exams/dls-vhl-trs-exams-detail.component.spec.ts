import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DlssyncTestModule } from '../../../test.module';
import { DlsVhlTrsExamsDetailComponent } from 'app/entities/dls-vhl-trs-exams/dls-vhl-trs-exams-detail.component';
import { DlsVhlTrsExams } from 'app/shared/model/dls-vhl-trs-exams.model';

describe('Component Tests', () => {
  describe('DlsVhlTrsExams Management Detail Component', () => {
    let comp: DlsVhlTrsExamsDetailComponent;
    let fixture: ComponentFixture<DlsVhlTrsExamsDetailComponent>;
    const route = ({ data: of({ dlsVhlTrsExams: new DlsVhlTrsExams(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DlssyncTestModule],
        declarations: [DlsVhlTrsExamsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(DlsVhlTrsExamsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DlsVhlTrsExamsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.dlsVhlTrsExams).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
