package view.ViewControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import model.Employee;
import view.ViewHandler;
import viewmodel.EmployeeView.AssignWorkersToTaskViewModel;
import viewmodel.ViewModel;
import viewmodel.WorkersWithCheckboxTable;

public class AssignWorkersToTaskViewController  extends ViewControllerWithNavigationMenu
{
    @FXML
    private Label nameL;
    @FXML
    private Label workingNumberL;
    @FXML public ImageView avatarPic;
    @FXML
    private TableView<WorkersWithCheckboxTable> workersTable;

    @FXML
    private TableColumn<WorkersWithCheckboxTable, String> numberColumn;
    @FXML
    private TableColumn<WorkersWithCheckboxTable, String> nameColumn;
    @FXML
    private Label projectName;
    @FXML
    private TableColumn<WorkersWithCheckboxTable, CheckBox> checkboxColumn;
    private Region root;
    private AssignWorkersToTaskViewModel viewModel;
    private ViewHandler viewHandler;
    private ObservableList<WorkersWithCheckboxTable> workerTableColumns = FXCollections.observableArrayList();
    @FXML private ImageView bellImage;
    @FXML private HBox projectsHbox;

    
    @Override
    public void init(ViewHandler viewHandler, ViewModel viewModel, Region root)
    {
        this.root = root;
        this.viewHandler = viewHandler;
        this.viewModel = (AssignWorkersToTaskViewModel) viewModel;
        this.viewModel.load();
        projectName.textProperty().bindBidirectional(this.viewModel.getTaskName());

        super.init(this.viewModel, viewHandler, bellImage, avatarPic , nameL, workingNumberL, projectsHbox);

        setUpTable();

        fillInTable();

        setWindow(this.viewModel.getEmployee().getRole());
    }

    private void setUpTable(){
        workerTableColumns = FXCollections.observableArrayList();
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        numberColumn.setCellValueFactory(cellData -> cellData.getValue().getNumberProperty());
        PropertyValueFactory<WorkersWithCheckboxTable, CheckBox> checkbox = new PropertyValueFactory("checkbox");
        checkboxColumn.setCellValueFactory(checkbox);
        checkboxColumn.setStyle("-fx-alignment: CENTER;");
        avatarPic.imageProperty().bindBidirectional(this.viewModel.avatarPicProperty());

        workersTable.setItems(workerTableColumns);
    }

    private void fillInTable(){
        workerTableColumns.clear();
        for (int i = 0; i < this.viewModel.getWorkersOfTask().size(); i++)
        {
            Employee employee = this.viewModel.getWorkersOfTask().get(i);
            workerTableColumns.add(new WorkersWithCheckboxTable(employee));
            CheckBox checkBox = new CheckBox(" ");
            checkBox.setId("checklist");
            checkBox.setOnAction(e ->
            {
                assignEmployee(employee);
            });
            checkBox.setSelected(this.viewModel.isAssigned(employee));
            workerTableColumns.get(i).setCheckbox(checkBox);
        }
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

    @Override
    public void reset() {
        viewModel.reset();
        fillInTable();
        setWindow(this.viewModel.getEmployee().getRole());
    }

}
