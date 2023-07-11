package com.jeethink.project.dzzlrkxt.view.dateTimePicker;

/**
 * @ClassName DateTimePickerSelect
 * @Description TODO
 * @Author lxl
 * @Date 2023/7/4 18:10
 * @Version 1.0
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;

class DateTimePickerSelect extends VBox implements Initializable {
    private final DateTimePicker dateTimePicker;
    private List<Button> dayList;
    private Calendar calendar;
    private LocalDateTime cursorDateTime;
    @FXML
    private FlowPane flowPane;
    @FXML
    private FlowPane flow;
    @FXML
    private Label labelYear;
    @FXML
    private Label labelMouth;
    @FXML
    private Button previousYear;
    @FXML
    private Button nextYear;
    @FXML
    private Button previousMonth;
    @FXML
    private Button nextMonth;
    @FXML
    private ComboBox<String> hour;
    @FXML
    private ComboBox<String> minute;
    @FXML
    private ComboBox<String> second;
    @FXML
    private Button buttonCancel;
    @FXML
    private Button buttonOK;
    @FXML
    private Button buttonReset;

    public void initialize(URL location, ResourceBundle resources) {
        this.dayList = new ArrayList();
        String[] weeks = new String[]{"一", "二", "三", "四", "五", "六", "七"};
        String[] var4 = weeks;
        int var5 = weeks.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            String week = var4[var6];
            Label label = new Label(week);
            label.setStyle("-fx-font-size: 9;-fx-font-family: Microsoft YaHei;");
            this.flowPane.getChildren().add(label);
        }

    }

    public DateTimePickerSelect(DateTimePicker parentCont) {
        this.dateTimePicker = parentCont;
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/DateTimePickerSelect.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException var4) {
            throw new RuntimeException(var4);
        }

        if (this.dateTimePicker.dateTimeProperty() != null) {
            this.cursorDateTime = (LocalDateTime)this.dateTimePicker.dateTimeProperty().getValue();
        }

        this.upDataCalendar(true);
    }

    public String strValue(int i) {
        String res;
        if (i < 10) {
            res = "0" + i;
        } else {
            res = i + "";
        }

        return res;
    }

    public void upDataLab() {
        if (this.calendar != null) {
            this.labelMouth.setText(this.calendar.get(2) + 1 + "");
            this.labelYear.setText(this.calendar.get(1) + "");
        }

    }

    public void upDataCalendar(boolean open) {
        this.dayList.clear();
        this.flow.getChildren().clear();
        this.cursorDateTime = this.dateTimePicker.dateTimeProperty() != null ? (LocalDateTime)this.dateTimePicker.dateTimeProperty().get() : null;
        int nowYear = this.cursorDateTime != null ? this.cursorDateTime.getYear() : -1;
        int nowMonth = this.cursorDateTime != null ? this.cursorDateTime.getMonthValue() : -1;
        Calendar tmpCalendar = this.cursorDateTime != null ? GregorianCalendar.from(ZonedDateTime.of(this.cursorDateTime, ZoneId.systemDefault())) : GregorianCalendar.from(ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault()));
        if (open) {
            this.calendar = (Calendar)tmpCalendar;
        } else {
            tmpCalendar = this.calendar;
        }

        int mouthDays = ((Calendar)tmpCalendar).getActualMaximum(5);
        ((Calendar)tmpCalendar).set(((Calendar)tmpCalendar).get(1), ((Calendar)tmpCalendar).get(2), mouthDays, ((Calendar)tmpCalendar).get(11), ((Calendar)tmpCalendar).get(12), ((Calendar)tmpCalendar).get(13));
        int weekMouthLastDay = ((Calendar)tmpCalendar).get(7);
        ((Calendar)tmpCalendar).set(((Calendar)tmpCalendar).get(1), ((Calendar)tmpCalendar).get(2), 1, ((Calendar)tmpCalendar).get(11), ((Calendar)tmpCalendar).get(12), ((Calendar)tmpCalendar).get(13));
        int weekMouthFirstDay = ((Calendar)tmpCalendar).get(7);
        ((Calendar)tmpCalendar).add(2, -1);
        int lastMouthDays = ((Calendar)tmpCalendar).getActualMaximum(5);
        ((Calendar)tmpCalendar).add(2, 1);
        int i;
        Button btn;
        if (weekMouthFirstDay == 1) {
            for(i = lastMouthDays - 5; i <= lastMouthDays; ++i) {
                btn = new Button(this.strValue(i));
                this.setDisable(btn);
                this.flow.getChildren().add(btn);
            }

            for(i = 1; i <= mouthDays; ++i) {
                btn = new Button(this.strValue(i));
                this.dayList.add(btn);
                this.setAble(btn);
                this.flow.getChildren().add(btn);
            }
        } else if (weekMouthFirstDay == 2) {
            for(i = 1; i <= mouthDays; ++i) {
                btn = new Button(this.strValue(i));
                this.dayList.add(btn);
                this.setAble(btn);
                if (this.cursorDateTime != null && this.cursorDateTime.getDayOfMonth() == i && this.calendar != null && this.calendar.get(1) == nowYear && this.calendar.get(2) + 1 == nowMonth) {
                    btn.setStyle("-fx-text-fill: white;-fx-background-color: #5b8cff;-fx-font-size: 10");
                }

                this.flow.getChildren().add(btn);
            }
        } else {
            for(i = lastMouthDays - weekMouthFirstDay + 3; i <= lastMouthDays; ++i) {
                btn = new Button(this.strValue(i));
                this.setDisable(btn);
                this.flow.getChildren().add(btn);
            }

            for(i = 1; i <= mouthDays; ++i) {
                btn = new Button(this.strValue(i));
                this.dayList.add(btn);
                this.setAble(btn);
                if (this.cursorDateTime != null && this.cursorDateTime.getDayOfMonth() == i && this.calendar != null && this.calendar.get(1) == nowYear && this.calendar.get(2) + 1 == nowMonth) {
                    btn.setStyle("-fx-text-fill: white;-fx-background-color: #5b8cff;-fx-font-size: 10");
                }

                this.flow.getChildren().add(btn);
            }
        }

        if (weekMouthLastDay != 1) {
            for(i = 1; i <= 8 - weekMouthLastDay; ++i) {
                btn = new Button(this.strValue(i));
                this.setDisable(btn);
                this.flow.getChildren().add(btn);
            }
        }

        this.setTime();
        this.upDataLab();
    }

    public void setDisable(Button btn) {
        btn.setDisable(true);
        btn.setStyle("-fx-text-fill: black;-fx-background-color: transparent;;-fx-font-size: 10");
    }

    public void setAble(final Button btn) {
        btn.setStyle("-fx-text-fill: black;-fx-background-color: #fff;-fx-font-size: 10");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                DateTimePickerSelect.this.dayList.forEach((e) -> {
                    e.setStyle("-fx-text-fill: black;-fx-background-color: #fff;-fx-font-size: 10");
                });
                btn.setStyle("-fx-text-fill: white;-fx-background-color: #5b8cff;-fx-font-size: 10");
                DateTimePickerSelect.this.calendar.set(DateTimePickerSelect.this.calendar.get(1), DateTimePickerSelect.this.calendar.get(2), Integer.valueOf(btn.getText()), DateTimePickerSelect.this.calendar.get(11), DateTimePickerSelect.this.calendar.get(12), DateTimePickerSelect.this.calendar.get(13));
            }
        });
    }

    @FXML
    public void previousYear() {
        this.calendar.add(1, -1);
        this.upDataCalendar(false);
    }

    @FXML
    public void previousMonth() {
        this.calendar.add(2, -1);
        this.upDataCalendar(false);
    }

    @FXML
    public void nextYear() {
        this.calendar.add(1, 1);
        this.upDataCalendar(false);
    }

    @FXML
    public void nextMonth() {
        this.calendar.add(2, 1);
        this.upDataCalendar(false);
    }

    private void setTime() {
        int i;
        for(i = 1; i < 25; ++i) {
            this.hour.getItems().add(this.strValue(i));
        }

        this.hour.getSelectionModel().select(this.calendar.get(11) == 0 ? 24 : this.calendar.get(11) - 1);

        for(i = 0; i < 60; ++i) {
            this.minute.getItems().add(this.strValue(i));
        }

        this.minute.getSelectionModel().select(this.calendar.get(12));

        for(i = 0; i < 60; ++i) {
            this.second.getItems().add(this.strValue(i));
        }

        this.second.getSelectionModel().select(this.calendar.get(13));
    }

    @FXML
    private void buttonOKOnMousePressed() {
        this.buttonOK.setStyle("-fx-background-color:#FFFFB9;-fx-font-size: 12;-fx-cursor: HAND;");
    }

    @FXML
    private void buttonOKOnMouseReleased() {
        this.buttonOK.setStyle("-fx-background-color:#ACD6FF;-fx-font-size: 12;-fx-cursor: HAND;");
    }

    @FXML
    private void buttonOKOnAction() {
        if (this.calendar != null) {
            this.calendar.set(this.calendar.get(1), this.calendar.get(2), this.calendar.get(5), Integer.parseInt((String)this.hour.getSelectionModel().getSelectedItem()), Integer.parseInt((String)this.minute.getSelectionModel().getSelectedItem()), Integer.parseInt((String)this.second.getSelectionModel().getSelectedItem()));
            LocalDateTime localDateTime = LocalDateTime.ofInstant(this.calendar.toInstant(), ZoneId.systemDefault());
            this.dateTimePicker.setTimeProperty(localDateTime);
        }

        this.dateTimePicker.hide();
    }

    @FXML
    private void buttonNowOnMousePressed() {
        this.buttonOK.setStyle("-fx-background-color:#FFFFB9;-fx-font-size: 12;-fx-cursor: HAND;");
    }

    @FXML
    private void buttonNowOnMouseReleased() {
        this.buttonOK.setStyle("-fx-background-color:#e7c269;-fx-font-size: 12;-fx-cursor: HAND;");
    }

    @FXML
    private void buttonNowOnAction() {
        LocalDateTime localDateTime = LocalDateTime.now();
        this.calendar = Calendar.getInstance();
        this.dateTimePicker.setTimeProperty(localDateTime);
        this.dateTimePicker.hide();
    }

    @FXML
    private void buttonCancelOnMousePressed() {
        this.buttonCancel.setStyle("-fx-background-color:#FFFFB9;-fx-font-size: 12;-fx-cursor: HAND;");
    }

    @FXML
    private void buttonCancelOnMouseReleased() {
        this.buttonCancel.setStyle("-fx-background-color:#FFD2D2;-fx-font-size: 12;-fx-cursor: HAND;");
    }

    @FXML
    private void buttonCancelOnAction() {
        this.dateTimePicker.hide();
    }

    @FXML
    private void buttonResetOnMousePressed() {
        this.buttonReset.setStyle("-fx-background-color:#FFFFB9;-fx-font-size: 12;-fx-cursor: HAND;");
    }

    @FXML
    private void buttonResetOnMouseReleased() {
        this.buttonReset.setStyle("-fx-background-color:#96e561;-fx-font-size: 12;-fx-cursor: HAND;");
    }

    @FXML
    private void buttonResetOnAction() {
        this.calendar = null;
        this.cursorDateTime = null;
        this.dateTimePicker.clearTimeProperty();
    }
}

