package com.eazylivings.validator;

import android.content.Context;

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
 * Created by Vibek on 5/30/2016.
 */
public class ServerSideValidationHandler {

    Context context;
    static String result="";
    static String currentAction="";
    String userName="";

    protected String checkUserExists(String action,String userName) {
        currentAction=action;
        String CheckUserUrl = "http://eazylivings.com/ExistingUser.php";
          if (action.equals(Constants.CHECK_EXISTING_USER)) {
                try {
                    URL url = new URL(CheckUserUrl);
                    HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
                    httpUrlConnection.setRequestMethod("POST");
                    httpUrlConnection.setDoOutput(true);
                    httpUrlConnection.setDoInput(true);
                    OutputStream outputStream = httpUrlConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                    String emailAddress = userName;
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
                    return result;
                } catch (MalformedURLException e) {
                    String connectError = "Please Check Network Connection";
                    return connectError;
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

        return result;
    }

}
