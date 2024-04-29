module grafos.grafosfinal {
    requires javafx.controls;
    requires javafx.fxml;


    opens grafos.grafosfinal to javafx.fxml;
    exports grafos.grafosfinal;
}