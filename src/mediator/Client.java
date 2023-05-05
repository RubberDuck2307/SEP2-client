package mediator;

import model.*;

import java.beans.PropertyChangeSupport;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Client implements ClientInterface {

    private RemoteModel model;

    public Client() throws MalformedURLException, NotBoundException, RemoteException {
        model = (RemoteModel) Naming.lookup("rmi://localhost:1099/Case");
    }


    @Override
    public TaskList getAllTasksOfProject(Long id) {
        try {
            return model.getAllTasksOfProject(id);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void assignWorkerToTask(Integer workingNumber, Long taskID){
        try
        {
            model.assignWorkerToTask(workingNumber, taskID);
        }
        catch (RemoteException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeList getEmployeesOfTask(Long taskId) {
        try {
            return model.getEmployeesOfTask(taskId);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProjectList getAllProjectsByWorkingNumber(Integer workingNumber){
        try {
            return model.getAllProjectsByWorkingNumber(workingNumber);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override public EmployeeList getEmployeesAssignedToManager(
        int managerNumber)
    {
        try
        {
            return model.getEmployeesAssignedToManager(managerNumber);
        }
        catch (RemoteException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeWorkerFromTask(Integer workingNumber, Long taskID) {
        try {
            model.removeWorkerFromTask(workingNumber, taskID);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeList getAllEmployeesAssignedToProject(Long projectId) {
        try {
            return model.getAllEmployeesAssignedToProject(projectId);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override public void saveProject(Project project)
    {
        try
        {
            model.saveProject(project);
        }
        catch (RemoteException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override public void saveTask(Task task)
    {
        try
        {
            model.saveTask(task);
        }
        catch (RemoteException e)
        {
            throw new RuntimeException(e);
        }
    }

    public String hello(){
        try {
            return model.hello();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override public void updateTask(Task task)
    {
        try
        {
            model.updateTask(task);
        }
        catch (RemoteException e)
        {
            throw new RuntimeException(e);
        }
    }
}
