import domain.Bucket;
import domain.Product;
import domain.User;
import service.BucketService;
import service.ProductService;
import service.UserService;
import service.impl.BucketServiceImpl;
import service.impl.ProductServiceImpl;
import service.impl.UserServiceImpl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        UserService userService = UserServiceImpl.getUserService();
        ProductService productService = ProductServiceImpl.getProductService();
        BucketService bucketService = BucketServiceImpl.getBucketService();
//just a comment
            userService.create(new User("test@test", "john", "dou", "human", "qwerty"));
            productService.create(new Product("iphone x", "ftgthfgthfhfgh nfh fh fg ", 1000.0));
            bucketService.create(new Bucket(1,1, new Date(Calendar.getInstance().getTime().getTime())));

    }
}
