package com.ambersu.springbootjasperreport.dao.impl;

import com.ambersu.springbootjasperreport.dao.ProductDao;
import com.ambersu.springbootjasperreport.model.Product;
import com.ambersu.springbootjasperreport.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Product> getProducts() {
        String sql = "select product_id, product_name, category, image_url, price, stock, description, created_date, last_modified_date " +
                "from product " +
                "where 1=1 ";
        Map<String, Object> map = new HashMap<>();

        //sql = addFilteringSql(sql, map, productQueryParams);

        //sql = sql + " order by " + productQueryParams.getOrderBy() + " " + productQueryParams.getSort();

        //sql = sql + " limit :limit offset :offset ";
        //map.put("limit", productQueryParams.getLimit());
        //map.put("offset", productQueryParams.getOffset());

        return namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
    }
}
