package lk.ijse.dep.app.business.custom;

import lk.ijse.dep.app.business.SuperBO;
import lk.ijse.dep.app.dto.OrderDTO;
import lk.ijse.dep.app.dto.OrderDTO2;

import java.sql.SQLException;
import java.util.List;

public interface ManageOrdersBO extends SuperBO {

    List<OrderDTO2> getOrdersWithCustomerNamesAndTotals() throws SQLException;

    List<OrderDTO> getOrders() throws SQLException;

    String generateOrderId() throws SQLException;

    void createOrder(OrderDTO dto) throws SQLException;

    OrderDTO findOrder(String orderId) throws SQLException;

}
