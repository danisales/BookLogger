# CPU

## Android Profiler

Ao fazer Login, podemos perceber um pequeno uso da CPU ao digitar as credenciais, que aumenta um pouco ao se autenticar e cresce mais ao carregar a tela principal.
![](https://lh5.googleusercontent.com/766RPtn2Ugoicv_KYGDMn_DuCl_99CowGzYibr96LSinxnkopJ8LvjJNs_88Q-v43IL2NR-jgBGqZBbMeF-n8i6hnzo7taFLvWAugRuNO_KLP4S-zpehoXUYjIIo4HMuu5ZuGLyM)

Ao deixar a Main Activity aberta, porém sem nenhuma interação do usuário, praticamente não há uso de processamento.
![](https://lh6.googleusercontent.com/s4wzh9NhmhzYkSIjfAdpgWJc18N9D2vk2blwEuRtCCYTg_Za8-UHX-zo1qj5KpCEJ-RBIDexO-2pTsJK-L4DW21GZMvPSEQ2m2c6BbLGXzIQ3gUC7ReTrNNGZTbP8E4yk_JrwvMM)

Quando pesquisamos um livro, assim como no caso do Login, vemos um pouco de uso quando digitamos a busca. E picos de uso quando a pesquisa é feita e os resultados são carregados na Activity.
![](https://lh4.googleusercontent.com/za6uSeVFnUs7HIbxk0qJn_jpCML82_wpW3BCkIswAFfXUEv9xBqFQiqN7L6v31S1_mwdiMV27J4SmRQVIis7Fo7Q3OG8UpX-XaBImN2_w2mL_TWKtn9szmWlyVdkIsmPuxUaXEyH)

Nesse último caso podemos ver picos de uso de CPU quando adicionamos um novo livro e quando recarregamos a tela principal.
![](https://lh3.googleusercontent.com/xcwXnjczZFmj58TCEqaer87k62OG1J9bczwFpkaE5HZqGVl5xCQUiO96cF3frilWhzjrcC1ugQKCnzfdQIz6TRFGRI37joF_T82zCIuGdXp1d2YZP0-ZkzTpvv5GFmpZt7baeGrl)

## Boas práticas

### Adapter
Foi usado um Adapter para as listas, que possibilita o View Recycling e ajuda a melhorar a performance.
![](https://lh4.googleusercontent.com/5DTIxNknbbXV_6ryY3g0Hdp5BcWpECdInoVhgn9HFydXzYgUNdsUXT8-aOPD2U9i0-JK6oETcr9xIZ7dGEwjXgSi0bGCp3VO6KE_SaXZGXco0RDjtqqZZPZGmqp-hSjQc8FkDIbw)

### Service
Foram utilizados Services para as tarefas de devem rodar em background.
![](https://lh3.googleusercontent.com/VSLAGzbqUvnwMlWEu69yCpdW2PAeSk4RtnXeuoYo0r5Ox5AjLduIHL040qPdEOrlYf_sItJj0fUXnNEIX9OpQZ4_HZ8f2_csa7Skw9eiOovgXOXGjnznPzq9T0mFMA6q5146TBF6)

### Biblioteca para carregar imagens
Para carregar as thumbnails dos livros, usei a biblioteca Glide, que é otimizada para usar menos memória e CPU, e também faz a tarefa em background.

![](https://lh6.googleusercontent.com/2cbqTOcuMSm2SFS1xXwddm1nQAioxpMiBOvdp6fEP847NtSpNVwDl7m22RGxeYH_AM3kAgxqyG-FYN3yYAAB8qc0m0qRjvAcf2amroEUDfowtV_cPJDlORMpniS0pY8yPlAWaP2V)
