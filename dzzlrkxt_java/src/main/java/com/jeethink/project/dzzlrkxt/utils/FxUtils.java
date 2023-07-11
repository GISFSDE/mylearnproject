package com.jeethink.project.dzzlrkxt.utils;


import com.jeethink.common.utils.http.HttpUtils;
import com.jeethink.project.dzzlrkxt.domain.MyPoint;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName FxUtils
 * @Description
 * @Author lxl
 * @Date 2023/6/20 14:31
 * @Version 1.0
 */
@Component
public class FxUtils {
    // @PostConstruct
    // public void init() {
    //     FxUtils instance = this;
    // }
    public static  MyPoint coordinateSwitch(MyPoint myPoint) throws DocumentException {
        String URL = "http://21.15.116.31/e288eefda8363b238133b1eaf142d3add473e5c7/Transform/LiquidGIS/HangZhou.gis";
        String result = HttpUtils.sendGet(URL, "x=" + myPoint.getX() + "&y=" + myPoint.getY() + "&COMMAND=TOCGCS2000");
        Document document = DocumentHelper.parseText(result);
        Element rootElement = document.getRootElement();
        List elements = rootElement.elements();

        Element xElement = (Element)elements.get(0);
        Element yElement = (Element)elements.get(1);


        String x = xElement.getText();
        String y = yElement.getText();
        myPoint.setX(x);
        myPoint.setY(y);
        return myPoint;
    }

    public static void main(String[] args) throws DocumentException {
        // coordinateSwitch(new MyPoint("75894.5300", "78274.7500"));
    }
    public static void openNewWindow(String fxmlPath, Parent root) throws Exception {
        Stage stage = new Stage();
        Scene scene = new Scene((Parent) FXMLLoader.load(Objects.requireNonNull(FxUtils.class.getResource(fxmlPath))));
        stage.setScene(scene);
        stage.show();
    }

    public static Date localDateToDate(LocalDate localDate) {
        return localDate == null ? null : Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return localDateTime == null ? null :Date.from( localDateTime.atZone( ZoneId.systemDefault()).toInstant());
    }
    public static LocalDate dateToLocaldate(Date date) {
        return date == null ? null : date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return date == null ? null : date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
    public static Boolean alert(String alertContent, Alert.AlertType alertType) {

        Alert alert = new Alert(alertType);
        if (alertType.equals(Alert.AlertType.NONE)) {
            alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
        }
        alert.setContentText(alertContent);
        AtomicReference<Boolean> isConfirm = new AtomicReference<>(false);
        alert.showAndWait().ifPresent(response -> {
            if ("O".equals(response.getButtonData().getTypeCode() )) {
                isConfirm.set(true);
            }
        });;
        return isConfirm.get();
    }


}
