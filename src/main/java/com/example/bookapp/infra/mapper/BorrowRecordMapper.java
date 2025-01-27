package com.example.bookapp.infra.mapper;

import com.example.bookapp.domain.entity.BorrowRecord;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface BorrowRecordMapper {

    @Select("SELECT * FROM borrow_records")
    List<BorrowRecord> findAllBorrowRecords();

    @Select("SELECT * FROM borrow_records WHERE user_id = #{userId}")
    List<BorrowRecord> findByUserId(int userId);

    @Select("SELECT * FROM borrow_records WHERE book_id = #{bookId}")
    List<BorrowRecord> findByBookId(int bookId);

    @Insert("""
            INSERT INTO
                borrow_records (user_id, book_id, borrowed_date)
            VALUES 
                (#{userId}, #{bookId}, #{borrowedDate})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertBorrowRecord(BorrowRecord borrowRecord);

    @Update("UPDATE borrow_records SET returned_date = NOW() WHERE id = #{borrowRecordId} AND book_id = #{bookId} AND returned_date IS NULL")
    int updateBorrowRecord(int borrowRecordId, int bookId);
}
