package com.its.book_project.entity;

import com.its.book_project.dto.BookDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

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

    @Column
    private int bookPrice;

    @Column
    private int fileAttached;

    // BookEntity 와 BookFileEntity 의 연관관계 (1 : N)
    @OneToMany(mappedBy = "bookEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BookFileEntity> bookFileEntityList = new ArrayList<>();

    // 책 저장 Entity
    public static BookEntity toSaveEntity(BookDTO bookDTO) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setBookName(bookDTO.getBookName());
        bookEntity.setBookPublisher(bookDTO.getBookPublisher());
        bookEntity.setBookPublishingDate(bookDTO.getBookPublishingDate());
        bookEntity.setBookPrice(bookDTO.getBookPrice());
        bookEntity.setFileAttached(0);
        return bookEntity;
    }

    // 책 이미지 저장 Entity
    public static BookEntity toSaveFileEntity(BookDTO bookDTO) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setBookName(bookDTO.getBookName());
        bookEntity.setBookPublisher(bookDTO.getBookPublisher());
        bookEntity.setBookPublishingDate(bookDTO.getBookPublishingDate());
        bookEntity.setBookPrice(bookDTO.getBookPrice());
        bookEntity.setFileAttached(1);
        return bookEntity;
    }
}
