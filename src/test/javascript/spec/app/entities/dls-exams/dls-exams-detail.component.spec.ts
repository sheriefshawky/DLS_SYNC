import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DlssyncTestModule } from '../../../test.module';
import { DlsExamsDetailComponent } from 'app/entities/dls-exams/dls-exams-detail.component';
import { DlsExams } from 'app/shared/model/dls-exams.model';

describe('Component Tests', () => {
  describe('DlsExams Management Detail Component', () => {
    let comp: DlsExamsDetailComponent;
    let fixture: ComponentFixture<DlsExamsDetailComponent>;
    const route = ({ data: of({ dlsExams: new DlsExams(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DlssyncTestModule],
        declarations: [DlsExamsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(DlsExamsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DlsExamsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.dlsExams).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
