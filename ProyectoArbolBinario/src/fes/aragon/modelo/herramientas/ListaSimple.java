package fes.aragon.modelo.herramientas;

public class ListaSimple<E> {

	protected Nodo<E> cabeza, cola;
	protected int longitud = 0;

	public ListaSimple() {
		cabeza = cola = null;
	}

	public void agregarEnCola(E dato) {
		//si cabeza es igual a null significa que no hay elementos en la lista
		if (cabeza == null) {
			//creamos el primer elemento de la lista
			cabeza = cola = new Nodo<E>(dato);
		} else {
			//si hay como minimo un elemento en la lista actualizamos la cola
			cola.setSiguiente(new Nodo<E>(dato));
			cola = cola.getSiguiente();
		}
		longitud++;
	}

	public void agregarEnCabeza(E dato) {
		cabeza = new Nodo<E>(dato, cabeza);
		// si no hay elementos en la lista
		if (cola == null) {
			// cola apunta al primer Nodo de la lista
			cola = cabeza;
		}
		longitud++;
	}

	public void imprimirElementos() {
		for (Nodo<E> tmp = cabeza; tmp != null; tmp = tmp.getSiguiente()) {
			System.out.print(tmp.getDato() + " ");
		}
		System.out.println();
	}

	public void eliminarCabeza() {
		if (cabeza != null) {
			if (cabeza == cola) {
				cabeza = cola = null;
				longitud--;
			} else {
				cabeza = cabeza.getSiguiente();
				longitud--;
			}
		}

	}

	public void eliminarCola() {
		if (cabeza != null) {
			if (cabeza.equals(cola)) {
				cabeza = cola = null;
				longitud--;
			}
		}
		for (Nodo<E> tmp = cabeza; tmp != cola; tmp = tmp.getSiguiente()) {
			if (tmp.getSiguiente() == cola) {
				cola = tmp;
				cola.setSiguiente(null);
				break;
			}
		}
		longitud--;
	}

	public int estaEnLista(E dato) {
		int indice;
		Nodo<E> tmp = null;
		tmp = cabeza;
		for (indice = 0; indice < longitud - 1 && tmp != null
				&& tmp.getDato().equals(dato); indice++, tmp = tmp.getSiguiente())
			;
		if (tmp != null) {
			return indice;
		}
		return -1;
	}

	public void asignar(E dato, E nuevoDato, boolean lados) {
		Nodo<E> tmp = null;
		if (lados) {
			for (tmp = cabeza; tmp != null; tmp = tmp.getSiguiente()) {
				if (tmp.getDato().equals(dato)) {
					tmp.setDato(nuevoDato);
					return;
				}
			}
		} else {
			for (tmp = cabeza; tmp != null; tmp = tmp.getSiguiente()) {
				if (tmp.getDato().equals(dato)) {
					tmp.setDato(nuevoDato);
				}
			}
		}
	}

	public boolean asignar(E dato, int indice) {
		Nodo<E> tmp = null;
		if (indice <= longitud - 1) {
			tmp = cabeza;
			for (int contador = 0; contador < indice && tmp != null; contador++, tmp = tmp.getSiguiente())
				;
		}
		if (tmp != null) {
			tmp.setDato(dato);
			return true;
		}
		return false;
	}

	public boolean insertarEnIndice(E dato, int indice) {
		boolean inserto = false;
		if (indice >= 0 && indice <= longitud - 1) {
			if (indice == 0) {
				this.agregarEnCabeza(dato);
				inserto = true;
			} else {
				Nodo<E> prv, tmp = null;
				int contador = 0;
				for (prv = null, tmp = cabeza; contador < indice; contador++, prv = tmp, tmp = tmp.getSiguiente())
					;
				prv.setSiguiente(new Nodo<E>(dato, tmp));
				longitud++;
				inserto = true;
			}
		}
		return inserto;
	}

	public E obtenerNodo(int indice) {
		Nodo<E> tmp = null;
		if (indice <= longitud) {
			tmp = cabeza;
			for (int contador = 0; contador < indice && tmp != null; contador++, tmp = tmp.getSiguiente())
				;
		}
		if (tmp != null) {
			return tmp.getDato();
		}
		return null;
	}

	public boolean eliminar(E dato) {
		boolean borrado = false;
		if (cabeza != null) {
			if (cabeza == cola && dato.equals(cabeza.getDato())) {
				cabeza = cola = null;
				borrado = true;
				longitud--;
			} else if (dato == cabeza.getDato()) {
				cabeza = cabeza.getSiguiente();
				borrado = true;
				longitud--;
			} else {
				Nodo<E> prd, tmp;
				for (prd = cabeza, tmp = cabeza.getSiguiente(); tmp != null
						&& !tmp.getDato().equals(dato); prd = prd.getSiguiente(), tmp = tmp.getSiguiente())
					;
				if (tmp != null) {
					borrado = true;
					longitud--;
					prd.setSiguiente(tmp.getSiguiente());
					if (tmp == cola) {
						cola = prd;
					}
				}
			}
		}
		return borrado;
	}

	public boolean eliminarEnIndice(int indice) {
		boolean borrado = false;
		if (indice >= 0 && indice <= longitud - 1) {
			if (cabeza != null) {
				if (cabeza == cola && indice == 0) {
					cabeza = cola = null;
					borrado = true;
					longitud--;
				} else if (indice == 0) {
					cabeza = cabeza.getSiguiente();
					borrado = true;
					longitud--;
				} else {
					Nodo<E> prd, tmp;
					int contador = -1;
					for (prd = cabeza, tmp = cabeza.getSiguiente(); contador < indice; prd = prd
							.getSiguiente(), tmp = tmp.getSiguiente(), contador++)
						;
					if (tmp != null) {
						borrado = true;
						longitud--;
						prd.setSiguiente(tmp.getSiguiente());
						if (tmp == cola) {
							cola = prd;
						}
					}
				}
			}
		}
		return borrado;
	}

	public boolean esVacia() {
		return cabeza == null;
	}

	public Nodo<E> getCabeza() {
		return cabeza;
	}

	public void setCabeza(Nodo<E> cabeza) {
		this.cabeza = cabeza;
	}

	public Nodo<E> getCola() {
		return cola;
	}

	public void setCola(Nodo<E> cola) {
		this.cola = cola;
	}

	public int getLongitud() {
		if (longitud < 0)
			longitud = 0;
		return longitud;
	}

	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}

}
