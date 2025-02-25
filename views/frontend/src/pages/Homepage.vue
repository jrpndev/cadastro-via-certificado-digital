<template>
    <div class="home-page">
        <h2>Bem-vindo ao Portal de Serviço ST</h2>
        <div class="actions">
            <router-link to="/register-company" class="btn btn-primary">Cadastrar Empresa</router-link>
            <router-link to="/list-companies" class="btn btn-secondary">Listar Empresas</router-link>
            <button @click="logout" class="btn btn-danger">Sair</button>
        </div>
    </div>
</template>

<script>
export default {
  name: 'HomePage',
  methods: {
    logout () {
      this.$store.commit('setToken', null)
      this.$router.push({ name: 'login' })
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
