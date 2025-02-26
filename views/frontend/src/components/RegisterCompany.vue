<template>
  <Header />
  <div class="cadastro-empresa d-flex justify-content-center align-items-center">
    <div class="form-container">
      <h2 class="text-center mb-4">Cadastro de Empresa</h2>

      <form @submit.prevent="handleSubmit">
        <div class="form-group">
          <label for="razao-social">Razão Social</label>
          <input type="text" v-model="empresa.razaoSocial" id="razao-social" class="form-control" required />
        </div>

        <div class="form-group">
          <label for="cnpj">CNPJ</label>
          <input type="text" v-model="empresa.cnpj" id="cnpj" class="form-control" required />
        </div>

        <div class="form-group">
          <label for="nome-fantasia">Nome Fantasia</label>
          <input type="text" v-model="empresa.nomeFantasia" id="nome-fantasia" class="form-control" required />
        </div>

        <div class="form-group">
          <label for="perfil">Perfil</label>
          <select v-model="empresa.perfil" id="perfil" class="form-control" required>
            <option value="empresa">Empresa</option>
            <option value="freelancer">Freelancer</option>
          </select>
        </div>

        <div class="form-group form-check">
          <input type="checkbox" v-model="empresa.faturamentoDireto" id="faturamento-direto" class="form-check-input" />
          <label class="form-check-label" for="faturamento-direto">Faturamento Direto</label>
        </div>
        <button type="submit" class="btn btn-primary w-100">Salvar</button>
      </form>
    </div>

    <div v-if="showModal" class="modal">
      <div class="modal-content">
        <h3>{{ modalMessage }}</h3>
        <button class="btn btn-success mt-3" @click="closeModal">Fechar</button>
      </div>
    </div>
  </div>
</template>

<script>
import Header from './Header.vue'
import router from '../router'

export default {
  name: 'CadastroEmpresa',
  components: {
    Header
  },
  data () {
    return {
      empresa: {
        razaoSocial: '',
        cnpj: '',
        nomeFantasia: '',
        perfil: 'empresa',
        faturamentoDireto: false
      },
      showModal: false,
      modalMessage: ''
    }
  },
  methods: {
    decodeJWT (token) {
      const payload = JSON.parse(atob(token.split('.')[1]))
      return payload.userType
    },

    async handleSubmit () {
      try {
        const token = localStorage.getItem('token')
        if (!token) {
          throw new Error('Usuário não autenticado')
        }
        const isExternalUser = this.decodeJWT(token)
        const payload = {
          razaoSocial: this.empresa.razaoSocial,
          cnpj: this.empresa.cnpj,
          nomeFantasia: this.empresa.nomeFantasia,
          perfil: this.empresa.perfil,
          directBilling: this.empresa.faturamentoDireto,
          isExternalUser: isExternalUser === 'EXTERNO'
        }

        const response = await fetch('http://localhost:5000/api/companies', {
          method: 'POST',
          headers: {
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(payload)
        })

        if (response.ok) {
          this.modalMessage = 'Cadastro realizado com sucesso!'
          this.showModal = true

          setTimeout(() => {
            this.closeModal()
            router.push('/home-page')
          }, 2000)
        } else {
          throw new Error('Falha ao cadastrar a empresa')
        }
      } catch (error) {
        console.error('Erro ao cadastrar empresa:', error)
        this.modalMessage = 'Erro ao cadastrar empresa. Tente novamente.'
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
.cadastro-empresa {
  height: 100vh;
  background-color: #f8f9fa;
}

.form-container {
  background: white;
  padding: 30px;
  border-radius: 10px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
  width: 400px;
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
