import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DlssyncTestModule } from '../../../test.module';
import { DlsReqTrainingsDetailComponent } from 'app/entities/dls-req-trainings/dls-req-trainings-detail.component';
import { DlsReqTrainings } from 'app/shared/model/dls-req-trainings.model';

describe('Component Tests', () => {
  describe('DlsReqTrainings Management Detail Component', () => {
    let comp: DlsReqTrainingsDetailComponent;
    let fixture: ComponentFixture<DlsReqTrainingsDetailComponent>;
    const route = ({ data: of({ dlsReqTrainings: new DlsReqTrainings(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DlssyncTestModule],
        declarations: [DlsReqTrainingsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(DlsReqTrainingsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DlsReqTrainingsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.dlsReqTrainings).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
