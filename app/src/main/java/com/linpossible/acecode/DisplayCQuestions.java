package com.linpossible.acecode;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
//import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


public class DisplayCQuestions extends AppCompatActivity {

    // Declare variables
    EditText editsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_c_questions);
        if(getActionBar()!= null){ getActionBar().setDisplayHomeAsUpEnabled(true);}

        populateListView();
        registerClickCallback();


//        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, getResources().getStringArray(R.array.cTitles)));
//        ListView lv = getListView();
//
//        // Enable filtering when the user types in the virtual keyboard
//        lv.setTextFilterEnabled(true);
//
//        // Set an setOnItemClickListener on the ListView
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                //Display a Toast message indicating the selected item
//                Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void populateListView() {
        // Create list of items
        String[] cTitles = {"Accessing Two-Dimensional Array Elements", "Area of Circle", "Area of Triangle",
                "Arithmetic Operation of a Complex number using Structure", "Armstrong Number", "Array Initialization",
                "Array of Objects", "Average of Numbers", "Basic Calculator", "Binary Search", "Binary Search Tree & Operations", "Binary to Decimal"};

        // Build Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, cTitles);

        // Configure the list view.
        ListView list = (ListView) findViewById(R.id.ListViewC);
        list.setAdapter(adapter);

    }

    private void registerClickCallback() {
        ListView list = (ListView) findViewById(R.id.ListViewC);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                TextView textView = (TextView) viewClicked;
                String message = "You clicked #" + position + ", " + textView.getText().toString();
                Toast.makeText(DisplayCQuestions.this, message, Toast.LENGTH_LONG).show();

                // Tries to open a new activity with intent that passes the position
                final Intent intent = new Intent(DisplayCQuestions.this, cProgramActivity.class);
                intent.putExtra("QUESTION_TITLE", textView.getText().toString());
                intent.putExtra("QUESTION_POSITION", position);
                startActivity(intent);
//                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.program_actions, menu);
//        return super.onCreateOptionsMenu(menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);


        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));


        // Show the search menu item in menu.xml
//        MenuItem menuSearch = menu.findItem(R.id.action_search);

        /*
        menuSearch.setOnActionExpandListener(new OnActionExpandListener() {

            // Menu Action Collapse
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Empty EditText to remove text filtering
                editsearch.setText("");
                editsearch.clearFocus();
                return true;
            }

            // Menu Action Expand
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Focus on EditText
                editsearch.requestFocus();

                // Force the keyboard to show on EditText focus
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                return true;
            }
        });
        */

//        MenuItem searchMenuItem = menu.findItem( R.id.action_search ); // get my MenuItem with placeholder submenu

        // Don't know what this is really
//        searchMenuItem.expandActionView();

        return true;

    }

    // EditText TextWatcher
    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void afterTextChanged(Editable s) {
            // TODO Auto-generated method stub
            String text = editsearch.getText().toString()
                    .toLowerCase(Locale.getDefault());
//            adapter.filter(text);
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                  int arg3) {
            // TODO Auto-generated method stub

        }
    };

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

        switch (item.getItemId()) {
            case R.id.action_search:
//                openSearch();
                Toast.makeText(this, "Search selected", Toast.LENGTH_SHORT).show();
                break;
//                return true;
            case R.id.action_start:
//                startQuiz();
                Toast.makeText(this, "Play selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings:
//                openSettings();
//                return true;
                Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;

    }
}
