# Rede

## Android Profiler

Como o aplicativo utiliza apenas o Firebase para persistência dos dados, todas as features precisam acessar a rede, e isso pode ser visto quando analizamos o Profiler. 

O primeiro caso é quando o usuário faz o Login e entra na MainActivity, onde são mostrados os livros da categoria Lidos.
![](https://lh5.googleusercontent.com/kcpoZFWLIqFvVjYc13_j4EF5A7pIrQX3Xl4UuNezSgGXXIKF84HRH2F2Hs2J0qnjHXWPhYAJv5TJNJZiGhBG1f6x08lQWVXWpuQXH9qID8K7AS0koBW3uLQNEF7HAk_Np6FY2ZZ9)

Quando o app está na tela principal, mas sem nenhuma interação, não há uso da rede.
![](https://lh4.googleusercontent.com/kMm-i0aNU-Z0T5ynr3AGRPWpWkUsLa1ZBirQZpNzESVTS9RErjQN_Gt83_cwsW69fDEl6sBfSLEhpTw4lXYMf0_MSlUV_0mXRHSljX1Ak_ILOEHDEHHOLDnpIdaUabH5fUNctwDR)

Nesse terceiro caso, vemos o uso da rede quando o usuário pesquisa um livro.
![](https://lh6.googleusercontent.com/Y8s0dAQShpSUUYx8GK-AL4q6o1uDhP1xIHZ2oi837URp2td_MRT3nN5SM6KvuzUgLMRwvNkKRLwS3k7Urx5gIVLwv-xoYy2Z60DaehVUHwqLdXVNTiELTwEbbyIbmLu_4rZzi7hW)

E, por último, quando o usuário adiciona um livro e volta para a Main Activity.
![](https://lh5.googleusercontent.com/Thh5ksZVmShtCwfzp89chEH9K2gjwoB-HTMy_otfvr3MUelL7PcxJhTm_widt9GiJ3G2T1bzzEujAZWIBBEFayOL5FxjUw6QrUhGuf2bG6CzPtv8Y3F-pOjv-TSE9A9q5wl_x0Mq)

