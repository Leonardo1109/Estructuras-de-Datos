package fes.aragon.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import fes.aragon.modelo.Estacionamiento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class InicioController implements Initializable {

	private Estacionamiento logica;

	@FXML
	private Button btnLimpiar;

	@FXML
	private Button btnSimular;

	@FXML
	private ScrollPane scrollPane;

	@FXML
	private TextFlow txtFlow;

	@FXML
	private TextField txtNumeroCarros;

	@FXML
	void simularEstacionamiento(ActionEvent event) {

		ArrayList<Text> textos;

		try {
			int numero = Integer.parseInt(txtNumeroCarros.getText());
			if (numero <= 0)
				throw new Exception();
			logica.simular(numero);
		} catch (Exception e) {
			Alert alerta;
			alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("Error");
			alerta.setHeaderText("Digite un numero");
			alerta.setContentText("Tiene que digitar un numero");
			alerta.showAndWait();
		}

		textos = logica.getTextos();
		textos.forEach(texto -> {
			texto.setFont(new Font("Lato", 20));
			txtFlow.getChildren().add(texto);
		});
	}

	@FXML
	void limpiar(ActionEvent event) {
		txtFlow.getChildren().clear();
		txtNumeroCarros.setText("");
		logica.getTextos().clear();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		logica = new Estacionamiento();
	}

}