package com.eazylivings.constant;

public class Constants {


    public final static String USER_PROFILE_ACTION="userProfileAction";

    //Sign Up details Table and Columns Names

    public final static String CHECK_EXISTING_USER ="check existing user";

    public final static String LOGIN="login";
    public final static String REGISTER="register";


    public final static String FORGOT_PASSWORD ="forgotPassword";

    public final static String MESSAGE_FOR_SUCCESSFUL_RESET_PASSWORD ="We have sent your password on your registered email address.";
    public final static String MESSAGE_FAIL_RESET_PASSWORD="Oops!! Sorry Mate. Something happend while sending the password. Come back again after sometime.";

    public final static String FLAT_NAME="Planning to move into a flat?";
    public final static String COOKING_NAME="Looking for a cook or want parcels";
    public final static String WASHING_NAME="Dont want to Iron or wash your clothes";
    public final static String CLEANING_NAME="Looking for a cleaner?";
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
    public static final String NOT_ONLINE="Oops!! You are not online.";
    public static final String USER_ALREADY_PRESENT="We think you are already part of EazyLivings. Please try to login with this email address";
    public static final String ERROR_MESSAGE="This should not happen. Anyways something went wrong.Come back again fella ";
    public static final String LOGIN_FOR_PROFILE="Please login to see your profile";
    public static final String UPDATE_ACCOUNT="You made changes in your profile. Are you sure you want to leave without updating changes";
    public static final String SERVER_HANDLER_FAILED_IN_POST_EXECUTE="Some error occurred. Please try again after sometime.";
    public static final String SERVER_HANDLER_REGISTRATION_FAILED_POPUP="Failed to register. Please try again.";
    public static final String SERVER_HANDLER_LOGIN_FAIL_POPUP="Failed to login. Please try again later.";
    public static final String REGISTRATION_SUCCESS_POPUP="Successfully Registered. Welcome to EazyLivings. You will be redirected to Home page.";
    public static final String LOGIN_TO_PROCEED="Please login to proceed further";
    public static final String COOK_FOR_VEG_NON_VEG_SELECT_CHOICE="Please select one of the options.";
    public static final String CHECK_BOX_SELECTION="Please choose alteast two options.";

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
    public static final String TITLE_OFFERED_SERVICES="EazyLivings's Offerings";
    public static final String TITLE_WALK_THROUGH_SERVICES_FLAT ="FLAT SETUP";
    public static final String TITLE_WALK_THROUGH_SERVICES_COOKING ="COOKING";
    public static final String TITLE_WALK_THROUGH_SERVICES_CLEANING ="CLEANING";
    public static final String TITLE_WALK_THROUGH_SERVICES_WASHING ="WASHING";
    public static final String TITLE_FORGOT_PASSWORD ="Forgot Password";
    public static final String TITLE_SIGN_UP ="Sign Up";
    public static final String TITLE_SIGN_IN ="Sign In";
    public static final String TITLE_PREFERRED_WAY_OF_COOKING ="Preferred Choice";
    public static final String TITLE_CONFIGURE_GROCERY_LIST="Configure Grocery";
    public static final String TITLE_COOK_FOR_VEG_OR_NON_VEG ="SELECT TYPE";
    public static final String TITLE_COOK_SELECTION ="SELECT COOK";
    public static final String TITLE_MY_ACCOUNT ="My Account";
    public static final String TITLE_ABOUT_US="About Us";
    public static final String TITLE_CONTACT_US="Contact Us";
    public static final String TITLE_UPDATE_ACCOUNT="Update Information";
    public static final String TITLE_FLAT_SUB_SERVICE="Flat Areas";
    public static final String TITLE_SELECT_ITEMS_FOR_FLAT="Select Items";



    //Shared Preference Key
    public static final String SHARED_PREFERENCE_CLICKED_SERVICE="clickedService";
    public static final String SHARED_PREFERENCE_USERNAME="userName";
    public static final String SHARED_PREFERENCE_LOGIN_STATUS="loginStatus";
    public static final String SHARED_PREFERENCE_PROFILE_ALREADY_LOADED="isProfileAlreadyLoaded";
    public static final String SHARED_PREFERENCE_DEFAULT_USERNAME="Newbie.";
    public static final String SHARED_PREFERENCE_DEFAULT_STRING="Empty";
    public static final String SHARED_PREFERENCE_DEFAULT_EMAIL="newbie@eazylivings.com";
    public static final String SHARED_PREFERENCE_EMAIL_ADDRESS="emailAddress";
    public static final String SHARED_PREFERENCE_CONTACT_NUMBER="contactNumber";
    public static final String SHARED_PREFERENCE_ADDRESS="address";
    public static final String SHARED_PREFERENCE_PREVIOUS_ACTIVITY ="previousActivity";
    public static final String SHARED_PREFERENCE_SELECTED_VEG_NON_VEG="selectedVegOrNonVeg";
    public static final String SHARED_PREFERENCE_MEALS_CHOICE="mealChoice";
    public static final String SHARED_PREFERENCE_COOK_CHOICE="cookCohice";
    public static final String SHARED_PREFERENCE_PREFERRED_WAY_OF_COOKING="preferredWayOfCooking";


    //Exception messages
    public static final String EXCEPTION_LOADING_PAGE="Unable to load the page. Please try again after sometime.";
    public static final String EXCEPTION_FORGOT_PASSWORD_RETRIEVE="Unable to retrieve password. Please try again after sometime.";
    public static final String EXCEPTION_SIGN_IN="Not able to signIn. Try again after sometime.";
    public static final String EXCEPTION_UPDATE_INFORMATION="Failed to save information. Try again after sometime.";
    public static final String EXCEPTION_COOK_FOR_VEG_OR_NON_VEG ="Failed to select any selection. Please try again after sometime.";
    public static final String EXCEPTION_SERVER_HANDLER_CONNECTION_FAILED="Failed ot connect with server.Please try again after sometime.";
    public static final String EXCEPTION_SETTING_WIDTH_HEIGHT="Failed to personalize the view. Try again later";



    public static final String WELCOME="Welcome ";
    public static final String NEWBIE="Newbie!!";

    //Regex
    public static final String REGEX_EMAIL="^[a-zA-Z0-9@$_]+[@][a-zA-Z]{2,8}[.][a-zA-Z]{2,3}$";
    public static final String REGEX_PASSWORD="^[a-zA-Z0-9@$_]{3,15}$";
    public static final String REGEX_USERNAME="^[a-zA-Z]{3,15}$";

    //Server handler
    public static final String SERVER_HANDLER_USERNAME="user_name";
    public static final String SERVER_HANDLER_PASSWORD="password";
    public static final String SERVER_HANDLER_EMAIL_ADDRESS="emailAddress";
    public static final String SERVER_HANDLER_PHONE_NUMBER="phoneNo";
    public static final String SERVER_HANDLER_UTF="UTF-8";


    //Server side status
    public static final String SERVER_HANDLER_LOGIN_SUCCESS="Login Success";
    public static final String SERVER_HANDLER_LOGIN_FAIL="Login Failed";
    public static final String SERVER_HANDLER_REGISTRATION_SUCCESS="Registration Success";
    public static final String SERVER_HANDLER_REGISTRATION_FAIL="Registration Failed";
    public static final String SERVER_HANDLER_EMAIL_SENT_SUCCESSFULLY="Email Sent successfully";

    //Color Coding
    public static final String BLUE_COLOR="#2E9AFE";

    //Activities
    public static final String ACTIVITY_WELCOME_SCREEN="WelcomeScreen";
    public static final String ACTIVITY_CHOICE_OF_COOKING="PreferredWayOfCooking";
    public static final String ACTIVITY_FLAT_SETUP="FlatSubServices";


    //One liners
    public static final String INTRODUCTION_FLAT_SUB_SERVICE_ENTRANCE="Buy essentials to make your entrance heart warming!!";
    public static final String INTRODUCTION_FLAT_SUB_SERVICE_COMMON_AREA="Fill you common area with items of daily needs!!";
    public static final String INTRODUCTION_FLAT_SUB_SERVICE_KITCHEN="Don't miss anything in your kitchen to prepare your first meal!!";
    public static final String INTRODUCTION_FLAT_SUB_SERVICE_BEDROOM="Nothing should be missed here as this place will help you in relaxing yourself!!";
    public static final String INTRODUCTION_FLAT_SUB_SERVICE_WASHROOM="Keep your place full with all the essentials!!";
    public static final String INTRODUCTION_PREFERRED_WAY_OF_COOKING_COOK="Automate grocery list. Cook will come and cook for you";
    public static final String INTRODUCTION_PREFERRED_WAY_OF_COOKING_MESS="Don't want cook or cook yourself No worry. Find a mess nearby your place";
    public static final String INTRODUCTION_PREFERRED_WAY_OF_COOKING_GROCERY="Automate grocery list and cook yourself";


    public static final String COOK_SELECTION_BREAKFAST_LUNCH="breakfast_lunch";
    public static final String COOK_SELECTION_BREAKFAST_DINNER="breakfast_dinner";
    public static final String COOK_SELECTION_LUNCH_DINNER="lunch_dinner";
    public static final String COOK_SELECTION_BREAKFAST_LUNCH_DINNER="breakfast_lunch_dinner";




}


