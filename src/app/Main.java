package app;

import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import interface_adapter.add_item.AddItemController;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.add_item.AddItemViewModel;
import interface_adapter.delete_item.DeleteItemViewModel;
import interface_adapter.inventory.InventoryViewModel;
import interface_adapter.recipe_information_getter.RecipeInfoViewModel;
import interface_adapter.recipe_result.ResultViewModel;
import interface_adapter.recipes_getter.GetRecipesViewModel;
import interface_adapter.restriction.RestrictionViewModel;
import interface_adapter.ViewManagerModel;
import use_case.login.LoginUserDataAccessInterface;
import view.LoggedInView;
import view.LoginView;
import view.SignupView;
import view.ViewManager;
import view.InventoryView;
import view.DetailsView;
import view.RestrictionView;
import view.ResultView;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.
        LoginViewModel loginViewModel = new LoginViewModel("Login");
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel("Logged In");
        SignupViewModel signupViewModel = new SignupViewModel("Signup");
        AddItemViewModel addItemViewModel = new AddItemViewModel("Add Item");
        DeleteItemViewModel deleteItemViewModel = new DeleteItemViewModel("Delete Item");
        InventoryViewModel inventoryViewModel = new InventoryViewModel("Inventory");
        RecipeInfoViewModel recipeInfoViewModel = new RecipeInfoViewModel("Recipe Info");
        ResultViewModel resultViewModel = new ResultViewModel("Result");
        GetRecipesViewModel getRecipesViewModel = new GetRecipesViewModel("Get Recipes");
        RestrictionViewModel restrictionViewModel = new RestrictionViewModel("Restriction");

        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./users.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel,
                signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel,
                loggedInViewModel, userDataAccessObject);
        views.add(loginView, loginView.viewName);

        LoggedInView loggedInView = new LoggedInView(loggedInViewModel);
        views.add(loggedInView, loggedInView.viewName);

        // TODO: instantiate the AddItemController later
        InventoryView inventoryView = new InventoryView(inventoryViewModel, addItemViewModel, new AddItemController());
        views.add(inventoryView, inventoryView.viewName);

        // TODO: detailsView waited to implement
        DetailsView detailsView = new DetailsView();
        views.add(detailsView, detailsView.viewName);

        // TODO: instantiate the AddItermController later
        RestrictionView restrictionView = new RestrictionView(restrictionViewModel, addItemViewModel, new AddItemController());
        views.add(restrictionView, restrictionView.viewName);

        ResultView resultView = new ResultView();
        views.add(resultView, resultView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}