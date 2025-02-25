<template>
  <div class="cadastro-empresa">
    <!-- Formulário de Cadastro -->
    <h2>Cadastro de Empresa</h2>
    <form @submit.prevent="handleSubmit">
      <div class="form-group">
        <label for="razao-social">Razão Social</label>
        <input type="text" v-model="empresa.razaoSocial" id="razao-social" required />
      </div>

      <div class="form-group">
        <label for="cnpj">CNPJ</label>
        <input type="text" v-model="empresa.cnpj" id="cnpj" required />
      </div>

      <div class="form-group">
        <label for="nome-fantasia">Nome Fantasia</label>
        <input type="text" v-model="empresa.nomeFantasia" id="nome-fantasia" required />
      </div>

      <div class="form-group">
        <label for="perfil">Perfil</label>
        <select v-model="empresa.perfil" id="perfil" required>
          <option value="empresa">Empresa</option>
          <option value="freelancer">Freelancer</option>
        </select>
      </div>

      <div class="form-group">
        <label for="faturamento-direto">
          <input type="checkbox" v-model="empresa.faturamentoDireto" id="faturamento-direto" />
          Faturamento Direto
        </label>
      </div>

      <div class="form-group">
        <label for="documento-comprovante">Documento Comprovante</label>
        <input type="file" @change="handleFileChange" id="documento-comprovante" />
      </div>

      <button type="submit" class="btn btn-primary">Salvar</button>
    </form>

    <div v-if="showModal" class="modal">
      <div class="modal-content">
        <h3>Cadastro realizado com sucesso!</h3>
        <button @click="closeModal">Fechar</button>
      </div>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue'

export default {
  name: 'CadastroEmpresa',
  setup () {
    const empresa = ref({
      razaoSocial: '',
      cnpj: '',
      nomeFantasia: '',
      perfil: 'empresa',
      faturamentoDireto: false,
      documentoComprovante: null
    })

    const showModal = ref(false)

    const handleFileChange = (event) => {
      const target = event.target
      if (target && target.files) {
        empresa.value.documentoComprovante = target.files[0]
      }
    }

    const handleSubmit = () => {
      showModal.value = true
    }

    const closeModal = () => {
      showModal.value = false
      window.location.href = '/dashboard'
    }

    return {
      empresa,
      showModal,
      handleFileChange,
      handleSubmit,
      closeModal
    }
  }
}
</script>

<style scoped>
.cadastro-empresa {
  padding: 20px;
}

.form-group {
  margin-bottom: 10px;
}

button {
  margin-top: 10px;
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
  margin-top: 10px;
}
</style>
