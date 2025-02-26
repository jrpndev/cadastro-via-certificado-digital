<template>
  <div>
    <Header />
    <div class="list-company">
      <h2>Lista de Empresas</h2>
      <div v-if="companies.length === 0">
        <p>Não há empresas cadastradas.</p>
      </div>
      <table class="table table-striped" v-else>
        <thead>
          <tr>
            <th>Razão Social</th>
            <th>CNPJ</th>
            <th>Nome Fantasia</th>
            <th>Status</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="company in companies" :key="company.id">
            <td>{{ company.razaoSocial }}</td>
            <td>{{ company.cnpj }}</td>
            <td>{{ company.nomeFantasia }}</td>
            <td>{{ company.status === 'PENDING' ? 'PENDENTE' : 'APROVADO' }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import httpClient from '../network/index.js'
import Header from './Header.vue'

export default {
  components: {
    Header
  },
  data () {
    return {
      companies: []
    }
  },
  created () {
    this.fetchCompanies()
  },
  methods: {
    async fetchCompanies () {
      try {
        const token = localStorage.getItem('token')
        const response = await httpClient.get('/companies', {
          headers: {
            Authorization: `Bearer ${token}`
          }
        })
        this.companies = response
      } catch (error) {
        console.error('Erro ao listar as empresas:', error)
      }
    }
  }
}
</script>

<style scoped>
.list-company {
  max-width: 1000px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f9f9f9;
}

h2 {
  text-align: center;
  margin-bottom: 20px;
}

table {
  width: 100%;
  margin-top: 20px;
}

th,
td {
  text-align: left;
  padding: 8px;
}

th {
  background-color: #f1f1f1;
}
</style>
