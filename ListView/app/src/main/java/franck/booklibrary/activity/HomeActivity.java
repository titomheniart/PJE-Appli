package franck.booklibrary.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import franck.listview.R;

/**
 * Created by franc on 03/10/2015.
 */
public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    /**
     * Called when the user touches the go to create button
     */
    public void goToCreateBookPage(View view) {
        Intent intent = new Intent(this, CreateBookActivity.class);
        startActivity(intent);
    }

    /**
     * Called when the user touches the go to library button
     */
    public void goToBookLibrary(View view) {
        Intent intent = new Intent(this, BookLibraryActivity.class);
        startActivity(intent);
    }

    /**
     * Called when the user touches the scan a book button
     */
    public void goToScanPage(View view) {
        Intent intent = new Intent(this, ScanBookActivity.class);
        startActivity(intent);
    }
}
