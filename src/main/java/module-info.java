module org.one.alura_hotel_g5 {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.one.alura_hotel_g5 to javafx.fxml;
    exports org.one.alura_hotel_g5;
}
