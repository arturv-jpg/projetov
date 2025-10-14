module Projeto {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.media;


    
    opens application.view to javafx.fxml;

   
    exports application.view;
    exports application;
}


