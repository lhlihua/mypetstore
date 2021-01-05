package org.csu.mypetstore.persistence.Impl;

import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.persistence.DBUtil;
import org.csu.mypetstore.persistence.ProductDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private static final String GETPRODUCTLISTBYCATEGORYID = "SELECT PRODUCTID ,NAME , DESCN AS" +
            " description , CATEGORY AS categoryId FROM product WHERE CATEGORY = ?";
    private static final String GETPRODUCTBYPRODUCTID = "SELECT PRODUCTID ,NAME , DESCN AS" +
            "description , CATEGORY AS categoryId FROM product WHERE PRODUCTID = ?";
    private static final String SEARCHPRODUCTLIST = "SELECT PRODUCTID,NAME,DESCN as description,CATEGORY as categoryId from PRODUCT WHERE lower(name) like ?";
    @Override
    public List<Product> getProductListByCategory(String categoryId) {
        List<Product>productList = new ArrayList<>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GETPRODUCTLISTBYCATEGORYID);
            preparedStatement.setString(1,categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Product product = new Product();
                getResultSetIntoProduct(product , resultSet);
                productList.add(product);
            }
            DBUtil.closePreparedStatementETC(connection,preparedStatement,resultSet);
        }catch (Exception e){
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public Product getProduct(String productId) {
        Product product = null;
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GETPRODUCTBYPRODUCTID);
            preparedStatement.setString(1,productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                product = new Product();
                getResultSetIntoProduct(product,resultSet);
            }
            DBUtil.closePreparedStatementETC(connection,preparedStatement,resultSet);
        }catch (Exception e){
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> searchProductList(String keywords){
        List<Product>productList = new ArrayList<>();
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SEARCHPRODUCTLIST);
            preparedStatement.setString(1,keywords);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Product product = new Product();
                getResultSetIntoProduct(product,resultSet);
                productList.add(product);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return productList;
    }

    private void getResultSetIntoProduct(Product product , ResultSet resultSet) throws SQLException {
        product.setProductId(resultSet.getString(1));
        product.setName(resultSet.getString(2));
        product.setDescription(resultSet.getString(3));
        product.setCategoryId(resultSet.getString(4));
    }
}
