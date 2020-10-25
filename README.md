# Microserviços em Java com Spring Boot
Projeto para demonstrar a construção de um sistema utilizando Arquitetura de Microserviços em Java com Spring Boot.

## Orientações

Você precisa ter o **Docker** instalado para executar este projeto. Se você é usuário Windows, recomendo que leia as orientações de instalação e e lista de compatibilidade no site oficial do Docker antes de baixar o projeto. Atualmente, apenas versões Home e Pro do Windows 10 são compatíveis com o Docker Client.

Usuários Windows precisarão modificar uma constante no arquivo de propriedades do config server, conforme abaixo.

Atual (Linux e Mac):

`spring.cloud.config.server.native.search-locations=file:/config-files`

Modificado (Windows):

`spring.cloud.config.server.native.search-locations=file:///config-files`

Antes de dar o *build* nas *compose files*, é necessário executar o comando **Maven** para gerar o jar de cada um dos projetos. Se você utilizar o VS Code, execute **CTRL + SHIFT + P > Maven: Execute Commands... > package**.

Com os arquivos gerados, você pode subir os dois ambientes (infra e serviços) separadamente, mas antes de fazer isso, **ajuste os mapeamentos dos volumes para a pasta correta em seu disco** em cada uma das *compose files*. Os locais que devem ser modificados são:
- INFRA: config-server
- SERVIÇOS: pessoas-db e animais-db

Para "subir" os ambientes, abra uma janela do terminal na pasta **java-microservices** e execute:

`docker-compose -f "docker-compose-infra.yml" up --build`

Depois, abra outra janela do terminal na mesma pasta e execute:

`docker-compose -f "docker-compose-servicos.yml" up --build`

Se todas as orientações dadas aqui foram seguidas corretamente, tudo funcionará corretamente.

Agora, para acessar seus microserviços, utilize um software como o **[Postman](https://www.postman.com/)** ou similar.