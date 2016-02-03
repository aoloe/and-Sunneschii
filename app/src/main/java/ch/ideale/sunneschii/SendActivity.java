package ch.ideale.sunneschii;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class SendActivity extends AppCompatActivity {

    private static final int PICK_CONTACT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // http://stackoverflow.com/questions/3013791/live-character-count-for-edittext
        final TextView mTextView = (TextView) findViewById(R.id.textViewCounter);
        EditText mEditText= (EditText) findViewById(R.id.editTextMessage);
        final TextWatcher mTextEditorWatcher = new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //This sets a textview to the current length
                mTextView.setText(String.format("(%d)",  160 - s.length()));
            }
            public void afterTextChanged(Editable s) {
            }
        };
        mEditText.addTextChangedListener(mTextEditorWatcher);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_send, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onOpenAddressbook(View view) {
        // EditText messageView = (EditText) findViewById(R.id.message);
        // String messageText = messageView.getText().toString();

        Intent intent = new Intent(this, AddressbookActivity.class);
        // intent.putExtra(SendActivity.ADDRESS_MESSAGE, addressText)

        // startActivity(intent);
        startActivityForResult(intent, PICK_CONTACT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == PICK_CONTACT_REQUEST) {
            switch(requestCode) {
                case PICK_CONTACT_REQUEST:
                    if (resultCode == RESULT_OK) {
                        Bundle res = data.getExtras();
                        String result = res.getString("results");
                        Log.d("FIRST", "result:" + result);
                        EditText editText = (EditText) findViewById(R.id.editTextNumber);
                        editText.setText(result, TextView.BufferType.EDITABLE);
                    }
                    break;
            }

        }
    }

    private void pickContact() {
        Intent intent = new Intent(this, AddressbookActivity.class);
        startActivityForResult(intent, PICK_CONTACT_REQUEST);
    }
}
