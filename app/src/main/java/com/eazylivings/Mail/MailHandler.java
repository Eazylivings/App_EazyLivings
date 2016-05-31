package com.eazylivings.Mail;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ProgressBar;

import com.eazylivings.R;
import com.eazylivings.activities.WelcomeScreen;
import com.eazylivings.activities.login.ForgotPassword;
import com.eazylivings.constant.Constants;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Vibek on 5/22/2016.
 */
public class MailHandler extends AsyncTask<String,Void,String> {

    Context context;
    AlertDialog alertDialog;
    static String result="";
    static String currentAction="";
    Activity activity;
    public MailHandler(Context ctx,Activity baseActivity){
        context=ctx;
        this.activity=baseActivity;
    }


    protected String doInBackground(String... params) {
        currentAction= params[0];
        String loginUrl = "http://eazylivings.com/mail.php";
        if(currentAction.equals(Constants.SENDEMAIL))
        {
        if (params[1].equals(Constants.FORGOTPASSWORD)) {
            try {
                URL url = new URL(loginUrl);
                HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
                httpUrlConnection.setRequestMethod("POST");
                httpUrlConnection.setDoOutput(true);
                httpUrlConnection.setDoInput(true);
                OutputStream outputStream = httpUrlConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String emailAddress = params[2];
                String post_data = URLEncoder.encode("emailAddress", "UTF-8") + "=" + URLEncoder.encode(emailAddress, "UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpUrlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpUrlConnection.disconnect();
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                preferences.edit().putString("result", result).commit();
                return result;
            } catch (MalformedURLException e) {
                String connectError = "Please Check Network Connection";
                alertDialog.setMessage(connectError);
                alertDialog.show();
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
        return result;
    }

    @Override
    protected void onPreExecute() {
        if(currentAction.equalsIgnoreCase(Constants.SENDEMAIL)) {

            ProgressBar progressBar = (ProgressBar) activity.findViewById(R.id.loginPage_progressBar_progress);
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onPostExecute(String accountAuthenticationString) {
        if (accountAuthenticationString.equalsIgnoreCase("Email Sent successfully")) {
            generatePopupMessage("The passsword for your account has been sent to your registered Email Id");
            Intent intent = new Intent(context,WelcomeScreen.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        }else {
            generatePopupMessage("Please provide a valid & registered Email Id and try again");
            Intent intent = new Intent(context,ForgotPassword.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    private void generatePopupMessage(String message){
        AlertDialog alertDialog = new AlertDialog.Builder(activity).create(); //Use context
        alertDialog.setTitle("Warning");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }



}
