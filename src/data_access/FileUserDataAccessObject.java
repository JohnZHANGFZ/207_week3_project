package data_access;

import entity.User;
import use_case.signup.SignupUserDataAccessInterface;

public class FileUserDataAccessObject implements SignupUserDataAccessInterface {
    @Override
    public boolean existByName(String identifier) {
        return false;
    }

    @Override
    public void save(User user) {

    }
}
