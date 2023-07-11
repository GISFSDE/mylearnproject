package com.jeethink.project.dzzlrkxt.view;

/**
 * @ClassName ProjectsManage
 * @Description
 * @Author PC
 * @Time 2023/6/5 17:40
 * @Version 1.0
 */

import com.github.pagehelper.PageInfo;
import com.jeethink.project.dzzlrkxt.common.util.BeanUtil;
import com.jeethink.project.dzzlrkxt.domain.DzDrill;
import com.jeethink.project.dzzlrkxt.domain.DzProject;
import com.jeethink.project.dzzlrkxt.domain.vo.DrillPageVo;
import com.jeethink.project.dzzlrkxt.domain.vo.ProjectPageVo;
import com.jeethink.project.dzzlrkxt.service.IServiceDzDrillService;
import com.jeethink.project.dzzlrkxt.service.IServiceDzProjectService;
import com.jeethink.project.dzzlrkxt.utils.FxUtils;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
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
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ProjectsManageController {

    @FXML
    private ResourceBundle resources;
    @FXML
    Stage stage;
    @FXML
    private URL location;

    @FXML
    private DatePicker cjTimeSearch;

    @FXML
    private Button deleteProjectButton;

    @FXML
    private TableColumn<DzProject, String> drillNumCol;

    @FXML
    private Button exportProjectButton;

    @FXML
    private TextField fgtzdmSearch;

    @FXML
    private TableColumn<DzProject, Date> hjTimeCol;

    @FXML
    private DatePicker hjTimeSearch;

    @FXML
    private TextField hjdwSearch;

    @FXML
    private TableColumn<DzProject, String> hjpzbhCol;

    @FXML
    private TextField hjpzbhSearch;

    @FXML
    private Button importProjectButton;

    @FXML
    private Button insertProjectButton;

    @FXML
    private TableColumn<DzProject, String> orderCol;

    @FXML
    private Pagination pagination;

    @FXML
    private TableColumn<DzProject, String> projectNameCol;

    @FXML
    private TextField projectNameSearch;

    @FXML
    private TableView<DzProject> projectTable;

    @FXML
    private TableColumn<DzProject, String> qyCol;

    @FXML
    private ChoiceBox<String> qySearch;
    @FXML
    private TableColumn<DzProject, Date> rkTimeCol;

    @FXML
    private DatePicker rkTimeSearch;

    @FXML
    private Button searchButton;

    @FXML
    private TableColumn<DzProject, Date> xcTimeCol;

    @FXML
    private TextField zlxcdwSearch;
    @FXML
    private TableColumn<DzProject, CheckBox> selectCol;
    List<DzProject> selectProject;
    private final String[] QY = {"上城区", "拱墅区", "西湖区", "滨江区", "萧山区", "余杭区", "临平区", "钱塘区", "富阳区", "临安区", "桐庐县", "淳安县", "建德市"};
    // 放置数据的集合
    private List<DzProject> dzProjectList = new ArrayList<DzProject>();
    // javaFX 的数据集合
    ObservableList<DzProject> list = FXCollections.observableArrayList();
    private int pageNum = 0;
    private int pageSize = 25;
    DzProject searchParame = new DzProject();

    // @FXML
    /*fxml文件中声明controller才能initialize()初始化*/
    @FXML
    public void initialize() {
        qySearch.getItems().addAll(QY);
        System.out.println("项目页面初始化中");
        configTableView();

        pagination.setPageFactory((Integer pageIndex) -> {
                    this.pageNum = pageIndex + 1;
                    PageInfo<DzProject> pageInfo = showData();
                    pagination.setPageCount(pageInfo.getPages());
                    return new Label();
                }
        );

        Callback<TableColumn<DzProject, CheckBox>, TableCell<DzProject, CheckBox>>
                selectCellFactory =
                new Callback<TableColumn<DzProject, CheckBox>, TableCell<DzProject, CheckBox>>() {
                    @Override
                    public TableCell call(TableColumn p) {
                        TableCell cell = new TableCell<DzProject, CheckBox>() {
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
                                                    for (DzProject DzProject : projectTable
                                                            .getItems()) {

                                                        if (!DzProject.getCb().isSelected()) {
                                                            booSelectAll = false;
                                                            break;
                                                        }
                                                    }

                                                    ObservableList<DzProject> list = projectTable.getItems();
                                                    selectProject = new ArrayList<>();
                                                    for (DzProject o : list) {
                                                        if (o.getCb().isSelected()) {
                                                            selectProject.add(o);
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


        projectTable.setRowFactory(tableView -> {
            final TableRow<DzProject> row = new TableRow<>();
            final ContextMenu rowMenu = new ContextMenu();
            MenuItem removeItem = new MenuItem("Delete");
            removeItem.setOnAction(e -> {
                projectTable.getItems().remove(row.getItem());
            });

            rowMenu.getItems().addAll(removeItem);
            row.contextMenuProperty().bind(
                    Bindings.when(Bindings.isNotNull(row.itemProperty()))
                            .then(rowMenu)
                            .otherwise((ContextMenu) null));

            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    System.out.println("Double click on " + row.getItem().getProjectName());
                    try {
                        showDetail(row.getItem());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            return row;
        });
    }

    private void showDetail(DzProject dzProject) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/project-detail.fxml"));
        loader.setControllerFactory(c -> {
            DetailController controller = new DetailController();
            DetailController.presentProject = dzProject;
            DetailController.isEditable = false;
            return controller;
        });
        Node root = loader.load();
        Scene scene = new Scene((Parent) root);
        Stage stage = new Stage();
        stage.setOnCloseRequest(e -> {
            e.consume();
            DetailController.presentProject = null;
            DetailController.isEditable = true;
            stage.close();
        });
        stage.setScene(scene);
        stage.show();
    }

    /**
     * 配置表格，绑定表格的每列
     */
    private void configTableView() {
        drillNumCol.setCellValueFactory(new PropertyValueFactory<DzProject, String>("drillNum"));
        hjTimeCol.setCellValueFactory(new PropertyValueFactory<DzProject, Date>("hjTime"));
        hjpzbhCol.setCellValueFactory(new PropertyValueFactory<DzProject, String>("hjpzbh"));
        orderCol.setCellValueFactory(new PropertyValueFactory<DzProject, String>("order"));
        projectNameCol.setCellValueFactory(new PropertyValueFactory<DzProject, String>("projectName"));
        qyCol.setCellValueFactory(new PropertyValueFactory<DzProject, String>("qy"));
        rkTimeCol.setCellValueFactory(new PropertyValueFactory<DzProject, Date>("rkTime"));
        xcTimeCol.setCellValueFactory(new PropertyValueFactory<DzProject, Date>("xcTime"));
        projectTable.setItems(list);
    }


    @FXML
    void clickDeleteProjectButton(MouseEvent event) {
        StringBuilder projectNameList = new StringBuilder();
        StringBuilder forbidDeleteList = new StringBuilder();
        StringBuilder deletedList = new StringBuilder();
        for (DzProject project : selectProject) {
            projectNameList.append(project.getProjectName()).append("\n");
        }
        Boolean isDelete = FxUtils.alert("是否删除以下项目:\n" + projectNameList, Alert.AlertType.CONFIRMATION);
        if (isDelete) {
            for (DzProject project : selectProject) {
                DzDrill dzDrill = new DzDrill();
                dzDrill.setProjectId(project.getProjectId());
                DrillPageVo drillPageVo = BeanUtil.getBean(IServiceDzDrillService.class).queryByPage(dzDrill, 1, 10);
                if (!drillPageVo.getData().isEmpty()) {
                    forbidDeleteList.append(project.getProjectName()).append("\n");
                } else {
                    int i = BeanUtil.getBean(IServiceDzProjectService.class).deleteByPrimaryKey(project.getProjectId());
                    if (i > 0) {
                        deletedList.append(project.getProjectName()).append("\n");
                    }
                }

            }
            if (!forbidDeleteList.isEmpty()) {
                FxUtils.alert("以下项目钻孔点不为0，无法删除:\n" + forbidDeleteList, Alert.AlertType.NONE);
            }
            if (!deletedList.isEmpty()) {
                FxUtils.alert("已删除以下项目:\n" + deletedList, Alert.AlertType.NONE);
            }

        }
        showData();
    }

    @FXML
    void clickExportProjectButton(MouseEvent event) {

    }

    @FXML
    void clickImportProjectButton(MouseEvent event) {

    }

    @FXML
    void clickInsertProjectButton(MouseEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/project-detail.fxml"));
        DetailController.presentProject = null;
        DetailController.isEditable = true;
        DetailController.tableItems=projectTable.getItems();
        Parent root = loader.load();

        // loader.setControllerFactory(c -> {
        //     DetailController controller = new DetailController();
        //     DetailController.presentProject = null;
        //     DetailController.isEditable = true;
        //     controller.setTableItems(projectTable.getItems());
        //     Scene scene = new Scene(root);
        //     Stage stage = new Stage();
        //     stage.setScene(scene);
        //     stage.show();
        //     return controller;
        // });

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        // DetailController controller = loader.getController();
        // controller.setTableItems(projectTable.getItems());


    }

    @FXML
    void clickSearchButton(MouseEvent event) {
        searchParame.setProjectName(projectNameSearch.getText());
        searchParame.setQy(qySearch.getValue());
        searchParame.setHjpzbh(hjpzbhSearch.getText());
        searchParame.setFgtzdm(fgtzdmSearch.getText());
        if (cjTimeSearch.getValue() != null) {
            searchParame.setXcTime(Date.from(cjTimeSearch.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        }
        if (hjTimeSearch.getValue() != null) {
            searchParame.setHjTime(Date.from(hjTimeSearch.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        }
        if (rkTimeSearch.getValue() != null) {
            searchParame.setRkTime(Date.from(rkTimeSearch.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        }
        searchParame.setHjdw(hjdwSearch.getText());
        searchParame.setZlxcdw(zlxcdwSearch.getText());
        showData();
    }


    /**
     * 展示数据
     */
    protected PageInfo<DzProject> showData() {
        ProjectPageVo projectPageVo = generateDate();
        if (projectPageVo.getData() != null) {
            dzProjectList = generateDate().getData();
        }
        // if (projectPageVo.getData() != null) {
        //     dzProjectList = projectPageVo.getData();
        // }
        for (int i = 1; i < dzProjectList.size() + 1; i++) {
            DzProject dzProject = dzProjectList.get(i - 1);
            dzProject.setOrder(String.valueOf(i));
            Long aLong = BeanUtil.getBean(IServiceDzDrillService.class).querySumNum(dzProject.getProjectId());
            dzProject.setDrillNum(String.valueOf(aLong));
        }
        list.setAll(dzProjectList);
        hjTimeCol.setCellFactory(column -> {
            TableCell<DzProject, Date> cell = new TableCell<DzProject, Date>() {
                private SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        if (item == null) {
                            setText(null);
                        } else {
                            setText(format.format(item));
                        }
                    }
                }
            };

            return cell;
        });
        rkTimeCol.setCellFactory(column -> {
            TableCell<DzProject, Date> cell = new TableCell<DzProject, Date>() {
                private SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss");

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        if (item == null) {
                            setText(null);
                        } else {
                            setText(format.format(item));
                        }
                    }
                }
            };

            return cell;
        });
        xcTimeCol.setCellFactory(column -> {
            TableCell<DzProject, Date> cell = new TableCell<DzProject, Date>() {
                private SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {

                    } else {
                        if (item == null) {
                            setText(null);
                        } else {
                            setText(format.format(item));
                        }
                    }
                }
            };

            return cell;
        });
        projectTable.setItems(list);
        return projectPageVo.getPageInfo();
    }

    /**
     * 生成数据
     *
     * @return
     */
    private ProjectPageVo generateDate() {
        return BeanUtil.getBean(IServiceDzProjectService.class).selectProjectList(searchParame, pageNum, pageSize);
    }
}
