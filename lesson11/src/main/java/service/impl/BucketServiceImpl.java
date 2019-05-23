package service.impl;

import dao.BucketDAO;
import dao.impl.BucketDAOImpl;
import domain.Bucket;
import org.apache.log4j.Logger;
import service.BucketService;
import java.sql.SQLException;
import java.util.List;

public class BucketServiceImpl implements BucketService {

    private BucketDAO bucketDAO;
    private  static Logger LOGGER = Logger.getLogger(BucketServiceImpl.class);
    private static BucketServiceImpl bucketService;

    private BucketServiceImpl() {
        try {
            this.bucketDAO = new BucketDAOImpl();
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
            LOGGER.error(e);
        }
    }

    public static BucketService getBucketService(){
        if(bucketService == null){
            bucketService = new BucketServiceImpl();
        }
        return bucketService;
    }

    @Override
    public Bucket create(Bucket bucket){
           return bucketDAO.create(bucket);

    }

    @Override
    public Bucket read(Integer id){
        return bucketDAO.read(id);
    }

    @Override
    public Bucket update(Bucket bucket) {
        return bucketDAO.update(bucket);
    }

    @Override
    public void delete(Integer id){
        bucketDAO.delete(id);
    }

    @Override
    public List<Bucket> readAll() {
        return bucketDAO.readAll();
    }
}
