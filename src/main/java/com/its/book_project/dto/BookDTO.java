package com.its.book_project.dto;

import com.its.book_project.entity.BookEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BookDTO {
    private Long id;
    private String bookName;
    private String bookPublisher;
    private String bookPublishingDate;
    private int bookPrice;
    // 책 이미지 업로드
    private MultipartFile bookFile;
    private String originalFileName;
    private String storedFileName;
    private int fileAttached;

    public static BookDTO toDTO(BookEntity bookEntity) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(bookEntity.getId());
        bookDTO.setBookName(bookEntity.getBookName());
        bookDTO.setBookPublisher(bookEntity.getBookPublisher());
        bookDTO.setBookPublishingDate(bookEntity.getBookPublishingDate());
        bookDTO.setBookPrice(bookEntity.getBookPrice());
        return bookDTO;
    }
}
