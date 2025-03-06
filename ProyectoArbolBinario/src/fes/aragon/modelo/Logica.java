package fes.aragon.modelo;

import java.util.Arrays;
import java.util.StringTokenizer;

import fes.aragon.modelo.herramientas.NodoArbol;
import fes.aragon.modelo.herramientas.Pila;

public class Logica {

	private Pila<NodoArbol<String>> expresiones;
	private Pila<NodoArbol<String>> operadores;
	private NodoArbol<String> raiz;

	public Logica() {
		expresiones = new Pila<>();
		operadores = new Pila<>();
	}

	public double evaluar(String expresion) throws Exception {
		StringTokenizer tokens = new StringTokenizer(expresion, "\t)+-*/^(", true);
		raiz = crearArbol(tokens);
		return evaluarExpresion(raiz);
	}

	private NodoArbol<String> crearArbol(StringTokenizer tokenizer) throws Exception {

		NodoArbol<String> nodo2, nodo1, nodoAux;

		while (tokenizer.hasMoreTokens()) {

			String caracterActual = tokenizer.nextToken();
			NodoArbol<String> nodo = new NodoArbol<String>(caracterActual);
			if (!esOperador(caracterActual)) {
				expresiones.insertar(nodo);
			} else {
				switch (caracterActual) {
				case "(":
					operadores.insertar(nodo);
					break;
				case ")":
					while (!operadores.estaVacia() && !operadores.elementoSuperior().getDato().equals("(")) {

						nodo2 = expresiones.extraer();
						nodo1 = expresiones.extraer();
						nodoAux = operadores.extraer();
						nodoAux = acomodarNodo(nodo2, nodo1, nodoAux);
						expresiones.insertar(nodoAux);
					}
					operadores.extraer();
					break;

				default:
					while (!operadores.estaVacia()
							&& precedencia(caracterActual, operadores.elementoSuperior().getDato().toString())) {
						nodo2 = expresiones.extraer();
						nodo1 = expresiones.extraer();
						nodoAux = operadores.extraer();
						nodoAux = acomodarNodo(nodo2, nodo1, nodoAux);
						expresiones.insertar(nodoAux);
					}
					operadores.insertar(nodo);
					break;
				}
			}
		}

		while (!operadores.estaVacia()) {
			nodo2 = expresiones.extraer();
			nodo1 = expresiones.extraer();
			nodoAux = operadores.extraer();
			nodoAux = acomodarNodo(nodo2, nodo1, nodoAux);
			expresiones.insertar(nodoAux);
		}
		return expresiones.extraer();
	}

	private NodoArbol<String> acomodarNodo(NodoArbol<String> dato2, NodoArbol<String> dato1,
			NodoArbol<String> operador) {
		operador.setIzquierdo(dato1);
		operador.setDerecho(dato2);
		return operador;
	}

	private double evaluarExpresion(NodoArbol<String> nodo) {

		double operacion = 0;
		if (!esOperador(nodo.getDato())) {
			return Double.parseDouble(nodo.getDato().toString());
		} else {
			switch (nodo.getDato()) {
			case "^":
				operacion += Math.pow(evaluarExpresion(nodo.getIzquierdo()), evaluarExpresion(nodo.getDerecho()));
				break;
			case "/":
				operacion += evaluarExpresion(nodo.getIzquierdo()) / evaluarExpresion(nodo.getDerecho());
				break;
			case "*":
				operacion += evaluarExpresion(nodo.getIzquierdo()) * evaluarExpresion(nodo.getDerecho());
				break;
			case "-":
				operacion += evaluarExpresion(nodo.getIzquierdo()) - evaluarExpresion(nodo.getDerecho());
				break;
			case "+":
				operacion += evaluarExpresion(nodo.getIzquierdo()) + evaluarExpresion(nodo.getDerecho());
				break;
			}
		}
		return operacion;

	}

	private boolean esOperador(String actual) {
		String[] operadores = { "(", ")", "^", "*", "/", "+", "-" };
		return Arrays.stream(operadores).anyMatch(op -> op.equals(actual));
	}

	private boolean precedencia(String elementoSuperior, String simbolo) {

		int precedencia1 = obtenerPrec(elementoSuperior);
		int precedencia2 = obtenerPrec(simbolo);

		if (precedencia1 <= precedencia2)
			return true;
		return false;

	}

	private int obtenerPrec(String operador) {
		int prec = -1;

		switch (operador) {
		case "(":
		case ")":
			prec = 0;
			break;
		case "^":
			prec = 10;
			break;
		case "*":
		case "/":
			prec = 5;
			break;
		case "+":
		case "-":
			prec = 1;
			break;
		}
		return prec;
	}

}
