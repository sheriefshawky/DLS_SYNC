import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDlsReqExams } from 'app/shared/model/dls-req-exams.model';

@Component({
  selector: 'jhi-dls-req-exams-detail',
  templateUrl: './dls-req-exams-detail.component.html'
})
export class DlsReqExamsDetailComponent implements OnInit {
  dlsReqExams: IDlsReqExams;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ dlsReqExams }) => {
      this.dlsReqExams = dlsReqExams;
    });
  }

  previousState() {
    window.history.back();
  }
}
