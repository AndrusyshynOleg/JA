package service.impl;

import dao.UserDAO;
import dao.impl.UserDAOImpl;
import domain.User;
import org.apache.log4j.Logger;
import service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO;
    private  static Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
    private static UserServiceImpl userService;

    private UserServiceImpl(){
        try {
            this.userDAO = new UserDAOImpl();
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
            LOGGER.error(e);
        }
    }

    public static UserService getUserService(){
        if(userService == null){
            userService = new UserServiceImpl();
        }
        return userService;
    }


    @Override
    public User create(User user){
        return userDAO.create(user);
    }

    @Override
    public User read(Integer id){
        return userDAO.read(id);
    }

    @Override
    public User update(User user) {
        return userDAO.update(user);
    }

    @Override
    public void delete(Integer id) {
        userDAO.delete(id);
    }

    @Override
    public List<User> readAll() {
        return userDAO.readAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }
}
