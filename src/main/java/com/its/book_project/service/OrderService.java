package com.its.book_project.service;

import com.its.book_project.dto.OrderDTO;
import com.its.book_project.entity.BookEntity;
import com.its.book_project.entity.MemberEntity;
import com.its.book_project.entity.OrderEntity;
import com.its.book_project.repository.BookRepository;
import com.its.book_project.repository.MemberRepository;
import com.its.book_project.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long save(OrderDTO orderDTO, String email) {
        MemberEntity memberEntity = memberRepository.findByMemberEmail(email).get();
        BookEntity bookEntity = bookRepository.findById(orderDTO.getBookId()).get();
        OrderEntity orderEntity = OrderEntity.toSaveEntity(memberEntity, bookEntity);
        Long id = orderRepository.save(orderEntity).getId();
        return id;
    }
}
