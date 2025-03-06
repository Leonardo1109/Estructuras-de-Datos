package fes.aragon.controlador;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.DatagramPacket;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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

public class VistaBurbujaController implements Initializable {

	@FXML
	private BarChart<String, Number> area;

	@FXML
	private Button bntAleatorio;

	@FXML
	private Button btnBurbuja;

	@FXML
	private Button btnInserccion;

	@FXML
	private Button btnMezcla;

	@FXML
	private Button btnQuicksort;

	@FXML
	private Button btnSeleccion;

	@FXML
	private Button btnSalir;

	private ListaSimple<Datos> lista = new ListaSimple<>();
	final CategoryAxis xAxis = new CategoryAxis();
	final NumberAxis yAxis = new NumberAxis();
	private XYChart.Series<String, Number> series;
	final String[] color = { "-fx-bar-fill: green", "-fx-bar-fill: red", "-fx-bar-fill: blue", "-fx-bar-fill: yellow" };
	private ScheduledExecutorService scheduledExecutorService;

	@FXML
	void burbuja(ActionEvent event) {
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
		}, 0, 1, TimeUnit.SECONDS);
	}

	@FXML
	void inserccion(ActionEvent event) {
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorService.scheduleAtFixedRate(() -> {
			Platform.runLater(() -> {
				String tmpEstilo = "";
				String tmpEstiloDos = "";
				for (int i = 0, j; i < lista.getLongitud(); i++) {
					Datos tmp = lista.obtenerNodo(i);
					tmpEstilo = series.getData().get(i).getNode().getStyle();
					for (j = i; j > 0 && tmp.getEdad() < lista.obtenerNodo(j - 1).getEdad(); j--) {
						lista.asignar(lista.obtenerNodo(j - 1), j);
						tmpEstiloDos = series.getData().get(j - 1).getNode().getStyle();
						series.getData().get(j).getNode().setStyle(tmpEstiloDos);
						series.getData().get(j).setYValue(lista.obtenerNodo(j - 1).getEdad());
					}
					lista.asignar(tmp, j);
					series.getData().get(j).getNode().setStyle(tmpEstilo);
					series.getData().get(j).setYValue(lista.obtenerNodo(j).getEdad());
				}
			});
		}, 0, 1, TimeUnit.SECONDS);
	}

	@FXML
	void mezcla(ActionEvent event) {// reviar pues esta ejecutaqndo la lista cuando es vacia, error de logica
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorService.scheduleAtFixedRate(() -> {
			Platform.runLater(() -> {
				int mitad = lista.getLongitud() / 2;
				boolean bandera = false;
				for (int i = 0; i < mitad; i++) {
					for (int j = i + 1; j < mitad; j++) {
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
				for (int i = mitad; i < lista.getLongitud(); i++) {
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
					if (i == lista.getLongitud() - 1) {
						bandera = true;
					}
				}

				if (bandera == true) {
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
				}
			});
		}, 0, 1, TimeUnit.SECONDS);
	}

	@FXML
	void quicksort(ActionEvent event) {
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorService.scheduleAtFixedRate(() -> {
			Platform.runLater(() -> {
				int marca = -1;
				Datos pivote = lista.obtenerCola();
				for (int i = 0; i < lista.getLongitud() - 1; i++) {
					if (lista.obtenerNodo(i).getEdad() < pivote.getEdad()) {
						marca++;
						Datos tmp = lista.obtenerNodo(i);
						lista.asignar(lista.obtenerNodo(marca), i);
						lista.asignar(tmp, marca);
						String tmpEstilo = series.getData().get(i).getNode().getStyle();
						String tmpEstiloDos = series.getData().get(marca).getNode().getStyle();
						series.getData().get(marca).getNode().setStyle(tmpEstiloDos);
						series.getData().get(i).getNode().setStyle(tmpEstilo);
						series.getData().get(marca).setYValue(lista.obtenerNodo(marca).getEdad());
						series.getData().get(i).setYValue(lista.obtenerNodo(i).getEdad());
					}
				}
				marca++;
				Datos tmp = lista.obtenerCola();
				lista.asignar(lista.obtenerNodo(marca), lista.getLongitud() - 1);
				lista.asignar(tmp, marca);
				String tmpEstilo = series.getData().get(marca).getNode().getStyle();
				String tmpEstiloDos = series.getData().get(lista.getLongitud() - 1).getNode().getStyle();
				series.getData().get(marca).getNode().setStyle(tmpEstiloDos);
				series.getData().get(lista.getLongitud() - 1).getNode().setStyle(tmpEstilo);
				series.getData().get(marca).setYValue(lista.obtenerNodo(marca).getEdad());
				series.getData().get(lista.getLongitud() - 1)
						.setYValue(lista.obtenerNodo(lista.getLongitud() - 1).getEdad());
				for (int i = 0; i < marca - 1; i++) {
					for (int j = i + 1; j < marca; j++) {
						if (lista.obtenerNodo(i).getEdad() > lista.obtenerNodo(j).getEdad()) {
							Datos tmp2 = lista.obtenerNodo(i);
							lista.asignar(lista.obtenerNodo(j), i);
							lista.asignar(tmp2, j);
							String tmp2Estilo = series.getData().get(j).getNode().getStyle();
							String tmp2EstiloDos = series.getData().get(i).getNode().getStyle();
							series.getData().get(j).getNode().setStyle(tmp2EstiloDos);
							series.getData().get(i).getNode().setStyle(tmp2Estilo);
							series.getData().get(j).setYValue(lista.obtenerNodo(j).getEdad());
							series.getData().get(i).setYValue(lista.obtenerNodo(i).getEdad());
							break;
						}
					}
				}
				for (int i = marca; i < lista.getLongitud() - 1; i++) {
					for (int j = i + 1; j < lista.getLongitud(); j++) {
						if (lista.obtenerNodo(i).getEdad() > lista.obtenerNodo(j).getEdad()) {
							Datos tmp2 = lista.obtenerNodo(i);
							lista.asignar(lista.obtenerNodo(j), i);
							lista.asignar(tmp2, j);
							String tmp2Estilo = series.getData().get(j).getNode().getStyle();
							String tmp2EstiloDos = series.getData().get(i).getNode().getStyle();
							series.getData().get(j).getNode().setStyle(tmp2EstiloDos);
							series.getData().get(i).getNode().setStyle(tmp2Estilo);
							series.getData().get(j).setYValue(lista.obtenerNodo(j).getEdad());
							series.getData().get(i).setYValue(lista.obtenerNodo(i).getEdad());
							break;
						}
					}
				}
			});
		}, 0, 1, TimeUnit.SECONDS);

	}

	@FXML
	void seleccion(ActionEvent event) {
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorService.scheduleAtFixedRate(() -> {
			Platform.runLater(() -> {
				int varTemp = 0;
				for (int i = 0; i < lista.getLongitud() - 1; i++) {
					varTemp = i;
					for (int j = i + 1; j < lista.getLongitud(); j++) {
						if (lista.obtenerNodo(j).getEdad() < lista.obtenerNodo(varTemp).getEdad()) {
							varTemp = j;
						}
					}
					if (varTemp != i) {
						Datos tmp = lista.obtenerNodo(i);
						lista.asignar(lista.obtenerNodo(varTemp), i);
						lista.asignar(tmp, varTemp);
						String tmpEstilo = series.getData().get(varTemp).getNode().getStyle();
						String tmpEstiloDos = series.getData().get(i).getNode().getStyle();
						series.getData().get(varTemp).getNode().setStyle(tmpEstiloDos);
						series.getData().get(i).getNode().setStyle(tmpEstilo);
						series.getData().get(varTemp).setYValue(lista.obtenerNodo(varTemp).getEdad());
						series.getData().get(i).setYValue(lista.obtenerNodo(i).getEdad());
						break;
					}
				}
			});
		}, 0, 1, TimeUnit.SECONDS);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
//		numerosAleatorios();
		try {
			edad();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		xAxis.setLabel("Time/s");
		xAxis.setAnimated(false);
		yAxis.setLabel("Value");
		yAxis.setAnimated(false);
		// idArea = new LineChart<>(xAxis, yAxis);
		area.setTitle("Métodos Ordenamiento");
		area.setAnimated(false); // disable animations
		series = new XYChart.Series<>();
		area.getData().add(series);
		for (int i = 0; i < lista.getLongitud(); i++) {
			final XYChart.Data<String, Number> dato = new XYChart.Data<>(String.valueOf(i),
					lista.obtenerNodo(i).getEdad());
			series.getData().add(dato);
			dato.getNode().setStyle(color[new Random().nextInt(4)]);
		}
	}

	@FXML
	void eventoSalir(ActionEvent event) {
		if (scheduledExecutorService != null) {
			scheduledExecutorService.shutdown();
		}
		Platform.exit();

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

}
