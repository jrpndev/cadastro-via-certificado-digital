## Como Executar o Sistema

### Pré-requisitos
- **Docker**: Instale o Docker para rodar o ambiente de desenvolvimento.

### Passos para Executar
1. Clone este repositório.
2. Navegue até a pasta do projeto.
3. Crie um arquivo `.env` na raiz do projeto com as seguintes variáveis:

```bash
# Configurações do PostgreSQL
POSTGRES_USER=example_user
POSTGRES_PASSWORD=example_password
POSTGRES_DB=example_db
POSTGRES_HOST=example_host
POSTGRES_PORT=5432

# Configuração da aplicação
PORT=5000
SPRING_DATASOURCE_URL=jdbc:postgresql://example_host:5432/example_db?connect_timeout=300
SPRING_DATASOURCE_USERNAME=example_user
SPRING_DATASOURCE_PASSWORD=example_password
SPRING_DATASOURCE_DRIVER_CLASS_NAME=org.postgresql.Driver

# Configuração do Hibernate
SPRING_JPA_HIBERNATE_DDL_AUTO=update
SPRING_JPA_PROPERTIES_HIBERNATE_FORMAT_SQL=true
SPRING_JPA_PROPERTIES_HIBERNATE_USE_SQL_COMMENTS=true

# Configuração do JWT
JWT_SECRET=example_secret_key
JWT_EXPIRATION=86400000

# URL base da API
BASE_URL=http://localhost:5000/api
```

4. Execute o comando abaixo para iniciar todos os containers via Docker Compose:

```bash
docker-compose up -d
```

5. # URLs de Conexão

## Backend (Spring Boot)
- API Base: [http://localhost:5000/api](http://localhost:5000/api)
- Documentação (se houver Swagger): [http://localhost:5000](http://localhost:5000)

## Frontend (Vue.js)
- Aplicação Vue: [http://localhost:8080](http://localhost:8080)
- Painel de desenvolvimento Vue: [http://localhost:8080/__VUE_DEVTOOLS_GLOBAL_HOOK__](http://localhost:8080/__VUE_DEVTOOLS_GLOBAL_HOOK__)

5. # Versões

- Versões Utilizadas

## Backend (Spring Boot)
```bash
Java: 21
```
## Frontend (Vue.js)

```bash
Node.js: 22.14.0
```