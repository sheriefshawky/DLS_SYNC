import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DlssyncTestModule } from '../../../test.module';
import { DlsReqTrainingsDeleteDialogComponent } from 'app/entities/dls-req-trainings/dls-req-trainings-delete-dialog.component';
import { DlsReqTrainingsService } from 'app/entities/dls-req-trainings/dls-req-trainings.service';

describe('Component Tests', () => {
  describe('DlsReqTrainings Management Delete Component', () => {
    let comp: DlsReqTrainingsDeleteDialogComponent;
    let fixture: ComponentFixture<DlsReqTrainingsDeleteDialogComponent>;
    let service: DlsReqTrainingsService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DlssyncTestModule],
        declarations: [DlsReqTrainingsDeleteDialogComponent]
      })
        .overrideTemplate(DlsReqTrainingsDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DlsReqTrainingsDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DlsReqTrainingsService);
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
