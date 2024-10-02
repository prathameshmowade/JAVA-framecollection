import java.util.*;

class Book {
    String title;
    String author;
    String isbn;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", ISBN: " + isbn;
    }
}

public class LibraryManagement {
    private List<Book> books = new ArrayList<>();
    private Set<String> isbnSet = new HashSet<>();
    private Map<String, Book> bookMap = new HashMap<>();

    public void addBook(Book book) {
        if (isbnSet.add(book.isbn)) {
            books.add(book);
            bookMap.put(book.title, book);
            System.out.println("Book added: " + book);
        } else {
            System.out.println("Book with this ISBN already exists: " + book.isbn);
        }
    }

    public void removeBook(String isbn) {
        books.removeIf(book -> book.isbn.equals(isbn));
        isbnSet.remove(isbn);
        bookMap.values().removeIf(book -> book.isbn.equals(isbn));
        System.out.println("Book removed with ISBN: " + isbn);
    }

    public Book searchByTitle(String title) {
        return bookMap.get(title);
    }

    public void listBooksSorted() {
        books.sort(Comparator.comparing(book -> book.title));
        System.out.println("Listing all books in sorted order:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public static void main(String[] args) {
        LibraryManagement library = new LibraryManagement();

        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565");
        Book book2 = new Book("1984", "George Orwell", "9780451524935");
        Book book3 = new Book("To Kill a Mockingbird", "Harper Lee", "9780061120084");

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        
        // Attempt to add a duplicate book
        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565"));

        // List all books
        library.listBooksSorted();

        // Search for a book by title
        Book foundBook = library.searchByTitle("1984");
        System.out.println("Found book: " + foundBook);

        // Remove a book
        library.removeBook("9780451524935");

        // List all books again after removal
        library.listBooksSorted();
    }
}
