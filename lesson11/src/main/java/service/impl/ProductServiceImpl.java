package service.impl;

import dao.ProductDAO;
import dao.impl.ProductDAOImpl;
import domain.Product;
import org.apache.log4j.Logger;
import service.ProductService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


public class ProductServiceImpl implements ProductService {
    private ProductDAO productDAO;
    private  static Logger LOGGER = Logger.getLogger(ProductServiceImpl.class);
    private static ProductServiceImpl productService;

    private ProductServiceImpl() {
        try {
            this.productDAO = new ProductDAOImpl();
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
            LOGGER.error(e);
        }
    }

    public static ProductService getProductService(){
        if(productService == null){
            productService =  new ProductServiceImpl();
        }

        return productService;
    }

    @Override
    public Product create(Product product){
        return productDAO.create(product);
    }

    @Override
    public Product read(Integer id){
        return productDAO.read(id);
    }

    @Override
    public Product update(Product product) {
        return productDAO.update(product);
    }

    @Override
    public void delete(Integer id){
        productDAO.delete(id);
    }

    @Override
    public List<Product> readAll() {
        return productDAO.readAll();
    }

    @Override
    public Map<Integer, Product> readAllMap() {
        return  readAll().stream().collect(Collectors.toMap(Product::getId, Function.identity()));
    }
}
