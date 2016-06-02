package com.eazylivings.profile;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import com.eazylivings.VO.UserDetails;
import com.eazylivings.activities.MyAccount;
import com.eazylivings.constant.Constants;

import org.json.JSONArray;
import org.json.JSONException;
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

/**
 * Created by Vibek on 6/2/2016.
 */

    public class UserProfileHandler extends AsyncTask<String,Void,String> {
        Context context;
        AlertDialog alertDialog;
        static String result="";
        static String currentAction="";
        static String resultOfAction="";
        Activity activity;
        public UserProfileHandler(Context ctx,Activity baseActivity){
            context=ctx;
            this.activity=baseActivity;
        }

    @Override
    protected String doInBackground(String... params) {
        currentAction= params[0];
        String loginUrl = "http://eazylivings.com/profile.php";
        if(currentAction.equals(Constants.USER_PROFILE_ACTION))
        {
            if (params[1].equals(Constants.USER_PROFILE)) {
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
                    UserDetails userDetails=new UserDetails();
                    JSONArray jsonarray = new JSONArray(result);
                    for (int i = 0; i < jsonarray.length(); i++) {
                        JSONObject jsonobject = jsonarray.getJSONObject(i);
                        userDetails.setUserId(jsonobject.getInt("userId"));
                        userDetails.setUserName(jsonobject.getString("name"));
                        userDetails.setEmail_address(jsonobject.getString("emailId"));
                        userDetails.setContact_number(jsonobject.getString("phoneNo"));
                        userDetails.setResidential_address(jsonobject.getString("address"));
                    }
                    resultOfAction="detailsFetchSuccessful";
                    bufferedReader.close();
                    inputStream.close();
                    httpUrlConnection.disconnect();
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                    preferences.edit().putString("result", result).commit();
                    return resultOfAction;
                } catch (MalformedURLException e) {
                    String connectError = "Please Check Network Connection";
                    alertDialog.setMessage(connectError);
                    alertDialog.show();
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }catch(JSONException e){
                    e.printStackTrace();
                }


            }
        }
        return resultOfAction;
    }

    @Override
    protected void onPostExecute(String accountAuthenticationString) {
        if (accountAuthenticationString.equalsIgnoreCase("detailsFetchSuccessful")) {
            Intent intent = new Intent(context,MyAccount.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        }else {
            generatePopupMessage("Please Try again Later");
            Intent intent = new Intent(context,MyAccount.class);
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
