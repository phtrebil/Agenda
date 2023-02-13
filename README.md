<h1 align="center"> Agenda de Clientes </h1>

Aplicativo de agenda para salvar dados de clientes no celular.

O app utiliza listas com o recyclerView que, inicialmente, traz as informações de nome e telefone do cliente.
Foram implementadas, com o Room, as funções de criação, edicão, exclusão e leitura de cada item da lista (falta implementar AsyncTask, para evitar risco de travamentos).
Ao clicar no item, abre-se a tela de detalhes, que mostra as informções completas do cliente (nome, endereço, telefone e email). 
O aplicativo também foi utilizado para testar a implementação de diferentes funções conforme o click que o usário faz. Assim, caso haja um longo click, o app fará uma função; caso haja click simples, será outro, conforme explicado abaixo.

<h4 align="center"> 
:construction: Projeto em construção :construction:
</h4>

<h5>
Tela de Lista de Clientes
</h5>

<h5 align="center">
<img src = "https://github.com/phtrebil/Agenda/blob/main/teladeclientesActivity.jpeg"
width="300px"/>
</h5>

<h5>
Implementado
</h5>
    
- Lista de cliente feita com recyclerView.
- Click curto para ir para tela de detalhes.
- Click longo para excluir item da lista.
- Floating Action Button, que leva para a tela de formulário a fim de adicionar novo cliente.

<h5>
A Implementar
</h5>

-  Carregar imagem do cliente ao lado de seu nome e telefone.
-  Diálogo de Alerta para quando for deletar item (a fim de evitar acidentes).


<h5>
Tela de Detalhes
</h5>

<h5 align="center">
<img src = "https://github.com/phtrebil/Agenda/blob/main/tela%20de%20detalhes.jpeg"
width="300px"/>
</h5>

<h5>
Implementado
</h5>
    
- Receber dados da Activity de Lista de Clientes para inserir informações na lista de detalhes.
- Menu de editar item que leva para Activity de Formulário, com os dados do cliente preenchidos.

<h5>
A Implementar
</h5>

-  Carregar imagem do cliente acima dos dados inseridos.
-  Fazer o chainConstraint entre os elementos de textview para não encavalar o texto sobre texto.

<h5>
Tela de Formulário
</h5>

<h5 align="center">
<img src = "https://github.com/phtrebil/Agenda/blob/main/tela%20de%20formul%C3%A1rio.jpeg"
width="300px"/>
</h5>

<h5>
Implementado
</h5>
    
- Receber dados da Activity de Detalhes para quando for editar algum cliente.
- Mudar o título em caso de edição de cliente.
- Menu de salvar cliente ou edição de cliente.

<h5>
A Implementar
</h5>

-  Clicar na imagem para adicionar foto do cliente.

