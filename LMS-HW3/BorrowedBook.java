package lms;

public class BorrowedBook {
    private Book book;
    private Student student;

    public BorrowedBook(Book book, Student student) {
        this.book = book;
        this.student = student;
    }

    public Book getBook() {
        return book;
    }

    public Student getStudent() {
        return student;
    }
}