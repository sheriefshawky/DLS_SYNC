import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DlssyncTestModule } from '../../../test.module';
import { DlsReqExamsDeleteDialogComponent } from 'app/entities/dls-req-exams/dls-req-exams-delete-dialog.component';
import { DlsReqExamsService } from 'app/entities/dls-req-exams/dls-req-exams.service';

describe('Component Tests', () => {
  describe('DlsReqExams Management Delete Component', () => {
    let comp: DlsReqExamsDeleteDialogComponent;
    let fixture: ComponentFixture<DlsReqExamsDeleteDialogComponent>;
    let service: DlsReqExamsService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [DlssyncTestModule],
        declarations: [DlsReqExamsDeleteDialogComponent]
      })
        .overrideTemplate(DlsReqExamsDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DlsReqExamsDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DlsReqExamsService);
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
