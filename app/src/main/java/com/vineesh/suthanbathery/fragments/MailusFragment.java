package com.vineesh.suthanbathery.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vineesh.suthanbathery.MainActivity;
import com.vineesh.suthanbathery.R;
import com.vineesh.suthanbathery.utils.Constant;
import com.vineesh.suthanbathery.utils.GMailSender;


public class MailusFragment extends Fragment {

     TextInputLayout sTxInputname,sTxInputMob,
             sTxInputMsg;

    EditText sEtxtName,sEtxtMsg, sEtxtNum;
    LinearLayout btn_send;

    GMailSender sender;


    String body,
            name,
            message,
            mail,
            number,
            subject,
            fromMail,
            toMail
                    ;

    public MailusFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_mailus, container, false);





        sEtxtName = (EditText)root.findViewById(R.id.etxt_name);
        sEtxtNum = (EditText) root.findViewById(R.id.etxt_mob);
        sEtxtMsg = (EditText) root.findViewById(R.id.etxt_descri);

        btn_send = (LinearLayout)root.findViewById(R.id.btn_send);

        sTxInputname = (TextInputLayout) root.findViewById(R.id.text_input_name);
        sTxInputMob = (TextInputLayout) root.findViewById(R.id.text_input_mob);
        sTxInputMsg = (TextInputLayout) root.findViewById(R.id.text_input_descri);




        sEtxtName.addTextChangedListener(new MyTextWatcher(sEtxtName));
        sEtxtNum.addTextChangedListener(new MyTextWatcher(sEtxtNum));
        sEtxtMsg.addTextChangedListener(new MyTextWatcher(sEtxtMsg));




        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });


        return root;
    }

    private void submitForm() {

        if (!validateName()) {
            return;
        }

        if (!validatePhone()) {
            return;
        }


        if (!validatemessage()) {
            return;
        }


        //after the correct information just perform send action

        name=sEtxtName.getText().toString();
        number=sEtxtNum.getText().toString();
        message=sEtxtMsg.getText().toString();


        sender = new GMailSender(Constant.EMAIL, Constant.PASSWORD);

        fromMail = "mobile.expose@gmail.com";


        try {

            Log.w("hai", "First mail");
            //Mail To
            toMail = Constant.toMail_zeta;
            //Subject of Email
             subject = "Enquiry request";

            body = "Subject: "+subject+"\n\nMessage: "+message+"\n\nName: "+name +"\n"+"Email: "+mail;

            new MyAsyncClass().execute();

        }

        catch (Exception ex) {

            Toast.makeText(getActivity(), ex.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    private boolean validatePhone() {
        String mobile =sEtxtNum.getText().toString().trim();
        if(mobile.isEmpty()||!isValidMobile(mobile)||sEtxtNum.getText().toString().toString().length()<10 || mobile.length()>13 )

        {
            sTxInputMob.setError("wrong number");
            requestFocus(sEtxtNum);
            return false;
        }

        else {
            sTxInputMob.setErrorEnabled(false);
        }

        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


    private static boolean isValidMobile(String mobile)
    {
        return !TextUtils.isEmpty(mobile)&& Patterns.PHONE.matcher(mobile).matches();
    }
    private boolean validatemessage() {
        if (sEtxtMsg.getText().toString().trim().isEmpty()) {
            sTxInputMsg.setError(" messaages should not be blank");
            requestFocus(sEtxtMsg);
            return false;
        } else {
            sTxInputMsg.setErrorEnabled(false);
        }

        return true;
    }


    private boolean validateName() {
        if (sEtxtName.getText().toString().trim().isEmpty()) {
            sTxInputname.setError("enter a valid name");
            requestFocus(sEtxtName);
            return false;
        } else {
            sTxInputname.setErrorEnabled(false);
        }

        return true;
    }




    @Override
    public void onResume() {
        super.onResume();

    }

    private class MyTextWatcher implements TextWatcher {
        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.etxt_name:
                    validateName();
                    break;
                case R.id.etxt_mob:
                    validatePhone();
                    break;
                case R.id.etxt_descri:
                    validatemessage();
                    break;


            }
        }
    }




    private class MyAsyncClass extends AsyncTask<Void, Void, Void> {

        ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Please wait...");
            pDialog.show();

        }



        @Override
        protected Void doInBackground(Void... voids) {

            try {

                // Add subject, Body, your mail Id, and receiver mail Id.
                sender.sendMail(subject, body, fromMail, toMail);



            }

            catch (Exception ex) {

            }
            return null;

        }




        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            pDialog.cancel();
            Toast.makeText(getActivity(), "Email send success fully",Toast.LENGTH_SHORT).show();


            sEtxtName.setHint(subject.toString());
            sEtxtMsg.setHint(message.toString());

        }
    }

}
