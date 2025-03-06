//package fes.aragon.utilerias.estaticas;
//
//import fes.aragon.excep.IndiceFueraDeRango;
//
///**
// * Clase que tiene funciones para ocupar arreglos de tipo Integer
// * 
// * Consideramos que siempre los indicies inician en 0 y el usuario esta
// * conciente de ello
// * 
// * @author mash
// *
// */
//public class Arreglos<E> {
//	private int indice = 0;
//	private final Object[] l;
//
//	public Arreglos(int numeroElementos) {
//		this.l = new Object[numeroElementos];
//	}
//
//	/**
//	 * M�todo que inserta un valor de tipo Integer consecutivo
//	 *
//	 * @param x es el par�metro que se recibe para agregar a la lista
//	 * @throws IndiceFueraDeRango exepci�n que pasa cuando nos salimos fuera del
//	 *                            �ndice
//	 */
//	public void insertar(E x) throws IndiceFueraDeRango {
//		if (indice < l.length) {
//			l[indice] = x;
//			indice++;
//		} else {
//			throw new IndiceFueraDeRango("Indice fuera de rango");
//		}
//	}
//
//	/**
//	 * M�todo que localiza un valor en la lista, retornan el indice
//	 *
//	 * @param x valor Integer que se busca en la lista
//	 * @return se retorna -1 si no esta el valor de la lista, en caso contrario se
//	 *         retorna el indice
//	 */
//	public Integer localiza(E x) {
//
//		for (int i = 0; i < l.length; i++) {
//			if (l[i].equals(x)) {
//				return i;
//			}
//		}
//		return -1; // -1?
//	}
//
//	/**
//	 * M�todo que recupera un elemento en el indice indicado
//	 *
//	 * @param p entero que indica el indice del elemento a devolver
//	 * @return E que se retorna, tomando la posici�n siguiente que se da como
//	 *         parametro
//	 * @throws IndiceFueraDeRango excepci�n que se arroja cuando el indice p esta
//	 *                            fuera de los rangos del arreglo
//	 */
//	public E recupera(int p) throws IndiceFueraDeRango {
//		if (p > l.length || p < 0) {
//			throw new IndiceFueraDeRango("Indice fuera de rango");
//		} else {
//			@SuppressWarnings("unchecked")
//			final E e = (E) l[p];
//			return e;
//		}
//	}
//
//	/**
//	 * M�todo que alimina un elemento en el indice indicado
//	 *
//	 * @param p entero que indica el indice del elemento a eliminar
//	 * @throws IndiceFueraDeRango excepci�n que se arroja cuando el indice p esta
//	 *                            fuera de los rangos del arreglo
//	 */
//	public void suprime(int p) throws IndiceFueraDeRango {
//		if (p > l.length || p < 0) {
//			throw new IndiceFueraDeRango("Indice fuera de rango");
//		} else {
//			l[p] = null;
//		}
//	}
//
//	/**
//	 * M�todo que da el elemento siguiente de la posici�n que nos indica
//	 *
//	 * @param p entero que indica el indice del elemento a devolver
//	 * @return E que se retorna, tomando la posici�n siguiente que se da como
//	 *         parametro
//	 * @throws IndiceFueraDeRango excepci�n que se arroja cuando el indice p esta
//	 *                            fuera de los rangos del arreglo
//	 */
//	public E siguiente(int p) throws IndiceFueraDeRango {
//		if (p >= l.length || p < 0) {
//			throw new IndiceFueraDeRango("Indice fuera de rango");
//		}
//		@SuppressWarnings("unchecked")
//		final E e = (E) l[p + 1];
//		return e;
//	}
//
//	/**
//	 * M�todo que da el elemento anterior de la posici�n que nos indica
//	 *
//	 * 
//	 * @param p entero que indica el indice del elemento a devolver
//	 * @return Integer que se retorna Integer tomando la posici�n anterior que se da
//	 *         como parametro
//	 * @throws IndiceFueraDeRango excepci�n que se arroja cuando el indice p esta
//	 *                            fuera de los rangos del arreglo
//	 */
//	public E anterior(int p) throws IndiceFueraDeRango {
//		if (p >= l.length || p < 0) {
//			throw new IndiceFueraDeRango("Indice fuera de rango");
//		}
//		@SuppressWarnings("unchecked")
//		final E e = (E) l[p - 1];
//		return e;
//	}
//
//	/**
//	 * M�otodo que limpia el arreglo de Enteros
//	 *
//	 */
//	public void limpiar() {
//		for (int i = 0; i < l.length; i++) {
//			l[i] = null;
//		}
//	}
//
//	/**
//	 * M�todo que regresa el primer elemento del arreglo, si no existe regresa un
//	 * null
//	 * 
//	 * @return retorna E o null del primer elemento del arreglo
//	 */
//	public E primero() {
//		@SuppressWarnings("unchecked")
//		final E e = (E) l[0];
//		return e;
//	}
//
//	/**
//	 * M�todo que devuelve la longitud del arreglo
//	 * 
//	 * @return un entero que es la longitud del arreglo
//	 */
//	public Integer longitud() {
//		return l.length;
//	}
//
//	/**
//	 * M�todo que imprime todos los valores del arreglo
//	 */
//	public void imprime() {
//		for (int i = 0; i < l.length; i++) {
//			System.out.print(l[i] + " ");
//		}
//		System.out.println();
//	}
//
//	/**
//	 * M�todo que asignara un valor en la posici�n indicada
//	 * 
//	 * @param p entero que indica la posici�n donde se inserta el valor en el
//	 *          arreglo
//	 * @param x valor que se insertara en la posicion que se indica en p
//	 * @throws IndiceFueraDeRango exepci�n que sucede cuando no estamos en el rango
//	 *                            del arreglo
//	 */
//	public void asignar(int p, E x) throws IndiceFueraDeRango {
//		if (p > l.length || p < 0) {
//			throw new IndiceFueraDeRango("Indice fuera de rango");
//		} else {
//			l[p] = x;
//		}
//	}
//
//	/**
//	 * Metodo que devuelve el promedio
//	 * 
//	 * @return el promedio. Suponiendo que Se quieran trabajar con numeros este
//	 *         metodo regresar� un numero
//	 * @throws IndiceFueraDeRango
//	 */
//	public double promedio() throws IndiceFueraDeRango {
//		if (l.length < 1) {
//			throw new IndiceFueraDeRango("Indice fuera de rango");
//		} else {
//			double promedio = 0;
//			int numeroElementos = 0;
//			for (int i = 0; i < l.length; i++) {
//				promedio += (int) l[i];
//				numeroElementos = i + 1;
//			}
//			promedio = promedio / numeroElementos;
//			return promedio;
//		}
//	}
//
//	public Integer maximo() {
//		int aux = (int) l[0];
//		for (int i = 0; i < l.length; i++) {
//			if ((int) l[i] > aux) {
//				aux = (int) l[i];
//			}
//		}
//		return aux;
//
//	}
//
//	public Integer minimo() {
//		int aux = (int) l[0];
//		for (int i = 0; i < l.length; i++) {
//			if ((int) l[i] < aux) {
//				aux = (int) l[i];
//			}
//		}
//		return aux;
//
//	}
//
//	public void rango(int x, int y) throws IndiceFueraDeRango {
//		if (x > y) {
//			int aux;
//			aux = y;
//			y = x;
//			x = aux;
//		}
//		if (x > l.length || x <= 0 || y > l.length || y < 0) {
//			throw new IndiceFueraDeRango("Indice fuera de rango");
//		} else {
//			for (int i = x; i <= y; i++) {
//				System.out.print(l[i] + " ");
//			}
//			System.out.println();
//		}
//	}
//
//	public Integer segundoMaximo(int maxUno) {
//		Integer aux = (Integer) l[0];
//		for (int i = 0; i < l.length; i++) {
//			if ((Integer) l[i] > aux && (int) l[i] < maxUno) {
//				aux = (Integer) l[i];
//			}
//		}
//		return aux;
//	}
//
//}
