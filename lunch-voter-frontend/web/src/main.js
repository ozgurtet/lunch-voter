import Vue from 'vue'
import App from './App.vue'
import Moment from 'moment';

import router from './router'
import store from './store'
import './registerServiceWorker'

import { Toast, Loading } from 'buefy'
import 'buefy/dist/buefy.css'

Vue.use(Toast)
Vue.use(Loading)
Vue.prototype.$moment = Moment

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
