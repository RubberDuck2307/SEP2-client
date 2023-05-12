package viewmodel.WorkerView;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import model.*;
import viewmodel.TaskView.TasksTable;
import viewmodel.ViewModel;
import viewmodel.ViewState;

import java.util.ArrayList;
import java.util.Objects;

public class WorkersViewModel implements ViewModel
{
  private StringProperty employeeName;
  private StringProperty employeeWorkingNumber;
  private ObjectProperty<Employee> employee;
  private ObjectProperty<Image> avatarPic;
  private Model model;
  private ViewState viewState;
  private ObservableList<WorkersTable> workersTables;
  private StringProperty name;
  private StringProperty role;
  private IntegerProperty number;
  private StringProperty email;
  private EmployeeList employeeList;

  public WorkersViewModel(Model model, ViewState viewState)
  {
    this.employeeName=new SimpleStringProperty();
    this.employeeWorkingNumber=new SimpleStringProperty();
    this.employee=new SimpleObjectProperty<>();
    this.model = model;
    this.viewState = viewState;
    this.workersTables = FXCollections.observableArrayList();
    this.name = new SimpleStringProperty();
    this.role = new SimpleStringProperty();
    this.number = new SimpleIntegerProperty();
    this.email = new SimpleStringProperty();
    this.avatarPic=new SimpleObjectProperty<>();
  }

  public void reset(){
    load();
  }
  public void load()
  {
    employee.setValue(model.getUser());
    setAvatarPicture();
    employeeName.setValue(model.getUser().getName());
    employeeWorkingNumber.setValue(model.getUser().getWorkingNumber().toString());
    employeeList = model.getAllEmployees();
    workersTables.clear();
    for (int i = 0; i < employeeList.size(); i++)
    {
      workersTables.add(new viewmodel.WorkerView.WorkersTable(employeeList.get(i)));
    }

  }
  public void chooseWorker(int workingNumber)
  {
    Employee employee = model.getEmployeeByWorkingNumber(workingNumber);
    viewState.setEmployee(employee);
    workersTables.clear();
  }
  public boolean isMainManager(int workingNumber){
    Employee employee = model.getEmployeeByWorkingNumber(workingNumber);
    return employee.getRole() == EmployeeRole.PROJECT_MANAGER;
  }

  public StringProperty getEmployeeName()
  {
    return employeeName;
  }





  public StringProperty getEmployeeWorkingNumber()
  {
    return employeeWorkingNumber;
  }
  public ObjectProperty<Employee> employeePropertyProperty() {
    return employee;
  }
  public Employee getEmployeeProperty() {
    return employee.get();
  }

  public ObservableList<viewmodel.WorkerView.WorkersTable> getWorkersTable(){return workersTables;}
  public boolean displayAddButton(){
    return employee.getValue().getRole() == EmployeeRole.HR;
  }
  public ObjectProperty<Image> avatarPicProperty()
  {
    return avatarPic;
  }
  public boolean isWoman(){
    return Objects.equals(employee.getValue().getGender(), "F");
  }
  public void setAvatarPicture(){
    if(isWoman()){
      avatarPic.setValue(new Image("/icons/woman-avatar.png"));
    }
    else{
      avatarPic.setValue(new Image("/icons/man-avatar.png"));
    }
  }
}
