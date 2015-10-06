package franck.booklibrary;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by franc on 24/09/2015.
 */
public class BookLibrary {
    protected List<Book> books;

    private static BookLibrary ourInstance = new BookLibrary();

    public static BookLibrary getInstance() {
        return ourInstance;
    }

    private BookLibrary() {
        Book book1 = new Book("book1", "Franck Warlouzet", "01234");
        Book book2 = new Book("book2", "Thomas Heniart", "45678");
        Book book3 = new Book("book3", "TH & FW", "91011");
        books = new ArrayList<Book>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
    }

    public List<Book> getBooks(BookFilter bookFilter) {
        List booksFiltered = new ArrayList<Book>();
        for (Book book : books)
            if (bookFilter.matches(book))
                booksFiltered.add(book);
        return booksFiltered;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Book createEmptyBook() {
        return new Book();
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    /**
     * @param book book to remove
     * @return boolean Returns true if the element was in the list, false otherwise
     */
    public boolean removeBook(Book book) {
        return this.books.remove(book);
    }
}
