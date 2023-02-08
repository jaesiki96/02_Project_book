package com.its.book_project.dto;

import com.its.book_project.entity.OrderEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDTO {
    private Long id;
    private Long memberId;
    private String memberEmail;
    private String memberAddress;
    private Long bookId;
    private String bookName;
    private int bookPrice;

    public static OrderDTO toDTO(OrderEntity orderEntity) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(orderEntity.getId());
        orderDTO.setMemberId(orderEntity.getMemberEntity().getId());
        orderDTO.setMemberEmail(orderEntity.getMemberEntity().getMemberEmail());
        orderDTO.setBookId(orderEntity.getBookEntity().getId());
        orderDTO.setBookName(orderEntity.getBookEntity().getBookName());
        orderDTO.setBookPrice(orderEntity.getBookEntity().getBookPrice());
        return orderDTO;
    }
}
