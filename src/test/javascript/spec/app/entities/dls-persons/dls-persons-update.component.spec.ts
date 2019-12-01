import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { DlssyncTestModule } from '../../../test.module';
import { DlsPersonsUpdateComponent } from 'app/entities/dls-persons/dls-persons-update.component';
import { DlsPersonsService } from 'app/entities/dls-persons/dls-persons.service';
import { DlsPersons } from 'app/shared/model/dls-persons.model';

describe('Component Tests', () => {
  describe('DlsPersons Management Update Component', () => {
    let comp: DlsPersonsUpdateComponent;
    let fixture: ComponentFixture<DlsPersonsUpdateComponent>;
    let service: DlsPersonsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DlssyncTestModule],
        declarations: [DlsPersonsUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(DlsPersonsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DlsPersonsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DlsPersonsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DlsPersons(123);
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
        const entity = new DlsPersons();
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
