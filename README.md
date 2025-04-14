# E2E Cucumber Java

Este projeto tem como objetivo automatizar testes de ponta a ponta (E2E) utilizando a linguagem Java em conjunto com o framework Cucumber, seguindo a abordagem de BDD (Behavior-Driven Development).

## 🧪 Sobre o Projeto

O repositório **e2e_cucumber_java** contém uma estrutura de automação de testes que permite escrever testes legíveis por humanos, integrando as etapas de testes com funcionalidades reais da aplicação.  
Com o uso de Cucumber, é possível descrever os cenários de teste em linguagem natural (Gherkin), facilitando a comunicação entre equipe técnica e não técnica.

## 🔧 Tecnologias Utilizadas

- Java 17+
- Cucumber
- JUnit
- Selenium WebDriver
- Maven
- Gherkin
- Page Object Model (POM)

## 📁 Estrutura do Projeto

```
e2e_cucumber_java/
├── features/     # Arquivos .feature com os cenários de teste
├── steps/        # Definições dos passos (Step Definitions)
├── pages/        # Classes das páginas seguindo o padrão POM
├── runners/      # Classes responsáveis por rodar os testes
├── utils/        # Utilitários e classes auxiliares
└── pom.xml       # Gerenciador de dependências (Maven)
```

## ▶️ Como Executar os Testes
```Clone o repositório
git clone https://github.com/erick-qa/e2e_cucumber_java.git
cd e2e_cucumber_java
```

## Compile o projeto
```mvn clean compile
```

## Execute os testes
```mvn test
```
