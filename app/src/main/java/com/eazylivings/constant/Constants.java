package com.eazylivings.constant;

public class Constants {


    public final static int DATABASE_VERSION=1;
    public final static String DATABASE_NAME="eazylivings.db";

    public final static String USER_PROFILE_ACTION="userProfileAction";
    public final static String USER_PROFILE="getUserDetails";
    public static final String USER_PROFILE_URL="http://eazylivings.com/profile.php";

    //Sign Up details Table and Columns Names
    public final static String SIGNUP_DETAILS_TABLE="user_details_";
    public final static String CHECK_EXISTING_USER ="check existing user";

    public final static String COLUMN_USER_NAME="user_name";
    public final static String COLUMN_EMAIL_ADDRESS="email_address";
    public final static String COLUMN_CONTACT_NUMBER="contact_number";
    public final static String COLUMN_PASSWORD="password";
    public final static String COLUMN_ADDRESS="residential_address";

    public final static String USER_PREFERENCES_TABLE="user_preferences";

    public final static String LOGIN="login";
    public final static String REGISTER="register";
    public final static String LOGIN_URL="http://eazylivings.com/login.php";
    public final static String REGISTER_URL="http://eazylivings.com/register.php";

    public final static String FORGOTPASSWORD="forgotPassword";

    public final static String SEND_EMAIL ="send email";

    public final static String MESSAGE_FOR_SUCCESSFUL_RESET_PASSWORD ="We have sent your password on your registered email address.";
    public final static String MESSAGE_FAIL_RESET_PASSWORD="Oops!! Sorry Mate. Something happend while sending the password. Come back again after sometime.";

    public final static String FLAT_NAME="Planning to move into a flat?";
    public final static String FLAT_DESCRIPTION="We will help you in accumulating all the essentials that you will need from day one into your new flat";
    public final static String COOKING_NAME="Looking for a cook or want parcels";
    public final static String COOKING_DESCRIPTION="We will help you in finding the cook. At the same time if you don't want cook, we will provide you tiffins or nearest parcel center ";
    public final static String WASHING_NAME="Dont want to Iron or wash your clothes";
    public final static String WASHING_DESCRIPTION="We will help you in finding a person who will wash as well as iron your clothes";
    public final static String CLEANING_NAME="Looking for a cleaner?";
    public final static String CLEANING_DESCRIPTION="We will provide you people who will clean you house and make it hygienic all the time.";

    public static final String SAVE_USER_UPDATE="update information";

}
