package com.eazylivings.constant;

public class Constants {


    public final static int DATABASE_VERSION=1;
    public final static String DATABASE_NAME="eazylivings.db";



    //Sign Up details Table and Columns Names
    public final static String SIGNUP_DETAILS_TABLE="user_details_";
    public final static String CHECK_EXISTING_USER ="check existing user";

    public final static String COLUMN_USER_NAME="user_name";
    public final static String COLUMN_EMAIL_ADDRESS="email_address";
    public final static String COLUMN_CONTACT_NUMBER="contact_number";
    public final static String COLUMN_PASSWORD="password";
    public final static String COLUMN_ADDRESS="residential_address";

    public final static String USER_DETAILS_TABLE="user_details";
    public final static String USER_PREFERENCES_TABLE="user_preferences";

    public final static String LOGIN="login";
    public final static String REGISTER="register";
    public final static String LOGIN_URL="http://eazylivings.com/login.php";
    public final static String REGISTER_URL="http://eazylivings.com/register.php";

    public final static String FORGOTPASSWORD="forgotPassword";

    public final static String SEND_EMAIL ="send email";

    public final static String MESSAGE_FOR_SUCCESSFUL_RESET_PASSWORD ="We have sent your password on your registered email address.";
    public final static String MESSAGE_FAIL_RESET_PASSWORD="Oops!! Sorry Mate. Something happend while sending the password. Come back again after sometime.";



}
