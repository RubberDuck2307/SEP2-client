package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.EmployeeRole;
import viewmodel.LoginViewModel;
import viewmodel.ViewModel;

import java.awt.*;

public class LoginViewController implements ViewController {
    
    public Label headline;
    private ViewHandler viewHandler;
    private LoginViewModel viewModel;

    @FXML
    public TextField workingNumberField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;
    private Region root;


    @Override
    public void init(ViewHandler viewHandler, ViewModel viewModel, Region root) {
        this.viewHandler = viewHandler;
        this.viewModel = (LoginViewModel) viewModel;
        this.root = root;
        bind();

    }

    private void bind() {
        passwordField.textProperty().bindBidirectional(viewModel.passwordPropertyProperty());
        workingNumberField.textProperty().bindBidirectional(viewModel.workingNumberPropertyProperty());
        errorLabel.textProperty().bindBidirectional(viewModel.errorPropertyProperty());
        headline.textProperty().bind(viewModel.headlinePropertyProperty());
    }

    @Override
    public Region getRoot() {
        return root;
    }

    @Override
    public void reset() {
        viewModel.reset();
    }

    @FXML
    private void login() {
        if (viewModel.login()){
            EmployeeRole role = this.viewModel.getEmployeeProperty().getRole();
            switch (role) {
                case WORKER -> {
                    viewHandler.openView("workerHomePage");
                }
                case HR -> {
                    viewHandler.openView("home");
                }
                case PROJECT_MANAGER -> {
                    viewHandler.openView("home");
                }
                case MAIN_MANAGER -> {
                    viewHandler.openView("home");
                }
            }
        }
    }

}
