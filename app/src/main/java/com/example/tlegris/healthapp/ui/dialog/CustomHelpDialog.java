package com.example.tlegris.healthapp.ui.dialog;


import android.app.Application;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.tlegris.healthapp.R;


public class CustomHelpDialog extends Dialog {


    public CustomHelpDialog(Application a) {
        super(a);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_help_activity);

        findViewById(R.id.dialog_help_activity_close_textview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
