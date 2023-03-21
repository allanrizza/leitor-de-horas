# Android TextToSpeech Clock

Este é um aplicativo Android simples que usa o TextToSpeech do Android para ler as horas em voz alta a cada minuto. O aplicativo exibe o horário atual em um TextView atualizado a cada segundo.

## Como funciona

O aplicativo inicializa o TextToSpeech em onCreate() e define o idioma como português do Brasil. Em seguida, um Handler é usado para aguardar até que o segundo atual seja igual a zero. Quando isso acontece, as horas e minutos são lidos em voz alta usando o método speak() do TextToSpeech.

Para exibir o horário atual na tela, um TextView é atualizado a cada segundo usando outro Handler.

## Requisitos

O aplicativo foi desenvolvido usando o Android Studio e foi testado em dispositivos com Android 11 (R) e superior.

## Como usar

Após compilar o aplicativo em seu dispositivo Android, basta abrir o aplicativo e esperar até que o TextToSpeech seja inicializado. A partir daí, o aplicativo irá ler as horas em voz alta a cada minuto. O horário atual também é exibido em um TextView na tela, atualizado a cada segundo.

## Melhorias futuras

Algumas melhorias que podem ser feitas para este aplicativo incluem:

- Adicionar suporte para outros idiomas além do português do Brasil.
- Permitir que o usuário escolha entre ler as horas em voz alta a cada minuto ou desligar a leitura automática.
- Adicionar suporte para diferentes formatos de hora (por exemplo, 12 horas ao invés de 24 horas).
- Melhorar a interface do usuário para que seja mais agradável e amigável.
