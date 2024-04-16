package com.LibraryApp2.demo.lib;

import com.LibraryApp2.demo.dto.BookResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class LibService {
    @Autowired
    LibRepository libRepository;

    public LibModel addBookWithImage(LibModel libModel) {
        return libRepository.save(libModel);
    }

    public BookResponse bookAdd(LibModel libModel) {
        LibModel savedModel = libRepository.save(libModel);
        BookResponse response = new BookResponse();
        response.setBookId(libModel.getId());
        response.setBookName(libModel.getBookName());
        response.setAuthorName(libModel.getAuthorName());
        libRepository.save(libModel);
        return response;

    }


    public List<LibModel> getAllBooks() {

        return libRepository.findAll();

    }

    public String updateDetails(LibModel libModel) {
        LibModel libModel1 = libRepository.findById(libModel.getId()).orElse(null);
        if (libModel1 != null) {
            libModel1.setBookName(libModel.getBookName());
            libModel1.setAuthorName(libModel.getAuthorName());
            libModel1.setCategory(libModel.getCategory());
            libModel1.setLanguage(libModel.getLanguage());
            libModel1.setRack(libModel.getRack());
            libModel1.setQuantity(libModel.getQuantity());
            log.info("saved : "+libModel1);
            libRepository.save(libModel1);
        }
        return "Details updated successfully";
    }

    public void deleteDetails(Integer id) {
        libRepository.deleteById(id);
    }

    public List<LibModel> findBooksByAuthorName(String authorName) {
        return libRepository.findByAuthorName(authorName);

    }

    public List<LibModel> findBooksByLanguage(String language) {
        return libRepository.findBooksByLanguage(language);

    }

    public List<LibModel> findByCategory(String category) {
        return libRepository.findByCategory(category);
    }

    public LibService(LibRepository libRepository) {
        this.libRepository = libRepository;


    }
}