# CEV_compra-e-venda
Site para um projeto de marketplace

# Estrutura atual do projeto

<iframe src="https://drive.google.com/file/d/1XUyTnJQUFiD7ZSzl2FvDql6rzMjLmtwl/preview" width="640" height="480" allow="autoplay"></iframe>

# Links uteis
- Swagger da api: http://ec2-44-215-112-249.compute-1.amazonaws.com:8080/swagger
- Collection do postman: https://github.com/LucasACampos/CEV_compra-e-venda/blob/main/CEV%20-%20Compras%20e%20vendas.postman_collection.json

# Comandos para rodar o projeto local
- Run wire mock para testes locais:
docker run -p 8081:8080 -v ./wiremock/mappings:/home/wiremock/mappings rodolpheche/wiremock
- Força o build das imagens:
docker compose up --build
