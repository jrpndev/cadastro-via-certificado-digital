<template>
  <div class="container">
    <div class="login-user">
      <h2>Login</h2>
      <form @submit.prevent="handleSubmit">
        <div class="mb-3">
          <label for="email" class="form-label">E-mail</label>
          <input type="email" id="email" class="form-control" v-model="user.email" required />
        </div>

        <div class="mb-3">
          <label for="password" class="form-label">Senha</label>
          <input type="password" id="password" class="form-control" v-model="user.password" required />
        </div>

        <button type="submit" class="btn btn-primary">Login</button>
      </form>

      <div class="register-link">
        <p>Não tem uma conta?</p>
        <router-link to="/register-user" class="btn btn-secondary">Criar Conta</router-link>
      </div>
    </div>
    <div v-if="showErrorModal" class="modal">
      <div class="modal-content">
        <h3>Erro ao realizar o login</h3>
        <p>{{ errorMessage }}</p>
        <button class="btn btn-danger mt-3" @click="closeErrorModal">Fechar</button>
      </div>
    </div>
  </div>
</template>

<script>
import httpClient from '../network/index.js'
import router from '../router/index.js'

export default {
  data () {
    return {
      user: {
        email: '',
        password: ''
      },
      showErrorModal: false,
      errorMessage: ''
    }
  },
  methods: {
    async handleSubmit () {
      try {
        const response = await httpClient.post('/users/login', this.user)
        const token = response.token

        if (token) {
          localStorage.setItem('token', token)
          router.push({ name: 'list-companies' })
        } else {
          this.showError('Erro: Nenhum token recebido')
        }
      } catch (error) {
        this.showError('Erro ao fazer login. Verifique suas credenciais.')
      }
    },
    showError (message) {
      this.errorMessage = message
      this.showErrorModal = true
    },
    closeErrorModal () {
      this.showErrorModal = false
    }
  }
}
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100vh;
}

.login-user {
  background-color: #f5f5f5;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  max-width: 400px;
  width: 100%;
  text-align: center;
}

h2 {
  margin-bottom: 1.5rem;
}

.form-label {
  color: #333;
}

.form-control {
  background-color: white;
  border: 1px solid #ccc;
  color: black;
}

.form-control:focus {
  background-color: white;
  border-color: #1e88e5;
}

.btn-primary {
  background-color: #1e88e5;
  border-color: #1e88e5;
}

.btn-primary:hover {
  background-color: #1565c0;
  border-color: #1565c0;
}

.register-link {
  margin-top: 1rem;
}

.register-link p {
  margin-bottom: 0.5rem;
  color: #333;
}

.btn-secondary {
  background-color: #6c757d;
  border-color: #6c757d;
}

.btn-secondary:hover {
  background-color: #5a6268;
  border-color: #545b62;
}

.modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal-content {
  background-color: white;
  padding: 20px;
  border-radius: 5px;
  text-align: center;
}

.modal button {
  width: 100%;
}
</style>
