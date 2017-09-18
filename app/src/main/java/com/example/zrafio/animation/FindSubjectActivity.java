package com.example.zrafio.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FindSubjectActivity extends AppCompatActivity {

    private static final String TAG = "";
    //UI references
    private EditText idNumber, firstName, lastName;
    private TextView idLabel, firstNameLabel, lastNameLabel, genderLabel, birthdayLabel,
            dateLabel, registrationDateLabel;
    private RadioButton male;
    private Button create, pickDate, pickRegistrationDate;
    private View mProgressView;
    private View mFindSubjectFormView;

    private String gender, birthday, registrationDate;
    static SubjectsList subjectsList = new SubjectsList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_subject);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFindSubjectFormView = findViewById(R.id.find_subject_view);
        mProgressView = findViewById(R.id.find_subject_progress);

        idNumber = (EditText) findViewById(R.id.id_number_find);
        firstName = (EditText) findViewById(R.id.first_name_find);
        lastName = (EditText) findViewById(R.id.last_name_find);
        idLabel = (TextView) findViewById(R.id.id_label_find);
        firstNameLabel = (TextView) findViewById(R.id.first_name_label_find);
        lastNameLabel = (TextView) findViewById(R.id.last_name_label_find);
        genderLabel = (TextView) findViewById(R.id.gender_label_find);
        birthdayLabel = (TextView) findViewById(R.id.birthday_label_find);
        dateLabel = (TextView) findViewById(R.id.date_find);
        registrationDateLabel = (TextView) findViewById(R.id.registration_date_find);
        male = (RadioButton) findViewById(R.id.male_find);

        pickDate = (Button) findViewById(R.id.pick_date_find);
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

        pickRegistrationDate = (Button) findViewById(R.id.pick_registration_date_find);
        pickRegistrationDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePickerFragment = new DialogFragment() {
                    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                            registrationDateLabel.setText(i2 + "/" + (i1 + 1) + "/" + i);
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

        create = (Button) findViewById(R.id.find_subject_button);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptFindingRegisteredSubject();
            }
        });

    }

    public void attemptFindingRegisteredSubject() {
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
        registrationDate = registrationDateLabel.getText().toString();
        if (male.isChecked())
            gender = "Male";
        else gender = "Female";


        boolean cancel = false;
        View focusView = null;

        //Check inputs validity
        if (!lName.isEmpty() && !isPureString(lName)) {
            lastNameLabel.setError("");
            lastName.setText("");
            lastName.setHint("Letters only!");
            focusView = lastNameLabel;
            cancel = true;
        }

        if (!fName.isEmpty() && !isPureString(fName)) {
            firstNameLabel.setError("");
            firstName.setText("");
            firstName.setHint("Letters only!");
            focusView = firstNameLabel;
            cancel = true;
        }

        if (!id.isEmpty() && !isPureNumber(id)) {
            idLabel.setError(getString(R.string.error_field_required));
            idNumber.setText("");
            idNumber.setHint("Numbers only!");
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
            //TODO: retrieve subjects matching chosen values
            // Read from the database
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("subjects");
            myRef.addValueEventListener(subjectsList);
            Intent showList = new Intent(FindSubjectActivity.this,SubjectsListActivity.class);
            //showList.putParcelableArrayListExtra("subjects", (ArrayList<? extends Parcelable>) subjectsList.subjects);
            startActivity(showList);

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

        mFindSubjectFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        mFindSubjectFormView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mFindSubjectFormView.setVisibility(show ? View.GONE : View.VISIBLE);
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

    public boolean isPureString(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isLetter(s.charAt(i)))
                return false;
        }
        return true;
    }

    public boolean isPureNumber(String s) {
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void onRadioButtonClicked(View view) {
    }

}
