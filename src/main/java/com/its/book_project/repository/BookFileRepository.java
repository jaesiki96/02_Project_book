package com.its.book_project.repository;

import com.its.book_project.entity.BookFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookFileRepository extends JpaRepository<BookFileEntity, Long> {
}
