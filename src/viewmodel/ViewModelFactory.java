package viewmodel;

import model.Model;
import view.ViewControllers.NoteVBOXController;
import viewmodel.NotesView.NotesViewModel;
import viewmodel.EmployeeView.*;
import viewmodel.ProjectView.AddProjectViewModel;
import viewmodel.ProjectView.EditProjectViewModel;
import viewmodel.ProjectView.ProjectsViewModel;
import viewmodel.TagsView.DeleteTagsViewModel;
import viewmodel.TaskView.AddTaskViewModel;
import viewmodel.TaskView.EditTaskViewModel;
import viewmodel.TaskView.TasksViewModel;

import viewmodel.WorkerView.ProjectManagerProfileViewModel;
import viewmodel.WorkerView.WorkersViewModel;
import viewmodel.WorkerView.*;

public class ViewModelFactory
{
    private Model model;
    private ForgotPasswordViewModel forgotPasswordViewModel;
    private TasksViewModel tasksViewModel;
    private ProjectsViewModel projectsViewModel;
    private AssignWorkersToTaskViewModel assignWorkersToTaskViewModel;
    private AssignEmployeesToProjectViewModel assignEmployeesToProjectViewModel;
    private AssignWorkersToProjectManagerViewModel assignWorkersToProjectManagerViewModel;
    private EditTaskViewModel editTaskViewModel;
    private AddTaskViewModel addTaskViewModel;
    private CreateUserProfileViewModel createUserProfileViewModel;
    private AddProjectViewModel addProjectViewModel;
    private WorkersViewModel workersViewModel;
    private ViewState viewState;
    private LoginViewModel loginViewModel;
    private ProjectManagerProfileViewModel projectManagerProfileViewModel;
    private HomeViewModel homeViewModel;
    private EditProjectViewModel editProjectViewModel;
    private HrAndMainManagerProfileViewModel hrAndMainManagerProfileViewModel;
    private WorkerProfileViewModel workerProfileViewModel;
    private NotesViewModel notesViewModel;
    private WorkerHomeViewModel workerHomeViewModel;
    private EditProfileViewModel editProfileViewModel;
    private ProjectManagerHomeViewModel projectManagerHomeViewModel;

    private DeleteTagsViewModel deleteTagsViewModel;
    private HrHomeViewModel hrHomeViewModel;
    private MainManagerHomeViewModel mainManagerHomeViewModel;
    public ViewModelFactory(Model model)
    {
        this.model = model;
        this.viewState = new ViewState();
        this.tasksViewModel = new TasksViewModel(model, viewState);
        this.projectsViewModel = new ProjectsViewModel(model, viewState);
        this.addTaskViewModel = new AddTaskViewModel(model, viewState);
        this.editTaskViewModel = new EditTaskViewModel(model, viewState);
        this.assignWorkersToTaskViewModel = new AssignWorkersToTaskViewModel(model, viewState);
        this.assignEmployeesToProjectViewModel = new AssignEmployeesToProjectViewModel(model, viewState);
        this.assignWorkersToProjectManagerViewModel = new AssignWorkersToProjectManagerViewModel(model, viewState);
        this.addProjectViewModel = new AddProjectViewModel(model);
        this.createUserProfileViewModel = new CreateUserProfileViewModel(model);
        this.loginViewModel = new LoginViewModel(model);
        this.workersViewModel = new WorkersViewModel(model, viewState);
        this.projectManagerProfileViewModel = new ProjectManagerProfileViewModel(model, viewState);
        this.homeViewModel = new HomeViewModel(model, viewState);
        this.editProjectViewModel = new EditProjectViewModel(model, viewState);
        this.hrAndMainManagerProfileViewModel = new HrAndMainManagerProfileViewModel(model,viewState);
        this.workerProfileViewModel = new WorkerProfileViewModel(model,viewState);
      this.notesViewModel = new NotesViewModel(model, viewState);
        //this.workerHomeViewModel= new WorkerHomeViewModel(model,viewState);
        this.workerHomeViewModel= new WorkerHomeViewModel(model);
        this.editProfileViewModel = new EditProfileViewModel(model,viewState);
        this.deleteTagsViewModel = new DeleteTagsViewModel(model);
        this.forgotPasswordViewModel = new ForgotPasswordViewModel(model);
        this.projectManagerHomeViewModel = new ProjectManagerHomeViewModel(model);
        this.hrHomeViewModel = new HrHomeViewModel(model, viewState);
        this.mainManagerHomeViewModel = new MainManagerHomeViewModel(model, viewState);
    }
    
    public Model getModel()
    {
        return model;
    }
    
    public ProjectsViewModel getProjectsViewModel()
    {
        return projectsViewModel;
    }

    public MainManagerHomeViewModel getMainManagerHomeViewModel()
    {
        return mainManagerHomeViewModel;
    }

    public HrHomeViewModel getHrHomeViewModel()
    {
        return hrHomeViewModel;
    }

    public ProjectManagerHomeViewModel getProjectManagerHomeViewModel()
    {
        return projectManagerHomeViewModel;
    }

    public WorkerProfileViewModel getWorkerProfileViewModel()
    {
        return workerProfileViewModel;
    }

    public HrAndMainManagerProfileViewModel getHrAndMainManagerProfileViewModel()
    {
        return hrAndMainManagerProfileViewModel;
    }

    public DeleteTagsViewModel getDeleteTagsViewModel() {
        return deleteTagsViewModel;
    }

    public EditProfileViewModel getEditProfileViewModel()
    {
        return editProfileViewModel;
    }

    public TasksViewModel getTasksViewModel()
    {
        return tasksViewModel;
    }
    
    public ProjectManagerProfileViewModel getProjectManagerProfileViewModel()
    {
        return projectManagerProfileViewModel;
    }
    
    public WorkersViewModel getWorkersViewModel()
    {
        return workersViewModel;
    }
    
    public AssignWorkersToTaskViewModel getAssignWorkersToTaskViewModel()
    {
        return assignWorkersToTaskViewModel;
    }
    
    public AssignEmployeesToProjectViewModel getAssignWorkersToProjectViewModel()
    {
        return assignEmployeesToProjectViewModel;
    }

    public AssignWorkersToProjectManagerViewModel getAssignWorkersToProjectManagerViewModel()
    {
        return assignWorkersToProjectManagerViewModel;
    }

    public AddTaskViewModel getAddTaskViewModel()
    {
        return addTaskViewModel;
    }
    
    public EditTaskViewModel getEditTaskViewModel()
    {
        return editTaskViewModel;
    }
    
    public AddProjectViewModel getAddProjectViewModel()
    {
        return addProjectViewModel;
    }
    
    public CreateUserProfileViewModel getCreateUserProfileViewModel()
    {
        return createUserProfileViewModel;
    }
    public LoginViewModel getLoginViewModel()
    {
        return loginViewModel;
    }
    
    public HomeViewModel getHomeViewModel()
    {
        return homeViewModel;
    }
  public NotesViewModel getNotesViewModel()
  {
    return notesViewModel;
  }
    public EditProjectViewModel getEditProjectViewModel()
    {
        return editProjectViewModel;
    }

    public WorkerHomeViewModel getWorkerHomeViewModel()
    {
        return workerHomeViewModel;
    }

    public ForgotPasswordViewModel getForgotPasswordViewModel() {
        return forgotPasswordViewModel;
    }
}