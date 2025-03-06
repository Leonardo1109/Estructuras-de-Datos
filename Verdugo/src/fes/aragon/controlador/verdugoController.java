package fes.aragon.controlador;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import fes.aragon.recursos.Prisionero;
import fes.aragon.utilerias.dinamicas.cola.Cola;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class verdugoController {

    @FXML
    private Button btnLimpiar;

    @FXML
    private Button btnResultado;

    @FXML
    private Label lblResultado;

    @FXML
    private TextField txtPatron;

    @FXML
    private TextField txtPosicion;

    @FXML
    private TextField txtTotalReos;

    @FXML
    void Limpiar(ActionEvent event) {
    	lblResultado.setText("");
    	txtPatron.setText("");
    	txtPosicion.setText("");
    	txtTotalReos.setText("");
    }

    @FXML
    void Operar(ActionEvent event) throws Exception {
    	Cola<Prisionero> calabozo = new Cola<Prisionero>();
		int numeroAmigo = Integer.parseInt(txtPosicion.getText());
		int numerosPrisioneros = Integer.parseInt(txtTotalReos.getText());
		int patron = Integer.parseInt(txtPatron.getText());

		for (int i = 1; i < numerosPrisioneros + 1; i++) {
			Prisionero prisionero = new Prisionero(i, false);
			calabozo.insertar(prisionero);
		}
		int tmp = 1;
		int prisionerosVivos = numerosPrisioneros;
		while (!calabozo.estaVacia() && prisionerosVivos > 1) {		
			if (tmp == patron) {
				calabozo.extraer();
				tmp = 0;
				prisionerosVivos--;
			} else {
				Prisionero prisioneroTmp = calabozo.extraer();
				calabozo.insertar(prisioneroTmp);
			}
			tmp++;
		}

		int sobreviviente = 0;
		while (!calabozo.estaVacia()) {
			sobreviviente = calabozo.extraer().getNumero();
		}

		int contador = 1;
		if (sobreviviente == numeroAmigo) {
			resultadoEnPantalla(numeroAmigo, 1, patron);
		} else if (sobreviviente < numeroAmigo) {
			contador = 1 + (numeroAmigo - sobreviviente);

			resultadoEnPantalla(numeroAmigo, contador, patron);
		} else if (sobreviviente > numeroAmigo) {
			contador = numerosPrisioneros - (sobreviviente - numeroAmigo) + 1;
			resultadoEnPantalla(numeroAmigo, contador, patron);
		}
    }
    
    
    public void ventanaEmergente() {
		JFrame ventanaEmergente = new JFrame();
			JOptionPane.showMessageDialog(ventanaEmergente, "No se ha ingresado algun valor");
	}
    
    public void resultadoEnPantalla(int numeroAmigo, int contador, int patron) {
    	lblResultado.setText("El verdugo para salvar a su amigo\nque esta en el numero " + numeroAmigo
					+ "\ndebe empezar a matar contando desde\nel prisionero numero: " + contador + " matando de\n"
					+ patron + " en " + patron);
    }

}
