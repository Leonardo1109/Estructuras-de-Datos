package fes.aragon.modelo;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.text.Text;

public class Estacionamiento{

	private ArrayList<Text> textos = new ArrayList<>();

	public void simular(int numeroDeCarros) throws Exception {

		Cola<Carro> cola = new Cola<>();
		ArrayList<Carro> coches = new ArrayList<>();
		int j;

		for (int i = 1; i <= numeroDeCarros; i++) {
			int numero = numeroAleatorio(90);
			j = 0;
			if (numero >= 0 && numero <= 60) {
				Carro coche = new Carro(i);
				cola.insertar(coche);
				coches.add(coche);
				textos.add(new Text("\nIngresa coche con boleto:" + coche.getNumeroBoleto()));
			} else {
				if (!coches.isEmpty() && !cola.estaVacia()) {
					numero = numeroAleatorio(coches.size());
					while (cola.mostrarTop().getNumeroBoleto() != coches.get(numero).getNumeroBoleto()) {
						Carro aux = cola.mostrarTop();
						cola.extraer();
						cola.insertar(aux);
						j++;
					}
					Carro carro = cola.mostrarTop();
					coches.remove(carro);
					cola.extraer();
					j++;
					textos.add(new Text("\nSale coche con boleto:" + carro.getNumeroBoleto()));
					while (!cola.estaVacia() && j <= coches.size()) {
						Carro carroAux = cola.mostrarTop();
						cola.extraer();
						cola.insertar(carroAux);
						j++;
					}
				}
			}
		}

		textos.add(new Text("\nEl orden de los carros es:"));
		while (!cola.estaVacia()) {
			textos.add(new Text("\n" + cola.extraer().toString()));
		}

	}

	public ArrayList<Text> getTextos() {
		return textos;
	}

	public int numeroAleatorio(int valor) {
		Random random = new Random();
		return random.nextInt(valor);
	}

}
