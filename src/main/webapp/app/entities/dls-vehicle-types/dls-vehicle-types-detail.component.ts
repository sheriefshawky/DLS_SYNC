import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDlsVehicleTypes } from 'app/shared/model/dls-vehicle-types.model';

@Component({
  selector: 'jhi-dls-vehicle-types-detail',
  templateUrl: './dls-vehicle-types-detail.component.html'
})
export class DlsVehicleTypesDetailComponent implements OnInit {
  dlsVehicleTypes: IDlsVehicleTypes;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ dlsVehicleTypes }) => {
      this.dlsVehicleTypes = dlsVehicleTypes;
    });
  }

  previousState() {
    window.history.back();
  }
}
