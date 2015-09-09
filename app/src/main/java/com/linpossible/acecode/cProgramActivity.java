package com.linpossible.acecode;

import android.app.ActionBar;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by jasonjli on 9/5/2015.
 */
public class cProgramActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        setContentView(R.layout.activity_program);

        // Setup for code format
        // Testing code layout

        // Get raw spanned text
        Resources res = getResources();
        BufferedReader input = new BufferedReader(new InputStreamReader(
                res.openRawResource(R.raw.program_1)));
        StringBuffer sb = new StringBuffer();
        try {
            String line;
            while ((line = input.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Spanned spannedCode = Html.fromHtml(sb.toString());
        ((TextView)findViewById(R.id.code_snippet)).setText(spannedCode);


        // Retrieve question title &  position from extras
        String question;
        int position;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                question = null;
                position = 0;
            } else {
                question = extras.getString("QUESTION_TITLE");
                position = extras.getInt("QUESTION_POSITION", 0);
            }
        } else {
            question = (String) savedInstanceState.getSerializable("QUESTION_TITLE");
            position = (int) savedInstanceState.getInt("QUESTION_POSITION");
        }



        String[] content = getResources().getStringArray(R.array.cPrograms);
        setTitle(question);
        /*
        ArrayAdapter<String> svAdapter = new ArrayAdapter<String>(this, R.layout.list_item, content);
//        ScrollView sv = (ScrollView) findViewById(R.id.scrollView);
//        sv.setAdapter(svAdapter);
        View item = svAdapter.getView(position, null, null);
        LinearLayout layout = (LinearLayout) findViewById(R.id.activity_program_layout);
        layout.addView(item);

        */
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

}
