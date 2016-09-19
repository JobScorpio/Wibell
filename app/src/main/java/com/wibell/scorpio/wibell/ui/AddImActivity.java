package com.wibell.scorpio.wibell.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wibell.scorpio.wibell.MainActivity;
import com.wibell.scorpio.wibell.R;
import com.wibell.scorpio.wibell.bean.PersonBean;
import com.wibell.scorpio.wibell.db.NoteDatabase;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Scorpio on 2016/9/6.
 */
public class AddImActivity extends AppCompatActivity {

    private EditText editIp;
    private EditText editAlias;
    private EditText editToken;
    private Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_im);
        initForm();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoteDatabase db = new NoteDatabase(getApplicationContext());
                PersonBean person = new PersonBean();
                person.setIp(editIp.getText().toString());
                person.setAlias(editAlias.getText().toString());
                person.setToken(editToken.getText().toString());
                db.addPerson(person);

                Toast.makeText(AddImActivity.this, "保存成功！", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddImActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initForm() {
        editIp = (EditText) findViewById(R.id.ip);
        editAlias = (EditText) findViewById(R.id.alias);
        editToken = (EditText) findViewById(R.id.token);
        submit = (Button) findViewById(R.id.submit);
    }
}
