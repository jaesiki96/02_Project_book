package com.its.book_project.entity;

import com.its.book_project.dto.BookDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

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
