# exercise2
Exercício 2<BR>
Consiste em dois módulos SpringBoot, um responsável pelo servicos Rest (mspatrimonio) e outro pelo token Oauth2 (oauth2-server)

<b>mspatrimonio </b>
<BR>- Spring Boot, que já facilita a nossa arquitetura para implementação dos services
<BR>- Banco postgresql para armazenar os Dados, alocado um banco na amazon para facilitar os testes por terceiros sem ter que instalar o banco local
<BR>- JPA para o acesso e manipulação dos Dados
<BR>- Swagger para melhor apresentação dos services em http://localhost:8080/swagger-ui/index.html
<BR>- Validators na camada de Resource(Controller) para não chegar nada errada na camada Services/Repository
<BR>- Controller advice para melhor apresentação do retorno das validações
<BR>- DTOs para abstrair as entidades, apesar de ser bem parecidos de início, mas já fica pronto para eventual evolução dos services

<b>Oauth2-server</b>
<BR>-Implementação de JWT - Json Web Token
<BR>-Baseado em SpringBoot/SpringSecurity e Database com os scripts inclusos na pasta resource
<BR>-Banco Postgres alocado da Amazon, com isso facilita o teste por terceiros
<BR>-Para gerar um token, use a rota http://localhost:9000/oauth/token
<BR>-USE curl -u clientId:secret -X POST localhost:9000/oauth/token\?grant_type=password\&username=user\&password=pass



