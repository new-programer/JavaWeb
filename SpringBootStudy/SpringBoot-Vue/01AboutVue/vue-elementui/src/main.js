// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
// import './plugins/iview.js'
import router from  './router/index'

/*导入自定义组件*/
import Login from "./views/Login";
import Index from "./views/Index";

Vue.config.productionTip = false;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  render:h => h(App),
}).$mount('#app');
