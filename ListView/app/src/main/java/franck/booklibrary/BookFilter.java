package franck.booklibrary;

import franck.booklibrary.Book;

/**
 * Created by franc on 27/09/2015.
 */
public class BookFilter {
    protected String filter;

    public BookFilter(String filter) {
        this.filter = filter;
    }

    public BookFilter() {
        this.filter = "";
    }

    public boolean matches(Book book) {
        if (this.filter == null) return true;
        if (filter.equals("")) return true;
        if (book.getAuthor().contains(filter)) return true;
        if (book.getTitle().contains(filter)) return true;
        if (book.getIsbn().contains(filter)) return true;
        return false;
    }
}
