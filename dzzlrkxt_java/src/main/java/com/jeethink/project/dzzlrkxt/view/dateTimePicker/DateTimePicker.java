package com.jeethink.project.dzzlrkxt.view.dateTimePicker;

/**
 * @ClassName dateTimePicker
 * @Description TODO
 * @Author lxl
 * @Date 2023/7/4 18:08
 * @Version 1.0
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Popup;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.util.ResourceBundle;

public class DateTimePicker extends HBox implements Initializable {
    private final DateTimeFormatter formatter;
    private ObjectProperty<LocalDateTime> dateTime;
    private final Popup popupContainer;
    private final DateTimePickerSelect dateTimePickerSelect;
    public Boolean showLocalizedDateTime = false;
    @FXML
    private TextField textField;
    @FXML
    private Button button;

    public DateTimePicker() {
        if (this.showLocalizedDateTime) {
            this.dateTime = new SimpleObjectProperty(LocalDateTime.now());
        }

        this.formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        this.popupContainer = new Popup();
        this.dateTimePickerSelect = new DateTimePickerSelect(this);
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/DateTimePicker.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException var3) {
            throw new RuntimeException(var3);
        }
    }

    public void initialize(URL location, ResourceBundle resources) {
        if (this.showLocalizedDateTime) {
            this.textField.setText(this.formatter.format((TemporalAccessor)this.dateTime.get()));
        }

        this.button.prefHeightProperty().bind(this.textField.heightProperty());
        this.popupContainer.getContent().add(this.dateTimePickerSelect);
        this.popupContainer.autoHideProperty().set(true);
    }

    @FXML
    void handleButtonAction(ActionEvent event) {
        if (this.popupContainer.isShowing()) {
            this.popupContainer.hide();
        } else {
            Window window = this.button.getScene().getWindow();
            double x = window.getX() + this.textField.localToScene(0.0, 0.0).getX() + this.textField.getScene().getX();
            double y = window.getY() + this.button.localToScene(0.0, 0.0).getY() + this.button.getScene().getY() + this.button.getHeight();
            this.popupContainer.show(this.getParent(), x, y);
            this.dateTimePickerSelect.upDataCalendar(true);
        }

    }

    public ObjectProperty<LocalDateTime> dateTimeProperty() {
        return this.dateTime;
    }

    public void setTimeProperty(LocalDateTime localDateTime) {
        this.dateTime = new SimpleObjectProperty(localDateTime);
        this.textField.setText(this.formatter.format((TemporalAccessor)this.dateTime.get()));
    }

    public void clearTimeProperty() {
        this.dateTime = null;
        this.textField.setText("");
    }

    public void hide() {
        if (this.popupContainer.isShowing()) {
            this.popupContainer.hide();
        }

    }

    public Boolean getShowLocalizedDateTime() {
        return this.showLocalizedDateTime;
    }

    public void setShowLocalizedDateTime(Boolean show) {
        this.showLocalizedDateTime = show;
    }
}

