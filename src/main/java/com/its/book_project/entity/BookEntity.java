package com.its.book_project.entity;

import com.its.book_project.dto.BookDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "book_table")
public class BookEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String bookName;

    @Column(length = 50, nullable = false)
    private String bookPublisher;

    @Column(length = 30, nullable = false)
    private String bookPublishingDate;

    @Column(length = 3000, nullable = false)
    private String bookContent;

    @Column
    private int bookPrice;

    @Column
    private int fileAttached;

    @Column
    private String storedFileName;

    @Column
    private String originalFileName;

    @OneToMany(mappedBy = "bookEntity", cascade = CascadeType.PERSIST, orphanRemoval = false, fetch = FetchType.LAZY)
    private List<OrderEntity> orderEntityList = new ArrayList<>();

    // 책 저장 Entity
    public static BookEntity toSaveEntity(BookDTO bookDTO) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setBookName(bookDTO.getBookName());
        bookEntity.setBookPublisher(bookDTO.getBookPublisher());
        bookEntity.setBookPublishingDate(bookDTO.getBookPublishingDate());
        bookEntity.setBookContent(bookDTO.getBookContent());
        bookEntity.setBookPrice(bookDTO.getBookPrice());
        bookEntity.setStoredFileName(bookDTO.getStoredFileName());
        bookEntity.setOriginalFileName(bookDTO.getOriginalFileName());
        bookEntity.setFileAttached(1);
        return bookEntity;
    }
}
