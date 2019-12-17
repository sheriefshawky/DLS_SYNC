import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DlssyncTestModule } from '../../../test.module';
import { DlsRequestsDeleteDialogComponent } from 'app/entities/dls-requests/dls-requests-delete-dialog.component';
import { DlsRequestsService } from 'app/entities/dls-requests/dls-requests.service';

describe('Component Tests', () => {
  describe('DlsRequests Management Delete Component', () => {
    let comp: DlsRequestsDeleteDialogComponent;
    let fixture: ComponentFixture<DlsRequestsDeleteDialogComponent>;
    let service: DlsRequestsService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DlssyncTestModule],
        declarations: [DlsRequestsDeleteDialogComponent]
      })
        .overrideTemplate(DlsRequestsDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DlsRequestsDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DlsRequestsService);
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
