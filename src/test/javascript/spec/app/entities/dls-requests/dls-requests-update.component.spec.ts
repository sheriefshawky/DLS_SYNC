import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { DlssyncTestModule } from '../../../test.module';
import { DlsRequestsUpdateComponent } from 'app/entities/dls-requests/dls-requests-update.component';
import { DlsRequestsService } from 'app/entities/dls-requests/dls-requests.service';
import { DlsRequests } from 'app/shared/model/dls-requests.model';

describe('Component Tests', () => {
  describe('DlsRequests Management Update Component', () => {
    let comp: DlsRequestsUpdateComponent;
    let fixture: ComponentFixture<DlsRequestsUpdateComponent>;
    let service: DlsRequestsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DlssyncTestModule],
        declarations: [DlsRequestsUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(DlsRequestsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DlsRequestsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DlsRequestsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DlsRequests(123);
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
        const entity = new DlsRequests();
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
