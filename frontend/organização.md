Started: ⌛
To do: 📌
Done: ✅

📁 views
    - home ⌛
        ✅ // visualização dos produtos ativos - visão cliente 
		// paginação
	- carrinho ⌛
		// onde ficam os produtos adicionados pelo cliente
    📁 produto 
        ✅ - add-produto 
        ✅ ** Serviço: Get, Add, Edit e Delete 
        ✅ - list-produto 
		✅ - admin-list-produto // para poder inativar o produto
		✅ - edit-produto 
    📁 endereco ⌛ (Amanda)
        ✅ - add-endereco 
        ✅ ** Serviço: Get, Add e Delete 
		✅ // Via CEP
		// ?? Como vai ser esse delete do endereço ??
    📁 cliente ⌛ (Rebeca)
        ✅ - add-cliente 
			// mask nos campos de input
        ** Serviço: Get, Add e Delete(inativa)
        - choose-cliente 
			// Quem é o cliente? Antes de fazer a compra escolher qual é o cliente.
			// usar o pipe da mask para exibir corretamente o cpf ou cnpj
		// ?? Método para buscar o id do endereco
    - carrinho ⌛
		// Adicionar itens no carrinho
		// Editar a quantidade
		// Excluir
		// Desconto
		// Enviar a lista dos produtos para o backend
		// Prosseguir para fechar o pedido
    - pedido 📌
        - last-pedido
			// quem é o cliente ?
			// Puxar o último id do pedido
        - history-pedido
			// listar todos os pedidos
    📁 shared
        ✅ - footer 
        ✅ - navbar
📁 models
    ✅ - produto
    ✅ - endereco
    ✅ - cliente
    - itemPedido 📌 (?) // vamos mandar uma lista com os produtos para o backend
    - pedido 📌 (?)
📁 services
    ✅ - produto 
    ✅ - endereco
    - cliente ⌛
	- itemPedido
    - pedido 📌 (?)