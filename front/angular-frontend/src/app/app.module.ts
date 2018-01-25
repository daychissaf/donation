import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FormsModule }   from '@angular/forms';
import { HttpModule }    from '@angular/http';


import { AppComponent } from './app.component';

import { DonorCrudComponent } from './donor/donor-crud.component';
import { DonorService } from './donor/donor.service';


@NgModule({
  declarations: [
    AppComponent,
	  DonorCrudComponent
  ],
  imports: [
    BrowserModule,
	  FormsModule,
    HttpModule
  ],
  providers: [DonorService],
  bootstrap: [AppComponent]
})
export class AppModule { }
