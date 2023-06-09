package view.ViewControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import model.Employee;
import model.EmployeeRole;
import view.ViewController;
import view.ViewHandler;
import viewmodel.EmployeeView.AssignWorkersToProjectManagerViewModel;
import viewmodel.ViewModel;
import viewmodel.WorkersWithCheckboxTable;

public class AssignWorkersToProjectManagerViewController extends ViewControllerWithNavigationMenu
{
    
    public Label nameL;
    public Label workingNumberL;
    @FXML public ImageView avatarPic;
    @FXML
    private TableView<WorkersWithCheckboxTable> workersTable;

    @FXML
    private TableColumn<WorkersWithCheckboxTable, String> numberColumn;
    @FXML
    private TableColumn<WorkersWithCheckboxTable, String> nameColumn;
    @FXML
    private Label employeeName;
    @FXML
    public TableColumn<WorkersWithCheckboxTable, CheckBox> checkboxColumn;
    private Region root;
    private AssignWorkersToProjectManagerViewModel viewModel;
    private ViewHandler viewHandler;
    @FXML
    private ImageView bellImage;
    @FXML
    private HBox projectsHbos;
    
    @Override
    public void init(ViewHandler viewHandler, ViewModel viewModel, Region root)
    {
        this.root = root;
        this.viewHandler = viewHandler;
        this.viewModel = (AssignWorkersToProjectManagerViewModel) viewModel;
        this.viewModel.load();
        super.init(this.viewModel, viewHandler, bellImage, avatarPic, nameL, workingNumberL, projectsHbos);
        employeeName.textProperty().bindBidirectional(this.viewModel.managerNameProperty());

        setUpTable();
        fillInTable();

        setWindow(this.viewModel.getEmployee().getRole());

    }

    private void setUpTable(){
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        numberColumn.setCellValueFactory(cellData -> cellData.getValue().getNumberProperty());
        PropertyValueFactory<WorkersWithCheckboxTable, CheckBox> checkbox = new PropertyValueFactory("checkbox");
        checkboxColumn.setCellValueFactory(checkbox);
        checkboxColumn.setStyle("-fx-alignment: CENTER;");
    }

    private void fillInTable(){
        ObservableList<WorkersWithCheckboxTable> workerTable = FXCollections.observableArrayList();
        for (int i = 0; i < this.viewModel.getEmployees().size(); i++)
        {
            Employee employee = this.viewModel.getEmployees().get(i);
            workerTable.add(new WorkersWithCheckboxTable(employee));
            CheckBox checkBox = new CheckBox(" ");
            checkBox.setId("checklist");
            checkBox.setOnAction(e ->
            {
                assignEmployee(employee);
            });
            checkBox.setSelected(this.viewModel.isAssigned(employee));
            workerTable.get(i).setCheckbox(checkBox);
        }

        workersTable.setItems(workerTable);
    }
    public void assignEmployee(Employee employee)
    {
        viewModel.assignEmployee(employee);
    }
    @FXML
    public void backButtonClick()
    {
        viewHandler.openLastWindow();
    }
    
    @Override
    public Region getRoot()
    {
        return root;
    }

    @Override public void reset()
    {
        viewModel.reset();
        setWindow(this.viewModel.getEmployee().getRole());
        fillInTable();
    }

}
