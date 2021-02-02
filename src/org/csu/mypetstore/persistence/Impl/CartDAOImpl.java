package org.csu.mypetstore.persistence.Impl;

import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.persistence.CartDAO;
import org.csu.mypetstore.persistence.DBUtil;
import org.csu.mypetstore.service.CatalogService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CartDAOImpl implements CartDAO {
    private static final String GETCARTBYUSERNAME ="SELECT itemid , quantity FROM cart WHERE username = ? ";
    private static final String INSERTCART = "INSERT INTO cart (username, itemid, quantity) VALUES (?, ?, ?)";
    private static final String DELETECARTBYITEMID = "DELETE FROM cart WHERE itemid = ? AND username = ?";
    private static final String UPDATECARTBYITEMID = "UPDATE cart SET quantity = ? WHERE username = ? AND itemid = ?";
    @Override
    public List<CartItem> getCartByUsername(String username){
        List<CartItem>cartList = null;
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GETCARTBYUSERNAME);
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            CatalogService catalogService = new CatalogService();
            CartItem cartItem = null;
            if(resultSet.next()){
                cartList = new ArrayList<>();
                cartItem = new CartItem();
                String itemId = resultSet.getString(1);
                Item item = catalogService.getItem(itemId);
                cartItem.setItem(item);
                cartItem.getItem().setItemId(itemId);
                cartItem.setQuantity(resultSet.getInt(2));
                cartItem.setInStock(resultSet.getInt(2)>0);
                cartList.add(cartItem);
            }
            while(resultSet.next()){
                cartItem = new CartItem();
                String itemId = resultSet.getString(1);
                Item item = catalogService.getItem(itemId);
                cartItem.setItem(item);
                cartItem.getItem().setItemId(itemId);
                cartItem.setQuantity(resultSet.getInt(2));
                cartItem.setInStock(resultSet.getInt(2)>0);
                cartList.add(cartItem);
            }
            DBUtil.closePreparedStatementETC(connection,preparedStatement,resultSet);
        }catch (Exception e){
            e.printStackTrace();
        }
        return cartList;
    }

    @Override
    public void insertCart(CartItem cartItem, String username) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERTCART);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,cartItem.getItem().getItemId());
            preparedStatement.setInt(3,cartItem.getQuantity());
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatementETC(connection,preparedStatement,null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCartByItemId(CartItem cartItem, String username) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETECARTBYITEMID);
            preparedStatement.setString(1,cartItem.getItem().getItemId());
            preparedStatement.setString(2,username);
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatementETC(connection,preparedStatement,null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateCartByItemId(CartItem cartItem, String username) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATECARTBYITEMID);
            preparedStatement.setInt(1,cartItem.getQuantity());
            preparedStatement.setString(2,username);
            preparedStatement.setString(3,cartItem.getItem().getItemId());
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatementETC(connection,preparedStatement,null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateCartByItemId(String ItemId, String username , int quantity) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATECARTBYITEMID);
            preparedStatement.setInt(1,quantity);
            preparedStatement.setString(2,username);
            preparedStatement.setString(3,ItemId);
            System.out.println(preparedStatement.toString());
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatementETC(connection,preparedStatement,null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
