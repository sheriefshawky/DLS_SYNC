import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDlsRequests } from 'app/shared/model/dls-requests.model';

@Component({
  selector: 'jhi-dls-requests-detail',
  templateUrl: './dls-requests-detail.component.html'
})
export class DlsRequestsDetailComponent implements OnInit {
  dlsRequests: IDlsRequests;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ dlsRequests }) => {
      this.dlsRequests = dlsRequests;
    });
  }

  previousState() {
    window.history.back();
  }
}
