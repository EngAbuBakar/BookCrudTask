package com.task.crudapplication.Repository;

import com.task.crudapplication.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    public Optional<Book> findById(Long id);
}
