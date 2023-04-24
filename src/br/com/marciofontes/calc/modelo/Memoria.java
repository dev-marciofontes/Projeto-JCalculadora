package br.com.marciofontes.calc.modelo;

import java.util.ArrayList;
import java.util.List;

public class Memoria {

	private enum TipoComando {
		ZERAR, NUMERO, DIV, MULT, SUB, SOMA, IGUAL, VIRGULA, PORCENTAGEM, TROCA_SINAL
	};

	private static final Memoria instancia = new Memoria();

	private final List<MemoriaObservador> observadores = new ArrayList<>();

	private TipoComando ultimaOperacao = null;;
	private boolean substituir = false;
	private String textoAtual = "";
	private String textoBuffer = "";

	private Memoria() {

	}

	public static Memoria getInstancia() {
		return instancia;
	}

	public void addObservador(MemoriaObservador observador) {
		observadores.add(observador);
	}

	public String getTextoAtual() {
		return textoAtual.isEmpty() ? "0" : textoAtual;
	}

	public void processarComando(String texto) {

		TipoComando comando = detectarTipoComando(texto);

		if (comando == null) {
			return;
		} else if (comando == TipoComando.ZERAR) {
			textoAtual = "";
			textoBuffer = "";
			substituir = false;
			ultimaOperacao = null;
		} else if (comando == TipoComando.TROCA_SINAL && textoAtual.contains("-")) {
			textoAtual = textoAtual.substring(1);
		} else if (comando == TipoComando.TROCA_SINAL && !textoAtual.contains("-")) {
			textoAtual = "-" + textoAtual;
		} else if (comando == TipoComando.NUMERO || comando == TipoComando.VIRGULA) {
			textoAtual = substituir ? texto : textoAtual + texto;
			substituir = false;
		} else {
			substituir = true;
			textoAtual = obterResultadoOperacao();
			textoBuffer = textoAtual;
			ultimaOperacao = comando;
		}

		observadores.forEach(o -> o.valorAlterado(getTextoAtual()));
	}

	private String obterResultadoOperacao() {
		if (ultimaOperacao == null || ultimaOperacao == TipoComando.IGUAL) {
			return textoAtual;
		}

		double numeroBuffer = Double.parseDouble(textoBuffer.replace(",", "."));
		double numeroAtual = Double.parseDouble(textoAtual.replace(",", "."));

		double resultado = 0;

		if (ultimaOperacao == TipoComando.SOMA) {
			resultado = numeroBuffer + numeroAtual;
		}

		if (ultimaOperacao == TipoComando.SUB) {
			resultado = numeroBuffer - numeroAtual;
		}

		if (ultimaOperacao == TipoComando.MULT) {
			resultado = numeroBuffer * numeroAtual;
		}

		if (ultimaOperacao == TipoComando.PORCENTAGEM) {
			resultado = (numeroBuffer * numeroAtual) / 100;
		}

		if (ultimaOperacao == TipoComando.DIV) {
			resultado = numeroBuffer / numeroAtual;
		}

		String resultadoString = Double.toString(resultado).replace(".", ",");
		boolean inteiro = resultadoString.endsWith(",0");

		return inteiro ? resultadoString.replace(",0", "") : resultadoString;
	}

	private TipoComando detectarTipoComando(String texto) {

		if (textoAtual.isEmpty() && texto == "0") {
			return null;
		}

		try {
			Integer.parseInt(texto);
			return TipoComando.NUMERO;
		} catch (NumberFormatException e) {
			// Quando não for número...
			if ("AC".equals(texto)) {
				return TipoComando.ZERAR;
			} else if ("/".equals(texto)) {
				return TipoComando.DIV;
			} else if ("*".equals(texto)) {
				return TipoComando.MULT;
			} else if ("+".equals(texto)) {
				return TipoComando.SOMA;
			} else if ("-".equals(texto)) {
				return TipoComando.SUB;
			} else if ("=".equals(texto)) {
				return TipoComando.IGUAL;
			} else if (",".equals(texto) && !textoAtual.contains(",")) {
				return TipoComando.VIRGULA;
			} else if ("%".equals(texto)) {
				return TipoComando.PORCENTAGEM;
			} else if ("±".equals(texto)) {
				return TipoComando.TROCA_SINAL;
			}
		}
		return null;
	}

}
