import Vue from 'vue'
import Router from 'vue-router'

/*导入自定义组件*/
import Login from "../views/Login";
import Index from "../views/Index";
import One from "../components/One";
import Two from "../components/Two";

Vue.use(Router);

export default new Router({
  routes: [//一级路由
    {
      path: '/',
      name: 'login',
      component:Login
      // component: () => import('./views/Login.vue')
    },
    {
      path:'/index',
      name:'index',
      component: Index,
      // component: () => import('./views/Index.vue'),

      children:[//二级路由
        {
          path:'/one',
          name:'one',
          component:One
          // component:() => import('./components/One.vue')
        },
        {
          path:'/two',
          name:'two',
          component: Two
          // component:() => import('./components/Two.vue')
        }
      ]
    }
  ]
})
