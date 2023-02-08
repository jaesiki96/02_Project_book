package com.its.book_project.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "order_table")
public class OrderEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String memberEmail;

    @Column
    private String memberAddress;

    @Column
    private String bookName;

    @Column
    private int bookPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private BookEntity bookEntity;

    public static OrderEntity toSaveEntity(MemberEntity memberEntity, BookEntity bookEntity) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setMemberEmail(memberEntity.getMemberEmail());
        orderEntity.setMemberAddress(memberEntity.getMemberAddress());
        orderEntity.setBookName(bookEntity.getBookName());
        orderEntity.setBookPrice(bookEntity.getBookPrice());
        orderEntity.setMemberEntity(memberEntity);
        orderEntity.setBookEntity(bookEntity);
        return orderEntity;
    }
}

