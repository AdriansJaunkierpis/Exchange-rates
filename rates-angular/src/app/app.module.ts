import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FxRateListComponent } from './components/fx-rate-list/fx-rate-list.component';
import { HttpClientModule } from '@angular/common/http';
import { FxRateHistoryComponent } from './components/fx-rate-history/fx-rate-history.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    FxRateListComponent,
    FxRateHistoryComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
