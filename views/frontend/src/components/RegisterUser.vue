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
                    <option value="INTERNAL">Interno</option>
                    <option value="EXTERNAL">Externo</option>
                </select>
            </div>

            <button type="submit" class="btn btn-primary">Cadastrar</button>
        </form>
    </div>
</template>

<script>
export default {
  data () {
    return {
      user: {
        name: '',
        email: '',
        password: '',
        userType: ''
      }
    }
  },
  methods: {
    handleSubmit () {
      this.registerUser(this.user)
    },
    async registerUser (userData) {
      try {
        const response = await this.$axios.post('/api/users', userData)
        console.log('Usuário cadastrado com sucesso:', response.data)
      } catch (error) {
        console.error('Erro ao cadastrar usuário:', error)
      }
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
</style>
