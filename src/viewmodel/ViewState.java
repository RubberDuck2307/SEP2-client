package viewmodel;

import model.Employee;
import model.Project;
import model.Task;

public class ViewState
{
  private Project project;
  private Task task;
  private Employee employee;

  public ViewState(){
    this.project = null;
    this.task=null;
    this.employee = null;
  }

  public Task getTask()
  {
    return task;
  }

  public Project getProject()
  {
    return project;
  }

  public Employee getEmployee()
  {
    return employee;
  }

  public void setProject(Project project)
  {
    this.project = project;
  }

  public void setTask(Task task)
  {
    this.task = task;
  }

  public void setEmployee(Employee employee)
  {
    this.employee = employee;
  }
}
