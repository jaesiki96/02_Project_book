package com.its.book_project.service;

import com.its.book_project.dto.BookDTO;
import com.its.book_project.entity.BookEntity;
import com.its.book_project.entity.BookFileEntity;
import com.its.book_project.repository.BookFileRepository;
import com.its.book_project.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookFileRepository bookFileRepository;

    // 책 등록 처리
    public Long save(BookDTO bookDTO) throws IOException {
        // 첨부파일 여부에 따라 로직 분리
        if (bookDTO.getBookFile().isEmpty()) {
            // 첨부파일 없음
            BookEntity bookEntity = BookEntity.toSaveEntity(bookDTO);
            return bookRepository.save(bookEntity).getId();
        } else {
            // 첨부파일 있음
            MultipartFile bookFile = bookDTO.getBookFile();
            String originalFileName = bookFile.getOriginalFilename();
            String storedFileName = System.currentTimeMillis() + "_" + originalFileName;
            String savePath = "D:\\springboot_img\\" + storedFileName;
            bookFile.transferTo(new File(savePath));

            BookEntity bookEntity = BookEntity.toSaveFileEntity(bookDTO);
            Long savedId = bookRepository.save(bookEntity).getId();

            BookEntity entity = bookRepository.findById(savedId).get();
            BookFileEntity bookFileEntity =
                    BookFileEntity.toSaveBookFileEntity(entity, originalFileName, storedFileName);
            bookFileRepository.save(bookFileEntity);
            return savedId;
        }
    }

    // 책 목록 처리
    public List<BookDTO> findAll() {
        List<BookEntity> bookEntityList = bookRepository.findAll();
        List<BookDTO> bookDTOList = new ArrayList<>();
        for (BookEntity bookEntity : bookEntityList) {
            bookDTOList.add(BookDTO.toDTO(bookEntity));
        }
        return bookDTOList;
    }
}
