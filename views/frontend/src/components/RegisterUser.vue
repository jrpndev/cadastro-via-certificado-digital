<template>
  <div class="register-user">
    <h2>Cadastro de Usuário</h2>
    <form @submit.prevent="handleSubmit">
      <div class="mb-3">
        <label for="name" class="form-label">Nome</label>
        <input type="text" id="name" class="form-control" v-model="user.name" required />
      </div>

      <div class="mb-3">
        <label for="email" class="form-label">E-mail</label>
        <input type="email" id="email" class="form-control" v-model="user.email" required />
      </div>

      <div class="mb-3">
        <label for="password" class="form-label">Senha</label>
        <input type="password" id="password" class="form-control" v-model="user.password" required />
      </div>

      <div class="mb-3">
        <label for="userType" class="form-label">Tipo de Usuário</label>
        <select id="userType" class="form-control" v-model="user.userType" required>
          <option value="" disabled>Selecione o tipo de usuário</option>
          <option value="INTERNO">Interno</option>
          <option value="EXTERNO">Externo</option>
        </select>
      </div>

      <button type="submit" class="btn btn-primary">Cadastrar</button>
    </form>

    <div v-if="showModal" class="modal">
      <div class="modal-content">
        <h3>{{ modalMessage }}</h3>
        <button class="btn btn-success mt-3" @click="closeModal">Fechar</button>
      </div>
    </div>
  </div>
</template>

<script>
import httpClient from '../network/index.js'
import router from '../router'

export default {
  data () {
    return {
      user: {
        name: '',
        email: '',
        password: '',
        userType: ''
      },
      showModal: false,
      modalMessage: ''
    }
  },
  methods: {
    async handleSubmit () {
      try {
        const payload = {
          name: this.user.name,
          email: this.user.email,
          password: this.user.password,
          userType: this.user.userType
        }

        await httpClient.post('/users/register', payload, {
          headers: { 'Content-Type': 'application/json' }
        })

        this.modalMessage = 'Usuário cadastrado com sucesso!'
        this.showModal = true

        setTimeout(() => {
          this.closeModal()
          router.push('/home-page')
        }, 2000)
      } catch (error) {
        console.error('Erro ao cadastrar usuário:', error)
        this.modalMessage = 'Erro ao cadastrar usuário. Tente novamente.'
        this.showModal = true
      }
    },
    closeModal () {
      this.showModal = false
    }
  }
}
</script>

<style scoped>
.register-user {
  max-width: 500px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
  background-color: #f9f9f9;
}

h2 {
  text-align: center;
  margin-bottom: 20px;
}

.mb-3 {
  margin-bottom: 15px;
}

button {
  width: 100%;
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
</style>
