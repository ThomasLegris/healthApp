package com.example.tlegris.healthapp.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tlegris.healthapp.R;

public class ProfileFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    EditText mName;
    EditText mSurname;
    EditText mAge;
    EditText mWeight;
    EditText mHeight;
    Button mSaveButton;
    Button mModifyButton;
    TextView mHelpButton;
    RadioButton mMaleButton;
    RadioButton mFemaleButon;
    RadioButton mNullActivityButton;
    RadioButton mModerateActivityButton;
    RadioButton mHighActivityButton;
    RadioButton mVeryHighActivityButton;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    /*
    LIFECYCLE
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        Bundle bundle = this.getArguments();
        Boolean haveInfos = bundle.getBoolean("haveInfos");

        //view
        mName = view.findViewById(R.id.profile_name_edittext);
        mSurname = view.findViewById(R.id.profile_surname_edittext);
        mAge = view.findViewById(R.id.profile_age_edittext);
        mWeight = view.findViewById(R.id.profile_weight_edittext);
        mHeight = view.findViewById(R.id.profile_height_edittext);
        mSaveButton = view.findViewById(R.id.profile_activity_save_button);
        mModifyButton = view.findViewById(R.id.profile_activity_modify_button);
        mHelpButton = view.findViewById(R.id.profile_activity_select_help_textview);
        mFemaleButon = view.findViewById(R.id.profile_sex_female_radio_button);
        mMaleButton = view.findViewById(R.id.profile_sex_male_radio_button);
        mNullActivityButton = view.findViewById(R.id.profile_activity_null_level_radio_button);
        mModerateActivityButton = view.findViewById(R.id.profile_activity_moderate_level_radio_button);
        mHighActivityButton = view.findViewById(R.id.profile_activity_high_level_radio_button);
        mVeryHighActivityButton = view.findViewById(R.id.profile_activity_very_high_level_radio_button);

        //listener
        mSaveButton.setOnClickListener(this);
        mModifyButton.setOnClickListener(this);
        mHelpButton.setOnClickListener(this);

        //Check if the user have save his information
        if (haveInfos) {
            mSaveButton.setVisibility(View.GONE);
            mModifyButton.setVisibility(View.VISIBLE);
            confirmProfile();
        }
        return view;
    }

    /*
    METHODS
     */
    private void confirmProfile() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("health-app", Context.MODE_PRIVATE);
        mName.setEnabled(false);
        mName.setText(sharedPreferences.getString("name", ""));
        mSurname.setEnabled(false);
        mSurname.setText(sharedPreferences.getString("surname", ""));
        mAge.setEnabled(false);
        mAge.setText(sharedPreferences.getString("age", ""));
        mWeight.setEnabled(false);
        mWeight.setText(sharedPreferences.getString("weight", ""));
        mHeight.setEnabled(false);
        mHeight.setText(sharedPreferences.getString("height", ""));
        mFemaleButon.setEnabled(false);
        mMaleButton.setEnabled(false);
        mNullActivityButton.setEnabled(false);
        mModerateActivityButton.setEnabled(false);
        mHighActivityButton.setEnabled(false);
        mVeryHighActivityButton.setEnabled(false);
    }

    private void deleteProfile() {
        mName.setEnabled(true);
        mName.setText("");
        mSurname.setEnabled(true);
        mSurname.setText("");
        mAge.setEnabled(true);
        mAge.setText("");
        mWeight.setEnabled(true);
        mWeight.setText("");
        mHeight.setEnabled(true);
        mHeight.setText("");
        mFemaleButon.setEnabled(true);
        mMaleButton.setEnabled(true);
        mNullActivityButton.setEnabled(true);
        mModerateActivityButton.setEnabled(true);
        mHighActivityButton.setEnabled(true);
        mVeryHighActivityButton.setEnabled(true);
        mSaveButton.setVisibility(View.VISIBLE);
        mModifyButton.setVisibility(View.GONE);
    }

    //method used when user click on the save button
    private void setupSaveListener() {

        final String name = mName.getText().toString();
        final String surname = mSurname.getText().toString();
        final String age = mAge.getText().toString();
        final String weight = mWeight.getText().toString();
        final String height = mHeight.getText().toString();
        if (name.equals("") || surname.equals("") || age.equals("") || weight.equals("") || height.equals("") || (!mFemaleButon.isChecked() && !mMaleButton.isChecked()) || (!mNullActivityButton.isChecked() && !mModerateActivityButton.isChecked() && !mHighActivityButton.isChecked() && !mVeryHighActivityButton.isChecked())) {
            Toast.makeText(getActivity(), "Please fill in all the fields", Toast.LENGTH_SHORT).show();
        } else {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("health-app", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name", name);
            editor.putString("surname", surname);
            editor.putString("age", age);
            editor.putString("weight", weight);
            editor.putString("height", height);
            editor.apply();
            Toast.makeText(getActivity(), "User " + name + " " + surname + " : " + age + " year old is saved", Toast.LENGTH_SHORT).show();
        }
    }

    //method used when the user click on the help button
    private void setupHelpDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View dialogView = layoutInflater.inflate(R.layout.dialog_help_activity, null);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        TextView close = dialogView.findViewById(R.id.dialog_help_activity_close_textview);

        alertDialogBuilder
                .setCancelable(true)
                .setView(dialogView);
        // create alert dialog
        final AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.cancel();
            }
        });
        alertDialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile_activity_save_button:
                setupSaveListener();
                break;
            case R.id.profile_activity_modify_button:
                deleteProfile();
                mSaveButton.setOnClickListener(this);
                break;
            case R.id.profile_activity_select_help_textview:
                setupHelpDialog();
                break;
        }
    }
}
