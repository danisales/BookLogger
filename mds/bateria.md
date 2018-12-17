# Bateria

O Android Studio 3.2 tem a opção de Energy no Android Profiler, porém, apenas para devices com Android 8.0 ou superior. Como o device que estou usando para testes usa Android 7, não consegui obter os dados usando o Profile.

Também tive problemas com o Battery Historian, um dos principais motivos é que a própria documentação estava errada algumas vezes, como é mostrado nessas issues:

* [https://github.com/google/battery-historian/issues/141](https://github.com/google/battery-historian/issues/141)
* [https://github.com/google/battery-historian/issues/125](https://github.com/google/battery-historian/issues/125)
* [https://github.com/google/battery-historian/issues/147](https://github.com/google/battery-historian/issues/147)

Consegui instalar o Battery Historian direto do código fonte e segui a [documentação](https://developer.android.com/studio/profile/battery-historian) para obter o arquivo do bugreport.

![](https://lh6.googleusercontent.com/FF21j6jqkRNopurmX0due1TCl_oJUaM3Kpe97JgV_6d95EtCoaAbncgwtCayHdpdZttrga9gK5hG1tB5H4I8VSUOTsZDXF2MdZf_xDwjd5ASISdq152g-BBQb1BqiTQYPFzOcg1Z)
![](https://lh3.googleusercontent.com/LGFXIJcir-xyFzCEKz29R_UcrYQ3CpXDL97WTE-rdNtC6RhBiDef4qZwbcqrE8KhPsN1PRQLWLqRHpUpp0fRq6r7dMfHU_nTtwPYUL4SvoA2UWS_vk-pCQeY4Kd44blc8_8IuR2e)

Porém, obtive o seguinte erro:

![](https://lh6.googleusercontent.com/E3CJzQDD2ou5hHy1eFzIsoXr_MAiwfOvixFeGspTtgwRJ-f0ALCVeIzWJL33uNvZfkxRzo3wm0Hqf8HL_eJHAo3-bW4OOTyjnrQdWy3RHZNHBaAQfd5F6WQTroWsRmeoAI8VmbgR)

Encontrei [esse site](https://www.google.com/url?q=https://bathist.ef.lc/&sa=D&ust=1545048554603000), que é o próprio Battery Historian, mas hospedado em um servidor, para não precisar de instalação. Mesmo assim o erro persistiu e não consegui encontrar nenhuma informação de como resolver, além do que é dito na [Issue #147](https://github.com/google/battery-historian/issues/147), que também não funcionou.

Os arquivos gerados estão na pasta /battery_files.
