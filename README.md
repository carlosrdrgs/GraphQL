# API GraphQL - Sistema de Museus

<p align="center">
  <img src="https://img.shields.io/badge/Java-25-orange" />
  <img src="https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen" />
  <img src="https://img.shields.io/badge/GraphQL-API-purple" />
  <img src="https://img.shields.io/badge/PostgreSQL-Database-blue" />
</p>

## 📝 Descrição

Módulo de API desenvolvido com **Spring Boot** e **GraphQL** para consultas flexíveis e otimizadas.  
Este componente é focado em **Dashboards** e relatórios gerenciais, permitindo que o cliente defina exatamente a estrutura dos dados retornados, evitando tráfego desnecessário de informações.

---

## 💡 REST vs GraphQL — Justificativa Arquitetural

### 1. Overfetching (Excesso de Dados)

No REST, ao consultar um agendamento, a API retorna o objeto inteiro, mesmo que o cliente precise apenas do **status**.

**GraphQL permite solicitar apenas o necessário:**

```graphql
{ status }
```

Resultado: menos tráfego, mais eficiência.

---

### 2. Underfetching (Múltiplas Requisições)

Para montar um Dashboard com:

- Nome do Museu  
- Agendamentos  
- Visitantes  

No REST, seriam necessárias várias requisições (problema *n+1*).

**Com GraphQL:**

Uma única Query aninhada resolve tudo.

---

## 🔍 Queries Implementadas

### 1. Museus + Agendamentos (Hierárquico)

```graphql
query ListarMuseusComAgendamentos {
  museus {
    id
    nome
    agendamentos(data: "2026-01-20") {
      data
      horario
      quantidadePessoas
      status
    }
  }
}
```

---

### 2. Agendamento Completo (Relacionamentos)

```graphql
query BuscarAgendamentoCompleto {
  agendamento(codigo: "ABC1234") {
    codigoConfirmacao
    data
    horario
    status
    museu {
      nome
      capacidadePorHorario
    }
    pessoas {
      nome
      tipoIngresso
    }
  }
}
```

---

### 3. Dashboard Gerencial (Resumo)

```graphql
query ResumoDiario {
  resumoMuseu(museuId: 1, data: "2026-01-20") {
    totalAgendamentos
    totalPessoas
  }
}
```

---

## 🛠️ Tecnologias

- Java 25
- Spring Boot 3.x  
- Spring GraphQL  
- PostgreSQL  
- Lombok  

---

## 📦 Como Executar

1. Inicie o banco **museus_db**  
2. Execute:

```bash
GraphqlApplication.java
```

3. Acesse o GraphiQL:

```
http://localhost:8081/graphiql
```

4. Endpoint:

```
http://localhost:8081/graphql
```

---

## 📌 Observações

- A API foi projetada para uso em **Dashboards**
- Foco em performance e flexibilidade
- Estrutura baseada em DTOs e Queries customizadas
