package fes.aragon.controlador;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.DatagramPacket;
import java.net.URL;
import java.util.Iterator;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import fes.aragon.inicio.Datos;
import fes.aragon.metodos.ordenamiento.MetodosOrdenamiento;
import fes.aragon.utilerias.dinamicas.listasimple.ListaSimple;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class BusquedaController implements Initializable {

	@FXML
	private BarChart<String, Number> area;

	@FXML
	private Button btnBinaria;

	@FXML
	private Button btnSalir;

	@FXML
	private Button btnSecuencial;

	@FXML
	private TextField lblNumeroBuscado;

	private ListaSimple<Datos> lista = new ListaSimple<>();
	final CategoryAxis xAxis = new CategoryAxis();
	final NumberAxis yAxis = new NumberAxis();
	private XYChart.Series<String, Number> series;
	final String[] color = { "-fx-bar-fill: green", "-fx-bar-fill: red", "-fx-bar-fill: blue", "-fx-bar-fill: yellow" };
	private ScheduledExecutorService scheduledExecutorService;

	@FXML
	void binaria(ActionEvent event) {
		int primero = 0;
		int ultimo = lista.getLongitud() - 1;
		int medio = ultimo / 2;
		int contador = 0;
		if (lblNumeroBuscado.getText().equals("")) {
			ventanaEmergente(1);
		}
		while (lista.obtenerNodo(medio).getEdad() != Integer.parseInt(lblNumeroBuscado.getText()) && contador < 20) {
			if (lista.obtenerNodo(medio).getEdad() > Integer.parseInt(lblNumeroBuscado.getText())) {
				int tmp = medio;
				medio = primero + ((medio - primero) / 2);
				ultimo = tmp;
				contador++;
			} else {
				int tmp = medio;
				medio = medio + ((ultimo - medio) / 2);
				primero = tmp;
				contador++;
			}

		}
		if (contador >= 20) {
			ventanaEmergente(2);
		}else if(lista.obtenerNodo(medio).getEdad() == Integer.parseInt(lblNumeroBuscado.getText())) {
			series.getData().get(medio).getNode().setStyle("-fx-bar-fill: black");
		}
	}

	@FXML
	void eventoSalir(ActionEvent event) {
		if (scheduledExecutorService != null) {
			scheduledExecutorService.shutdown();
		}
		Platform.exit();
	}

	@FXML // advertencia que no ha puesto nada
	void secuencial(ActionEvent event) {
		if (lblNumeroBuscado.getText().equals("")) {
			ventanaEmergente(1);
		}
		for (int i = 0; i < lista.getLongitud(); i++) {
			if (lista.obtenerNodo(i).getEdad() == Integer.parseInt(lblNumeroBuscado.getText())) {
				series.getData().get(i).getNode().setStyle("-fx-bar-fill: black");
				break;
			} else if (i == lista.getLongitud() - 1) {
				ventanaEmergente(2);
			}
		}
	}

	public void ordenar() {
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorService.scheduleAtFixedRate(() -> {
			Platform.runLater(() -> {
				for (int i = 0; i < lista.getLongitud() - 1; i++) {
					for (int j = i + 1; j < lista.getLongitud(); j++) {
						if (lista.obtenerNodo(i).getEdad() > lista.obtenerNodo(j).getEdad()) {
							Datos tmp = lista.obtenerNodo(i);
							lista.asignar(lista.obtenerNodo(j), i);
							lista.asignar(tmp, j);
							String tmpEstilo = series.getData().get(j).getNode().getStyle();
							String tmpEstiloDos = series.getData().get(i).getNode().getStyle();
							series.getData().get(j).getNode().setStyle(tmpEstiloDos);
							series.getData().get(i).getNode().setStyle(tmpEstilo);
							series.getData().get(j).setYValue(lista.obtenerNodo(j).getEdad());
							series.getData().get(i).setYValue(lista.obtenerNodo(i).getEdad());
							break;
						}
					}
				}
			});
		}, 0, 30, TimeUnit.MILLISECONDS);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			edad();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		xAxis.setLabel("Time/s");
		xAxis.setAnimated(false);
		yAxis.setLabel("Value");
		yAxis.setAnimated(false);
		// idArea = new LineChart<>(xAxis, yAxis);
		area.setTitle("Métodos Búsqueda");
		area.setAnimated(false); // disable animations
		series = new XYChart.Series<>();
		area.getData().add(series);
		for (int i = 0; i < lista.getLongitud(); i++) {
			final XYChart.Data<String, Number> dato = new XYChart.Data<>(String.valueOf(i),
					lista.obtenerNodo(i).getEdad());
			series.getData().add(dato);
			dato.getNode().setStyle(color[new Random().nextInt(4)]);
		}
		ordenar();
	}

	private void edad() throws FileNotFoundException {
		lista = new ListaSimple<>();
		File doc = new File(System.getProperty("user.dir") + "/src/Prueba");
		Scanner obj = new Scanner(doc);
		while (obj.hasNextLine()) {
			String[] letras = obj.nextLine().split(";");
			for (int i = 0; i < letras.length; i = i + 4) {
				Datos persona = new Datos();
				persona.setNombre(letras[i]);
				persona.setEdad(Integer.parseInt(letras[i + 1]));
				persona.setAltura(Integer.parseInt(letras[i + 2]));
				persona.setPeso(Integer.parseInt(letras[i + 3]));
				lista.agregarEnCola(persona);
			}
		}
	}

	public void ventanaEmergente(int caso) {
		JFrame ventanaEmergente = new JFrame();
		if (caso == 1) {
			JOptionPane.showMessageDialog(ventanaEmergente, "No se ha ingresado ningun valor");
		} else if (caso == 2) {
			JOptionPane.showMessageDialog(ventanaEmergente, "No se ha encontrado el valor solicitado");
		}
	}
}
