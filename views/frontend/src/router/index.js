import Vue from 'vue'
import Router from 'vue-router'
import RegisterUser from '@/components/RegisterUser'
import LoginUser from '@/components/LoginUser'
import RegisterCompany from '@/components/RegisterCompany'
import ListCompany from '@/components/ListCompany'
import Homepage from '@/pages/Homepage.vue'
import store from '@/store'

Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/',
      name: 'login',
      component: LoginUser
    },
    {
      path: '/register-user',
      name: 'register-user',
      component: RegisterUser
    },
    {
      path: '/register-company',
      name: 'register-company',
      component: RegisterCompany,
      meta: { requiresAuth: true }
    },
    {
      path: '/list-companies',
      name: 'list-companies',
      component: ListCompany,
      meta: { requiresAuth: true }
    },
    {
      path: '/home-page',
      name: 'home-page',
      component: Homepage,
      meta: { requiresAuth: true }
    }
  ]
})

router.beforeEach((to, from, next) => {
  const token = store.state.token
  if (to.matched.some(record => record.meta.requiresAuth)) {
    if (!token) {
      next({ name: 'login' })
    } else {
      next()
    }
  } else {
    next()
  }
})

export default router
