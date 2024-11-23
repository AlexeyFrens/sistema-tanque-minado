# :bomb: Tanque Minado 

 

O tanque minado é um jogo de tabuleiro de batalha de tanques 1 vs 1. Os jogadores batalharão entre si com 3 tanques cada, cada tanque com um sistema de armas diferente e gerado aleatoriamente. O jogo termina quando um dos jogadores tiver todos os tanques destruídos. A ideia do jogo em si foi criada do zero, mas está utilizando como referência o sistema de movimentação do xadrez. 

Quando começar a batalha, cada jogador terá 2 rodadas (uma para movimento e outra para ataque), o vencedor será o jogador que destruir todos os tanques do outro jogador primeiro. 

A partida terá um timer e durará apenas 3 minutos. As regras propostas após os 3 minutos estão no tópico “Desafios”. 

 

### :runner: Regras de movimento 

Cada tanque terá um sistema de movimento diferente com a direção e quantidade de blocos possíveis para se movimentar únicos. O jogador é livre para escolher a direção que quiser para movimentar o tanque. 

O jogo verificará se é possível fazer o movimento e acenderá os quadrados que o jogador pode escolher para movimentar seu tanque. 

O movimento será inserido pelo terminal, o jogador terá que colocar as coordenadas do tanque escolhido e as coordenadas de destino correspondentes ao tabuleiro. 

 

### :dart: Regras de Ataque 

Após fazer um movimento, o jogador poderá fazer um ataque se possível, ou seja, se um jogador inimigo estiver no alcance de seu tanque. O alcance do ataque depende da arma entregue no primeiro round. 

Se tiver algum inimigo no alcance do tanque que fará o ataque, o quadrado que está o tanque alvo acenderá. 

Para realizar o ataque, o jogador deverá colocar as coordenadas de destino correspondentes ao tabuleiro. 

 

### :gun: Tipos de armas 

Canhão pesado – Aguenta 3 tiros antes de ser destruído, há um alcance de 2 quadrados e demora 2 rounds para ser usado novamente. 

Canhão longo alcance - Aguenta 2 tiros antes de ser destruído, há um alcance de 3 quadrados e demora 2 rounds para ser usado novamente. 

Bomba - Aguenta 1 tiros antes de ser destruído, há um alcance de 4 quadrados, demora 3 rounds para ser usado novamente e a área afetada é de 2 x 2 quadrados. 

 

### :jigsaw: Desafio 

Se após 3 minutos ninguém ter destruído todos os tanques, o jogo eliminará automaticamente os tanques dos jogadores, até que cada jogador tenha apenas 1 tanque.  

 

# Estrutura do Jogo 

### Classes Principais 

1. Main 

   A classe Main é responsável por controlar o fluxo principal do jogo. Suas tarefas consistem em inicializar a partida com a classe “GameMatch”, gerenciar turnos, ataques e movimentos, exibir o estado do tabuleiro e as mensagens do jogo e verificar o tempo limite (“timeOut”) e encerrar o jogo adequadamente. 

 

2. GameMatch 

   A classe GameMatch é o que gerencia o estado do jogo. Suas tarefas consistem em armazenar o tabuleiro e as peças, determinar os turnos e verificar as condições de vitória, contém a lógica para movimentar e atacar peças, inclui o temporizador para redução do campo de batalha e define as bordas restritas do tabuleiro após o tempo limite. 

 

3. Board 

   A classe Board é a representação do tabuleiro do jogo. Suas principais tarefas consistem em armazenar peças em uma matriz bidimensional e colocar, mover e remover peças. 

 

4. GameTankPiece 

   A classe GameTankPiece é responsável por representar um tanque no jogo. Suas principais tarefas consistem em determinar se o tanque tem uma possibilidade de atacar algum inimig e é onde estão implementados os atributos de cor e vida da peça. 

 

5. BlockPiece 

   A classe BlockPiece é responsável por representar uma peça fixa que não pode ser atacada e fica nas bordas do tabuleiro nas rodadas de deathmatch. 

 

6. UI 

   A classe UI é responsável por gerenciar a interface do jogo. Suas principais tarefas consistem em exibir o tabuleiro e as mensagens do jogo, ler a posição de origem e destino das ações do jogador e destacar áreas de movimento e ataque no tabuleiro. 

 

 

# Interação com o jogador 

### :desktop_computer: Interface no console 

O jogo exibe o tabuleiro atual com as peças posicionadas, como também movimentos e ataques possíveis destacados com a cor de fundo branco na tela. Para movimentar as peças, os jogadores inserem coordenadas no formado “a1”, “b2” por exemplo. 

 

### :warning: Mensagens de erro 

Erros comuns, como coordenadas inválidas ou ações fora das regras, exibem mensagens personalizadas. E o usuário necessita apertar a tecla ENTER para continuar o jogo. 

 

### :bell: Notificações 

Informações sobre o turno atual, vida dos tanques de ambos os jogadores, tanques destruídos de cada jogador e o ganhador são exibidas na tela. 

# :framed_picture: Imagens

| Tela inicial | Tratamento de Erro | Movimentos Possíveis |
|--------------|--------------------|----------------------|
| ![image](https://github.com/user-attachments/assets/04ccb054-473d-440a-acc5-d0c920fcb316) | ![image](https://github.com/user-attachments/assets/f35e0168-bd77-4007-9656-f4a988525293) | ![image](https://github.com/user-attachments/assets/d4935f4a-7476-449a-9d33-36f41bcd36a0) |

| Ataque Possível | Deathmatch | Final do Jogo |
|-----------------|------------|---------------|
| ![image](https://github.com/user-attachments/assets/069ef326-da95-4bb2-8835-96897c80ce26) | ![image](https://github.com/user-attachments/assets/fdcb2e60-4571-4a34-8ad3-8290adf543e5) | ![image](https://github.com/user-attachments/assets/a36eefbb-9711-4aaf-924d-ce3bfccc109f) |
