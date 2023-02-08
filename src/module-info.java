module Kideappdatanalyysivehje {
	requires javafx.controls;
	requires com.google.gson;
	
	opens view to javafx.graphics, javafx.fxml, com.google.gson.Gson, com.google.gson.reflect;
	opens model to com.google.gson;
	exports model to com.google.gson;
}
