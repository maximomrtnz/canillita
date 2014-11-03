package canillita.maximomrtnz.com.canillita;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import canillita.maximomrtnz.com.canillita.dialogs.InputDialog;


public class MainActivity extends Activity implements InputDialog.InputDialogListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){

            case R.id.action_search:
                return true;
            case R.id.action_new:
                openNew();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Methods for Action Bar Items
     */

    private void openNew(){
        DialogFragment newFragment = InputDialog.newInstance(R.string.title_new_rss, R.string.message_new_rss);
        newFragment.show(getFragmentManager(), "inputDialog");
    }


    /**
     * Method from the InputDialogListener interface
     */

    @Override
    public void onDialogPositiveClick(String input) {

        // Check if the input passed is a valid URL
        boolean isValidURL = Patterns.WEB_URL.matcher(input).matches();

        if(isValidURL){



        }else{

            // Show a message saying that is a invalid URL
            Toast.makeText(getApplicationContext(), R.string.message_invalid_url, Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onDialogNegativeClick() {

    }
}
