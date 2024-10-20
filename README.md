# CEV_compra-e-venda
Site para um projeto de marketplace

# Estrutura atual do projeto
![CEV-compas-e-vendas](https://github.com/user-attachments/assets/a4cbbf18-05b3-42b8-9981-e9596500c028)

# Links uteis
- Swagger da api: http://ec2-44-215-112-249.compute-1.amazonaws.com:8080/swagger
- Collection do postman: https://github.com/LucasACampos/CEV_compra-e-venda/blob/main/CEV%20-%20Compras%20e%20vendas.postman_collection.json

# Comandos para rodar o projeto local
- Run wire mock para testes locais:
docker run -p 8081:8080 -v ./wiremock/mappings:/home/wiremock/mappings rodolpheche/wiremock
- Força o build das imagens:
docker compose up --build
