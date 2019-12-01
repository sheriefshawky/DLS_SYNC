import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DlssyncTestModule } from '../../../test.module';
import { DlsPersonsDeleteDialogComponent } from 'app/entities/dls-persons/dls-persons-delete-dialog.component';
import { DlsPersonsService } from 'app/entities/dls-persons/dls-persons.service';

describe('Component Tests', () => {
  describe('DlsPersons Management Delete Component', () => {
    let comp: DlsPersonsDeleteDialogComponent;
    let fixture: ComponentFixture<DlsPersonsDeleteDialogComponent>;
    let service: DlsPersonsService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DlssyncTestModule],
        declarations: [DlsPersonsDeleteDialogComponent]
      })
        .overrideTemplate(DlsPersonsDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DlsPersonsDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DlsPersonsService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
