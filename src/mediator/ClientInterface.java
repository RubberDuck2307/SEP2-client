package mediator;

import model.*;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ClientInterface {

    TaskList getAllTasksOfProject(Long id) ;

    ProjectList getAllProjectsByWorkingNumber(Integer workingNumber);
    void assignWorkerToTask(Integer workingNumber, Long taskID);
    EmployeeList getEmployeesAssignedToManager(int managerNumber);
    void removeWorkerFromTask(Integer workingNumber, Long taskID);
    EmployeeList getAllEmployeesAssignedToProject(Long projectId);
    void assignEmployeesToTask(ArrayList<Integer> employeeWorkingNumbers, Long TaskID);
    EmployeeList getAllProjectManagers();
    EmployeeList getAllWorkers();

    void assignEmployeeToProject(Integer workingNumber, Long projectID);
    void removeEmployeeFromProject(Integer workingNumber, Long projectID);
    void saveProject(Project project);
    void unassignEmployeesFromTask(ArrayList<Integer> employeeWorkingNumbers, Long TaskID);
    Long saveTask(Task task);

    void assignWorkerToManager(int managerNumber, int workerNumber);
    void removeWorkerFromManager(int managerNumber, int workerNumber);


    Integer saveEmployee(Employee employee, String password);


    Employee login(UserProfile userProfile);

    EmployeeList getEmployeesOfTask(Long taskId);
    String hello();
    void updateTask(Task task);
    EmployeeList getAllEmployees();
    Employee getEmployeeByWorkingNumber(int workingNumber);
}
