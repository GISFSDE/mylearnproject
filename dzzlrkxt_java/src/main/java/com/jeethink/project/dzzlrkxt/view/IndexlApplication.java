package com.jeethink.project.dzzlrkxt.view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Optional;


public class IndexlApplication extends Application {
    private static final Logger log = LoggerFactory.getLogger(IndexlApplication.class);
    @Override
    public void start(Stage stage) throws IOException {
        System.out.println("项目开始啦");
        FXMLLoader fxmlLoader = new FXMLLoader(IndexlApplication.class.getResource("/fxml/index.fxml"));

        // FXMLLoader fxmlLoader = new FXMLLoader(new URL("file:/D:/Projects/dzzlrkxt/dzzlrkxt_java/src/main/resources/fxml/index.fxml"));
        // FXMLLoader fxmlLoader = new FXMLLoader(new URL("D:/Projects/dzzlrkxt/dzzlrkxt_java/src/main/resources/fxml/index.fxml"));
        // FXMLLoader fxmlLoader = new FXMLLoader(new URL("file:///D:/Projects/dzzlrkxt/dzzlrkxt_java/src/main/resources/fxml/index.fxml"));
        log.warn(fxmlLoader.getLocation()+"=========");
        Scene scene = new Scene(fxmlLoader.load(), 1280, 960);
        stage.setTitle("地质资料管理系统");
        // 设置场景对象
        stage.setScene(scene);
        // StageStyle.DECORATED//默认，白色背景并带有操作系统装饰
        // StageStyle.UNDECORATED//白色背景没有操作系统装饰
        // StageStyle.TRANSPARENT//透明背景没有操作系统装饰
        // StageStyle.UNIFIED//白色背景带有操作系统装饰，但工作区域和装饰区域没有分割线
        // StageStyle.UTILITY//白色背景带有最简的操作系统装饰
        // stage.initStyle(StageStyle.UTILITY);
        // stage.getIcons().add(new Image("D:\\Projects\\MY\\mylearnproject\\javafxlearn\\src\\main\\resources\\img\\icon.png"));
        // APPLICATION_MODAL 定义一个模式窗口，阻止事件传递到任何其他应用程序窗口。
        // NONE 定义一个非模式窗口，并且不阻止任何其他窗口。
        // WINDOW_MODAL 定义一个模式窗口，该窗口阻止事件传递到其整个所有者窗口层次结构。
        // stage.initModality();
        // 使Stage可见并立即退出该方法
        stage.show();
        // 1、取消操作系统默认退出事件
        Platform.setImplicitExit(false);
        stage.setOnCloseRequest(event -> {
            // 2、取消右上角的×关闭窗口事件
            event.consume();
            // 3、创建一个弹窗
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("退出程序");
            alert.setHeaderText(null);
            alert.setContentText("您是否要退出程序？");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                // 4.1退出程序
                Platform.exit();
                // 4.2关闭窗口，不退出程序
                stage.close();

            }
        });
        // 切换Scene
        // FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("projects.fxml"));
        // Scene sceneBye = new Scene(fxmlLoader1.load(), 960, 720);
        // stage.setTitle("Bye!");
        // // 设置场景对象
        // stage.setScene(sceneBye);
    }

    @Override
    public void init() throws Exception {
        super.init();
        System.out.println("项目初始化啦");
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        System.out.println("项目结束啦");
    }

    public static void main(String[] args) {
        launch();
    }
}


