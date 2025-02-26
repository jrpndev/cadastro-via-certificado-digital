import { createRouter, createWebHistory } from 'vue-router'
import RegisterUser from '@/components/RegisterUser'
import LoginUser from '@/components/LoginUser.vue'
import RegisterCompany from '@/components/RegisterCompany'
import ListCompany from '@/components/ListCompany.vue'
import Homepage from '@/pages/Homepage.vue'

const routes = [
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

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token')
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
