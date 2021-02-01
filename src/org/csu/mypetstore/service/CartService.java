package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.persistence.CartDAO;
import org.csu.mypetstore.persistence.Impl.CartDAOImpl;

import java.util.List;

public class CartService {
    private CartDAO cartDAO;
    public CartService(){
        cartDAO = new CartDAOImpl();
    }
    public List<CartItem> getCartByUsername(String username){
        return cartDAO.getCartByUsername(username);
    }
    public void insertCart(CartItem cartItem, String username){
        cartDAO.insertCart(cartItem,username);
    }
    public void deleteCartByItemId(CartItem cartItem, String username){
        cartDAO.deleteCartByItemId(cartItem,username);
    }
    public void updateCartByItemId(CartItem cartItem, String username){
        cartDAO.updateCartByItemId(cartItem,username);
    }
    public void updateCartByItemId(String itemId , String username ,int quantity){
        cartDAO.updateCartByItemId(itemId,username,quantity);
    }
}
