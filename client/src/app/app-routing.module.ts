import {NgModule, Component} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RegisterComponent} from './register/register.component';
import {AppComponent} from './app.component';
import {LoginComponent} from './login/login.component';
import {HomeComponent} from './home/home.component';
import {UserComponent} from './user/user.component';
import {TmComponent} from '../app/tm/tm.component';
import {AdminComponent} from './admin/admin.component';
import {MenuComponent} from './menu/menu.component';
import {CarsComponent} from './cars/cars.component';
import {UsersComponent} from './users/users.component';
import {InfoComponent} from './info/info.component';
import {CarProfileComponent} from './car-profile/car-profile.component';
import {UserProfileComponent} from './user-profile/user-profile.component';
import {ChartPageComponent} from './chart-page/chart-page.component';
import {BookingComponent} from './booking/booking.component';
import {SkillComponent} from './techservice/worker/skill/skill.component';
import {WorkerComponent} from './techservice/worker/worker.component';
import {TechserviceComponent} from './techservice/techservice.component';
import {TechmanagerProfileComponent} from './techmanager-profile/techmanager-profile.component';
import {NotificationsListComponent} from './notifications/notifications-list/notifications-list.component';
import {NotificationsApprovementComponent} from './notifications/notifications-approvement/notifications-approvement.component';
import {MapComponent} from './techservice/map/map.component';
import { CarTrackerComponent } from './cars/car-tracker/car-tracker.component';
import { CarDetailsComponent } from './cars/car-details/car-details.component';
import { ServicesFeedbackFormComponent } from './services-feedback-form/services-feedback-form.component';
import { WorkersFeedbackComponent } from './workers-feedback/workers-feedback.component';
import { DealerComponent } from './dealer/dealer.component';
import { DealcarComponent } from './dealer/dealcar/dealcar.component';
import { DealerstoaddComponent } from './dealer/dealerstoadd/dealerstoadd.component';
import { DealercarsComponent } from './dealer/dealercars/dealercars.component';
import { DealerstosComponent } from './dealer/dealerstos/dealerstos.component';
import { TradesinComponent } from './dealer/tradesin/tradesin.component';
import { TradeInComponent } from './trade-in/trade-in.component';
import { PreviewComponent } from './preview/preview.component';
import { ProgresbarComponent } from './progresbar/progresbar.component';
import { AlertsComponent } from './alerts/alerts.component';
import { PopupComponent } from './notifications/popup/popup.component';
import { ServicesFeedbackComponent } from './services-feedback/services-feedback.component';
import { DealersComponent } from './dealers/dealers.component';
import { TechservicePageComponent } from './techservice-page/techservice-page.component';
import {ServiceHistoryComponent} from "./service-history/service-history.component";
import { CarNormsComponent } from './cars/car-norms/car-norms.component';
import {ChatComponent} from './chat/chat.component';

const routes: Routes = [
  // { path: '**', redirectTo: 'auth/login'},

  {
    path: 'ui/dealerChat',
    component: ChatComponent
  },
  {
    path: 'ui/techservice/dealer',
    component: DealersComponent
  },
  {
    path: 'ui/progresbar',
    component: ProgresbarComponent
  },
  {
    path: 'ui/preview',
    component: PreviewComponent
  },
  {
    path: 'ui/tradeIn/:vin',
    component: TradeInComponent
  },
  {
    path: 'ui/dealersTradeIn',
    component: TradesinComponent
  },
  {
    path: 'ui/dealersSto',
    component: DealerstosComponent
  },

  {
    path: 'ui/dealercars',
    component: DealercarsComponent
  },
  {
    path: 'ui/dealer',
    component: DealerComponent
  },
  {
    path: 'ui/dealerstoadd',
    component: DealerstoaddComponent
  },

  {
    path: 'ui/addDealerCar',
    component: DealcarComponent
  },
  {
    path: 'ui/leavefeedback',
    component: ServicesFeedbackFormComponent
  },
  {
    path: 'ui/map',
    component: MapComponent
  },
  {
    path: 'ui/techmanager/profile',
    component: TechmanagerProfileComponent
  },
  {
    path: 'ui/techservice',
    component: TechserviceComponent
  },
  {
    path: 'ui/techservices',
    component: TechservicePageComponent
  },
  {
    path: 'ui/techservice/feedback',
    component: ServicesFeedbackComponent
  },
  {
    path: 'ui/skills',
    component: SkillComponent
  },
  {
    path: 'ui/workers',
    component: WorkerComponent
  },
  {
    path: 'ui/home',
    component: AppComponent
    // component: MenuComponent
  },
  {
    path: 'ui/user',
    component: UserComponent
  },
  {
    path: 'ui/tm',
    component: TmComponent
  },
  {
    path: 'ui/admin',
    component: AdminComponent
  },
  {
    path: 'ui/auth/login',
    component: LoginComponent
  },
  {
    path: 'ui/signup',
    component: RegisterComponent
  },
  {
    path: '',
    redirectTo: 'ui/home',
    pathMatch: 'full'
  },

  {
    path: 'ui/charts',
    component: ChartPageComponent
  },
  {
    path: 'ui/history',
    component: ServiceHistoryComponent
  },

  {
    path: 'ui/cars',
    component: CarsComponent
  },
  {
    path: 'ui/allusers',
    component: UsersComponent
  },

  {
    path: 'ui/booking',
    component: BookingComponent
  },

  {
    path: 'ui/info',
    component: InfoComponent
  },

  {
    path: 'ui/newcar',
    component: CarProfileComponent
  },

  {
    path: 'ui/userprofile',
    component: UserProfileComponent
  },
  {
    path: 'ui/notifications-list/:id',
    component: NotificationsListComponent
  },
  {
    path: 'ui/notifications-approvement/:id',
    component: NotificationsApprovementComponent
  },
{
    path: 'ui/location',
    component: CarTrackerComponent
},

{
    path: 'ui/carprofile',
    component: CarDetailsComponent
},
{
  path: 'ui/message',
  component: AlertsComponent
},
{
  path: 'ui/popup',
  component: PopupComponent
},
{
  path: 'ui/norms',
  component: CarNormsComponent
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
