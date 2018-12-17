# Memória

## Android Profiler

Nesse primeiro caso, vemos que quando fazemos Login o uso da memória aumenta e depois, ao carregar a Main Activity, aumenta mais um pouco e fica constante.
![](https://lh6.googleusercontent.com/opL96Tcr5Fl2XaPpvd6OOBujzn01Iv3sGiluW1-4cJXxXCKZ_14wwGy2GwIpcdnoYjCzxqYTmjZjTzFBDks2bHjnMoNwQoH0cFrdQx4E9rZUt8iHFtabE3qVCqOKqO3Yj6XuJZmH)

Quando não há nenhuma interação do usuário, o uso da memória se mantém constante.
![](https://lh3.googleusercontent.com/mtEl19hsU6UPJzFDxIna197HGjUOKBCz6Yqg4ILvwfmc4SloZUrGF5CHpbR_VUm2YdJFv2Ps4wS6cp4P03kwst1NzpdMN5jyhZWBRurYF63Y0bXF7WiQLxjlthJWvmX8eoU0aBR0)

Ao pesquisar um livro, há uma pequena variação quando digitamos a busca e mais um aumento quando a pesquisa é feita.
![](https://lh3.googleusercontent.com/EiecS3tT-xGQtakdWCH7DjC47IXz78jtRx-dm6ESd6NWcx74UHqPasv2SzO1oHQGT5D-A5SqAYi_mcp7ZI-quhWjT9e5xXcyaHK_AH2fOYaLk38GNZlqVw2sAKIAvQRpwhlUUoj0)

E ao adicionar um livro, o uso da memória aumenta um pouco e depois cai.
![](https://lh3.googleusercontent.com/S2PWWNcEfQmfSMwF0_5NeA79iNFLw_ipwCMq_QoH042bIqF6tGsyToLq4PMIB5oPT5ss_ycmAf14J7SmR-oCko9yrpTSNZ52e4vBmOZ_ETaYvrKK-H6M3YMQ3uxEy9X_QkA9IMrE)

## Leak Canary

O LeakCanary foi configurado no projeto, mas nenhum leak foi encontrado. Fiz testes adicionando, editando e removendo livros, trocando de fragmento na Main Activity várias vezes e fazendo scrolling nas listas e pesquisas.

![](https://lh5.googleusercontent.com/5ggnOo_fqOl6bM9zomwLtR6xMicCuqSkiWv1-dT8dfMjMecv_axV7t6vRdFJTY0TC1LLi-J5IXmzPbE3SgeCm4b1vesCEVKRBAbw5MoXGxIbL5ndnDG1qrcPHIzD_azVK3SloojN)

![](https://lh3.googleusercontent.com/D_FrtNYhIt-K3SSdl8jk4UTvw5aTJp6l-Li5FJwE6HvgV7YlGuummZBEIveCzEIDTOaviOdCCLXNoViWajNJwAhZMjyh-Fn03q42-ixSaHnbpsgpwrQbvg9SRYf130dw6ZKn5aN9)

## Boas práticas

### Adapter
Foi usado um Adapter para as listas, que possibilita o View Recycling e ajuda a melhorar a performance.

![](https://lh4.googleusercontent.com/5DTIxNknbbXV_6ryY3g0Hdp5BcWpECdInoVhgn9HFydXzYgUNdsUXT8-aOPD2U9i0-JK6oETcr9xIZ7dGEwjXgSi0bGCp3VO6KE_SaXZGXco0RDjtqqZZPZGmqp-hSjQc8FkDIbw)

### Desregistrar broadcast
É dado unregister nos dois broadcasts que são enviados. Na Activity isso é feito em onDestroy e no Fragment isso é feito em onPause.

![](https://lh6.googleusercontent.com/Hm37axNLdOMIZJABNJ1-Tj42fG802lCk5JBg12btmYNGMDmtHk0RQhvODbH83ByDuG65UbYBB_4zmGcySL2KDP5bzuRvm3RDBF60LL_Y4Vzckr9g_q8MUkcub_m2gza5ucFzu0i7)
