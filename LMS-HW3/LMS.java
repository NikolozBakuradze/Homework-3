package lms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The LMS class represents the Library Management System.
 * It handles operations related to books, students, and borrowed books.
 */
public class LMS {
    private List<Book> books;
    private List<Student> students;
    public List<BorrowedBook> borrowedBooks;

    public LMS() {
        books = new ArrayList<>();
        students = new ArrayList<>();
        borrowedBooks = new ArrayList<>();
    }

    /**
     * Adds a new book to the library if it doesn't already exist.
     *
     * @param book The book to be added.
     */
    public void addBook(Book book) {
        if (!books.contains(book)) {
            books.add(book);
        }
    }

    /**
     * Removes a book from the library.
     *
     * @param book The book to be removed.
     */
    public void removeBook(Book book) {
        books.remove(book);
    }

    /**
     * Allows a student to borrow a book if it's available.
     *
     * @param book    The book to be borrowed.
     * @param student The student borrowing the book.
     */
    public void borrowBook(Book book, Student student) {
        if (books.contains(book)) {
            BorrowedBook borrowedBook = new BorrowedBook(book, student);
            borrowedBooks.add(borrowedBook);
        }
    }

    /**
     * Returns a borrowed book to the library.
     *
     * @param book The book to be returned.
     */
    public void returnBook(Book book) {
        BorrowedBook borrowedBook = getBorrowedBook(book);
        if (borrowedBook != null) {
            borrowedBooks.remove(borrowedBook);
        }
    }

    /**
     * Saves the current state of the LMS to a file.
     *
     * @param filePath The path of the file to save the state.
     */
    public void saveState(String filePath) {
        try (PrintWriter writer = new PrintWriter(filePath, "UTF-8")) {
            // Write book information
            for (Book book : books) {
                writer.println(book.getTitle() + "," + book.getAuthor());
            }

            // Write an empty line
            writer.println();

            // Write borrowed book information
            for (BorrowedBook borrowedBook : borrowedBooks) {
                writer.println(borrowedBook.getBook().getTitle() + "," + borrowedBook.getBook().getAuthor() + "," + borrowedBook.getStudent().getName() + "," + borrowedBook.getStudent().getSurname() + "," + borrowedBook.getStudent().getPersonalNumber());
            }

            System.out.println("State saved successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the state.");
            e.printStackTrace();
        }
    }

    /**
     * Loads the state of the LMS from a file.
     *
     * @param filePath The path of the file to load the state from.
     */
    public void loadState(String filePath) {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            // Clear current state
            books.clear();
            students.clear();
            borrowedBooks.clear();

            // Load book information
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isEmpty()) break; // Empty line indicates end of books

                String[] parts = line.split(",");
                books.add(new Book(parts[0], parts[1]));
            }

            // Load borrowed book information
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isEmpty()) break; // Empty line indicates end of borrowed books

                String[] parts = line.split(",");
                Book book = new Book(parts[0], parts[1]);
                Student student = new Student(parts[2], parts[3], parts[4]);
                borrowedBooks.add(new BorrowedBook(book, student));
            }

            scanner.close();

            System.out.println("State loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while loading the state.");
            e.printStackTrace();
        }
    }

    /**
     * Gets the borrowed book object for a given book.
     *
     * @param book The book for which to find the borrowed book object.
     * @return The borrowed book object, or null if not found.
     */
    private BorrowedBook getBorrowedBook(Book book) {
        for (BorrowedBook borrowedBook : borrowedBooks) {
            if (borrowedBook.getBook().equals(book)) {
                return borrowedBook;
            }
        }
        return null;
    }
}