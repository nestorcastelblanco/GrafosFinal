module grafos.grafosfinal {
    requires javafx.controls;
    requires javafx.fxml;


    opens grafos.grafosfinal to javafx.fxml;
    exports Controladores;
    opens Controladores to javafx.fxml;
    exports run;
    opens run to javafx.fxml;
}