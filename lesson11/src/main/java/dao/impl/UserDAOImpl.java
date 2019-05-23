package dao.impl;

import dao.UserDAO;
import domain.User;
import org.apache.log4j.Logger;
import utils.ConnectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static String readAll = "select * from user";
    private static String create = "insert into user (email, first_name, last_name, role, password) values (?,?,?,?,?)";
    private static String readById = "select * from user where id = ?";
    private static String readByEmail = "select * from user where email = ?";
    private static String updateById =
            "update user set email = ?, first_name = ?, last_name = ?, role = ? , password = ? where id = ?";
    private static String deleteById = "delete from user where id = ?";

    private  static Logger LOGGER = Logger.getLogger(UserDAOImpl.class);


    private Connection connection;
    private PreparedStatement preparedStatement;

    public UserDAOImpl() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        this.connection = ConnectionUtils.openConnection();
    }

    @Override
    public User create(User user) {
        try {
            preparedStatement = connection.prepareStatement(create, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            user.setId(rs.getInt(1));

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return user;

    }

@Override
public User read(Integer id) {
    User user  = null;
    try {
        preparedStatement = connection.prepareStatement(readById);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();
        String email = resultSet.getString("email");
        String first_name = resultSet.getString("first_name");
        String last_name = resultSet.getString("last_name");
        String role = resultSet.getString("role");
        String password = resultSet.getString("password");


        user = new User(id, email, first_name, last_name, role, password);

    } catch (SQLException e) {
        LOGGER.error(e);
    }

    return user;
        }

@Override
public User update(User user) {
    try {
        preparedStatement = connection.prepareStatement(updateById);
        preparedStatement.setString(1, user.getEmail());
        preparedStatement.setString(2, user.getFirstName());
        preparedStatement.setString(3, user.getLastName());
        preparedStatement.setString(4, user.getRole());
        preparedStatement.setString(5, user.getPassword());
        preparedStatement.setInt(6, user.getId());


        preparedStatement.executeUpdate();
    } catch (SQLException e) {
        LOGGER.error(e);
    }
    return null;
        }

@Override
public void delete(Integer id) {
    try {
        preparedStatement = connection.prepareStatement(deleteById);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    } catch (SQLException e) {
        LOGGER.error(e);
    }
        }

    @Override
    public List<User> readAll() {
        List<User> users = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(readAll);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int userId = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String role = resultSet.getString("role");
                String password = resultSet.getString("password");

                User user = new User(userId, email, first_name, last_name, role, password);
                users.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return users;
    }

    @Override
    public User getUserByEmail(String email) {
        User user  = null;
        try {
            preparedStatement = connection.prepareStatement(readByEmail);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            int userId = resultSet.getInt("id");
            String first_name = resultSet.getString("first_name");
            String last_name = resultSet.getString("last_name");
            String role = resultSet.getString("role");
            String password = resultSet.getString("password");


            user = new User(userId, email, first_name, last_name, role, password);

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return user;
    }
}
