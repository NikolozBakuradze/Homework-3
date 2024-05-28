package lms;

/**
 * The LMSTester class is used to test the functionality of the LMS.
 */
class LMSTester {
    public static void main(String[] args) {
        LMS universityLibrary = new LMS();

        Book harryPotter = new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling");
        Book cleanCode = new Book("Clean Code", "Robert C. Martin");
        Book theLeanStartup = new Book("The Lean Startup", "Eric Ries");

        universityLibrary.addBook(harryPotter);
        universityLibrary.addBook(cleanCode);
        universityLibrary.addBook(theLeanStartup);

        Student john = new Student("John", "Doe", "123456");
        Student jane = new Student("Jane", "Smith", "789012");
        Student bob = new Student("Bob", "Johnson", "345678");

        universityLibrary.borrowBook(harryPotter, john);
        universityLibrary.borrowBook(cleanCode, jane);
        universityLibrary.borrowBook(theLeanStartup, bob);

        System.out.println("Currently borrowed books:");
        for (BorrowedBook borrowedBook : universityLibrary.borrowedBooks) {
            System.out.println(borrowedBook.getBook().getTitle() + " is borrowed by " + borrowedBook.getStudent().getName() + " " + borrowedBook.getStudent().getSurname());
        }

        universityLibrary.saveState("libraryState.txt");

        universityLibrary.loadState("libraryState.txt");

        universityLibrary.returnBook(harryPotter);

        System.out.println("\nBorrowed books after returning 'Harry Potter and the Philosopher's Stone':");
        for (BorrowedBook borrowedBook : universityLibrary.borrowedBooks) {
            System.out.println(borrowedBook.getBook().getTitle() + " is borrowed by " + borrowedBook.getStudent().getName() + " " + borrowedBook.getStudent().getSurname());
        }
    }
}