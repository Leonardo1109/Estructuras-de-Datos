package fes.aragon.modelo.herramientas;

public class ArbolBinarioOrden<E> {
	protected NodoArbol<E> raiz;

	public ArbolBinarioOrden() {
		raiz = null;
	}

	public NodoArbol<E> getRaiz() {
		return raiz;
	}

	public void insertar(E dato) {
		NodoArbol<E> n = raiz;// guardamos la raiz
		NodoArbol<E> previo = null;

		while (n != null) {
			previo = n;
			if (n.mayor(dato)) {
				n = n.derecho;
			} else {
				n = n.izquierdo;
			}
		}
		if (raiz == null) {
			raiz = new NodoArbol<E>(dato);
		} else if (previo.mayor(dato)) {
			previo.derecho = new NodoArbol<E>(dato);
		} else {
			previo.izquierdo = new NodoArbol<E>(dato);
		}
	}

	public void imprimir(NodoArbol<E> n) {
		System.out.println(n.dato);
	}

	public void recorridoAmplitud() throws Exception {
		NodoArbol<E> n = raiz;
		Cola<NodoArbol<E>> cola = new Cola<>();
		if (n != null) {
			cola.insertar(n);
			while (!cola.estaVacia()) {
				n = cola.extraer();
				imprimir(n);
				if (n.izquierdo != null) {
					cola.insertar(n.izquierdo);
				}
				if (n.derecho != null) {
					cola.insertar(n.derecho);
				}
			}
		}
	}

	public void preorden(NodoArbol<E> n) {
		if (n != null) {
			imprimir(n);
			preorden(n.izquierdo);
			preorden(n.derecho);
		}
	}

	public void orden(NodoArbol<E> n) {
		if (n != null) {
			orden(n.izquierdo);
			imprimir(n);
			orden(n.derecho);
		}
	}

	public void postorden(NodoArbol<E> n) {
		if (n != null) {
			postorden(n.izquierdo);
			postorden(n.derecho);
			imprimir(n);
		}
	}

	public void noRecursivoOrden() throws Exception {

		NodoArbol<E> n = raiz;
		Pila<NodoArbol<E>> pila = new Pila<>();
		while (n != null) {
			while (n != null) {
				if (n.derecho != null) {
					pila.insertar(n.derecho);
				}
				pila.insertar(n);
				n = n.izquierdo;
			}
			n = pila.extraer();
			while (!pila.estaVacia() && n.derecho == null) {
				imprimir(n);
				n = pila.extraer();
			}
			imprimir(n);
			if (!pila.estaVacia()) {
				n = pila.extraer();
			} else {
				n = null;
			}
		}
	}

	public void noRecursivoPreorden() throws Exception {
		NodoArbol<E> n = raiz;
		Pila<NodoArbol<E>> pila = new Pila<>();
		if (n != null) {
			pila.insertar(n);
			while (!pila.estaVacia()) {
				n = (NodoArbol<E>) pila.extraer();
				imprimir(n);
				if (n.derecho != null) {
					pila.insertar(n.derecho);
				}
				if (n.izquierdo != null) {
					pila.insertar(n.izquierdo);
				}
			}
		}
	}

	public void noRecursivoPostOrden() throws Exception {
		NodoArbol<E> n = raiz, p = raiz;
		Pila<NodoArbol<E>> pila = new Pila<>();
		while (n != null) {
			for (; n.izquierdo != null; n = n.izquierdo) {
				pila.insertar(n);
			}
			while (n != null && (n.derecho == null || n.derecho == p)) {
				imprimir(n);
				p = n;
				if (pila.estaVacia()) {
					return;
				}
				n = (NodoArbol<E>) pila.extraer();
			}
			pila.insertar(n);
			n = n.derecho;
		}
	}

	public void eliminar(E dato) {
		NodoArbol<E> tmp;
		NodoArbol<E> nodo;
		NodoArbol<E> n = raiz;
		NodoArbol<E> previo = null;
		// mientras exista una raiz y el dato de la raiz no sea el del dato
		while (n != null && !n.equals(dato)) {
			previo = n;
			if (n.mayor(dato)) {
				n = n.derecho;
			} else {
				n = n.izquierdo;
			}
		}
		nodo = n;
		// si existe una raiz y el valor de la raiz es el que queremos eliminar
		if (n != null && n.equals(dato)) {
			// si no hay un nodo derecho
			if (nodo.derecho == null) {
				nodo = nodo.izquierdo;
			}
			// si no hay un nodo izquierdo
			else if (nodo.izquierdo == null) {
				nodo = nodo.derecho;
			} else {
				tmp = nodo.izquierdo; // 1
				while (tmp.derecho != null) { // 2
					tmp = tmp.derecho;
				}
				tmp.derecho = nodo.derecho; // 3
				nodo = nodo.izquierdo; // 4
			}
			if (n == raiz) {
				raiz = nodo;
			} else if (previo.izquierdo == n) {
				previo.izquierdo = nodo;
			} else {
				previo.derecho = nodo; // 5
			}
		} else if (raiz != null) {
			System.out.println("No se encuentra el dato " + dato);
		} else {
			System.out.println("Arbol vacio");
		}
	}
}