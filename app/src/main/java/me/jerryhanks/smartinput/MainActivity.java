package me.jerryhanks.smartinput;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import me.jerryhanks.smartinput.fieldvalidator.ConfirmFieldValidator;
import me.jerryhanks.smartinput.fieldvalidator.EmailFieldValidator;
import me.jerryhanks.smartinput.fieldvalidator.PasswordFieldValidator;
import me.jerryhanks.smartinput.fieldvalidator.RequiredFieldValidator;

public class MainActivity extends AppCompatActivity implements View.OnFocusChangeListener, View.OnClickListener {

    private static final int PASS_MIN_LENGTH = 6;
    private TextInputEditText mNameEd;
    private TextInputEditText mEmailEd;
    private TextInputEditText mPasswordEd;
    private TextInputEditText mConfirmPasswordEd;
    private RequiredFieldValidator mNameFieldValidator;
    private EmailFieldValidator mEmailFieldValidator;
    private PasswordFieldValidator mPasswordFieldValidator;
    private ConfirmFieldValidator mConfirmFieldValidator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextInputLayout mNameWrapper = (TextInputLayout) findViewById(R.id.name_ed_wrapper);
        mNameEd = (TextInputEditText) findViewById(R.id.name_ed);

        TextInputLayout mEmailWrapper = (TextInputLayout) findViewById(R.id.email_ed_wrapper);
        mEmailEd = (TextInputEditText) findViewById(R.id.email_ed);

        TextInputLayout mPasswordWrapper = (TextInputLayout) findViewById(R.id.password_ed_wrapper);
        mPasswordEd = (TextInputEditText) findViewById(R.id.password_ed);

        TextInputLayout mConfirmPasswordWrapper = (TextInputLayout) findViewById(R.id.confirm_password_ed_wrapper);
        mConfirmPasswordEd = (TextInputEditText) findViewById(R.id.confirm_password_ed);

        Button mSignUpButton = (Button) findViewById(R.id.submit_btn);


        //set onFocus change Listener to all the EditText Views
        mNameEd.setOnFocusChangeListener(this);
        mEmailEd.setOnFocusChangeListener(this);
        mPasswordEd.setOnFocusChangeListener(this);
        mConfirmPasswordEd.setOnFocusChangeListener(this);

        //init all the validators
        mNameFieldValidator = new RequiredFieldValidator(mNameWrapper);
        mEmailFieldValidator = new EmailFieldValidator(mEmailWrapper);
        mPasswordFieldValidator = new PasswordFieldValidator(mPasswordWrapper, PASS_MIN_LENGTH);
        mConfirmFieldValidator = new ConfirmFieldValidator(mConfirmPasswordWrapper);

        //set the sibmit button onclick Listener
        mSignUpButton.setOnClickListener(this);


    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if (hasFocus) {
            return; //we want to validate only fields loosing focus and not fields gaining focus
        }

        int id = view.getId();
        if (id == R.id.name_ed) {
            mNameFieldValidator.validate(mNameEd.getText().toString());
        } else if (id == R.id.email_ed) {
            mEmailFieldValidator.validate(mEmailEd.getText().toString());
        } else if (id == R.id.password_ed) {
            mPasswordFieldValidator.validate(mPasswordEd.getText().toString());
        } else if (id == R.id.confirm_password_ed) {
            mConfirmFieldValidator.confirm(mConfirmPasswordEd.getText().toString(), mPasswordEd.getText().toString());
        }

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.submit_btn) {
            //d a final validation here
            String name = mNameEd.getText().toString();
            String email = mEmailEd.getText().toString();
            String password = mPasswordEd.getText().toString();
            String confirmPass = mConfirmPasswordEd.getText().toString();

            boolean isNameValid = mNameFieldValidator.validate(name);
            boolean isEmailValid = mEmailFieldValidator.validate(email);
            boolean isPasswordValid = mPasswordFieldValidator.validate(password);
            boolean isPasswordConfirmed = mConfirmFieldValidator.confirm(confirmPass, password);

            if (isNameValid && isEmailValid && isPasswordValid && isPasswordConfirmed) {
                //go ahead ans submit the form for all things are fine now
                Toast.makeText(this, "All field Validations passed", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
