package viewmodel;

import javafx.beans.property.*;
import javafx.scene.image.Image;
import model.Employee;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

public abstract class ViewModelWithNavigationMenu implements ViewModel, PropertyChangeListener {

    protected BooleanProperty notification;
    protected ObjectProperty<Image> avatarPic;
    protected ObjectProperty<Employee> employee;
    protected StringProperty employeeName;
    protected StringProperty employeeWorkingNumber;
    protected Model model;

    public ViewModelWithNavigationMenu(Model model) {
        this.model = model;
        this.avatarPic = new SimpleObjectProperty<>();
        this.employee = new SimpleObjectProperty<>();
        this.notification = new SimpleBooleanProperty(false);
        this.employeeName = new SimpleStringProperty();
        this.employeeWorkingNumber = new SimpleStringProperty();

        model.addNotificationListener(this);
    }

    public boolean isNotification() {
        return notification.get();
    }

    public BooleanProperty notificationProperty() {
        return notification;
    }

    public void reset() {
        notification.setValue(false);

    }

    public void load(){
        employee.setValue(model.getUser());
        employeeName.setValue(employee.getValue().getName());
        employeeWorkingNumber.setValue(employee.getValue().getWorkingNumber().toString());
        setAvatarPicture();
    }

    public ObjectProperty<Image> avatarPicProperty() {
        return avatarPic;
    }

    public boolean isWoman() {
        return Objects.equals(employee.getValue().getGender(), "F");
    }

    public void setAvatarPicture() {
        if (isWoman()) {
            avatarPic.setValue(new Image("/icons/woman-avatar.png"));
        } else {
            avatarPic.setValue(new Image("/icons/man-avatar.png"));
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("notification")) {
            notification.setValue(true);
        }
    }

    public Employee getEmployee() {
        return employee.get();
    }

    public ObjectProperty<Employee> employeeProperty() {
        return employee;
    }

    public String getEmployeeName() {
        return employeeName.get();
    }

    public StringProperty employeeNameProperty() {
        return employeeName;
    }

    public String getEmployeeWorkingNumber() {
        return employeeWorkingNumber.get();
    }

    public StringProperty employeeWorkingNumberProperty() {
        return employeeWorkingNumber;
    }
}
