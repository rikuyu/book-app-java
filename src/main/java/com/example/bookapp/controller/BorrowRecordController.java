package com.example.bookapp.controller;

import com.example.bookapp.domain.entity.BorrowRecord;
import com.example.bookapp.domain.service.BorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<BorrowRecord> getBorrowRecord() {
        return service.findAllBorrowRecords();
    }

    @GetMapping("/user")
    public List<BorrowRecord> getBookRecordsByUserId(@RequestParam("id") int userId) {
        return service.findByUserId(userId);
    }

    @GetMapping("/book")
    public List<BorrowRecord> getBookRecordsByBookId(@RequestParam("id") int bookId) {
        return service.findByBookId(bookId);
    }

    @PostMapping()
    public void borrowBook(@RequestBody BorrowRecord body) {
        var isSuccess = service.insertBorrowRecordIfAvailable(body);
        if (isSuccess) {
            System.out.println("success");
        } else {
            System.out.println("failure");
        }
    }

    @PutMapping("/{borrow_record_id}/book/{book_id}")
    public void returnBook(@PathVariable("borrow_record_id") int borrowRecordId, @PathVariable("book_id") int bookId) {
        var isSuccess = service.returnBook(borrowRecordId, bookId);

        if (isSuccess) {
            System.out.println("success");
        } else {
            System.out.println("failure");
        }
    }
}
