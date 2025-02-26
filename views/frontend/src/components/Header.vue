<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
    <a class="navbar-brand" href="#">Portal de Serviço ST</a>
    <button
      class="navbar-toggler"
      type="button"
      data-bs-toggle="collapse"
      data-bs-target="#navbarNav"
      aria-controls="navbarNav"
      aria-expanded="false"
      aria-label="Toggle navigation"
    >
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ms-auto">
        <li class="nav-item" v-if="isAuthenticated">
          <router-link to="/home-page" class="nav-link">Página Inicial</router-link>
        </li>
        <li class="nav-item" v-if="isAuthenticated">
          <router-link to="/register-company" class="nav-link">Cadastrar Empresa</router-link>
        </li>
        <li class="nav-item" v-if="isAuthenticated">
          <router-link to="/list-companies" class="nav-link">Listar Empresas</router-link>
        </li>
        <li class="nav-item" v-if="!isAuthenticated">
          <router-link to="/" class="nav-link">Login</router-link>
        </li>
        <li class="nav-item" v-if="!isAuthenticated">
          <router-link to="/register-user" class="nav-link">Cadastrar Usuário</router-link>
        </li>
        <li class="nav-item" v-if="isAuthenticated">
          <button @click="logout" class="btn btn-outline-danger">Sair</button>
        </li>
      </ul>
    </div>
  </nav>
</template>

<script>
import router from '../router'

export default {
  name: 'HeaderNav',
  data () {
    return {
      isAuthenticated: !!localStorage.getItem('token')
    }
  },
  methods: {
    logout () {
      localStorage.removeItem('token')
      this.isAuthenticated = false
      router.push('/')
    }
  },
  mounted () {
    this.isAuthenticated = !!localStorage.getItem('token')
  }
}
</script>

<style scoped>
.navbar {
  width: 100%;
  background-color: #f8f9fa;
  padding: 1rem;
  position: fixed;
  top: 0;
  left: 0;
  z-index: 1000;
  box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
}

.navbar-nav {
  display: flex;
  align-items: center;
}

.nav-link,
.btn {
  margin-left: 10px;
}
</style>
