package com.jeethink.project.dzzlrkxt.view;

import com.jeethink.project.system.service.ISysUserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

/**
 * @author lxl
 */
@Component
public class IndexController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button drillManageButton;

    @FXML
    private BorderPane index;

    @FXML
    private Button oneMapButton;

    @FXML
    private Button projectManageButton;
    @Autowired
    ISysUserService iSysUserService;
    @FXML
    void clickDrillManageButton(MouseEvent event) throws IOException {
        index.setCenter(new FXMLLoader(DrillManageController.class.getResource("/fxml/drill.fxml")).load());
    }

    // @FXML
    // void clickOneMapButton(MouseEvent event) throws IOException {
    //     index.setCenter(new FXMLLoader(MapController.class.getResource("/fxml/map.fxml")).load());
    // }

    @FXML
    void clickProjectManageButton(MouseEvent event) throws IOException {
        index.setCenter(new FXMLLoader(ProjectsManageController.class.getResource("/fxml/projects.fxml")).load());

    }

    @FXML
    void initialize() throws IOException, ParseException {
        index.setCenter(new FXMLLoader(MapController.class.getResource("/fxml/projects.fxml")).load());
        assert drillManageButton != null : "fx:id=\"drillManageButton\" was not injected: check your FXML file 'index.fxml'.";
        assert index != null : "fx:id=\"index\" was not injected: check your FXML file 'index.fxml'.";
        // assert oneMapButton != null : "fx:id=\"oneMapButton\" was not injected: check your FXML file 'index.fxml'.";
        assert projectManageButton != null : "fx:id=\"projectManageButton\" was not injected: check your FXML file 'index.fxml'.";

    }

}
