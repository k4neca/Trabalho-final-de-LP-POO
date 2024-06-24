# Teleco Store

## Objetivo do Projeto

O projeto tem por objetivo criar um e-comerce em Java utilizando Swing para criar a Teleco Store, uma loja ficticia de celulares.

## Instruções

Talves seja necessário a utilização do plug-in "Database Navigator" para a integração do código com o Banco de Dados.
Também será necessário alterar no código o caminho absoluto de acesso ao banco de dados, em meu código está assim: 
jdbc:sqlite:C:/Users/kaneca/Desktop/Workspace/TelecoStore/src/scripts/script_teleco_store - Substitua o /k4neca pelo usuário do seu computador.

### Como usar

Ao iniciar o projeto deve ser cadastrado um usuário e utilizar o e-mail e senha cadastrados para conseguir acessar a tela de produtos. Dentro da tela de produtos é possível escolher quantos produtos quiser.
Cada clique no botão "Carrinho" adiciona uma unidade do item ao carrinho, que pode ser observado no paínel na esquerda.
Clicando em finalizar pedido você será direcionado a tela de pagamento. Nessa tela é possível escolher a forma de envio ou retirada em alguma das lojas cadastradas. Também é possível escolher uma forma de pagamento (pix, crédito e débito).
Existe novamente no canto inferior da tela um paínel com os itens selecionados.
Clique em finalizar compra para finalizar a operação.

## Dificuldades

Devido a falta de experiência em desenvolvimento de um sistema mais complexo houve uma certa dificuldade em conectar o banco de dados com a aplicação sendo assim necessária a utilização de ArrayList para o funcionamento do código.
Também houve dificuldade com relação aos pilares da Orientação a Objetos.

## Pontos a serem Melhorados

Encapsulamento das classes;
Delegação de tarefas individuais para os métodos criados;
Conexão das informações dos produtos e dos pedidos com o banco de dados;
Criar um paínel de administrador para controle dos produtos diretemante na interface ao invés do DBeaver;

## Agradecimentos

Gostaria de agradecer aos professores João Paulo Aramuni e Marcelo Luiz dos Santos pelos conhecimentos fornecidos durante a aula e por serem sempre solicitos e atenciosos para solucionar as dúvidas.
Sem o apoio de vocês este projeto não seria possível.
