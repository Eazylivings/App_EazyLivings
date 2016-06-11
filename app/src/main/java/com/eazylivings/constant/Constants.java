package com.eazylivings.constant;

public class Constants {


    public final static int DATABASE_VERSION=1;
    public final static String DATABASE_NAME="eazylivings.db";

    public final static String USER_PROFILE_ACTION="userProfileAction";
    public final static String USER_PROFILE="getUserDetails";


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


    public final static String FORGOT_PASSWORD ="forgotPassword";

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




    //Popup messages
    public static final String CHECK_EMAIL_ADDRESS ="Please check email address";
    public static final String ENTER_USERNAME="Hey Mate, you forgot to enter username!!";
    public static final String ENTER_PASSWORD="Hey Mate, you forgot to enter password!!";
    public static final String ENTER_EMAIL_ADDRESS="Hey Mate, you forgot to enter email address!!";
    public static final String ENTER_CONTACT_NUMBER="Hey Mate, you forgot to enter contact number!!";
    public static final String ENTER_CORRECT_EMAIL_ADDRESS="Hey Mate, email address is not correct. Please check once";
    public static final String ENTER_CORRECT_USERNAME="Hey Mate, user name is not correct. Please check once";
    public static final String ENTER_CORRECT_PASSWORD="Hey Mate, password is not correct. Please check once";
    public static final String ENTER_CORRECT_CONTACT_NUMBER="Hey Mate, contact number is not correct. Please check once";
    public static final String NOT_ONLINE="Oops!! You are not online.";
    public static final String USER_ALREADY_PRESENT="We think you are already part of EazyLivings. Please try to login with this email address";
    public static final String ERROR_MESSAGE="This should not happen. Anyways something went wrong.Come back again fella ";
    public static final String LOGIN_FOR_PROFILE="Please login to see your profile";
    public static final String UPDATE_ACCOUNT="You made changes in your profile. Are you sure you want to leave without updating changes";

    //URLS
    public final static String LOGIN_URL="http://eazylivings.com/login.php";
    public final static String REGISTER_URL="http://eazylivings.com/register.php";
    public static final String USER_PROFILE_URL="http://eazylivings.com/profile.php";
    public static final String FORGOT_PASSWORD_MAIL_URL="http://eazylivings.com/mail.php";
    public static final String CHECK_EXISTING_USER_URL="http://eazylivings.com/ExistingUser.php";


    //Alert Titles
    public static final String ALERT_TITLE="Message";
    public static final String ALERT_WARNING="Warning";
    public static final String ALERT_CONFIRM="Confirm";

    //Action Bar Titles
    public static final String OFFERED_SERVICES_TITLE="EazyLivings's Offerings";
    public static final String WALK_THROUGH_SERVICES_FLAT_TITLE="";
    public static final String WALK_THROUGH_SERVICES_COOKING_TITLE="";
    public static final String WALK_THROUGH_SERVICES_CLEANING_TITLE="";
    public static final String WALK_THROUGH_SERVICES_WASHING_TITLE="";
    public static final String FORGOT_PASSWORD_TITLE="Forgot Password";
    public static final String SIGN_UP_TITLE ="Sign Up";
    public static final String SIGN_IN_TITLE="Sign In";
    public static final String CHOICE_OF_COOKING_TITLE="Preferred Choice";
    public static final String CONFIGURE_GROCERY_LIST_TITLE="Configure Grocery";
    public static final String COOK_SELECTION_TITLE="Select Type";
    public static final String FLAT_SETUP_TITLE="Flat Setup";
    public static final String SELECT_COOK_TITLE="Select Cook";
    public static final String MY_ACCOUNT_TITLE="My Account";



    //Shared Preference Key
    public static final String SHARED_PREFERENCE_CLICKED_SERVICE="clickedService";
    public static final String SHARED_PREFERENCE_USERNAME="userName";
    public static final String SHARED_PREFERENCE_LOGIN_STATUS="loginStatus";
    public static final String SHARED_PREFERENCE_PROFILE_ALREADY_LOADED="isProfileAlreadyLoaded";
    public static final String SHARED_PREFERENCE_DEFAULT_USERNAME="Newbie.";
    public static final String SHARED_PREFERENCE_DEFAULT_STRING="Empty";
    public static final String SHARED_PREFERENCE_EMAIL_ADDRESS="emailAddress";

    //Exception messages
    public static final String EXCEPTION_LOADING_PAGE="Unable to load the page. Please try again after sometime.";
    public static final String EXCEPTION_FORGOT_PASSWORD_RETRIEVE="Unable to retrieve password. Please try again after sometime.";
    public static final String EXCEPTION_SIGN_IN="Not able to signIn. Try again after sometime.";
    public static final String EXCEPTION_UPDATE_INFORMATION="Failed to save information. Try again after sometime.";
    public static final String EXCEPTION_COOK_SELECTION="Failed to select any selection. Please try again after sometime.";



    public static final String WELCOME="Welcome ";
    public static final String NEWBIE="Newbie!!";


}


