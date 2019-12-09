import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDlsTrsTypes } from 'app/shared/model/dls-trs-types.model';

@Component({
  selector: 'jhi-dls-trs-types-detail',
  templateUrl: './dls-trs-types-detail.component.html'
})
export class DlsTrsTypesDetailComponent implements OnInit {
  dlsTrsTypes: IDlsTrsTypes;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ dlsTrsTypes }) => {
      this.dlsTrsTypes = dlsTrsTypes;
    });
  }

  previousState() {
    window.history.back();
  }
}
