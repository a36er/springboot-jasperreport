package com.ambersu.springbootjasperreport.service.impl;

import com.ambersu.springbootjasperreport.dao.ProductDao;
import com.ambersu.springbootjasperreport.model.Product;
import com.ambersu.springbootjasperreport.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> getProducts() {

        return productDao.getProducts();
    }
}
