package franck.booklibrary.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import franck.booklibrary.scanIntent.IntentIntegrator;
import franck.booklibrary.scanIntent.IntentResult;
import franck.booklibrary.R;

/**
 * Created by franc on 03/10/2015.
 */
public class ScanBookActivity extends Activity {
    protected String scanFormat, scanContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanbook);
    }

    /**
     * Called when the user touches the scan button
     */
    public void scanABook(View view) {
        if (view.getId() == R.id.scan_button) {
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            scanContent = scanningResult.getContents();
            scanFormat = scanningResult.getFormatName();
            TextView scanContentView = (TextView) findViewById(R.id.scan_content);
            TextView scanFormatView = (TextView) findViewById(R.id.scan_format);
            scanContentView.setText("Content : " + scanContent);
            scanFormatView.setText("Format : " + scanFormat);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Error : no data scan received", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * Called when the user touches the go to home button
     */
    public void goToHomePage(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
