package com.its.book_project.service;

import com.its.book_project.dto.BookDTO;
import com.its.book_project.entity.BookEntity;
import com.its.book_project.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

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
            bookDTO.setStoredFileName(storedFileName);
            bookDTO.setOriginalFileName(originalFileName);
            BookEntity bookEntity = BookEntity.toSaveEntity(bookDTO);
            Long savedId = bookRepository.save(bookEntity).getId();
            return savedId;
        }
    }

    // 책 목록 처리
    public List<BookDTO> findAll() {
        List<BookEntity> bookEntityList = bookRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
        List<BookDTO> bookDTOList = new ArrayList<>();
        for (BookEntity bookEntity : bookEntityList) {
            bookDTOList.add(BookDTO.toDTO(bookEntity));
        }
        return bookDTOList;
    }

    // 책 조회 처리
    public BookDTO findById(Long id) {
        Optional<BookEntity> optionalBookEntity = bookRepository.findById(id);
        if (optionalBookEntity.isPresent()) {
            return BookDTO.toDTO(optionalBookEntity.get());
        } else {
            return null;
        }
    }

    // 페이징 처리
    public Page<BookDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        final int pageLimit = 3;
        Page<BookEntity> bookEntities = bookRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.ASC,"id")));
        Page<BookDTO> bookList = bookEntities.map(
                book -> new BookDTO(
                        book.getId(),
                        book.getBookName(),
                        book.getBookPublisher(),
                        book.getBookPrice(),
                        book.getStoredFileName()
                )
        );
        return bookList;
    }
}
