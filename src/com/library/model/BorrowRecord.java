package com.library.model;

import java.time.LocalDate;

public class BorrowRecord {
    private int id;
    private int bookId;
    private int memberId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private boolean returned;

    public BorrowRecord(int id, int bookId, int memberId,
                        LocalDate borrowDate, LocalDate returnDate, boolean returned) {
        this.id = id;
        this.bookId = bookId;
        this.memberId = memberId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.returned = returned;
    }

    public int getId() { return id; }
    public int getBookId() { return bookId; }
    public int getMemberId() { return memberId; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public boolean isReturned() { return returned; }
}
