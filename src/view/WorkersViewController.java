package view;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import viewmodel.TaskView.TasksTable;
import viewmodel.TaskView.WorkersTable;
import viewmodel.TasksViewModel;
import viewmodel.ViewModel;
import viewmodel.WorkerView.WorkersViewModel;

public class WorkersViewController implements ViewController
{
  @FXML public TableView<viewmodel.WorkerView.WorkersTable> workerTable;
  @FXML public TableColumn<viewmodel.WorkerView.WorkersTable, String> name;
  @FXML public TableColumn<viewmodel.WorkerView.WorkersTable, String> role;
  @FXML public TableColumn<viewmodel.WorkerView.WorkersTable, String> workingNumber;
  @FXML public TableColumn<viewmodel.WorkerView.WorkersTable, String> email;

  private Region root;
  private WorkersViewModel viewModel;
  private ViewHandler viewHandler;
  private ObservableList<WorkersTable> workersTables;


  @Override public void init(ViewHandler viewHandler, ViewModel viewModel,
      Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
    this.viewModel = (WorkersViewModel) viewModel;
    this.viewModel.load();
    name.setCellValueFactory(
        cellData -> cellData.getValue().getNameProperty());
    workingNumber.setCellValueFactory(
        cellData -> cellData.getValue().getNumberProperty());
    email.setCellValueFactory(
        cellData -> cellData.getValue().getEmailProperty());
    role.setCellValueFactory(
        cellData -> cellData.getValue().getRoleProperty());
    workerTable.setItems(((WorkersViewModel) viewModel).getWorkersTable());
  }

  @Override public Region getRoot()
  {
    return root;
  }
  public void openProjects(MouseEvent mouseEvent)
  {
  }

  public void createNewProfile(ActionEvent actionEvent)
  {
  }

  public void workerTableClick(MouseEvent mouseEvent)
  {
  }
}
