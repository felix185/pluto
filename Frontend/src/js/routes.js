import Main from './Main';
import ActivityLog from './components/activityLog';
import LaundryAlert from './components/laundryAlert';
import ReceptCollection from './components/receptCollection';
import ReceptCreation from './components/receptCreation';

export default {
  path: '/',
  component: Main,
  indexRoute: { component: ActivityLog},
  childRoutes: [
    { path: 'laundryAlert', component: LaundryAlert},
    { path: 'receptCollection', component: ReceptCollection},
    { path: 'receptCreation', component: ReceptCreation}
  ]
};
