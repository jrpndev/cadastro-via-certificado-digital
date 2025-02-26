import { createApp } from 'vue'
import App from './App.vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import './styles/global.css'
import router from './router/index.js'

createApp(App)
  .use(router)
  .mount('#app')
