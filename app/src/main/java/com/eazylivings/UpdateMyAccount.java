package com.eazylivings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class UpdateMyAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_my_account);
    }

    public void onClickSaveInformation(View view){

        EditText userName=(EditText)findViewById(R.id.updateMyAccount_editText_userName);


    }
}
