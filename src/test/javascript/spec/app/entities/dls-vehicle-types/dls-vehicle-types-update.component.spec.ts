import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { DlssyncTestModule } from '../../../test.module';
import { DlsVehicleTypesUpdateComponent } from 'app/entities/dls-vehicle-types/dls-vehicle-types-update.component';
import { DlsVehicleTypesService } from 'app/entities/dls-vehicle-types/dls-vehicle-types.service';
import { DlsVehicleTypes } from 'app/shared/model/dls-vehicle-types.model';

describe('Component Tests', () => {
  describe('DlsVehicleTypes Management Update Component', () => {
    let comp: DlsVehicleTypesUpdateComponent;
    let fixture: ComponentFixture<DlsVehicleTypesUpdateComponent>;
    let service: DlsVehicleTypesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DlssyncTestModule],
        declarations: [DlsVehicleTypesUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(DlsVehicleTypesUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DlsVehicleTypesUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DlsVehicleTypesService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DlsVehicleTypes(123);
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
        const entity = new DlsVehicleTypes();
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
