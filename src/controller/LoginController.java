package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LoginController {
	
	@FXML
	private TextField usuario;
	
	@FXML
	private PasswordField senha;
	
	
	public void entrar() {
		System.out.println("Efetuando o login... ");
		
		//Logica de entrada
		if (usuario.getText().equals("admin") && senha.getText().equals("admin")) {
		
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/dashboard.fxml"));
			
			try {
				
				StackPane root = loader.load();
				
				Scene scene = new Scene(root, 600, 400);
				
				Stage currentStage = (Stage) usuario.getScene().getWindow();
				
				currentStage.setScene(scene);
				currentStage.setTitle("Dashboard");
				currentStage.show();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else {
			mensagemErro();
		}
	}
	
	public void mensagemErro() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Sua senha está errada");
		alert.setContentText("Senha Incorreta");
		alert.setHeaderText(null);
		alert.showAndWait();
		
	}
	
}
