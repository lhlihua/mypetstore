package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.LineItem;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.domain.Sequence;
import org.csu.mypetstore.persistence.Impl.LineItemDAOImpl;
import org.csu.mypetstore.persistence.Impl.OrderDAOImpl;
import org.csu.mypetstore.persistence.Impl.SequenceDAOImpl;
import org.csu.mypetstore.persistence.LineItemDAO;
import org.csu.mypetstore.persistence.OrderDAO;
import org.csu.mypetstore.persistence.SequenceDAO;

import java.util.List;

public class OrderService {
    private OrderDAO orderDAO;
    private SequenceDAO sequenceDAO;
    private LineItemDAO lineItemDAO;
    public OrderService(){
        orderDAO = new OrderDAOImpl();
        sequenceDAO = new SequenceDAOImpl();
        lineItemDAO = new LineItemDAOImpl();
    }
    public void insertOrder(Order order){
        orderDAO.insertOrder(order);
        orderDAO.insertOrderStatus(order);
    }
    public void insertLineItem(LineItem lineItem){
        lineItemDAO.insertLineItem(lineItem);
    }
    public List<LineItem> getLineItemsByOrderId(int orderId){
        return lineItemDAO.getLineItemsByOrderId(orderId);
    }
    public Order getOrder(int orderId){
        return orderDAO.getOrder(orderId);
    }
    public List<Order> getOrdersByUsername(String username){
        return orderDAO.getOrdersByUsername(username);
    }
    public int getNextId(String name){
        Sequence sequence = new Sequence(name, -1);
        sequence = (Sequence) sequenceDAO.getSequence(sequence);
        if (sequence == null) {
            throw new RuntimeException("Error: A null sequence was returned from the database (could not get next " + name
                    + " sequence).");
        }
        Sequence parameterObject = new Sequence(name, sequence.getNextId() + 1);
        sequenceDAO.updateSequence(parameterObject);
        return sequence.getNextId();
    }
}
