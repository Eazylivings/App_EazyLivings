package com.eazylivings.databasehandler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eazylivings.R;
import com.eazylivings.VO.UserDetails;
import com.eazylivings.activities.WelcomeScreen;
import com.eazylivings.constant.Constants;
import com.eazylivings.sharedpreference.SharedPreference;

import org.json.JSONArray;
import org.json.JSONObject;

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

public class ServerDatabaseHandler  extends AsyncTask<String,Void,String> {

    Context context;
    static String currentAction="";
    String userName="";
    UserDetails userDetails;
    Activity activity;

    public ServerDatabaseHandler(Context ctx,Activity baseActivity){
        context=ctx;
        this.activity=baseActivity;
    }

    @Override
    protected String doInBackground(String... params) {

        String result="";
        currentAction = params[0];
        userName=params[1];

        String post_data="";

        try {
            URL url=new URL(Constants.LOGIN_URL);

            if(currentAction.equalsIgnoreCase(Constants.LOGIN)){
                url = new URL(Constants.LOGIN_URL);
                post_data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(userName, "UTF-8") + "&"
                        + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8");

            }else if(currentAction.equalsIgnoreCase(Constants.REGISTER)){
                url = new URL(Constants.REGISTER_URL);
                userDetails=new UserDetails();
                post_data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(userName, "UTF-8") + "&"
                        + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8")+"&"
                        + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(params[3], "UTF-8")+"&"
                        + URLEncoder.encode("phoneNo", "UTF-8") + "=" + URLEncoder.encode(params[4], "UTF-8");

                userDetails.setUserName(userName);
                userDetails.setPassword(params[2]);
                userDetails.setEmail_address(params[3]);
                userDetails.setContact_number(params[4]);

            }else if(currentAction.equalsIgnoreCase(Constants.USER_PROFILE_ACTION)){
                url = new URL(Constants.USER_PROFILE_URL);
                post_data = URLEncoder.encode("emailAddress", "UTF-8") + "=" + URLEncoder.encode(userName, "UTF-8");

           }else if(currentAction.equalsIgnoreCase(Constants.SAVE_USER_UPDATE)){
                url = new URL(Constants.USER_PROFILE_URL);
                post_data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(userName, "UTF-8") + "&"
                        + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8")+"&"
                        + URLEncoder.encode("phoneNo", "UTF-8") + "=" + URLEncoder.encode(params[3], "UTF-8");
            }else if(currentAction.equalsIgnoreCase(Constants.FORGOT_PASSWORD)){
                url = new URL(Constants.FORGOT_PASSWORD_MAIL_URL);
                post_data = URLEncoder.encode("emailAddress", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8");
            }

            HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
            httpUrlConnection.setRequestMethod("POST");
            httpUrlConnection.setDoOutput(true);
            httpUrlConnection.setDoInput(true);
            OutputStream outputStream = httpUrlConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();

            outputStream.close();

            InputStream inputStream = httpUrlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }

            bufferedReader.close();
            inputStream.close();
            httpUrlConnection.disconnect();
            if(currentAction.equalsIgnoreCase(Constants.USER_PROFILE_ACTION)){

                userDetails=new UserDetails();
                try {
                    JSONArray jsonarray = new JSONArray(result);
                    for (int i = 0; i < jsonarray.length(); i++) {
                        JSONObject jsonobject = jsonarray.getJSONObject(i);
                        userDetails.setUserId(jsonobject.getInt("userId"));
                        userDetails.setUserName(jsonobject.getString("name"));
                        userDetails.setEmail_address(jsonobject.getString("emailId"));
                        userDetails.setContact_number(jsonobject.getString("phoneNo"));
                        userDetails.setResidential_address(jsonobject.getString("address"));
                    }

                    if (userDetails!=null) {
                        DeviceSetup deviceSetup;
                        deviceSetup=new DeviceSetup(context);
                        deviceSetup.saveUserDetailsUsingSharedPreference(userDetails,context);
                    }else {
                        generatePopupMessage("Some error occurred. Please try again after sometime");
                    }

                }catch (Exception e){
                    e.printStackTrace();

                }

            }
            return result;
        } catch (MalformedURLException e) {
            result="Exception Occurred";
            return result;
        } catch (IOException e) {
            result="Exception Occurred";
            return result;
        }catch(Exception e){
            result="Exception Occurred";
            return result;
        }
    }

    @Override
    protected void onPreExecute() {

        if(currentAction.equalsIgnoreCase(Constants.LOGIN)) {

            ProgressBar progressBar = (ProgressBar) activity.findViewById(R.id.loginPage_progressBar_progress);
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onPostExecute(String accountAuthenticationString) {

        if(currentAction.equalsIgnoreCase(Constants.LOGIN)) {
            ProgressBar progressBar = (ProgressBar) activity.findViewById(R.id.loginPage_progressBar_progress);
            progressBar.setVisibility(View.INVISIBLE);
        }

        if (accountAuthenticationString.equalsIgnoreCase("Login Success")) {

            setSharedPreferences("userName",userName);
            Intent intent = new Intent(context,WelcomeScreen.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            activity.finish();

        } else if(accountAuthenticationString.equalsIgnoreCase("Login Failed")) {
            generatePopupMessage("Please check login details and try again");

        }else if(accountAuthenticationString.equalsIgnoreCase("Registration Success")){
            generatePopupMessage("Failed to register. Please try again with correct inputs");

        }else if(accountAuthenticationString.equalsIgnoreCase("Registration Failed")){
            generatePopupMessage("Failed to register. Please try again with correct inputs");

        }else if(accountAuthenticationString.equalsIgnoreCase("Email Sent successfully")){
            TextView defaultMessage = (TextView) activity.findViewById(R.id.forgotPassword_button_defaultMessage);
            Button signInButton=(Button)activity.findViewById(R.id.forgotPassword_button_signIn);
            ImageView image = (ImageView) activity.findViewById(R.id.forgotPassword_button_image);

            if(defaultMessage!=null && signInButton!=null && image!=null){
                defaultMessage.setText(Constants.MESSAGE_FOR_SUCCESSFUL_RESET_PASSWORD);
                signInButton.setVisibility(View.VISIBLE);
                image.setBackgroundResource(R.drawable.emailsentsmiley);
            }else if(defaultMessage!=null && image!=null){
                defaultMessage.setText(Constants.MESSAGE_FAIL_RESET_PASSWORD);
                image.setBackgroundResource(R.drawable.failtosendpassword);

            }else{
                generatePopupMessage("Some error occurred. Please try again after sometime");
            }
        }else{
            generatePopupMessage("Some error occurred. Please try again after sometime");
        }

    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    private void setSharedPreferences(String key,String value){


        SharedPreference sharedPreference=new SharedPreference();
        sharedPreference.setStringValueInSharedPreference(context,key,value);
        sharedPreference.setBooleanValueInSharedPreference(context,Constants.SHARED_PREFERENCE_LOGIN_STATUS,true);
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
