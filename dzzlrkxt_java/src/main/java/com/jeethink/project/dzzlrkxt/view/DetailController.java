package com.jeethink.project.dzzlrkxt.view;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.jeethink.common.utils.GeoUtils;
import com.jeethink.common.utils.IdWorker;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.framework.config.JeeThinkConfig;
import com.jeethink.project.dzzlrkxt.common.util.BeanUtil;
import com.jeethink.project.dzzlrkxt.domain.DzDrill;
import com.jeethink.project.dzzlrkxt.domain.DzProject;
import com.jeethink.project.dzzlrkxt.domain.MyPoint;
import com.jeethink.project.dzzlrkxt.service.IServiceDzDrillService;
import com.jeethink.project.dzzlrkxt.service.IServiceDzProjectService;
import com.jeethink.project.dzzlrkxt.utils.FxUtils;
import com.jeethink.project.dzzlrkxt.view.dateTimePicker.DateTimePicker;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.dom4j.DocumentException;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;
import org.geotools.swing.JMapFrame;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;

/**
 * @ClassName DetailController
 * @Description 项目详情
 * @Author lxl
 * @Time 2023/6/20 11:26
 * @Version 1.0
 */
public class DetailController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label deleteShpButton;

    @FXML
    private TextField drillNum;

    @FXML
    private TextField fgtzdm;

    @FXML
    private DatePicker hjTime;

    @FXML
    private TextField hjdw;

    @FXML
    private TextField hjdwlxr;

    @FXML
    private TextField hjdwlxrdh;

    @FXML
    private TextField hjpzbh;

    @FXML
    private Button projectDetailCancelButton;

    @FXML
    private Button projectDetailSaveButton;

    @FXML
    private TextField projectLocation;

    @FXML
    private TextField projectName;

    @FXML
    private ChoiceBox<String> qy;

    @FXML
    private TextField rkLxr;

    @FXML
    private DateTimePicker rkTime;

    @FXML
    private Label showShpButton;

    @FXML
    private Label shpName;

    @FXML
    private Button uploadShpButton;
    @FXML
    private Button uploadDrillButton;

    @FXML
    private DatePicker xcTime;

    @FXML
    private TextField zlmc;

    @FXML
    private TextField zlxcdw;

    @FXML
    private TextField zlxcdwlxr;

    @FXML
    private TextField zlxcdwlxrdh;
    @FXML
    private Label shp;
    @FXML
    private ProgressBar processBar;
    static Boolean isEditable = true;

    static ObservableList<DzProject> tableItems;
    List<DzDrill> dzDrills;
    static DzProject presentProject;

    private final String[] QY = {"上城区", "拱墅区", "西湖区", "滨江区", "萧山区", "余杭区", "临平区", "钱塘区", "富阳区", "临安区", "桐庐县", "淳安县", "建德市"};

    File selectedShpFile;

    @FXML
    void clickDeleteShpButton(MouseEvent event) {
        shpName.setText("暂无文件");
        deleteShpButton.setVisible(false);
        showShpButton.setVisible(false);
    }

    @FXML
    void clickProjectDetailCancelButton(MouseEvent event) {
        Stage stage = (Stage) projectDetailCancelButton.getScene().getWindow();
        List<DzProject> dzProjects = BeanUtil.getBean(IServiceDzProjectService.class).selectProjectList(null, 1, 25).getData();
        tableItems.setAll(dzProjects);
        stage.close();
    }

    @FXML
    void clickProjectDetailSaveButton(MouseEvent event) throws IOException, DocumentException, ParseException {
        try {
            Geometry geometry = GeoUtils.wktStrToGeometry((String) presentProject.getShp());
            for (Coordinate coordinate : geometry.getCoordinates()) {
                MyPoint myPoint = FxUtils.coordinateSwitch(new MyPoint(String.valueOf(coordinate.getX()), String.valueOf(coordinate.getY())));
                coordinate.setX(Double.parseDouble(myPoint.getX()));
                coordinate.setY(Double.parseDouble(myPoint.getY()));
            }
            String wkt = GeoUtils.geometry2Wkt(geometry);
            Long oldProjectId = presentProject.getProjectId();
            presentProject = getPresentEntity();
            presentProject.setProjectId(oldProjectId);
            presentProject.setShp(wkt);
            if (dzDrills != null && presentProject != null) {
                int insertNum = BeanUtil.getBean(IServiceDzProjectService.class).insertProject(presentProject);
                int i = BeanUtil.getBean(IServiceDzDrillService.class).insertBatch(dzDrills);
                if (insertNum > 0 && i > 0) {
                    clickProjectDetailCancelButton(null);
                    FxUtils.alert("保存成功", Alert.AlertType.NONE);
                } else {
                    FxUtils.alert("保存失败请检查数据", Alert.AlertType.WARNING);
                }
            } else {
                FxUtils.alert("保存失败，请上传钻孔数据", Alert.AlertType.WARNING);
            }
        } catch (Exception e) {
            e.printStackTrace();
            FxUtils.alert("保存失败请检查数据", Alert.AlertType.WARNING);
        }
    }

    /**
     * @Author lxl
     * @Description 从输入框获取当前输入实体
     * @Date 9:45 2023/6/21
     **/
    public DzProject getPresentEntity() {
        DzProject dzProject = new DzProject();
        IdWorker idWorker = new IdWorker();
        dzProject.setProjectId(idWorker.nextId());
        dzProject.setHjpzbh(hjpzbh.getText());
        dzProject.setProjectName(projectName.getText());
        dzProject.setFgtzdm(fgtzdm.getText());
        dzProject.setZlmc(zlmc.getText());
        dzProject.setQy(qy.getValue());
        dzProject.setProjectLocation(projectLocation.getText());
        dzProject.setHjdw(hjdw.getText());
        dzProject.setHjTime(FxUtils.localDateToDate(hjTime.getValue()));
        dzProject.setHjdwlxr(hjdwlxr.getText());
        dzProject.setHjdwlxrdh(hjdwlxrdh.getText());
        dzProject.setZlxcdw(zlxcdw.getText());
        dzProject.setXcTime(FxUtils.localDateToDate(xcTime.getValue()));
        dzProject.setZlxcdwlxr(zlxcdwlxr.getText());
        dzProject.setZlxcdwlxrdh(zlxcdwlxrdh.getText());
        dzProject.setRkTime(FxUtils.localDateTimeToDate(rkTime.dateTimeProperty().get()));
        dzProject.setRkLxr(rkLxr.getText());
        dzProject.setRemark(null);
        dzProject.setShp(shp.getText());
        return dzProject;
    }

    // public static void setTableItems(ObservableList<DzProject> tableItems) {
    //     tableItems = tableItems;
    // }


    @FXML
    void clickShowShpButton(MouseEvent event) throws IOException {
        FileDataStore store = FileDataStoreFinder.getDataStore(this.selectedShpFile);
        SimpleFeatureSource featureSource = store.getFeatureSource();


        MapContent map = new MapContent();
        map.setTitle("Quickstart");
        Style style = SLD.createSimpleStyle(featureSource.getSchema());
        Layer layer = new FeatureLayer(featureSource, style);
        map.addLayer(layer);

        // JMapFrame.showMap(map);


        MapContent mapContent = new MapContent();
        mapContent.setTitle("GeoTools Mapping");


        mapContent.addLayer(layer);
        JMapFrame frame = new JMapFrame(mapContent);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.enableStatusBar(true);
        frame.enableToolBar(true);
        JToolBar toolBar = frame.getToolBar();
        toolBar.addSeparator();
        frame.initComponents();
        frame.setSize(1000, 500);
        frame.setVisible(true);

    }

    @FXML
    void clickUploadShpButton(MouseEvent event) {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            shpName.setText(selectedFile.getName());
            String savePath = JeeThinkConfig.getProfile() + "/tempFile/" + UUID.randomUUID();
            File localFile = new File(savePath);
            localFile.mkdirs();
            List<Map<String, Object>> maps = GeoUtils.resolveZip(selectedFile, savePath);
            this.selectedShpFile = new File(GeoUtils.shpPath);
            if (maps.size() > 0) {
                List<Map<String, Object>> matchMaps = mapNameMatch(maps);
                Boolean aBoolean = importShp(matchMaps.get(0));
                deleteShpButton.setVisible(true);
                showShpButton.setVisible(true);
                uploadDrillButton.setVisible(true);
            }
        }
    }

    public List<Map<String, Object>> mapNameMatch(List<Map<String, Object>> maps) {
        if (maps.isEmpty()) {
            return null;
        }
        Map<String, Object> stringObjectMap = maps.get(0);
        stringObjectMap.put("hjTime", stringObjectMap.get("hjrq"));
        stringObjectMap.put("projectName", stringObjectMap.get("xmmc"));
        stringObjectMap.put("fgtzdm", stringObjectMap.get("fgdm"));
        stringObjectMap.put("projectLocation", stringObjectMap.get("xmwz"));

        return maps;
    }

    @FXML
    void clickUploadDrillButton(MouseEvent event) {

        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(stage);
        try (FileInputStream fileInputStream = new FileInputStream(selectedFile)) {

            ExcelUtil<DzDrill> util = new ExcelUtil<DzDrill>(DzDrill.class);
            dzDrills = util.importExcel(fileInputStream);
            List<DzDrill> drillFilter = new ArrayList<>();

            drillNum.setText(String.valueOf(dzDrills.size()));
            Task task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    for (int i = 0; i < dzDrills.size(); i++) {
                        DzDrill dzDrill = dzDrills.get(i);
                        dzDrill.setProjectId(presentProject.getProjectId());
                        IdWorker idWorker = new IdWorker();
                        dzDrill.setDrillId(idWorker.nextId());
                        dzDrill.setCreateTime(new Date());
                        if (!dzDrill.getLocaltionX().contains(".")) {
                            drillFilter.add(dzDrill);
                        }
                        updateProgress(i, dzDrills.size()-1);
                        MyPoint myPoint = FxUtils.coordinateSwitch(new MyPoint(dzDrill.getLocaltionX(), dzDrill.getLocaltionY()));
                        dzDrill.setLocaltionX(myPoint.getX());
                        dzDrill.setLocaltionY(myPoint.getY());
                    }
                    dzDrills.removeAll(drillFilter);

                    return null;
                }
            };
            processBar.progressProperty().bind(task.progressProperty());
            Thread thread = new Thread(task);
            thread.start();

            task.setOnSucceeded(e -> {
                FxUtils.alert("上传钻孔数据成功", Alert.AlertType.NONE);
            });

        } catch (Exception e) {
            FxUtils.alert("上传异常请联系管理员", Alert.AlertType.ERROR);
            e.printStackTrace();

        }


    }

    public Boolean importShp(Map<String, Object> map) {
        try {
            hjpzbh.setText((String) map.get("hjpzbh"));
            projectName.setText((String) map.get("projectName"));
            fgtzdm.setText((String) map.get("fgtzdm"));
            zlmc.setText((String) map.get("zlmc"));
            qy.setValue((String) map.get("qy"));

            // qy.getSelectionModel().select(3);
            projectLocation.setText((String) map.get("projectLocation"));
            hjdw.setText((String) map.get("hjdw"));
            LocalDate hjrqLocalDate = null;
            if (map.get("hjrq") != null) {
                DateTime hjrq = DateUtil.parse((String) map.get("hjrq"), "yyyy-MM-dd");
                hjrqLocalDate = FxUtils.dateToLocaldate(hjrq);
            }
            Object hjTimeDate = map.get("hjTime");
            if (hjTimeDate != null) {
                if (hjTimeDate instanceof String) {
                    hjrqLocalDate = LocalDate.parse(hjTimeDate.toString());
                } else {
                    hjrqLocalDate = FxUtils.dateToLocaldate((Date) hjTimeDate);
                }
            }
            hjTime.setValue(hjrqLocalDate);
            hjdwlxr.setText((String) map.get("hjdwlxr"));
            hjdwlxrdh.setText((String) map.get("hjdw"));
            zlxcdw.setText((String) map.get("zlxcdw"));
            xcTime.setValue(FxUtils.dateToLocaldate((Date) map.get("xcTime")));
            zlxcdwlxr.setText((String) map.get("zlxcdwlxr"));
            zlxcdwlxrdh.setText((String) map.get("zlxcdwlxrdh"));
            LocalDateTime rkLocalDate = null;
            // DateUtil.parse((String) map.get("rkTime"), "yyyy-MM-dd HH:mm:ss");
            LocalDateTime rkTimeDate = FxUtils.dateToLocalDateTime((Date) map.get("rkTime"));
            if (rkTimeDate != null) {
                rkLocalDate = rkTimeDate;
                // rkLocalDate = rkTimeDate;
            } else {
                rkLocalDate = LocalDateTime.now();
            }
            rkTime.setTimeProperty(rkLocalDate);
            rkLxr.setText((String) map.get("rkLxr"));
            // shp.setText(Arrays.toString(GeoUtils.wktToWkb((String) map.get("the_geom"))));
            shp.setText((String) map.get("the_geom"));
            drillNum.setText((String) map.get("drillNum"));
            presentProject = getPresentEntity();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    void initialize() {
        System.out.println("初始化");
        assert deleteShpButton != null : "fx:id=\"deleteShpButton\" was not injected: check your FXML file 'project-detail.fxml'.";
        assert drillNum != null : "fx:id=\"drillNum\" was not injected: check your FXML file 'project-detail.fxml'.";
        assert fgtzdm != null : "fx:id=\"fgtzdm\" was not injected: check your FXML file 'project-detail.fxml'.";
        assert hjTime != null : "fx:id=\"hjTime\" was not injected: check your FXML file 'project-detail.fxml'.";
        assert hjdw != null : "fx:id=\"hjdw\" was not injected: check your FXML file 'project-detail.fxml'.";
        assert hjdwlxr != null : "fx:id=\"hjdwlxr\" was not injected: check your FXML file 'project-detail.fxml'.";
        assert hjdwlxrdh != null : "fx:id=\"hjdwlxrdh\" was not injected: check your FXML file 'project-detail.fxml'.";
        assert hjpzbh != null : "fx:id=\"hjpzbh\" was not injected: check your FXML file 'project-detail.fxml'.";
        assert projectDetailCancelButton != null : "fx:id=\"projectDetailCancelButton\" was not injected: check your FXML file 'project-detail.fxml'.";
        assert projectDetailSaveButton != null : "fx:id=\"projectDetailSaveButton\" was not injected: check your FXML file 'project-detail.fxml'.";
        assert projectLocation != null : "fx:id=\"projectLocation\" was not injected: check your FXML file 'project-detail.fxml'.";
        assert projectName != null : "fx:id=\"projectName\" was not injected: check your FXML file 'project-detail.fxml'.";
        assert qy != null : "fx:id=\"qy\" was not injected: check your FXML file 'project-detail.fxml'.";
        assert rkLxr != null : "fx:id=\"rkLxr\" was not injected: check your FXML file 'project-detail.fxml'.";
        assert rkTime != null : "fx:id=\"rkTime\" was not injected: check your FXML file 'project-detail.fxml'.";
        assert showShpButton != null : "fx:id=\"showShpButton\" was not injected: check your FXML file 'project-detail.fxml'.";
        assert shpName != null : "fx:id=\"shpName\" was not injected: check your FXML file 'project-detail.fxml'.";
        assert uploadShpButton != null : "fx:id=\"uploadShpButton\" was not injected: check your FXML file 'project-detail.fxml'.";
        assert xcTime != null : "fx:id=\"xcTime\" was not injected: check your FXML file 'project-detail.fxml'.";
        assert zlmc != null : "fx:id=\"zlmc\" was not injected: check your FXML file 'project-detail.fxml'.";
        assert zlxcdw != null : "fx:id=\"zlxcdw\" was not injected: check your FXML file 'project-detail.fxml'.";
        assert zlxcdwlxr != null : "fx:id=\"zlxcdwlxr\" was not injected: check your FXML file 'project-detail.fxml'.";
        assert zlxcdwlxrdh != null : "fx:id=\"zlxcdwlxrdh\" was not injected: check your FXML file 'project-detail.fxml'.";

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        editable();
        qy.getItems().addAll(QY);
        qy.setOnAction(this::getQy);
        if (presentProject != null) {
            try {
                importShp(objectToMap(presentProject));
                qy.getSelectionModel().select(presentProject.getQy());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        uploadDrillButton.setVisible(false);
    }

    public void editable() {
        projectName.setEditable(isEditable);
        fgtzdm.setEditable(isEditable);
        qy.setDisable(!isEditable);
        projectLocation.setEditable(isEditable);
        zlmc.setEditable(isEditable);
        hjpzbh.setEditable(isEditable);
        hjdw.setEditable(isEditable);
        hjTime.setEditable(isEditable);
        hjdwlxr.setEditable(isEditable);
        hjdwlxrdh.setEditable(isEditable);
        zlxcdw.setEditable(isEditable);
        xcTime.setEditable(isEditable);
        zlxcdwlxr.setEditable(isEditable);
        zlxcdwlxrdh.setEditable(isEditable);
        rkLxr.setEditable(isEditable);
        zlxcdw.setEditable(isEditable);
        // rkTime.setEditable(isEditable);

    }

    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }

        return map;
    }

    public String getQy(ActionEvent event) {
        String qyString = qy.getValue();
        return qyString;
    }
}
