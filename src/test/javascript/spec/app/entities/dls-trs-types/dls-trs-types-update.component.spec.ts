import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { DlssyncTestModule } from '../../../test.module';
import { DlsTrsTypesUpdateComponent } from 'app/entities/dls-trs-types/dls-trs-types-update.component';
import { DlsTrsTypesService } from 'app/entities/dls-trs-types/dls-trs-types.service';
import { DlsTrsTypes } from 'app/shared/model/dls-trs-types.model';

describe('Component Tests', () => {
  describe('DlsTrsTypes Management Update Component', () => {
    let comp: DlsTrsTypesUpdateComponent;
    let fixture: ComponentFixture<DlsTrsTypesUpdateComponent>;
    let service: DlsTrsTypesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DlssyncTestModule],
        declarations: [DlsTrsTypesUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(DlsTrsTypesUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DlsTrsTypesUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DlsTrsTypesService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DlsTrsTypes(123);
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
        const entity = new DlsTrsTypes();
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
