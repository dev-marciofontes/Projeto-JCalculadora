# Projeto Calculadora Java
Este projeto é uma implementação de uma calculadora simples em Java, utilizando o Swing para construir a interface gráfica. A calculadora possui funcionalidades básicas, incluindo adição, subtração, multiplicação, divisão, porcentagem e troca de sinal.

## Funcionalidades
Operações básicas: adição, subtração, multiplicação e divisão.
Porcentagem: calcula a porcentagem entre dois números.
Troca de sinal: inverte o sinal do número atualmente exibido.
Interface gráfica amigável e fácil de usar.

## Estrutura do Código
O projeto é organizado em pacotes e classes, conforme descrito abaixo:

Memoria.java: Classe responsável por gerenciar o estado da calculadora e realizar as operações. É implementada como um singleton para garantir que apenas uma instância seja usada durante a execução do programa.

MemoriaObservador.java: Interface para observadores da classe Memoria, permitindo que componentes da interface gráfica sejam atualizados quando o valor exibido for alterado.

Botao.java: Classe que estende a classe JButton do Swing, customizando sua aparência e comportamento.

Calculadora.java: Classe principal que estende a classe JFrame do Swing e organiza os componentes da interface gráfica.

Display.java: Classe que estende a classe JPanel do Swing e implementa a interface MemoriaObservador. Exibe o valor atual da calculadora.

Teclado.java: Classe que estende a classe JPanel do Swing e implementa a interface ActionListener. Contém todos os botões da calculadora e gerencia as ações realizadas por eles.

## Instruções de Uso
Para executar a calculadora, basta compilar e executar a classe Calculadora.java. A interface gráfica será exibida e estará pronta para uso.
