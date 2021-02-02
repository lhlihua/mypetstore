package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.CartItem;

import java.util.List;

public interface CartDAO {
    List<CartItem> getCartByUsername(String username);

    void insertCart(CartItem cartItem, String username);

    void deleteCartByItemId(CartItem cartItem , String username);

    void updateCartByItemId(CartItem cartItem , String username);

    void updateCartByItemId(String ItemId , String username ,int quantity);
}
