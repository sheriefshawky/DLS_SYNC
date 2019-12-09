import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { DlssyncTestModule } from '../../../test.module';
import { DlsVehicleTypesComponent } from 'app/entities/dls-vehicle-types/dls-vehicle-types.component';
import { DlsVehicleTypesService } from 'app/entities/dls-vehicle-types/dls-vehicle-types.service';
import { DlsVehicleTypes } from 'app/shared/model/dls-vehicle-types.model';

describe('Component Tests', () => {
  describe('DlsVehicleTypes Management Component', () => {
    let comp: DlsVehicleTypesComponent;
    let fixture: ComponentFixture<DlsVehicleTypesComponent>;
    let service: DlsVehicleTypesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DlssyncTestModule],
        declarations: [DlsVehicleTypesComponent],
        providers: []
      })
        .overrideTemplate(DlsVehicleTypesComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DlsVehicleTypesComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DlsVehicleTypesService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new DlsVehicleTypes(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.dlsVehicleTypes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
