package franck.booklibrary.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.UUID;

/**
 * Created by franc on 05/10/2015.
 */
public class BooksDatabase {
    protected BooksDatabaseHelper databaseHelper;

    public BooksDatabase(Context context) {
        databaseHelper = new BooksDatabaseHelper(context);
    }

    public void createBook(String title, String author, String isbn) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BooksDatabaseContract.BooksDatabaseColumns.ID, UUID.randomUUID().toString());
        values.put(BooksDatabaseContract.BooksDatabaseColumns.TITLE, title);
        values.put(BooksDatabaseContract.BooksDatabaseColumns.AUTHOR, author);
        values.put(BooksDatabaseContract.BooksDatabaseColumns.ISBN, isbn);
        database.insert(
                BooksDatabaseContract.BooksDatabaseColumns.TABLE_NAME,
                BooksDatabaseContract.BooksDatabaseColumns.NULLABLE,
                values);
    }

    public Cursor query(String sqlQuery, String[] selectionArgs) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor c = db.rawQuery(sqlQuery, selectionArgs);
        return c;
    }
}
