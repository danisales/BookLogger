# BookLogger

## Aplicação

A ideia da aplicação é catalogar livros, dividindo em: lidos, lendo, para ler e wishlist. As categorias Para ler e Wishlist são similares, mas a diferença é que a primeira é para os livros o usuário já possui e pretende ler e o segundo para livros que o usuário pretende comprar. O usuário também pode dizer se o livro está emprestado ou não.

## Interface

A tela principal do app usa o layout Bottom Navigation e foram criados dois fragmentos: BookFragment e SettingsFragment. O SettingsFragment é onde o usuário pode alterar a senha e fazer logout.

O BookFragment possui um Recycler View, que mostra a lista de livros de acordo com a categoria escolhida. Ao criar uma instância do fragmento, é passada uma String com a categoria desejada, que é utilizada para criar a lista que será passada para o BookAdapter.

Ao adicionar um livro, o usuário vai para uma tela onde faz uma busca e escolhe o livro. Depois disso, pode alterar a categoria e selecionar se o livro está emprestado.

## APIs e Bibliotecas

A [API do Google Books](https://developers.google.com/books/) foi usada para obter as informações dos livros, para fazer os requests para a API foi utilizado o [Fuel](https://github.com/kittinunf/Fuel). O [Firebase](https://firebase.google.com/) foi usado para a parte de autenticação e banco de dados. E a biblioteca [Glide](https://github.com/bumptech/glide) foi usada para carregar e exibir as thumbnails dos livros.

## Componentes Android

Foram usadas Activities para as telas. E foram criados dois services: FirebaseService e GoogleBooksService. O primeiro é usado para recuperar os dados do banco de dados, filtrando pela categoria. E o segundo é usado para fazer o request para a API do Google Books.

Nos dois casos, após a operação terminar, um Broadcast é enviado com o resultado para a Activity (ou Fragment) que chamou o Service.
