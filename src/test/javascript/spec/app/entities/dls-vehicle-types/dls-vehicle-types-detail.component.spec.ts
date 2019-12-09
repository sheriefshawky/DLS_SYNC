import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DlssyncTestModule } from '../../../test.module';
import { DlsVehicleTypesDetailComponent } from 'app/entities/dls-vehicle-types/dls-vehicle-types-detail.component';
import { DlsVehicleTypes } from 'app/shared/model/dls-vehicle-types.model';

describe('Component Tests', () => {
  describe('DlsVehicleTypes Management Detail Component', () => {
    let comp: DlsVehicleTypesDetailComponent;
    let fixture: ComponentFixture<DlsVehicleTypesDetailComponent>;
    const route = ({ data: of({ dlsVehicleTypes: new DlsVehicleTypes(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DlssyncTestModule],
        declarations: [DlsVehicleTypesDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(DlsVehicleTypesDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DlsVehicleTypesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.dlsVehicleTypes).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
