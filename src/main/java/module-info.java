module it.didattica.cs.unicam.progettoloretimgc {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.jena.arq;
    requires org.apache.jena.core;
    requires java.net.http;
    //requires openllet.jena;


    opens it.didattica.cs.unicam.progettoloretimgc to javafx.fxml;
    exports it.didattica.cs.unicam.progettoloretimgc;
}