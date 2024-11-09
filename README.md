<h1>Desafio de Backend Java Pleno com Spring Boot: Sistema de Notificações</h1>
<p align="center"><a href="https://github.com/MV-Mobilidade/coding_challenge/tree/main">Desafio.</a></p>
<b>Objetivo:</b> API RESTful utilizando Java e a framework Spring Boot para o gerenciamento e envio de notificações personalizadas para usuários de um aplicativo.

<h1>Tecnologias utilizzadas</h1>
<ul>
  <li>Java 17</li>
  <li>Spring Boot 3.3.5</li>
  <li>MySQL</li>
  <li>Docker</li>
  <li>OpenAPI/Swagger</li>
</ul>

<h1>Diagramas</h1>
<img src="https://i.imgur.com/9IoJUwN.png">
<p><i>* Por favor, clicar na imagem para ampliá-la.</i></p>

<h3>Entidades:</h3>
<img src="https://i.imgur.com/rxO5DxT.png">

<h3>1. Clonando o repositório</h3>
    <pre><code>
git clone https://github.com/seu-usuario/sistema-notificacoes.git
cd sistema-notificacoes
    </code></pre>

<h3>2. Configurando o Banco de Dados</h3>
    <p>O sistema utiliza o MySQL como banco de dados. As configurações estão definidas no arquivo <code>docker-compose.yml</code>.</p>
    <p>Para subir o banco de dados com Docker:</p>
    <pre><code>
docker-compose up -d
    </code></pre>

<h1>Endpoints</h1>
 <p>A documentação da API pode ser acessada via Swagger:</p>
<code>http://localhost:8080/swagger-ui.html</code>


 <h3>Exemplo de Requisições</h3>
 <h4>1. Criar notificações:</h4>
 <p>Método <b>POST</b> <code>/notifications</code></p>
 <pre><code>
 {
  "title": "Nova Notificação",
  "description": "Você tem uma nova notificação",
    "type": "Informativo",
  "user": {
     "userId": 1
   }
}</code></pre>

<h4>1. Visualizar notificações por usuário:</h4>
 <p>Método <b>GET</b> <code>/notifications/{userId}</code></p>
 <p>Retorna todas as notificações para o usuário com o <code>userId</code> informado.</p>

<h4>1. Marcar notificação como lida:</h4>
 <p>Método <b>PUT</b> <code>/notifications/{notificationId}/mark-as-read</code></p>
 <pre><code>{
  "markAsRead":true
}</code></pre>

<h4>1. Enviar e-mail:</h4>
 <p>O envio de e-mails é realizado de forma assíncrona utilizando a anotação <code>@Async</code>. 
   Quando uma notificação é criada, se o canal de notificação for e-mail, a aplicação dispara um e-mail para o usuário. Para o envio, foi utilizado o <code>JavaMailSender</code>.</p>

<h2>Docker</h2>
<p>O projeto pode ser facilmente executado com Docker. Siga os passos:</p>

<h3>1. Compile a aplicação:</h3>
    <pre><code>
./mvnw clean package
    </code></pre>

<h3>2. Suba os contêineres:</h3>
    <pre><code>
docker-compose up --build
    </code></pre>

<p>Isso irá criar o contêiner da aplicação e do banco de dados MySQL, e a aplicação estará disponível em <code>http://localhost:8080</code>.</p>
