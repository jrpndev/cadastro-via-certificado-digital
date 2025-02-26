const API_BASE_URL = 'http://localhost:5000/api'

const httpClient = {
  get: async (url, options = {}) => {
    const response = await fetch(`http://localhost:5000/api${url}`, options)

    if (!response.ok) {
      throw new Error(`Erro HTTP: ${response.status}`)
    }

    const data = await response.text()
    return data ? JSON.parse(data) : []
  },

  async post (endpoint, data, headers = {}) {
    const response = await fetch(`${API_BASE_URL}${endpoint}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        ...headers
      },
      body: JSON.stringify(data)
    })
    return response.json()
  },

  async put (endpoint, data, headers = {}) {
    try {
      const response = await fetch(`${API_BASE_URL}${endpoint}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
          ...headers
        },
        body: JSON.stringify(data)
      })
      return await response.json()
    } catch (error) {
      console.error('Erro na requisição PUT:', error)
      throw error
    }
  },

  async patch (endpoint, data, headers = {}) {
    try {
      const response = await fetch(`${API_BASE_URL}${endpoint}`, {
        method: 'PATCH',
        headers: {
          'Content-Type': 'application/json',
          ...headers
        },
        body: JSON.stringify(data)
      })
      return await response.json()
    } catch (error) {
      console.error('Erro na requisição PATCH:', error)
      throw error
    }
  },

  async delete (endpoint, headers = {}) {
    try {
      const response = await fetch(`${API_BASE_URL}${endpoint}`, {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json',
          ...headers
        }
      })
      return await response.json()
    } catch (error) {
      console.error('Erro na requisição DELETE:', error)
      throw error
    }
  }
}

export default httpClient
