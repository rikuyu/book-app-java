package com.example.backend.controller;

import com.example.backend.domain.entity.BorrowRecord;
import com.example.backend.domain.service.BorrowRecordService;
import com.example.backend.utils.BadRequestException;
import com.example.backend.utils.InternalServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/borrow_record")
public class BorrowRecordController {

    private final BorrowRecordService service;

    @Autowired
    public BorrowRecordController(BorrowRecordService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<BorrowRecord>> getBorrowRecord() {
        try {
            return ResponseEntity.ok().body(service.findAllBorrowRecords());
        } catch (Exception e) {
            throw new InternalServerException("something went wrong", e);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<List<BorrowRecord>> getBookRecordsByUserId(@RequestParam("id") int userId) {
        if (userId <= 0) {
            throw new BadRequestException("id must be greater than 0");
        }

        try {
            return ResponseEntity.ok().body(service.findByUserId(userId));
        } catch (Exception e) {
            throw new InternalServerException("something went wrong", e);
        }
    }

    @GetMapping("/book")
    public ResponseEntity<List<BorrowRecord>> getBookRecordsByBookId(@RequestParam("id") int bookId) {
        if (bookId <= 0) {
            throw new BadRequestException("id must be greater than 0");
        }

        try {
            return ResponseEntity.ok().body(service.findByBookId(bookId));
        } catch (Exception e) {
            throw new InternalServerException("something went wrong", e);
        }
    }

    @PostMapping()
    public ResponseEntity<Void> borrowBook(@RequestBody BorrowRecord body) {
        if (body == null) {
            throw new BadRequestException("body must not be null");
        }
        try {
            if (service.insertBorrowRecordIfAvailable(body)) {
                return ResponseEntity.noContent().build();
            } else {
                throw new InternalServerException("book isn't available");
            }
        } catch (InternalServerException e) {
            throw new InternalServerException(e.getMessage());
        } catch (Exception e) {
            throw new InternalServerException("something went wrong", e);
        }
    }

    @PutMapping("/{borrow_record_id}/book/{book_id}")
    public ResponseEntity<Void> returnBook(
            @PathVariable("borrow_record_id") int borrowRecordId,
            @PathVariable("book_id") int bookId
    ) {
        if (bookId <= 0) {
            throw new BadRequestException("book id must be greater than 0");
        }

        if (borrowRecordId <= 0) {
            throw new BadRequestException("borrow record id must be greater than 0");
        }

        try {
            if (!service.returnBook(borrowRecordId, bookId)) {
                throw new InternalServerException("book record not found");
            }
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new InternalServerException("something went wrong", e);
        }
    }
}
