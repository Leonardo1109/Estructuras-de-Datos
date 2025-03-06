package fes.aragon.utilerias.dinamicas.listasimple;

import fes.aragon.excep.IndiceFueraDeRango;

/***
 * 
 * @author personal
 *
 * @param <E> Nodo
 * 
 *            Consideramos que el usuario esta conciente que los Nodos
 *            compeinzan desde cero, asi si quiere eliminar el nodo numero 1,
 *            estará eliminando el nodo numero 1 en la 2° posicion.
 * 
 */
public class ListaSimple<E> {
	protected Nodo<E> cabeza, cola;
	protected int longitud = 0;

	public ListaSimple() {
		cabeza = null;
		cola = null;
	}

	public void agregarCabeza(E dato) {
		cabeza = new Nodo<E>(dato, cabeza);
		if (cola == null) {
			cola = cabeza;
		}
		longitud++;
	}

	public void agregarEnCola(E dato) {
		if (cabeza == null) {
			cabeza = cola = new Nodo<E>(dato);
		} else {
			cola.setSiguiente(new Nodo<E>(dato));
			cola = cola.getSiguiente();
		}
		longitud++;
	}

	public void eliminarCola() {
		if (cabeza != null) {
			if (cabeza == cola) {
				cabeza = cola = null;
				longitud--;
			} else {
				Nodo<E> temp;
				for (temp = cabeza; temp.getSiguiente() != cola; temp = temp.getSiguiente());
				temp.setSiguiente(null);
				cola = temp;
				longitud--;
			}
		}
	}

	public boolean eliminarDato(E dato) {
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

	public int getLongitud() {
		if (longitud < 0) {
			longitud = 0;
		}
		return longitud;
	}

	public boolean esVacia() {
		return cabeza == null;
	}

	public void eliminarEnCabeza() {
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

	public void eliminarEnCola() {
		if (cabeza != null) {
			if (cabeza == cola) {
				cabeza = cola = null;
				longitud--;
			} else {
				Nodo<E> tmp;
				for (tmp = cabeza; tmp.getSiguiente() != cola; tmp = tmp.getSiguiente())
					;
				tmp.setSiguiente(null);
				cola = tmp;
				longitud--;
			}
		}
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

	/**/public void agregarNodo(int numero, E dato) {
		int contador = 1;
		for (Nodo<E> tmp = cabeza; tmp != null; tmp = tmp.getSiguiente()) {
			if (contador == numero) {
				Nodo<E> p = new Nodo<E>(dato);
				Nodo<E> q = tmp.getSiguiente();
				p.setSiguiente(q);
				cabeza.setSiguiente(p);
			} else {
				contador++;
			}
		}

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
		} else {
			return null;
		}
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
		} else {
			return -1;
		}

	}

	public void imprimirElementos() {
		for (Nodo<E> tmp = cabeza; tmp != null; tmp = tmp.getSiguiente()) {
			System.out.println(tmp.getDato());
		}
	}

	public boolean isInList(E dato, ListaSimple<E> lista) {
		boolean isInList = false;
		if (lista.esVacia()) {
			return isInList;
		}
		for (int i = 0; i < lista.longitud; i++) {
			if (dato == lista.obtenerNodo(i)) {
				isInList = true;
			}
		}
		return isInList;
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
					Nodo<E> prd, temp;
					int contador = 1;
					for (prd = cabeza, temp = cabeza.getSiguiente(); contador < indice; prd = prd
							.getSiguiente(), temp = temp.getSiguiente(), contador++)
						;
					if (temp != null) {
						borrado = true;
						longitud--;
						prd.setSiguiente(temp.getSiguiente());
						if (temp == cola) {
							cola = prd;
						}
					}
				}
			}
		}
		return borrado;
	}

	public boolean insertarEnIndice(E dato, int indice) {
		boolean insertado = false;
		if (indice >= 0 && indice <= longitud) {
			if (indice == 0) {
				this.agregarCabeza(dato);
				insertado = true;
			} else {
				Nodo<E> prd, temp = null;
				int contador = 0;
				for (prd = null, temp = cabeza; contador < indice; contador++, prd = temp, temp = temp.getSiguiente())
					;
				prd.setSiguiente(new Nodo<E>(dato, temp));
				longitud++;
				insertado = true;
			}
		}
		return insertado;
	}

	public boolean asignar(E dato, int indice) {
		Nodo<E> temp = null;
		if (indice <= longitud - 1) {
			temp = cabeza;
			for (int contador = 0; contador < indice && temp != null; contador++, temp = temp.getSiguiente())
				;

		}
		if (temp != null) {
			temp.setDato(dato);
			return true;
		} else {
			return false;
		}
	}

	public void asginar(E dato, E nuevoDato, boolean todos) {
		Nodo<E> temp = null;
		if (!todos) {
			for (temp = cabeza; temp != null; temp = temp.getSiguiente()) {
				if (temp.getDato().equals(dato)) {
					temp.setDato(nuevoDato);
					return;
				}
			}
		} else {
			for (temp = cabeza; temp != null; temp = temp.getSiguiente()) {
				if (temp.getSiguiente().equals(dato)) {
					temp.setDato(nuevoDato);
					return;
				}

			}
		}
	}

	public E obtenerCabeza() {
		return cabeza.getDato();
	}

	public E obtenerCola() {
		return cola.getDato();
	}
}
