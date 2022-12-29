package com.its.book_project.dto;

import com.its.book_project.entity.BookEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class BookDTO {
    private Long id;
    private String bookName;
    private String bookPublisher;
    private String bookPublishingDate;
    private String bookContent;
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
        bookDTO.setBookContent(bookEntity.getBookContent());
        bookDTO.setBookPrice(bookEntity.getBookPrice());
        if (bookEntity.getFileAttached() == 1) {
            // 첨부파일 있음
            bookDTO.setFileAttached(bookEntity.getFileAttached());
            bookDTO.setOriginalFileName(bookEntity.getBookFileEntityList().get(0).getOriginalFileName());
            bookDTO.setStoredFileName(bookEntity.getBookFileEntityList().get(0).getStoredFileName());
        } else {
            // 첨부파일 없음
            bookDTO.setFileAttached(bookEntity.getFileAttached());
        }
        return bookDTO;
    }
}
