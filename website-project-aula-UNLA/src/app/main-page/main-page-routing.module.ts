import { StudentAdmnistrationModule } from './../student-admnistration/student-admnistration.module';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainLayoutComponent } from './main-layout/main-layout.component';

const routes: Routes = [
  {
    path: '',
    component: MainLayoutComponent,
    children: [
      { path: 'student-administration', loadChildren: () => import('../student-admnistration/student-admnistration.module').then(m => m.StudentAdmnistrationModule)}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MainPageRoutingModule { }