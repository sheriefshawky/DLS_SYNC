import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DlssyncTestModule } from '../../../test.module';
import { DlsTrsTypesDetailComponent } from 'app/entities/dls-trs-types/dls-trs-types-detail.component';
import { DlsTrsTypes } from 'app/shared/model/dls-trs-types.model';

describe('Component Tests', () => {
  describe('DlsTrsTypes Management Detail Component', () => {
    let comp: DlsTrsTypesDetailComponent;
    let fixture: ComponentFixture<DlsTrsTypesDetailComponent>;
    const route = ({ data: of({ dlsTrsTypes: new DlsTrsTypes(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DlssyncTestModule],
        declarations: [DlsTrsTypesDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(DlsTrsTypesDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DlsTrsTypesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.dlsTrsTypes).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
