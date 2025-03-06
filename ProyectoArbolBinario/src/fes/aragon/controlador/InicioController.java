package fes.aragon.controlador;

import fes.aragon.modelo.Logica;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class InicioController {

	private Logica logica = new Logica();

	@FXML
	private Button btnExpresion, btnLimpiar;

	@FXML
	private Label lblExpresion, lblResultado, lblTitulo;

	@FXML
	private TextField txtExpresion, txtResultado;

	@FXML
	void evaluarExpresion(ActionEvent event) {
		try {
			double res = logica.evaluar(txtExpresion.getText());
			txtResultado.setText(String.valueOf(res));
			lblResultado.setVisible(true);
			txtResultado.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void limpiar(ActionEvent event) {
		txtExpresion.setText("");
		lblResultado.setVisible(false);
		txtResultado.setText("");
		txtResultado.setVisible(false);
	}

}
