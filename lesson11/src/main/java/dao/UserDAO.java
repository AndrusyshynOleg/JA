package dao;

import domain.User;
import shared.AbstractCrud;

public interface UserDAO extends AbstractCrud<User> {
    User getUserByEmail(String email);
}
