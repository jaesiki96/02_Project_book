package com.its.book_project.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "book_file_table")
public class BookFileEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;

    // BookFileEntity 와 BookEntity 의 연관관계 (N : 1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private BookEntity bookEntity;

    public static BookFileEntity toSaveBookFileEntity(BookEntity bookEntity, String originalFileName, String storedFileName) {
        BookFileEntity bookFileEntity = new BookFileEntity();
        bookFileEntity.setOriginalFileName(originalFileName);
        bookFileEntity.setStoredFileName(storedFileName);
        bookFileEntity.setBookEntity(bookEntity);
        return bookFileEntity;
    }
}
