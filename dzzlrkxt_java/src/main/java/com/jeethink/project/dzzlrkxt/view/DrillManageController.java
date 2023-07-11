package com.jeethink.project.dzzlrkxt.view;

/**
 * @ClassName ProjectsManage
 * @Description
 * @Author lxl
 * @Time 2023/6/5 17:40
 * @Version 1.0
 */

import com.github.pagehelper.PageInfo;
import com.jeethink.project.dzzlrkxt.common.util.BeanUtil;
import com.jeethink.project.dzzlrkxt.domain.DzDrill;
import com.jeethink.project.dzzlrkxt.domain.DzProject;
import com.jeethink.project.dzzlrkxt.domain.vo.DrillPageVo;
import com.jeethink.project.dzzlrkxt.service.IServiceDzDrillService;
import com.jeethink.project.dzzlrkxt.service.IServiceDzProjectService;
import com.jeethink.project.dzzlrkxt.utils.FxUtils;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DrillManageController {

    @FXML
    private Button deleteDrillButton;

    @FXML
    private TableColumn<DzDrill, String> drillBhCol;

    @FXML
    private TableColumn<DzDrill, String> drillDepthCol;

    @FXML
    private TableColumn<DzDrill, String> drillElevationCol;

    @FXML
    private TableColumn<DzDrill, String> drillIdCol;

    @FXML
    private TableView<DzDrill> drillTable;

    @FXML
    private TableColumn<DzDrill, String> drillTypeCol;

    @FXML
    private Button exportDrillButton;

    @FXML
    private TableColumn<DzDrill, String> groundwaterDepthCol;
    @FXML
    private Pagination pagination;
    @FXML
    private TableColumn<DzDrill, String> groundwaterElevationCol;

    @FXML
    private Button importDrillButton;

    @FXML
    private TableColumn<DzDrill, String> localtionXCol;

    @FXML
    private TableColumn<DzDrill, String> localtionYCol;

    @FXML
    private TableColumn<DzDrill, String> projectNameCol;
    @FXML
    private TableColumn<DzDrill, CheckBox> selectCol;
    @FXML
    private Button searchDrillButton;

    @FXML
    private ResourceBundle resources;

    @FXML
    private ComboBox<String> drillTypeSearch;

    @FXML
    private TextField hjpzbhSearch;

    @FXML
    private TextField projectNameSearch;

    @FXML
    private ComboBox<String> qySearch;
    DzProject searchParame = new DzProject();
    ObservableList<DzDrill> list = FXCollections.observableArrayList();
    private List<DzDrill> dzDrillList = new ArrayList<DzDrill>();
    private int pageNum = 0;
    private int pageSize = 25;
    List<DzDrill> selectDrill;
    private final String[] QY = {"上城区", "拱墅区", "西湖区", "滨江区", "萧山区", "余杭区", "临平区", "钱塘区", "富阳区", "临安区", "桐庐县", "淳安县", "建德市"};

    @FXML
    public void initialize() {

        drillTypeSearch.getItems().addAll(getAllDrillType());
        qySearch.getItems().addAll(QY);
        configTableView();
        pagination.setPageFactory((Integer pageIndex) -> {
            this.pageNum = pageIndex + 1;
            PageInfo<DzDrill> pageInfo = null;
            pageInfo = showData(null);
            pagination.setPageCount(pageInfo.getPages());
            return new Label();
        });


        drillTable.setRowFactory(tableView -> {
            final TableRow<DzDrill> row = new TableRow<>();
            final ContextMenu rowMenu = new ContextMenu();
            MenuItem removeItem = new MenuItem("Delete");
            removeItem.setOnAction(e -> {
                drillTable.getItems().remove(row.getItem());
            });

            rowMenu.getItems().addAll(removeItem);
            row.contextMenuProperty().bind(
                    Bindings.when(Bindings.isNotNull(row.itemProperty()))
                            .then(rowMenu)
                            .otherwise((ContextMenu) null));

            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    System.out.println("Double click on " + row.getItem().getDrillBh());
                }
            });

            return row;
        });

        Callback<TableColumn<DzDrill, CheckBox>, TableCell<DzDrill, CheckBox>>
                selectCellFactory =
                new Callback<TableColumn<DzDrill, CheckBox>, TableCell<DzDrill, CheckBox>>() {
                    @Override
                    public TableCell call(TableColumn p) {
                        TableCell cell = new TableCell<DzDrill, CheckBox>() {
                            @Override
                            public void updateItem(CheckBox item, boolean empty) {
                                super.updateItem(item, empty);
                                setGraphic(item);
                                if (item != null) {
                                    // 初始化复选框-监听-所有checkbox复选框都勾选则全选框勾选，所有checkbox复选框不勾选则全选框不勾选
                                    item.selectedProperty()
                                            .addListener(new ChangeListener<Boolean>() {
                                                @Override
                                                public void changed(
                                                        ObservableValue<? extends Boolean> observable,
                                                        Boolean oldValue, Boolean newValue) {
                                                    boolean booSelectAll = true;
                                                    for (DzDrill dzDrill : drillTable
                                                            .getItems()) {

                                                        if (!dzDrill.getCb().isSelected()) {
                                                            booSelectAll = false;
                                                            break;
                                                        }
                                                    }

                                                    ObservableList<DzDrill> list = drillTable.getItems();
                                                    selectDrill = new ArrayList<>();
                                                    for (DzDrill o : list) {
                                                        if (o.getCb().isSelected()) {
                                                            selectDrill.add(o);
                                                        }
                                                    }

                                                    // mSelectAll.setSelected(booSelectAll);
                                                }
                                            });
                                }
                            }
                        };
                        return cell;
                    }
                };
        selectCol.setCellFactory(selectCellFactory);
        // 初始化所有checkbox复选框
        selectCol.setCellValueFactory(cellData -> cellData.getValue().getCb().getCheckBox());
    }
    private String[] getAllDrillType(){
        String[] allDrillType=  BeanUtil.getBean(IServiceDzDrillService.class).queryDrillType();
        return allDrillType;
    }
    /**
     * 配置表格，绑定表格的每列
     */
    private void configTableView() {
        projectNameCol.setCellValueFactory(new PropertyValueFactory<DzDrill, String>("projectName"));
        drillIdCol.setCellValueFactory(new PropertyValueFactory<DzDrill, String>("drillId"));
        drillBhCol.setCellValueFactory(new PropertyValueFactory<DzDrill, String>("drillBh"));
        drillTypeCol.setCellValueFactory(new PropertyValueFactory<DzDrill, String>("drillType"));
        localtionXCol.setCellValueFactory(new PropertyValueFactory<DzDrill, String>("localtionX"));
        localtionYCol.setCellValueFactory(new PropertyValueFactory<DzDrill, String>("localtionY"));
        drillElevationCol.setCellValueFactory(new PropertyValueFactory<DzDrill, String>("drillElevation"));
        drillDepthCol.setCellValueFactory(new PropertyValueFactory<DzDrill, String>("drillDepth"));
        groundwaterDepthCol.setCellValueFactory(new PropertyValueFactory<DzDrill, String>("groundwaterDepth"));
        groundwaterElevationCol.setCellValueFactory(new PropertyValueFactory<DzDrill, String>("groundwaterElevation"));
        drillTable.setItems(list);
    }


    /**
     * 展示数据
     */
    protected PageInfo<DzDrill> showData(DrillPageVo dzDrills) {
        if (generateDate().getData() != null) {
            dzDrillList = generateDate().getData();
        }
        if (dzDrills != null) {
            dzDrillList = dzDrills.getData();
        }
        for (DzDrill dzDrill : dzDrillList) {
            Long projectId = dzDrill.getProjectId();
            String projectName = BeanUtil.getBean(IServiceDzProjectService.class).selectByPrimaryKey(projectId).getProjectName();
            dzDrill.setProjectName(projectName);
        }
        list.setAll(dzDrillList);
        drillTable.setItems(list);
        return generateDate().getPageInfo();
    }

    /**
     * 生成数据
     *
     * @return
     */
    private DrillPageVo generateDate() {
        return BeanUtil.getBean(IServiceDzDrillService.class).queryByPage(null, pageNum, pageSize);
    }

    @FXML
    void clickDeleteDrillButton(MouseEvent event) {
        StringBuilder drillNameList = new StringBuilder();
        StringBuilder forbidDeleteList = new StringBuilder();
        StringBuilder deletedList = new StringBuilder();
        for (DzDrill dzDrill : selectDrill) {
            drillNameList.append(dzDrill.getDrillBh()).append("\n");
        }
        Boolean isDelete = FxUtils.alert("是否删除以下钻孔数据:\n" + drillNameList, Alert.AlertType.CONFIRMATION);
        if (isDelete) {
            for (DzDrill dzDrill : selectDrill) {
                boolean isdelete = BeanUtil.getBean(IServiceDzDrillService.class).deleteById(dzDrill.getDrillId());
                if (isdelete) {
                    deletedList.append(dzDrill.getDrillBh()).append("\n");
                }
            }
        }
        if (!deletedList.isEmpty()) {
            FxUtils.alert("已删除以下项目:\n" + deletedList, Alert.AlertType.NONE);
        }
        showData(null);
    }

    @FXML
    void clickExportDrillButton(MouseEvent event) {

    }

    @FXML
    void clickImportDrillButton(MouseEvent event) {

    }

    @FXML
    void clickSearchDrillButton(MouseEvent event) {
        searchParame.setProjectName(projectNameSearch.getText());
        searchParame.setQy(qySearch.getValue());
        searchParame.setHjpzbh(hjpzbhSearch.getText());
        System.out.println(drillTypeSearch.getValue());
        List<Long> ids = BeanUtil.getBean(IServiceDzProjectService.class).selectProjectListIds(searchParame);
        Long[] idsArray = ids.toArray(new Long[ids.size()]);
        DrillPageVo drillPageVo = BeanUtil.getBean(IServiceDzDrillService.class).queryByIds(idsArray, drillTypeSearch.getValue(), pageNum, pageSize);
        showData(drillPageVo);
    }

}
