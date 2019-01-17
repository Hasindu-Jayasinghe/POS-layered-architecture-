package lk.ijse.dep.app.business.custom.impl;

import lk.ijse.dep.app.business.Converter;
import lk.ijse.dep.app.business.custom.ManageCustomersBO;
import lk.ijse.dep.app.dao.custom.CustomerDAO;
import lk.ijse.dep.app.dao.DAOFactory;
import lk.ijse.dep.app.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public class ManageCustomersBOImpl implements ManageCustomersBO {

    private CustomerDAO customerDAO;

    public ManageCustomersBOImpl(){
        customerDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    }

    public List<CustomerDTO> getCustomers() throws SQLException {
        return customerDAO.findAll().map(Converter::<CustomerDTO>getDTOList).get();

//        return customerDAO.findAll().map(customers -> {
//
//            return Converter.getDTOList(customers);
//
////                List<CustomerDTO> dtos = new ArrayList<>();
////                customers.forEach(c -> dtos.add(new CustomerDTO(c.getId(),c.getName(),c.getAddress())));
//
////                for (Customer c : customers) {
////                    dtos.add(new CustomerDTO(c.getId(),c.getName(),c.getAddress()));
////                }
////                return dtos;
//        }).get();
    }

    public boolean createCustomer(CustomerDTO dto) throws SQLException {
        return customerDAO.save(Converter.getEntity(dto));
    }

    public boolean updateCustomer(CustomerDTO dto) throws SQLException {
        return customerDAO.update(Converter.getEntity(dto));
    }

    public boolean deleteCustomer(String customerID) throws SQLException {
        return customerDAO.delete(customerID);
    }

    public CustomerDTO findCustomer(String id) throws SQLException {
//        return (CustomerDTO) customerDAO.find(id).map(Converter::getDTO).get();

//        return customerDAO.find(id).map(new Function<Customer, CustomerDTO>() {
//            @Override
//            public CustomerDTO apply(Customer c) {
//                return (CustomerDTO) Converter.getDTO(c);
//            }
//        }).get();

//        return customerDAO.find(id).map(c -> Converter.<CustomerDTO>getDTO(c)).get();

        return customerDAO.find(id).map(Converter::<CustomerDTO>getDTO).orElse(null);
    }

}
