package viewmodel.WorkerView;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import model.*;
import viewmodel.ProjectView.ProjectsTable;
import viewmodel.ViewModel;
import viewmodel.ViewModelWithNavigationMenu;
import viewmodel.ViewState;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;

public class WorkerHomeViewModel extends ViewModelWithNavigationMenu {
    private final ObservableList<ProjectsTable> currentProjectsTable;
    private final ObservableList<TasksTableForWorkerProfile> tasksTable;

    private StringProperty taskTitle;
    private StringProperty taskStatus;
    private StringProperty taskProjectName;
    private TaskList taskList;
    private final StringProperty workerName;
    private StringProperty message;
    private ObservableList<NotificationTable> notificationList;

    private StringProperty workerName2;
    private StringProperty workerRole;
    private StringProperty workerDateOfBirth;
    private StringProperty workerPhoneNumber;
    private StringProperty workerEmail;
    private StringProperty workerManagers;

    public WorkerHomeViewModel(Model model) {
        super(model);

        this.workerName = new SimpleStringProperty();

        this.currentProjectsTable = FXCollections.observableArrayList();
        this.workerRole = new SimpleStringProperty();
        this.workerDateOfBirth = new SimpleStringProperty();
        this.workerPhoneNumber = new SimpleStringProperty();
        this.workerEmail = new SimpleStringProperty();
        this.workerManagers = new SimpleStringProperty();
        this.workerName2 = new SimpleStringProperty();

        this.tasksTable = FXCollections.observableArrayList();
        this.taskStatus = new SimpleStringProperty();
        this.taskTitle = new SimpleStringProperty();
        this.taskProjectName = new SimpleStringProperty();

        this.notificationList = FXCollections.observableArrayList();
        this.message = new SimpleStringProperty();


    }

    public void reset() {
        super.reset();
        load();
    }

    public void load() {
        super.load();
        Employee worker = model.getUser();
        workerName.setValue("Welcome back, " + worker.getName() + "!");
        workerName2.setValue(worker.getName());
        workerEmail.setValue(worker.getEmail());
        workerPhoneNumber.setValue(worker.getPhoneNumber());
        workerRole.setValue(worker.getRoleString());
        workerDateOfBirth.setValue(worker.getDob().toString());
        headline();

        EmployeeList managersList = model.getAllWorkersManagersByWorkerWorkingNumber(worker.getWorkingNumber());
        String managers = "";
        for (int i = 0; i < managersList.size(); i++) {
            if (i == 0) {
                managers = managersList.get(i).getName();
            } else {
                managers = managers + ", " + managersList.get(i).getName();
            }

        }

        if (Objects.equals(managers, "")) {
            workerManagers.setValue("This worker is not assigned to any manager.");
        } else {
            workerManagers.setValue(managers);
        }


        taskList = model.getAllTasksByUserId(worker.getWorkingNumber());
        tasksTable.clear();
        for (int i = 0; i < taskList.size(); i++) {
            tasksTable.add(new TasksTableForWorkerProfile(taskList.getTask(i), model.getProjectById(taskList.getTask(i).getProjectId())));
        }

        getNotifications();
    }

    private void getNotifications(){
        notificationList.clear();
        IdObjectList<AssignedToTaskNotification> notifications = model.getAssignedToTaskNotification(model.getUser().getWorkingNumber());
        for (int i = notifications.size() -1; i >= 0; i--)
        {
            notificationList.add(new NotificationTable(notifications.get(i)));
        }

    }

    public void headline() {
        Employee worker = model.getUser();
        LocalTime time = LocalTime.now();
        int hour = time.getHour();
        switch (hour) {
            case 6, 7, 8, 9, 10, 11 -> workerName.setValue("Good morning, " + worker.getName() + "!");
            case 12, 13, 14, 15, 16, 17 -> workerName.setValue("Good afternoon, " + worker.getName() + "!");
            case 18, 19, 20, 21, 22, 23 -> workerName.setValue("Good evening, " + worker.getName() + "!");
            case 0, 1, 2, 3, 4, 5 -> workerName.setValue("Good night, " + worker.getName() + "!");
        }
    }

    public void deleteNotification(String message) {
        //model.deleteTag(tag.getId());
    }

    public ObservableList<TasksTableForWorkerProfile> getTaskTable() {
        return tasksTable;
    }


    public StringProperty workerNameProperty() {
        return workerName;
    }

    public String getWorkerName2() {
        return workerName2.get();
    }

    public StringProperty workerName2Property() {
        return workerName2;
    }

    public String getWorkerRole() {
        return workerRole.get();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("notification")){
            getNotifications();
        }
        super.propertyChange(evt);

    }

    public StringProperty workerRoleProperty() {
        return workerRole;
    }

    public String getWorkerDateOfBirth() {
        return workerDateOfBirth.get();
    }

    public StringProperty workerDateOfBirthProperty() {
        return workerDateOfBirth;
    }

    public String getWorkerPhoneNumber() {
        return workerPhoneNumber.get();
    }

    public StringProperty workerPhoneNumberProperty() {
        return workerPhoneNumber;
    }

    public String getWorkerEmail() {
        return workerEmail.get();
    }

    public StringProperty workerEmailProperty() {
        return workerEmail;
    }

    public String getWorkerManagers() {
        return workerManagers.get();
    }

    public StringProperty workerManagersProperty() {
        return workerManagers;
    }

    public ObservableList<NotificationTable> getNotificationList() {
        return notificationList;
    }

    public boolean isWoman() {
        return Objects.equals(employee.getValue().getGender(), "F");
    }
    public void logOut() {
        model.logOut();
    }
}
