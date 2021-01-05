package org.csu.mypetstore.persistence.Impl;

import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.persistence.DBUtil;
import org.csu.mypetstore.persistence.ItemDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemDAOImpl implements ItemDAO {
    private static String GETITEMLISTBYPRUDUCT = "SELECT I.ITEMID,LISTPRICE,UNITCOST,SUPPLIER AS supplierId,I.PRODUCTID AS productId,NAME AS productName,DESCN AS productDescription,CATEGORY AS categoryId,STATUS,ATTR1 AS attribute1,ATTR2 AS attribute2,ATTR3 AS attribute3,ATTR4 AS attribute4,ATTR5 AS attribute5 FROM ITEM I, PRODUCT P WHERE P.PRODUCTID = I.PRODUCTID AND I.PRODUCTID = ?";
    private static final String GETITEM = "select I.ITEMID,LISTPRICE,UNITCOST,SUPPLIER AS supplierId,I.PRODUCTID AS productId," +
            "NAME AS productName,DESCN AS productDescription,CATEGORY AS CategoryId,STATUS,ATTR1 AS attribute1,ATTR2 AS " +
            "attribute2,ATTR3 AS attribute3,ATTR4 AS attribute4,ATTR5 AS attribute5,QTY AS quantity from ITEM I, " +
            "INVENTORY V, PRODUCT P where P.PRODUCTID = I.PRODUCTID and I.ITEMID = V.ITEMID and I.ITEMID=?";
    private static final String UPDATEINVENTTORYQUANTITY = "UPDATE INVENTORY SET QTY = QTY - ? WHERE ITEMID = ?";
    private static final String GETINVENTORYQUANTITY = "SELECT QTY AS QUANTITY FROM INVENTORY WHERE ITEMID = ?";

    //更新库存数量
    @Override
    public void updateInventoryQuantity(Map<String, Object> param) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATEINVENTTORYQUANTITY);
            String ItemId = param.keySet().iterator().next();
            Integer Increment = (Integer) param.get(ItemId);
            preparedStatement.setInt(1,Increment.intValue());
            preparedStatement.setString(2,ItemId);
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatementETC(connection,preparedStatement,null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //获取库存数量
    @Override
    public int getInventoryQuantity(String itemId) {
        int result = -1;
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GETINVENTORYQUANTITY);
            preparedStatement.setString(1,itemId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                result = resultSet.getInt(1);
            }
            DBUtil.closePreparedStatementETC(connection,preparedStatement,resultSet);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    //获取Item列表
    @Override
    public List<Item> getItemListByProduct(String productId) {
        List<Item>itemList = new ArrayList<>();
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GETITEMLISTBYPRUDUCT);
            preparedStatement.setString(1,productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Item item = new Item();
                getResultSetIntoItem(item , resultSet);
                itemList.add(item);
            }
//            System.out.println(productId);
//            System.out.println(itemList);
            DBUtil.closePreparedStatementETC(connection,preparedStatement,resultSet);
        }catch (Exception e){
            e.printStackTrace();
        }
        return itemList;
    }
    //根据itemId得到Item
    @Override
    public Item getItem(String itemId){
        Item item = null;
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GETITEM);
            preparedStatement.setString(1,itemId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                item = new Item();
                getResultSetIntoItem(item , resultSet);
                item.setQuantity(resultSet.getInt(15));
            }
            DBUtil.closePreparedStatementETC(connection,preparedStatement,resultSet);
        }catch (Exception e){
            e.printStackTrace();
        }
        return item;
    }

    private void getResultSetIntoItem(Item item , ResultSet resultSet) throws SQLException {
        item.setItemId(resultSet.getString(1));
        item.setListPrice(resultSet.getBigDecimal(2));
        item.setUnitCost(resultSet.getBigDecimal(3));
        item.setSupplierId(resultSet.getByte(4));
        item.setProductId(resultSet.getString(5));
        Product product = new Product();
        product.setProductId(resultSet.getString(5));
        product.setName(resultSet.getString(6));
        product.setDescription(resultSet.getString(7));
        product.setCategoryId(resultSet.getString(8));
        item.setProduct(product);
        item.setStatus(resultSet.getString(9));
        item.setAttribute1(resultSet.getString(10));
        item.setAttribute2(resultSet.getString(11));
        item.setAttribute3(resultSet.getString(12));
        item.setAttribute4(resultSet.getString(13));
        item.setAttribute5(resultSet.getString(14));
    }
}
