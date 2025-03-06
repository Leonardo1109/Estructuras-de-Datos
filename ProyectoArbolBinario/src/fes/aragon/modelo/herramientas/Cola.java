package fes.aragon.modelo.herramientas;

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
			tmp = cola.getCabeza().getDato();
			cola.eliminarCabeza();
		} else
			throw new Exception("cola vacia");
		return tmp;
	}

	public E mostrarTop() throws Exception {
		E tmp = null;
		if (!estaVacia()) {
			tmp = cola.getCabeza().getDato();
		} else
			throw new Exception("cola vacia");
		return tmp;
	}
}
