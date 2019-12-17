import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDlsReqTrainings } from 'app/shared/model/dls-req-trainings.model';

@Component({
  selector: 'jhi-dls-req-trainings-detail',
  templateUrl: './dls-req-trainings-detail.component.html'
})
export class DlsReqTrainingsDetailComponent implements OnInit {
  dlsReqTrainings: IDlsReqTrainings;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ dlsReqTrainings }) => {
      this.dlsReqTrainings = dlsReqTrainings;
    });
  }

  previousState() {
    window.history.back();
  }
}
