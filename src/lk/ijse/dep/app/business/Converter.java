package lk.ijse.dep.app.business;

import lk.ijse.dep.app.dto.*;
import lk.ijse.dep.app.entity.CustomEntity;
import lk.ijse.dep.app.entity.Customer;
import lk.ijse.dep.app.entity.Item;
import lk.ijse.dep.app.entity.SuperEntity;

import java.util.ArrayList;
import java.util.List;

public class Converter {

    public static <T extends SuperDTO> T getDTO(SuperEntity entity) {
        if (entity instanceof Customer) {
            Customer c = (Customer) entity;
            return (T) new CustomerDTO(c.getId(), c.getName(), c.getAddress());
        } else if (entity instanceof Item) {
            Item i = (Item) entity;
            return (T) new ItemDTO(i.getCode(), i.getDescription(), i.getUnitPrice(), i.getQtyOnHand());
        } else {
            throw new RuntimeException("This entity can't be converted to a DTO");
        }
    }

    public static <T extends SuperEntity> T getEntity(SuperDTO dto) {
        if (dto instanceof CustomerDTO) {
            CustomerDTO c = (CustomerDTO) dto;
            return (T) new Customer(c.getId(), c.getName(), c.getAddress());
        } else if (dto instanceof ItemDTO) {
            ItemDTO i = (ItemDTO) dto;
            return (T) new Item(i.getCode(), i.getDescription(), i.getUnitPrice(), i.getQtyOnHand());
        } else {
            throw new RuntimeException("This DTO can't be converted to an entity");
        }
    }

    public static <T extends SuperDTO> List<T> getDTOList(List<? extends SuperEntity> entities) {
        SuperEntity o = (SuperEntity) entities.get(0);
        if (o instanceof Customer) {
            List<CustomerDTO> dtos = new ArrayList<>();
            for (Object e : entities) {
                Customer c = (Customer) e;
                dtos.add((CustomerDTO) getDTO(c));
            }
            return (List<T>) dtos;
        } else if (o instanceof Item) {
            List<ItemDTO> dtos = new ArrayList<>();
            for (Object e : entities) {
                Item c = (Item) e;
                dtos.add((ItemDTO) getDTO(c));
            }
            return (List<T>) dtos;
        } else {
            throw new RuntimeException("This entity list can't be converted to a DTO list");
        }
    }

    public static <T extends SuperEntity> List<T> getEntityList(List<? extends SuperDTO> dtos) {
        SuperDTO d = (SuperDTO) dtos.get(0);
        if (d instanceof CustomerDTO) {
            List<Customer> list = new ArrayList<>();
            dtos.forEach(c -> {
                list.add(getEntity(c));
            });
            return (List<T>) list;
        } else if (d instanceof ItemDTO) {
            List<Item> list = new ArrayList<>();
            dtos.forEach(c -> {
                list.add(getEntity(c));
            });
            return (List<T>) list;
        } else {
            throw new RuntimeException("This dto list can't be converted to an entity list");
        }
    }

    // =========================================================================

    public static <T extends SuperDTO> T getDTO(CustomEntity entity, Class<T> dtoClass){
        if (dtoClass == OrderDTO2.class){
            return (T) new OrderDTO2(entity.getOrderId(),entity.getOrderDate().toLocalDate(),
                    entity.getCustomerId(),entity.getCustomerName(),entity.getTotal());
        }else if (dtoClass == OrderDetailDTO.class){
            return (T) new OrderDetailDTO(entity.getItemCode(),entity.getDescription(),entity.getQty(),entity.getUnitPrice());
        }else{
            throw new RuntimeException("Not Supported DTO");
        }
    }

    public static <T extends SuperDTO> List<T> getDTOList(List<CustomEntity> list, Class<T> dtoClass){
        if (dtoClass == OrderDTO2.class){
            List<OrderDTO2> dtos = new ArrayList<>();
            list.forEach(c -> dtos.add(getDTO(c, OrderDTO2.class)));
            return (List<T>) dtos;
        } else if (dtoClass == OrderDetailDTO.class) {
            List<OrderDetailDTO> dtos = new ArrayList<>();
            list.forEach(c -> dtos.add(getDTO(c, OrderDetailDTO.class)));
            return (List<T>) dtos;
        }else{
            throw new RuntimeException("Not Supported DTO");
        }
    }

}
