package fes.aragon.utilerias.dinamicas.cola;

import fes.aragon.utilerias.dinamicas.listasimple.ListaSimple;
import fes.aragon.utilerias.dinamicas.listasimple.Nodo;

public class Cola<E> {

	private ListaSimple<E> cola = new ListaSimple<>();

	public void borrar() {
		cola = new ListaSimple<>();
	}

	public boolean estaVacia() {
		return cola.esVacia();
	}

	public void insertar(E dato) {
		cola.agregarEnCola(dato);
	}

	public E extraer() throws Exception {
		E tmp = null;
		if (!estaVacia()) {
			tmp = cola.obtenerCabeza();
			cola.eliminarCabeza();
		} else {
			throw new Exception("cola vacia");
		}
		return tmp;
	}

	public E elementoSuperior() throws Exception {
		E tmp = null;
		if (!estaVacia()) {
			tmp = cola.obtenerCola();
			cola.agregarCabeza(tmp);
		} else {
			throw new Exception("cola vacia");
		}
		return tmp;
	}

	public E elementoInferior() throws Exception {
		E tmp = null;
		if (!estaVacia()) {
			tmp = cola.obtenerCabeza();
			cola.agregarCabeza(tmp);
		} else {
			throw new Exception("cola vacia");
		}
		return tmp;
	}
}
