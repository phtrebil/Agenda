# Agenda
Aplicativo de agenda para salvar dados de seus clientes no celular.

O app utiliza listas com o recyclerView que inicialmente traz as informações de nome e telefone do cliente.
Implementamos, com o Room, as funções de criação, edicão, exclusão e leitura de cada item da lista. 
Ao clicar no item abre-se a tela de detalhes que mostra as informções completas do cliente (nome, endereço, telefone e email). 
O app ainda está inacabado, pois em sua concepção as informações do cliente contavam com uma imagem que poderia ser carregada da galeria do celular.
O banco de dados ainda não está completamente implementado, não estamos utilizando no momento Asynctask, o que traz risco de quebra para o app.
Ainda há necessidade de aperfeiçoar a tela de detalhes.

O aplicativo também foi utilizado para testar a implementação de diferentes funções conforme o click que o usário faz, assim caso haja um longo click
o cliente será deletado(devo atualizar essa função com uma caixa de dialogo questionando "realmente deseja excluir cliente?", a fim de evitar acidentes).
caso o usuário execute click simples abrirá a tela de detalhes com informações completas do cliente. Da tela de detalhes é possível realizar a edição do cliente.
