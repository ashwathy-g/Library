package com.LibraryApp2.demo.lib;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibRepository extends JpaRepository<LibModel,Integer>
{
    List<LibModel> findByAuthorName(String authorName);

    List<LibModel> findByCategory(String category);

    List<LibModel> findBooksByLanguage(String language);

    List<LibModel> findByBookName(String bookName);
}
