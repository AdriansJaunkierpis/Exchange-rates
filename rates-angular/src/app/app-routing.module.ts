import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FxRateListComponent } from './components/fx-rate-list/fx-rate-list.component';
import { FxRateHistoryComponent } from './components/fx-rate-history/fx-rate-history.component';

const routes: Routes = [
  { path: '', redirectTo: '/rates', pathMatch: 'full' },
  { path: 'rates', component: FxRateListComponent },
  { path: 'rates/:currency', component: FxRateHistoryComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
