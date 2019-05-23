package dao.impl;

import dao.ProductDAO;
import domain.Product;
import org.apache.log4j.Logger;
import utils.ConnectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private static String readAll = "select * from product";
    private static String create = "insert into product (name, description, price) values (?,?,?)";
    private static String readById = "select * from product where id = ?";
    private static String updateById = "update product set name = ?, description = ?, price = ? where id = ?";
    private static String deleteById = "delete from product where id = ?";

    private  static Logger LOGGER = Logger.getLogger(ProductDAOImpl.class);

    private Connection connection;
    private PreparedStatement preparedStatement;

    public ProductDAOImpl() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        this.connection = ConnectionUtils.openConnection();
    }

    @Override
    public Product create(Product product) {
        try {
            preparedStatement = connection.prepareStatement(create, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            product.setId(rs.getInt(1));

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return product;
    }

    @Override
    public Product read(Integer id) {
        Product product = null;
        try {
            preparedStatement = connection.prepareStatement(readById);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            Double price = resultSet.getDouble("price");


            product = new Product(id, name, description, price);

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return product;
    }

    @Override
    public Product update(Product product) {
        try {
            preparedStatement = connection.prepareStatement(updateById);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getId());


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
    public List<Product> readAll() {
        List<Product> products = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(readAll);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Integer productId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                Double price = resultSet.getDouble("price");


                Product product = new Product(productId, name, description, price);
                products.add(product);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return products;
    }
}
