module Projeto {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;

    
    opens application.view to javafx.fxml;

   
    exports application.view;
    exports application;
}


