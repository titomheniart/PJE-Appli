package franck.booklibrary.database;

import android.provider.BaseColumns;

/**
 * Created by franc on 05/10/2015.
 */
public final class BooksDatabaseContract {
    public static final String TEXT_TYPE = " TEXT";
    public static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + BooksDatabaseColumns.TABLE_NAME + " (" +
                    BooksDatabaseColumns._ID + " INTEGER PRIMARY KEY," +
                    BooksDatabaseColumns.ID + TEXT_TYPE + COMMA_SEP +
                    BooksDatabaseColumns.TITLE + TEXT_TYPE + COMMA_SEP +
                    BooksDatabaseColumns.AUTHOR + TEXT_TYPE + COMMA_SEP +
                    BooksDatabaseColumns.ISBN + TEXT_TYPE + COMMA_SEP +
                    " )";
    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + BooksDatabaseColumns.TABLE_NAME;

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public BooksDatabaseContract() {
    }

    /* Inner class that defines the table contents */
    public static abstract class BooksDatabaseColumns implements BaseColumns {
        public static final String TABLE_NAME = "book";
        public static final String ID = "bookid";
        public static final String NULLABLE = "null";
        public static final String TITLE = "title";
        public static final String AUTHOR = "author";
        public static final String ISBN = "isbn";
    }
}

