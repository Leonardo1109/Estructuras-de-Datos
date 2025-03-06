package fes.aragon.modelo;

public class Nodo<E> {

	private E dato;
	private Nodo<E> siguiente;
	private Nodo<E> anterior;

	public Nodo(E dato) {
		this(dato, null);
	}

	public Nodo(E dato, Nodo<E> siguiente) {
		this.dato = dato;
		this.siguiente = siguiente;
	}

	public Nodo(E dato, Nodo<E> siguiente, Nodo<E> anterior) {
		this.dato = dato;
		this.siguiente = siguiente;
		this.anterior = anterior;
	}

	public Nodo<E> getAnterior() {
		return anterior;
	}

	public void setAnterior(Nodo<E> anterior) {
		this.anterior = anterior;
	}

	public E getDato() {
		return dato;
	}

	public void setDato(E dato) {
		this.dato = dato;
	}

	public Nodo<E> getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(Nodo<E> siguiente) {
		this.siguiente = siguiente;
	}
}
