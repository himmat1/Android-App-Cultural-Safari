package com.itp341.singh.himmat.finalprojhimmatsinghitp341;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by himmatsingh on 4/25/17.
 */

public class CreateAccountActivity extends Activity{
    private static final String TAG = CreateAccountActivity.class.getSimpleName();

    //add Firebase Auth members
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser user;

    EditText lastName;
    EditText firstName;
    EditText password;
    EditText confirmPassword;
    EditText addressLine1;
    EditText city;
    Spinner state;
    EditText email;
    EditText postalCode;
    EditText phoneNumber;
    EditText dateOfBirth;
    Button createAccountButton;

    String hashedPasswordText;
    String lastNameText;
    String firstNameText;
    String passwordText;
    String confirmPasswordText;
    String addressLine1Text;
    String cityText;
    String stateText;
    String emailText;
    String postalCodeText;
    String phoneNumberText;
    String dateOfBirthText;

    DatePickerDialog.OnDateSetListener date;
    Calendar myCalendar;
    //FirebaseDatabase firebase;
public CreateAccountActivity(){

}

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        firstName = (EditText)findViewById(R.id.first_name);
        lastName = (EditText)findViewById(R.id.last_name);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        confirmPassword = (EditText)findViewById(R.id.confirm_password);
        addressLine1 = (EditText)findViewById(R.id.address_line_1);
        city=(EditText) findViewById(R.id.city);
        state=(Spinner) findViewById(R.id.state);
        postalCode = (EditText)findViewById(R.id.postal_code);
        phoneNumber = (EditText)findViewById(R.id.phone_number);
        dateOfBirth = (EditText)findViewById(R.id.date_of_birth);
        createAccountButton = (Button) findViewById(R.id.sign_up_button);

        mAuth = FirebaseAuth.getInstance();

        //TODO attach new authlistener to detect sign in/out
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();
                if(user !=null)//user is logged in
                { Log.d(TAG, "Signed In:" + user.getUid());}
                else //signed out
                {Log.d(TAG, "User Currently signed out");}

                //updateUI(user);//////////////////???????????
            }

        };

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hashedPasswordText= HashPassword.encryptPassword(password.getText().toString());
                lastNameText =lastName.getText().toString();
                firstNameText=firstName.getText().toString();
                passwordText=password.getText().toString();
                confirmPasswordText=confirmPassword.getText().toString();
                addressLine1Text=addressLine1.getText().toString();
                cityText=city.getText().toString();
                stateText=state.getItemAtPosition(state.getSelectedItemPosition()).toString();
                emailText=email.getText().toString();
                postalCodeText=postalCode.getText().toString();
                phoneNumberText=phoneNumber.getText().toString();
                dateOfBirthText=dateOfBirth.getText().toString();

                Boolean fieldsValidated=true;


                if(!passwordText.equals(confirmPasswordText) || passwordText.length()<6)
                {
                    password.setError("Make sure your passwords match and are 6 characters or longer!");
                    confirmPassword.setError("Make sure your passwords match and 6 characters or longer!");
                    fieldsValidated=false;
                }
                if(!MainActivity.isValidEmail(emailText))
                {
                    email.setError("Enter a valid email!");
                    fieldsValidated=false;
                }
                if(addressLine1Text.length()<5)
                {
                    addressLine1.setError("Make sure you entered a valid email");
                    fieldsValidated=false;
                }
                if(state.getSelectedItemPosition()==0)
                {
                    TextView errorText = (TextView)state.getSelectedView();
                    errorText.setError("Select a State");
                    errorText.setTextColor(Color.RED);

                    fieldsValidated=false;
                }
                if((postalCodeText.length()!=5))
                {
                    postalCode.setError("Invalid Postal Code!");
                    fieldsValidated=false;
                }
                if(!android.util.Patterns.PHONE.matcher(phoneNumberText).matches() || phoneNumberText.length()!=10)
                {
                    phoneNumber.setError("Invalid Phone Number!");
                    fieldsValidated=false;
                }
                if(dateOfBirthText=="")
                {
                    dateOfBirth.setError("Select your Date of Birth!");
                    fieldsValidated=false;
                }

                //if all fields are validated then create user by adding to database
                if(fieldsValidated)
                {
                    Log.d("Validated","Validated");
                    createAccount(emailText,passwordText);
                    finish();
                }

                else
                {
                    fieldsValidated=true;
                    Log.d("Not Validated","Not Validated");
                }




            }
        });

        //following is the listener for the date picker dialog that allows selecting of user birthday
        myCalendar = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "yyyy-MM-dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                dateOfBirth.setText(sdf.format(myCalendar.getTime()));
            }
        };

        dateOfBirth.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    new DatePickerDialog(CreateAccountActivity.this,android.R.style.Theme_Holo_Light_Dialog,date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });
    }



    public static boolean isNumeric(String s) {
        boolean isValidInteger = false;
        try
        {
            Integer.parseInt(s);

            // s is a valid integer

            isValidInteger = true;
        }
        catch (NumberFormatException ex)
        {
            // s is not an integer
        }

        return isValidInteger;
    }

    @Override
    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener); //add the auth listener
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
        //updateUI(null);
        mAuth.signOut();
    }

    private void createAccount(String email, String password) {
        Log.d(TAG, "Attempting createAccount:" + email);
//        if (!validateForm()) {
//            Log.d(TAG, "Create Account credenitals not valid");
//            return;
//        }

        // [START create_user_with_email]
        // If sign in fails, display a message to the user. If sign in succeeds
        // the auth state listener will be notified and logic to handle the
        // signed in user can be handled in the listener.
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Account Created",
                                    Toast.LENGTH_SHORT).show();
                        }

                        if (!task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

//    public void signIn(String email, String password) {
//        Log.d(TAG, "Attempting signIn:" + email);
////        if (!validateForm()) {
////            Log.d(TAG, "signIn credenitals not valid");
////            return;
////        }
//
//
//        // [START sign_in_with_email]
//        mAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
//                        if(task.isSuccessful()){
//                            //Intent i = new Intent(this, AboutActivity.class);
//                            //startActivity(i);
//                            Log.d(TAG, "LOGGED IN:" + task.isSuccessful());
//                        }
//                        // If sign in fails, display a message to the user. If sign in succeeds
//                        // the auth state listener will be notified and logic to handle the
//                        // signed in user can be handled in the listener.
//                        if (!task.isSuccessful()) {
//                            Log.w(TAG, "signInWithEmail:failed", task.getException());
//                            Toast.makeText(getApplicationContext(), R.string.auth_failed,
//                                    Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//                });
//    }



//    private boolean validateForm() {
//        boolean valid = true;
//
//        String email = mUsernameView.getText().toString();
//        if (TextUtils.isEmpty(email)) {
//            mUsernameView.setError("Required.");
//            valid = false;
//        } else {
//            mUsernameView.setError(null);
//        }
//
//        String password = mPasswordView.getText().toString();
//        if (TextUtils.isEmpty(password)) {
//            mPasswordView.setError("Required.");
//            valid = false;
//        } else {
//            mPasswordView.setError(null);
//        }
//
//        return valid;
//    }


}
