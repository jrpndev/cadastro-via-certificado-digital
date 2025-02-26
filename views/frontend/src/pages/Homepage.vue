<template>
  <div class="home-page">
    <h2>Bem-vindo ao Portal de Serviço ST</h2>
    <div class="actions">
      <router-link to="/register-company" class="btn btn-primary">Cadastrar Empresa</router-link>
      <button @click="logout" class="btn btn-danger">Sair</button>
    </div>
    <ListCompany />
  </div>
</template>

<script>
import router from '../router'
import ListCompany from '../components/ListCompany.vue'

export default {
  name: 'HomePage',
  components: {
    ListCompany
  },
  methods: {
    logout () {
      localStorage.removeItem('token')
      router.push({ name: 'login' })
    }
  }
}
</script>

<style scoped>
.home-page {
  padding: 20px;
}

.actions {
  margin-top: 20px;
}

button {
  margin-left: 10px;
}
</style>
