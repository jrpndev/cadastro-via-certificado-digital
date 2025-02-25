<template>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <a class="navbar-brand" href="#">Portal de Serviço ST</a>
      <div class="collapse navbar-collapse">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item" v-if="isAuthenticated">
            <router-link to="/homepage" class="nav-link">Página Inicial</router-link>
          </li>
          <li class="nav-item" v-if="isAuthenticated">
            <router-link to="/register-company" class="nav-link">Cadastrar Empresa</router-link>
          </li>
          <li class="nav-item" v-if="isAuthenticated">
            <router-link to="/list-companies" class="nav-link">Listar Empresas</router-link>
          </li>
          <li class="nav-item" v-if="!isAuthenticated">
            <router-link to="/login" class="nav-link">Login</router-link>
          </li>
          <li class="nav-item" v-if="!isAuthenticated">
            <router-link to="/register-user" class="nav-link">Cadastrar Usuário</router-link>
          </li>
        </ul>
      </div>
    </nav>
  </template>
  
  <script>
  export default {
    name: 'HeaderNav',
    computed: {
      isAuthenticated () {
        return this.$store.state.token !== null
      }
    }
  }
  </script>
  
  <style scoped>
  .navbar {
    width: 100%;
    background-color: #f8f9fa;
    padding: 1rem;
  }
  </style>
  