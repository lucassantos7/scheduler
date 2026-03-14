# 🗓️ Scheduler API

Uma solução robusta de backend para agendamento de serviços, desenvolvida com **Java 21** e **Spring Boot 3**. O sistema gerencia o fluxo completo entre prestadores de serviço e clientes, calculando disponibilidade em tempo real e validando regras de negócio.

## 🚀 Diferenciais Técnicos
* **Algoritmo de Slots**: Cálculo dinâmico de horários livres, considerando jornada de trabalho, intervalos de almoço e conflitos de agendamentos existentes.
* **Arquitetura em Camadas**: Separação clara entre Entidades, Repositories, Services e Controllers para fácil manutenção.
* **Validação de Dados & Tratamento de Erros**: Implementação de Bean Validation (Jakarta) e um `GlobalExceptionHandler` para respostas de erro amigáveis e precisas.
* **Documentação Moderna**: Interface interativa com **Scalar** integrada via OpenAPI, facilitando testes e integração.

## 🛠️ Tecnologias Utilizadas
* **Linguagem:** Java 21 (LTS)
* **Framework:** Spring Boot 3
* **Banco de Dados:** PostgreSQL
* **Persistência:** Spring Data JPA / Hibernate
* **Containerização:** Docker & Docker Compose
* **Documentação:** SpringDoc / Scalar

## 🏗️ Estrutura do Projeto
* `model`: Entidades mapeadas para o banco de dados.
* `dto`: Objetos de transferência para respostas seguras e otimização de performance.
* `service`: Camada de inteligência, cálculos de disponibilidade e regras de negócio.
* `controller`: Endpoints REST documentados e validados.
* `exception`: Tratamento global de erros da API.

## 📖 Como visualizar a documentação
Com a aplicação rodando, a documentação interativa fica disponível em:
👉 http://localhost:8080/docs.html

## ⚙️ Configuração do Ambiente
1. Certifique-se de ter o **Docker** instalado.
2. **Subir Infra (Docker):**
```bash
docker-compose up -d
```
3. Navegue até a pasta backend e execute:
```bash
./mvnw spring-boot:run
```

## 📋 Endpoints Principais

* `POST /api/users`: Cadastro de novos prestadores.
* `POST /api/users/{id}/config`: Configuração de expediente e horários.
* `GET /api/users/{id}/slots?date=YYYY-MM-DD`: Consulta de horários disponíveis.
* `POST /api/appointments`: Realização de agendamentos com validação.
* `GET /api/appointments/daily/{providerId}?date=YYYY-MM-DD`: Agenda cronológica do profissional.

---

Desenvolvido com foco em excelência técnica por **Lucas Santos** 🚀
