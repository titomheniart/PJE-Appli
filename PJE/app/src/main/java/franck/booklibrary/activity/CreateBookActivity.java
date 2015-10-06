package franck.booklibrary.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import franck.booklibrary.Book;
import franck.booklibrary.BookLibrary;
import franck.booklibrary.R;

/**
 * Created by franc on 03/10/2015.
 */
public class CreateBookActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createbook);
    }

    /**
     * Called when the user touches the validation button to create a book
     */
    public void validateBookCreation(View view) {
        EditText editTitle = (EditText) findViewById(R.id.editTitle);
        EditText editAuthor = (EditText) findViewById(R.id.editAuthor);
        EditText editISBN = (EditText) findViewById(R.id.editIsbn);
        BookLibrary.getInstance().addBook(new Book(editTitle.getText().toString(), editAuthor.getText().toString(), editISBN.getText().toString()));
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Book created !");
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
    }

    /**
     * Called when the user touches the go to home button
     */
    public void goToHomePage(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
