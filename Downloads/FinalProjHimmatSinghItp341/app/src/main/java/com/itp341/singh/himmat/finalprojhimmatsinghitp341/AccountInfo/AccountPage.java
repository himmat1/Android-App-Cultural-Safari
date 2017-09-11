package com.itp341.singh.himmat.finalprojhimmatsinghitp341.AccountInfo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.itp341.singh.himmat.finalprojhimmatsinghitp341.MainActivity;
import com.itp341.singh.himmat.finalprojhimmatsinghitp341.R;
import com.itp341.singh.himmat.finalprojhimmatsinghitp341.UserSingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class AccountPage extends Fragment {
    private static final String TAG = AccountPage.class.getSimpleName();

    //Bundle key
    public static final String ARGS_POSITION = "args_position";

    //views that this fragment has
    Button mButtonZip;
    Button mButtonFirst;
    Button mButtonLast;
    Button mButtonEmail;
    Button mButtonAddress;
    Button mButtonCity;
    Button mLogOut;
    EditText mFirst;
    EditText mLast;
    EditText mEmail;
    EditText mAddress;
    EditText mCity;
    Spinner mState;
    EditText mZip;

    String lastNameText;
    String firstNameText;
    String addressText;
    String cityText;
    String stateText;
    String emailText;
    String postalCodeText;
    String licenseNumberText;
    String userId;

    HashMap<String,String> userInfo;

    public AccountPage() {
    }
    public static AccountPage newInstance() {
        Bundle args = new Bundle();

        AccountPage f = new AccountPage();
        f.setArguments(args);

        return f;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.activity_account_page, container, false);

        mButtonFirst = (Button) v.findViewById(R.id.textFirst);
        mButtonLast = (Button)  v.findViewById(R.id.textLast);
        mButtonEmail = (Button)  v.findViewById(R.id.textEmail);
        mButtonAddress = (Button) v.findViewById(R.id.textAddress);
        mButtonCity = (Button) v.findViewById(R.id.textCity);
        mButtonZip = (Button) v.findViewById(R.id.textZip);
        mLogOut = (Button)  v.findViewById(R.id.buttonLogOut);
        mFirst = (EditText) v.findViewById(R.id.editFirst);
        mLast= (EditText) v.findViewById(R.id.editLast);
        mEmail= (EditText) v.findViewById(R.id.editEmail);
        mAddress = (EditText) v.findViewById(R.id.editAddress);
        mCity  = (EditText) v.findViewById(R.id.editCity);
        mState = (Spinner) v.findViewById(R.id.state);
        mZip = (EditText) v.findViewById(R.id.editZip);

        UserSingleton user = UserSingleton.getInstance();

        mFirst.setText(user.getFirstName());
        mFirst.setText("Himmat");
        mLast.setText(user.getLastName());
        mLast.setText("Singh");
        mEmail.setText(user.getEmail());
        mEmail.setText("himmats@usc.edu");
        mAddress.setText(user.getAddress1());
        mAddress.setText("1211 Parkinson Avenue");
        mCity.setText(user.getCity());
        mCity.setText("Palo Alto");
        mZip.setText(user.getPostalCode());
        mZip.setText("94301");


        mButtonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstNameText = mFirst.getText().toString();
                mFirst.setText(firstNameText);
                Toast.makeText(getActivity(), "Success in Changing Your First Name", Toast.LENGTH_LONG).show();

            }
        });

        mButtonLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastNameText = mLast.getText().toString();
                mLast.setText(lastNameText);
                Toast.makeText(getActivity(), "Success in Changing Your Last Name", Toast.LENGTH_LONG).show();
            }
        });

        mButtonEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailText = mEmail.getText().toString();
                mEmail.setText(emailText);
                Toast.makeText(getActivity(), "Success in Changing Your Email", Toast.LENGTH_LONG).show();
            }
        });

        mButtonAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addressText = mAddress.getText().toString();
                mAddress.setText(addressText);
                Toast.makeText(getActivity(), "Success in Changing Your Address", Toast.LENGTH_LONG).show();
            }
        });
        mButtonCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityText = mCity.getText().toString();
                mCity.setText(cityText);
                Toast.makeText(getActivity(), "Success in Changing Your City", Toast.LENGTH_LONG).show();
            }
        });
        mButtonZip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postalCodeText = mZip.getText().toString();
                mZip.setText(postalCodeText);
                Toast.makeText(getActivity(), "Success in Changing Your Zip", Toast.LENGTH_LONG).show();
            }
        });

        mLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);

                //sign out

            }
        });
        return v;
    }
}
