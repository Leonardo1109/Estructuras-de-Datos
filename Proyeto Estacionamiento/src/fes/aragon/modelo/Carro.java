package fes.aragon.modelo;

public class Carro {

	private int numero;

	public Carro(int numeroBoleto) {
		this.numero = numeroBoleto;
	}

	public int getNumeroBoleto() {
		return numero;
	}

	@Override
	public String toString() {
		return "Coche [numeroBoleto=" + numero + "]";
	}

}
