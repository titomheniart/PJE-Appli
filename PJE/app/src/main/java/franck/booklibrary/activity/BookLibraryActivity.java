package franck.booklibrary.activity;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import franck.booklibrary.Book;
import franck.booklibrary.BookFilter;
import franck.booklibrary.BookLibrary;
import franck.booklibrary.BookListViewAdapter;
import franck.booklibrary.R;
import franck.booklibrary.database.BooksDatabase;
import franck.booklibrary.database.BooksDatabaseContract;

public class BookLibraryActivity extends ListActivity {
    protected ListView listView;
    protected BookListViewAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        handleIntent(getIntent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the BookLibraryActivity/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            this.getListItemsView(new BookFilter(query));
        } else {
            this.getListItemsView(new BookFilter());
        }
    }

    public List<Book> getBooks(BookFilter bookFilter) {
        List<Book> books = new ArrayList<Book>();
        BooksDatabase database = new BooksDatabase(getApplicationContext());
        String[] args = {};
        Cursor cursor = database.query("SELECT * FROM " + BooksDatabaseContract.BooksDatabaseColumns.TABLE_NAME, args);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String title = cursor.getString(cursor.getColumnIndex(BooksDatabaseContract.BooksDatabaseColumns.TITLE));
                    String author = cursor.getString(cursor.getColumnIndex(BooksDatabaseContract.BooksDatabaseColumns.AUTHOR));
                    String isbn = cursor.getString(cursor.getColumnIndex(BooksDatabaseContract.BooksDatabaseColumns.ISBN));
                    Book book = new Book(title, author, isbn);
                    if(bookFilter.matches(book))
                        books.add(book);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return books;
    }


    public void getListItemsView(BookFilter bookFilter) {
        listView = (ListView) findViewById(android.R.id.list);
        listAdapter = new BookListViewAdapter(this, R.layout.activity_book, this.getBooks(bookFilter));
        listView.setAdapter(listAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                final int checkedCount = listView.getCheckedItemCount();
                mode.setTitle(checkedCount + " Selected");
                listAdapter.toggleSelection(position);
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.delete:
                        SparseBooleanArray selected = listAdapter.getSelectedIds();
                        for (int i = (selected.size() - 1); i >= 0; i--) {
                            if (selected.valueAt(i)) {
                                Book selectedItem = listAdapter.getItem(selected.keyAt(i));
                                listAdapter.remove(selectedItem);
                                BookLibrary.getInstance().removeBook(selectedItem);
                            }
                        }
                        mode.finish();
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.selected_home, menu);
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                listAdapter.removeSelection();
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }
        });
    }

    /**
     * Called when the user touches the go to home button
     */
    public void goToHomePage(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

}