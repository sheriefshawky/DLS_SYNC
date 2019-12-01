import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { DlssyncTestModule } from '../../../test.module';
import { DlsReqTrainingsUpdateComponent } from 'app/entities/dls-req-trainings/dls-req-trainings-update.component';
import { DlsReqTrainingsService } from 'app/entities/dls-req-trainings/dls-req-trainings.service';
import { DlsReqTrainings } from 'app/shared/model/dls-req-trainings.model';

describe('Component Tests', () => {
  describe('DlsReqTrainings Management Update Component', () => {
    let comp: DlsReqTrainingsUpdateComponent;
    let fixture: ComponentFixture<DlsReqTrainingsUpdateComponent>;
    let service: DlsReqTrainingsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DlssyncTestModule],
        declarations: [DlsReqTrainingsUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(DlsReqTrainingsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DlsReqTrainingsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DlsReqTrainingsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DlsReqTrainings(123);
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
        const entity = new DlsReqTrainings();
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
