package com.example.springbootmallmac.rowmapper;

import com.example.springbootmallmac.model.OrderItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemRowMapper implements RowMapper<OrderItem> {
    @Override
    public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(rs.getInt("product_id"));
        orderItem.setOrderId(rs.getInt("order_id"));
        orderItem.setQuantity(rs.getInt("quantity"));
        orderItem.setAmount(rs.getInt("amount"));

        orderItem.setProductName(rs.getString("product_name"));
        orderItem.setImageUrl(rs.getString("image_url"));

        return orderItem;
    }
}