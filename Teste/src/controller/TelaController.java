package controller;

import java.net.URL;

import java.util.ResourceBundle;


import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import model.RelacoesEntreMoedas;

public class TelaController implements Initializable{

    @FXML
    private Button botaoCalcular;

    @FXML
    private ImageView iconeApp;

    @FXML
    private ImageView imgClose;

    @FXML
    private ChoiceBox<String> moeda1;

    @FXML
    private ChoiceBox<String> moeda2;
    
    private String[] moedas = {"Real", "Dolar Americano", "Euro"};

    @FXML
    private TextField valorEntrada;

    @FXML
    private Label valorsaida;
    
    private Alert alertaErro = new Alert(AlertType.ERROR);


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		imgClose.setOnMouseClicked(event -> {
            System.exit(0);
        });
		
		moeda1.setValue("Real");
		moeda1.getItems().addAll(moedas);
		moeda1.setOnAction(this::getMoeda1);
		
		moeda2.setValue("Dolar Americano");
		moeda2.getItems().addAll(moedas);
		moeda2.setOnAction(this::getMoeda2);
	}
	
	@FXML
    void mostrarResultado(ActionEvent event) {
		
		RelacoesEntreMoedas rem = new RelacoesEntreMoedas();
		
		String valorInput = rem.valorValido(valorEntrada.getText());
		
		if(valorInput != null) {
			
			String m1 = moeda1.getValue();
			String m2 = moeda2.getValue();
			
			String config = rem.configurarConversao(m1, m2);
			
			if (config == null) {
				return;
			}
			
			System.out.println("Config: " + config);
			
			String valorDeCambio = rem.valorDeConversao(config);
			
			System.out.println("valorDeCambio: " + valorDeCambio);
			
			String novoValor = rem.calcularNovoValor(valorInput, valorDeCambio);
			
			System.out.println("novoValor: " + novoValor);
			
			valorsaida.setText(novoValor);
		}
    }
	
	public Double realizarConversao(String tipoMoeda1, String tipoMoeda2, double valor) {
		
		// Se as moedas forem iguais:
		if (tipoMoeda1.equals(tipoMoeda2)) {
			alertaErro.setContentText("Escolha moedas diferentes!");
			alertaErro.showAndWait();
			return null;
		
		// Se forem diferentes:
		} else {
			
			
			if (tipoMoeda1.equals("Real") && tipoMoeda2.equals("Dolar")) {
				System.out.println("real x dolar");
				
				valor *= 0.20;
				
				
			}
			
			
		}
		
		
		return null;
	}
	
	
	public void getMoeda1(ActionEvent event) {
		moeda1.getValue();
	}
	
	public void getMoeda2(ActionEvent event) {
		moeda2.getValue();
	}

}