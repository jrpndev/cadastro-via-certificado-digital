import { createApp } from 'vue'
import App from './App.vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import { createRouter, createWebHistory } from 'vue-router'
import './styles/global.css'
import RegisterCompany from './components/RegisterCompany.vue'
import ListCompanies from './components/ListCompany.vue'
import LoginUser from './components/LoginUser.vue'
import RegisterUser from './components/RegisterUser.vue'
import store from './store'

const routes = [
  { path: '/register-company', component: RegisterCompany },
  { path: '/list-companies', component: ListCompanies },
  { path: '/login', component: LoginUser },
  { path: '/register-user', component: RegisterUser }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

createApp(App)
  .use(router)
  .use(store)
  .mount('#app')
