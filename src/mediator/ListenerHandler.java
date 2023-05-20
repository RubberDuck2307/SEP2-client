package mediator;

import javafx.scene.control.Alert;
import model.Employee;
import model.EmployeeRole;
import util.NamedPropertyChangeSubject;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.util.regex.Pattern;

public class ListenerHandler implements NamedPropertyChangeSubject {

    private RemoteModel model;
    private GeneralListener<String, String> listener;
    private PropertyChangeSupport propertyChangeSupport;

    public ListenerHandler(RemoteModel model, GeneralListener<String, String> listener) {
        this.model = model;
        this.listener = listener;
        propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public void addServerListener(Employee employee) throws RemoteException {
        if (employee != null) {
            switch (employee.getRole()) {
                case HR:
                    model.addListener(listener, "00|forgetPassword|notification");
                    break;
                case WORKER:
                    model.addListener(listener, employee.getWorkingNumber() + "|assignedToTask|notification");
                    break;
                case PROJECT_MANAGER:
                    model.addListener(listener, employee.getWorkingNumber() + "|assignedToProject|notification");
                    break;
            }
        }
    }


    public void removeServerListener() {

        try {
            model.removeListener(listener);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }


    }

    public void handlePropertyChange(ObserverEvent<String, String> event) {
        String[] eventName = event.getPropertyName().split(Pattern.quote("|"));
        if (eventName[2].equals("notification"))
            handleNotification();
    }

    public void handleNotification() {
        propertyChangeSupport.firePropertyChange("notification", 0, 1);
    }

    @Override
    public void addListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(propertyName, listener);

    }

    @Override
    public void removeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
    }
}