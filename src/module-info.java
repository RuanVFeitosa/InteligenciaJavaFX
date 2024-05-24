module InteligenciaJavaFX {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	
	// Linha 6 permie o package model utilizar o javafx.base
	opens model to javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
	opens controller to javafx.fxml;
}
