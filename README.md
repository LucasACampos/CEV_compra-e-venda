# CEV Compra e Venda

**CEV Compra e Venda** é uma API para um projeto de marketplace que registra logs das requisições e das queries executadas no banco de dados, proporcionando monitoramento detalhado e suporte a operações de comércio eletrônico.

## Funcionalidades

- **Log de Requisições**: Todas as requisições feitas à API são registradas, permitindo monitoramento de ações dos usuários e desempenho.
- **Log de Queries de Banco de Dados**: Registra todas as consultas SQL executadas, permitindo auditoria e otimização.

## Estrutura do Projeto

Abaixo está uma representação da estrutura atual do projeto:

![CEV-compra-e-venda](https://github.com/user-attachments/assets/a4cbbf18-05b3-42b8-9981-e9596500c028)

## Links Úteis

- **Swagger da API**: [Swagger UI](http://ec2-44-215-112-249.compute-1.amazonaws.com:8080/swagger) — Utilize para explorar e testar os endpoints da API.
- **Collection do Postman**: [CEV - Compras e Vendas](https://github.com/LucasACampos/CEV_compra-e-venda/blob/main/CEV%20-%20Compras%20e%20vendas.postman_collection.json) — Importe a collection para o Postman e teste as requisições localmente.

## Executando o Projeto Localmente

### Pré-requisitos

Certifique-se de ter o Docker instalado em sua máquina.

### Formas de Execução

1. **Execução local (ide)**

   O projeto utiliza o **WireMock** para simular APIs durante os testes. Para executar o WireMock localmente:

   ```bash
   docker run -p 8081:8080 -v ./wiremock/mappings:/home/wiremock/mappings rodolpheche/wiremock
   ```
   Isso iniciará o WireMock e o deixará disponível na porta 8081 para que o projeto utilize

2. **Execução com docker**
   Forçar o Build das Imagens Docker
   
   ```bash
   docker compose up --build
   ```
   Este comando compilará a aplicação e subirá os containers necessários.

## Melhorias

Lista de melhorias que gostaria de implementar mais ainda não tive tempo

- Criação de usuarios, utilizando jwt para logar na aplicação e salvar usuario nos logs
- Bloqueio de certos end-points caso o usuario não esteja logado (Ex: deletar produto)
- Conexão com S3 para salvar imagens dos produtos
- Consumir a api em um sistema nextJS e criar uma interface do marketplace
