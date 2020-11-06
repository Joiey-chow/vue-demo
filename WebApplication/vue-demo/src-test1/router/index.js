import Vue from 'vue'
import Router from 'vue-router'
import About from './About'
import Home from './Home'
import Test from './Test'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/about',
      component: About
    },
    {
      path: '/home',
      component: Home
    },
    {
      path: '/test',
      component: Test
    },
    {
      path: '/',
      redirect: '/test'
    }
  ]
})
