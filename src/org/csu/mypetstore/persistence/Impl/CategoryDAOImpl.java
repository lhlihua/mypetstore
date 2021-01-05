package org.csu.mypetstore.persistence.Impl;

import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.persistence.CategoryDAO;
import org.csu.mypetstore.persistence.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    private static final String GET_CATEGORY_LIST = "SELECT CATID AS categoryId , name , DESCN AS description FROM CATEGORY";
    private static final String GET_CATEGORY = "SELECT CATID AS categoryId , name , DESCN AS description FROM CATEGORY WHERE CATID = ?";

    @Override
    public List<Category> getCategoryList() {
        List<Category>categoryList = new ArrayList<>();
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CATEGORY_LIST);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Category category = new Category();
                getResultSetIntoCategory(category,resultSet);
                categoryList.add(category);
            }
            DBUtil.closePreparedStatementETC(connection,preparedStatement,resultSet);
        }catch (Exception e){
            e.printStackTrace();
        }
        return categoryList;
    }

    @Override
    public Category getCategory(String categoryId) {
        Category category = null;
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CATEGORY);
            preparedStatement.setString(1,categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                category = new Category();
                getResultSetIntoCategory(category,resultSet);
            }
            DBUtil.closePreparedStatementETC(connection,preparedStatement,resultSet);
        }catch (Exception e){
            e.printStackTrace();
        }
        return category;
    }

    private void getResultSetIntoCategory(Category category , ResultSet resultSet) throws SQLException {
        category.setCategoryId(resultSet.getString(1));
        category.setName(resultSet.getString(2));
        category.setDescription(resultSet.getString(3));
    }
}
