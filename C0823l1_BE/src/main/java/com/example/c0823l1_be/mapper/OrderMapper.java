package com.example.c0823l1_be.mapper;

import com.example.c0823l1_be.dto.OrderDTO;
import com.example.c0823l1_be.entity.Order;
import com.example.c0823l1_be.repository.ICustomerRepository;
import com.example.c0823l1_be.repository.StaffRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {
    public static final OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    @Autowired
    protected StaffRepository staffRepository;
    @Autowired
    protected ICustomerRepository customerRepository;

//    public OrderDTO OrderToOrderDTO(Order order) {
//        OrderDTO orderDTO = new OrderDTO();
//        orderDTO.setId(order.getId());
//        orderDTO.setCustomerId(order.getCustomer().getId());
//        orderDTO.setStaffId(order.getStaff().getId());
//        return orderDTO;
//    }

//    @Mapping(target = "staff", expression = "java(staffRepository.findById(orderDTO.getStaffId()).get())")
//    @Mapping(target = "customer", expression = "java(customerRepository.findById(orderDTO.getStaffId()).get())")
//    public abstract Order OrderDtoToOrder(OrderDTO orderDTO);
//}
}