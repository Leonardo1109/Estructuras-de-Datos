package fes.aragon.metodos.ordenamiento;
import java.util.Random;
import fes.aragon.inicio.Datos;
import fes.aragon.utilerias.dinamicas.listasimple.ListaSimple;

public class MetodosOrdenamiento {

	public MetodosOrdenamiento() {
			super();
			// TODO Auto-generated constructor stub
	}

	public void aleatorio(int numAleatorios, ListaSimple<Integer> datos) {
		Random rd = new Random();
		for (int i = 0; i < numAleatorios; i++) {
			datos.agregarEnCola(rd.nextInt(200));
		}
	}

	public ListaSimple<Datos> burbujaEdad (ListaSimple<Datos> datos) {
		for (int i = 0; i < datos.getLongitud() - 1; i++) {
			for (int j = i + 1; j < datos.getLongitud(); j++) {
				if (datos.obtenerNodo(i).getEdad() >= datos.obtenerNodo(j).getEdad()) {
					Datos tmp = datos.obtenerNodo(i);
					datos.asignar(datos.obtenerNodo(j), i);
					datos.asignar(tmp, j);
				}
			}
		}
		return datos;
	}
	
	public ListaSimple<Datos> burbujaAltura (ListaSimple<Datos> datos) {
		for (int i = 0; i < datos.getLongitud() - 1; i++) {
			for (int j = i + 1; j < datos.getLongitud(); j++) {
				if (datos.obtenerNodo(i).getAltura() >= datos.obtenerNodo(j).getAltura()) {
					Datos tmp = datos.obtenerNodo(i);
					datos.asignar(datos.obtenerNodo(j), i);
					datos.asignar(tmp, j);
				}
			}
		}
		return datos;
	}
	
	public ListaSimple<Datos> burbujaPeso (ListaSimple<Datos> datos) {
		for (int i = 0; i < datos.getLongitud() - 1; i++) {
			for (int j = i + 1; j < datos.getLongitud(); j++) {
				if (datos.obtenerNodo(i).getPeso() >= datos.obtenerNodo(j).getPeso()) {
					Datos tmp = datos.obtenerNodo(i);
					datos.asignar(datos.obtenerNodo(j), i);
					datos.asignar(tmp, j);
				}
			}
		}
		return datos;
	}

	public ListaSimple<Datos> seleccionEdad(ListaSimple<Datos> datos) {
		int varTemp = 0;
		for (int i = 0; i < datos.getLongitud() - 1; i++) {
			varTemp = i;
			for (int j = i + 1; j < datos.getLongitud(); j++) {
				if (datos.obtenerNodo(j).getEdad() <= datos.obtenerNodo(varTemp).getEdad()) {
					varTemp = j;
				}
			}
			if (varTemp != i) {
				Datos tmp = datos.obtenerNodo(i);
				datos.asignar(datos.obtenerNodo(varTemp), i);
				datos.asignar(tmp, varTemp);
			}
		}
		return datos;
	}
	
	public ListaSimple<Datos> seleccionAltura(ListaSimple<Datos> datos) {
		int varTemp = 0;
		for (int i = 0; i < datos.getLongitud() - 1; i++) {
			varTemp = i;
			for (int j = i + 1; j < datos.getLongitud(); j++) {
				if (datos.obtenerNodo(j).getAltura() <= datos.obtenerNodo(varTemp).getAltura()) {
					varTemp = j;
				}
			}
			if (varTemp != i) {
				Datos tmp = datos.obtenerNodo(i);
				datos.asignar(datos.obtenerNodo(varTemp), i);
				datos.asignar(tmp, varTemp);
			}
		}
		return datos;
	}
	
	public ListaSimple<Datos> seleccionPeso(ListaSimple<Datos> datos) {
		int varTemp = 0;
		for (int i = 0; i < datos.getLongitud() - 1; i++) {
			varTemp = i;
			for (int j = i + 1; j < datos.getLongitud(); j++) {
				if (datos.obtenerNodo(j).getPeso() <= datos.obtenerNodo(varTemp).getPeso()) {
					varTemp = j;
				}
			}
			if (varTemp != i) {
				Datos tmp = datos.obtenerNodo(i);
				datos.asignar(datos.obtenerNodo(varTemp), i);
				datos.asignar(tmp, varTemp);
			}
		}
		return datos;
	}

	public ListaSimple<Datos> insercionEdad(ListaSimple<Datos> datos) {
		for (int i = 1, j; i < datos.getLongitud(); i++) {
			Datos tmp = datos.obtenerNodo(i);
			for (j = i; j > 0 && tmp.getEdad() < datos.obtenerNodo(j - 1).getEdad(); j--) {
				datos.asignar(datos.obtenerNodo(j - 1), j);
			}
			datos.asignar(tmp, j);
		}
		return datos;
	}
	
	public ListaSimple<Datos> insercionAltura(ListaSimple<Datos> datos) {
		for (int i = 1, j; i < datos.getLongitud(); i++) {
			Datos tmp = datos.obtenerNodo(i);
			for (j = i; j > 0 && tmp.getAltura() < datos.obtenerNodo(j - 1).getAltura(); j--) {
				datos.asignar(datos.obtenerNodo(j - 1), j);
			}
			datos.asignar(tmp, j);
		}
		return datos;
	}
	
	public ListaSimple<Datos> insercionPeso(ListaSimple<Datos> datos) {
		for (int i = 1, j; i < datos.getLongitud(); i++) {
			Datos tmp = datos.obtenerNodo(i);
			for (j = i; j > 0 && tmp.getPeso() < datos.obtenerNodo(j - 1).getPeso(); j--) {
				datos.asignar(datos.obtenerNodo(j - 1), j);
			}
			datos.asignar(tmp, j);
		}
		return datos;
	}
	
	public ListaSimple<Datos> mezclaEdad(ListaSimple<Datos> datos) {
		MetodosOrdenamiento inicio = new MetodosOrdenamiento();
		int mitad = datos.getLongitud() / 2;
		ListaSimple<Datos> izquierdo = new ListaSimple<Datos>();
		ListaSimple<Datos> derecho = new ListaSimple<Datos>();

		for (int i = 0; i < mitad; i++) {
			izquierdo.agregarEnCola(datos.obtenerCabeza());
			datos.eliminarCabeza();
		}

		while (!datos.esVacia()) {
			derecho.agregarEnCola(datos.obtenerCabeza());
			datos.eliminarCabeza();
		}
		inicio.insercionEdad(izquierdo);
		inicio.insercionEdad(derecho);
		while (!derecho.esVacia() && !izquierdo.esVacia()) {
			if (derecho.obtenerCabeza().getEdad() <= izquierdo.obtenerCabeza().getEdad()) {
				datos.agregarEnCola(derecho.obtenerCabeza());
				derecho.eliminarCabeza();
			} else {
				datos.agregarEnCola(izquierdo.obtenerCabeza());
				izquierdo.eliminarCabeza();
			}
		}
		if (!derecho.esVacia()) {
			while (!derecho.esVacia()) {
				datos.agregarEnCola(derecho.obtenerCabeza());
				derecho.eliminarCabeza();
			}
		} else if (!izquierdo.esVacia()) {
			while (!izquierdo.esVacia()) {
				datos.agregarEnCola(izquierdo.obtenerCabeza());
				izquierdo.eliminarCabeza();
			}
		}
		return datos;
		
	}
	
	public ListaSimple<Datos> mezclaAltura(ListaSimple<Datos> datos) {
		MetodosOrdenamiento inicio = new MetodosOrdenamiento();
		int mitad = datos.getLongitud() / 2;
		ListaSimple<Datos> izquierdo = new ListaSimple<Datos>();
		ListaSimple<Datos> derecho = new ListaSimple<Datos>();

		for (int i = 0; i < mitad; i++) {
			izquierdo.agregarEnCola(datos.obtenerCabeza());
			datos.eliminarCabeza();
		}

		while (!datos.esVacia()) {
			derecho.agregarEnCola(datos.obtenerCabeza());
			datos.eliminarCabeza();
		}
		inicio.insercionAltura(izquierdo);
		inicio.insercionAltura(derecho);
		while (!derecho.esVacia() && !izquierdo.esVacia()) {
			if (derecho.obtenerCabeza().getAltura() <= izquierdo.obtenerCabeza().getAltura()) {
				datos.agregarEnCola(derecho.obtenerCabeza());
				derecho.eliminarCabeza();
			} else {
				datos.agregarEnCola(izquierdo.obtenerCabeza());
				izquierdo.eliminarCabeza();
			}
		}
		if (!derecho.esVacia()) {
			while (!derecho.esVacia()) {
				datos.agregarEnCola(derecho.obtenerCabeza());
				derecho.eliminarCabeza();
			}
		} else if (!izquierdo.esVacia()) {
			while (!izquierdo.esVacia()) {
				datos.agregarEnCola(izquierdo.obtenerCabeza());
				izquierdo.eliminarCabeza();
			}
		}
		return datos;
	}
	
	public ListaSimple<Datos> mezclaPeso(ListaSimple<Datos> datos) {
		MetodosOrdenamiento inicio = new MetodosOrdenamiento();
		int mitad = datos.getLongitud() / 2;
		ListaSimple<Datos> izquierdo = new ListaSimple<Datos>();
		ListaSimple<Datos> derecho = new ListaSimple<Datos>();

		for (int i = 0; i < mitad; i++) {
			izquierdo.agregarEnCola(datos.obtenerCabeza());
			datos.eliminarCabeza();
		}

		while (!datos.esVacia()) {
			derecho.agregarEnCola(datos.obtenerCabeza());
			datos.eliminarCabeza();
		}
		inicio.insercionPeso(izquierdo);
		inicio.insercionPeso(derecho);
		while (!derecho.esVacia() && !izquierdo.esVacia()) {
			if (derecho.obtenerCabeza().getPeso() <= izquierdo.obtenerCabeza().getPeso()) {
				datos.agregarEnCola(derecho.obtenerCabeza());
				derecho.eliminarCabeza();
			} else {
				datos.agregarEnCola(izquierdo.obtenerCabeza());
				izquierdo.eliminarCabeza();
			}
		}
		if (!derecho.esVacia()) {
			while (!derecho.esVacia()) {
				datos.agregarEnCola(derecho.obtenerCabeza());
				derecho.eliminarCabeza();
			}
		} else if (!izquierdo.esVacia()) {
			while (!izquierdo.esVacia()) {
				datos.agregarEnCola(izquierdo.obtenerCabeza());
				izquierdo.eliminarCabeza();
			}
		}
		return datos;
	}
}

