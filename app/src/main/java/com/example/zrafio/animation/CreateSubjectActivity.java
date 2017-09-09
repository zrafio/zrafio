package com.example.zrafio.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class CreateSubjectActivity extends AppCompatActivity {

    //UI references
    private EditText idNumber, firstName, lastName;
    private TextView idLabel, firstNameLabel, lastNameLabel, genderLabel, birthdayLabel, dateLabel;
    private RadioButton male;
    private Button create, pickDate;
    private View mProgressView;
    private View mCreateSubjectFormView;

    private String gender, birthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_subject);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mCreateSubjectFormView = findViewById(R.id.create_subject_view);
        mProgressView = findViewById(R.id.create_subject_progress);

        idNumber = (EditText) findViewById(R.id.id_number_new);
        firstName = (EditText) findViewById(R.id.first_name_new);
        lastName = (EditText) findViewById(R.id.last_name_new);
        idLabel = (TextView) findViewById(R.id.id_label_new);
        firstNameLabel = (TextView) findViewById(R.id.first_name_label_new);
        lastNameLabel = (TextView) findViewById(R.id.last_name_label_new);
        genderLabel = (TextView) findViewById(R.id.gender_label_new);
        birthdayLabel = (TextView) findViewById(R.id.birthday_label_new);
        dateLabel = (TextView) findViewById(R.id.date_new);
        male = (RadioButton) findViewById(R.id.male_new);

        pickDate = (Button) findViewById(R.id.pick_date_new);
        pickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePickerFragment = new DialogFragment() {
                    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                            dateLabel.setText(i2 + "/" + (i1 + 1) + "/" + i);
                        }
                    };

                    @Override
                    public Dialog onCreateDialog(Bundle savedInstanceState) {
                        return new DatePickerDialog(getActivity(), listener, 1970, 0, 1);
                    }

                };
                datePickerFragment.show(getFragmentManager(), "datePicker");
            }
        });

        create = (Button) findViewById(R.id.create_new_subject);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptCreatingNewSubject();
            }
        });

    }

    public void attemptCreatingNewSubject() {
        // Reset errors.
        idLabel.setError(null);
        firstNameLabel.setError(null);
        lastNameLabel.setError(null);
        genderLabel.setError(null);
        birthdayLabel.setError(null);

        // Store values.
        String id = idNumber.getText().toString();
        String fName = firstName.getText().toString();
        String lName = lastName.getText().toString();
        birthday = dateLabel.getText().toString();
        if (male.isChecked())
            gender = "Male";
        else gender = "Female";


        boolean cancel = false;
        View focusView = null;

        //Check inputs validity
        if (TextUtils.isEmpty(birthday)) {
            birthdayLabel.setError(getString(R.string.error_field_required));
            focusView = birthdayLabel;
            cancel = true;
        }

        if (TextUtils.isEmpty(lName)){
            lastNameLabel.setError(getString(R.string.error_field_required));
            focusView = lastNameLabel;
            cancel = true;
        } else if (!isPureString(lName)){
            lastNameLabel.setError("Invalid input value!");
            focusView = lastNameLabel;
            cancel = true;
        }else if (lName.length()<3){
            lastNameLabel.setError("Too short!");
            focusView = lastNameLabel;
            cancel = true;
        }

        if (TextUtils.isEmpty(fName)){
            firstNameLabel.setError(getString(R.string.error_field_required));
            focusView = firstNameLabel;
            cancel = true;
        } else if (!isPureString(fName)){
            firstNameLabel.setError("Invalid input value!");
            focusView = firstNameLabel;
            cancel = true;
        }else if (fName.length()<3){
            firstNameLabel.setError("Too short!");
            focusView = firstNameLabel;
            cancel = true;
        }

        if (TextUtils.isEmpty(id)){
            idLabel.setError(getString(R.string.error_field_required));
            focusView = idLabel;
            cancel = true;
        } else if (!isPureNumber(id)){
            idLabel.setError(getString(R.string.error_field_required));
            focusView = idLabel;
            cancel = true;
        }

        if (cancel) {
             /*There was an error; don't perform the attempt and focus the first
             form field with an error.*/
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
        }
    }

    /**
     * Shows the progress UI and hides the form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.

            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mCreateSubjectFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mCreateSubjectFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mCreateSubjectFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
    }

    public boolean isPureString(String s){
        for (int i=0;i<s.length();i++){
            if (!Character.isLetter(s.charAt(i)))
                return false;
        }
        return true;
    }

    public boolean isPureNumber(String s){
        try{
            Long.parseLong(s);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }

    public void onRadioButtonClicked(View view) {
    }
}