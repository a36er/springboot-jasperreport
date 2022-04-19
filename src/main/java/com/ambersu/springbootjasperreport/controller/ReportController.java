package com.ambersu.springbootjasperreport.controller;

import com.ambersu.springbootjasperreport.model.Product;

import com.ambersu.springbootjasperreport.service.ProductService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class ReportController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/report" ,produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getProducts() throws FileNotFoundException, JRException {

        List<Product> productList = productService.getProducts();
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(productList, false);

        Map<String, Object> parameters = new HashMap<>();

        JasperReport compileReport = JasperCompileManager
                .compileReport(new FileInputStream("src/main/resources/Product.jrxml"));

        JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, parameters, beanCollectionDataSource);
        byte data[] = JasperExportManager.exportReportToPdf(jasperPrint);
        System.err.println(data);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=product.pdf");


        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(data);
    }

}
