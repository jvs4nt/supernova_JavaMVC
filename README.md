# 🌠 Supernova — Sistema de Apoio em Eventos Extremos

**Supernova** é um sistema web voltado para o gerenciamento de informações críticas em situações de **eventos extremos**, como desastres naturais, emergências ambientais e crises humanitárias. O sistema oferece funcionalidades completas para o controle de **usuários, notícias, alertas**, garantindo segurança, desempenho e organização por meio de tecnologias modernas como **Spring Boot**, **MongoDB**, **RabbitMQ**, **OCR** e **JWT**.


## Funcionalidades 

- CRUD completo de usuários, com autenticação e autorização por perfil (ADMIN, USER, VOLUNTÁRIO).
- Aprovação de documentos com validação via OCR (Spring AI + Tess4J).
- Gerenciamento de notícias e alertas diretamente pelo painel administrativo.
- Integração com RabbitMQ para notificações assíncronas.
- Camada de segurança com JWT e controle de acesso baseado em roles.
- Interface web com Spring MVC + Thymeleaf, incluindo login, formulários e painéis administrativos.

---

## Tecnologias Utilizadas

| Camada         | Tecnologias                                                            |
| -------------- | ---------------------------------------------------------------------- |
| Backend        | Spring Boot 3.2, Spring Data MongoDB, Spring Security (JWT), Spring AI |
| OCR / IA       | Tess4J + Spring AI                                                     |
| Banco de Dados | MongoDB                                                                |
| Filas          | RabbitMQ                                                               |
| Testes         | JUnit 5, Mockito                                                       |
| Interface Web  | Thymeleaf, Spring MVC                                                  |
| Build Tool     | Maven                                                                  |

---

## Como rodar o projeto Localmente

### 1. Clonar o repositório

```bash
    git clone https://github.com/LipeArcanjo/supernova_JavaMVC.git
    cd supernova
```

### 2. Subir MongoDB e RabbitMQ com Docker
```bash
    docker compose up -d
```
### 3. Rodar a aplicação
```bash
    ./mvnw spring-boot:run
```
Acesse em: http://localhost:8080

---

## Interface Web (Spring MVC)

- A interface administrativa permite:
- Login com autenticação JWT
- Gestão de usuários, notícias e alertas
- Validação de documentos com OCR

---

## Executar Testes

### 1. Testes Unitários + Integração
```bash
    ./mvnw test
```

### 2. Para rodar apenas testes de fila:

```bash
mvn -Dtest=UsuarioConsumerTest test
```

---

## Capturas de Tela

⚠️ Espaço reservado para imagens da interface web (MVC).

--- 

## 🚀 Deploy
⚠️ Espaço reservado para link do deploy hospedado.

--- 

## 🎥 Vídeo Pitch e Demonstração
⚠️ Link para o vídeo de pitch e demonstração técnica da aplicação.

----

## Equipe de Desenvolvimento

| Nome                 | Função                | RM        |
|----------------------| --------------------- |-----------|
| Willian Daniel O. D. | Backend Developer     | RM 552671 |
| Felipe Arcanjo M. S. | Frontend & QA         | RM 554018 |
| João Vitor S. S.     | DevOps & Documentação | RM 554328 |
