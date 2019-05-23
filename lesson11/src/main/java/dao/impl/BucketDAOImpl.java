package dao.impl;

import dao.BucketDAO;
import domain.Bucket;
import org.apache.log4j.Logger;
import utils.ConnectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BucketDAOImpl implements BucketDAO {
    private static String readAll = "select * from bucket";
    private static String create = "insert into bucket (user_id, product_id, purchase_date) values (?,?,?)";
    private static String readById = "select * from bucket where id = ?";
    private static String deleteById = "delete from bucket where id = ?";

    private  static Logger LOGGER = Logger.getLogger(BucketDAOImpl.class);

    private Connection connection;
    private PreparedStatement preparedStatement;

    public BucketDAOImpl() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        this.connection = ConnectionUtils.openConnection();
    }

    @Override
    public Bucket create(Bucket bucket) {
        try {
        preparedStatement = connection.prepareStatement(create, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, bucket.getUserId());
        preparedStatement.setInt(2, bucket.getProductId());
        preparedStatement.setDate(3, new Date(bucket.getPurchaseDate().getTime()));
        preparedStatement.executeUpdate();

        ResultSet rs = preparedStatement.getGeneratedKeys();
        rs.next();
        bucket.setId(rs.getInt(1));
 throw new SQLException();
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return bucket;
    }

    @Override
    public Bucket read(Integer id)  {
        Bucket bucket = null;
        try {
        preparedStatement = connection.prepareStatement(readById);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();
            Integer userId = resultSet.getInt("user_id");
            Integer productId = resultSet.getInt("product_id");
            java.util.Date purchaseDate = resultSet.getDate("purchase_date");

        bucket = new Bucket(id, userId, productId, purchaseDate);

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return bucket;
    }

    @Override
    public Bucket update(Bucket bucket) {
        throw new IllegalStateException("there is no update for bucket");

    }

    @Override
    public void delete(Integer id){
        try {
            preparedStatement = connection.prepareStatement(deleteById);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public List<Bucket> readAll() {
        List<Bucket> buckets = new ArrayList<>();
        try {
        preparedStatement = connection.prepareStatement(readAll);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Integer bucketId = resultSet.getInt("id");
            Integer userId = resultSet.getInt("user_id");
            Integer productId = resultSet.getInt("product_id");
            java.util.Date purchaseDate = resultSet.getDate("purchase_date");

           Bucket bucket = new Bucket(bucketId, userId, productId, purchaseDate);
           buckets.add(bucket);
        }
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return buckets;
    }
}
