package service;

import domain.Product;
import shared.AbstractCrud;

import java.util.Map;

public interface ProductService extends AbstractCrud<Product> {
    public Map<Integer, Product> readAllMap();
}
