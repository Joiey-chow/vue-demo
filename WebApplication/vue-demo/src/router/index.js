import Test from './Test'
import Login from './Login'
import Router from 'vue-router'
import Vue from 'vue'
import store from '../store'

Vue.use(Router);


const router = new Router({
  routes:[

    {
      path:'/',
      name:'Login',
      component:Login,
      meta:{
      requireAuth: false
      }
    },
    {
      path:'/test',
      name:'Test',
      component:Test,
      meta:{
        requireAuth:true
      }
    }
  ]
});

router.beforeEach(function (to,from,next) {
  if(to.meta.requireAuth){
    if(localStorage.getItem('Authorization') == null || '' ){
      next('/');
    }else {
      next()
    }
  }else {
    next();
  }

})

export default router;
//导航守卫


