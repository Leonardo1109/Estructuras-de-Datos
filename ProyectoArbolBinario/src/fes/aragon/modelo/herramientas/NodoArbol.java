package fes.aragon.modelo.herramientas;

import java.util.Objects;

public class NodoArbol<E> {
	protected E dato;
	protected NodoArbol<E> izquierdo, derecho;

	public NodoArbol() {
		izquierdo = derecho = null;
	}

	public NodoArbol(E dato) {
		this(dato, null, null);
	}

	public NodoArbol(E dato, NodoArbol<E> izquierdo, NodoArbol<E> derecho) {
		this.dato = dato;
		this.izquierdo = izquierdo;
		this.derecho = derecho;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dato);
	}

	public boolean mayor(Object obj) {
		boolean resultado = false;
		if (obj instanceof Integer && this.dato instanceof Integer) {
			Integer dato1 = (Integer) this.dato;
			Integer dato2 = (Integer) obj;
			if (dato1 < dato2) {
				return true;
			}
		}
		return resultado;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Integer && this.dato instanceof Integer) {
			Integer dato1 = (Integer) this.dato;
			Integer dato2 = (Integer) obj;
			if (dato1.equals(dato2)) {
				return true;
			} else {
				return false;
			}
		}
		NodoArbol<E> other = (NodoArbol<E>) obj;
		return Objects.equals(dato, other.dato);
	}

	public E getDato() {
		return dato;
	}

	public void setIzquierdo(NodoArbol<E> izquierdo) {
		this.izquierdo = izquierdo;
	}

	public void setDerecho(NodoArbol<E> derecho) {
		this.derecho = derecho;
	}

	public NodoArbol<E> getIzquierdo() {
		return izquierdo;
	}

	public NodoArbol<E> getDerecho() {
		return derecho;
	}

}