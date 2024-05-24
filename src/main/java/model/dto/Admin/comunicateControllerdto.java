package model.dto.Admin;

import controller.ComunicativeController;
import javafx.scene.Parent;

public class comunicateControllerdto {
    private ComunicativeController controller;
    private Parent parent;


    public comunicateControllerdto(ComunicativeController controller, Parent parent) {
        this.controller = controller;
        this.parent = parent;
    }

    public ComunicativeController getController() {
        return controller;
    }

    public Parent getParent() {
        return parent;
    }
}
