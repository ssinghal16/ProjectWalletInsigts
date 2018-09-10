package com.achivers.ssinghal.walletinsights;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    private FirebaseAuth firebaseAuth;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    private ImageView imgNavHeaderBg, imgProfile;
    private TextView txtName, txtWebsite;
    private Toolbar toolbar;
    private FloatingActionButton fab;

   // private static final String urlNavHeaderBg = "https://api.androidhive.info/images/nav-menu-header-bg.jpg";
   // private static final String urlProfileImg = "https://lh3.googleusercon";

    // index to identify current nav menu item
    public static int navItemIndex = 0;

    // tags used to attach the fragments
    private static final String TAG_HOME = "Wallet Insights";
    private static final String TAG_Budget = "budget";
    private static final String TAG_Income = "income";
    private static final String TAG_Expense = "expense";
    private static final String TAG_Setting = "Setting";
    private static final String TAG_Logout = "Logout";
    private static final String TAG_AboutUs = "About Us";
    private static final String TAG_ContactUs = "Contact Us";
    private static final String TAG_Rate = "Rate";
    public static String CURRENT_TAG = TAG_HOME;

    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth= FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mHandler = new Handler();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        // Navigation view header
        navHeader = navigationView.getHeaderView(0);
        txtName = (TextView) navHeader.findViewById(R.id.name);
        txtWebsite = (TextView) navHeader.findViewById(R.id.textWebsite);
        imgNavHeaderBg = (ImageView) navHeader.findViewById(R.id.img_header_bg);
        // imgProfile = (ImageView) navHeader.findViewById(R.id.img_profile);

        // load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popup = new PopupMenu(Home.this, view);
                popup.setOnMenuItemClickListener(Home.this);
                popup.inflate(R.menu.menu_plusmenu);
                popup.show();
            /*    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });

        // load nav menu header data
        //loadNavHeader();

        // initializing navigation menu
        setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;

        }
    }


    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {

        //Toast.makeText(this, "Selected Item: " +menuItem.getTitle(), Toast.LENGTH_SHORT).show();
        switch (menuItem.getItemId()){
            case R.id.addIncome:
                startActivity(new Intent(Home.this,AddIncome.class));
                return true;
            case R.id.addexpense:
                startActivity(new Intent(Home.this,AddExpense.class));
                return true;
            default:
                return false;
        }
    }

  private void loadHomeFragment() {
      // selecting appropriate nav menu item
      selectNavMenu();

      // set toolbar title
      setToolbarTitle();

      // if user select the current navigation menu again, don't do anything
      // just close the navigation drawer
      if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
          drawer.closeDrawers();

          // show or hide the fab button
          toggleFab();
          return;
      }


      // show or hide the fab button
      toggleFab();

      //Closing drawer on item click
      drawer.closeDrawers();

      // refresh toolbar menu
      invalidateOptionsMenu();
  }

       private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_addMember:

                return true;
            case R.id.action_createGroup:

                return true;
            default:

                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_home:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        break;
                       /* startActivity(new Intent(Home.this, HomeNavActivity.class));
                        return true;*/
                    case R.id.nav_budget:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_Budget;
                        startActivity(new Intent(Home.this, BudgetActivity.class));
                        return true;
                    case R.id.nav_income:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_Income;
                        startActivity(new Intent(Home.this, IncomeActivity.class));
                        return true;
                    case R.id.nav_expense:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_Expense;
                        startActivity(new Intent(Home.this, ExpenseActivity.class));
                        return true;
                    case R.id.nav_settings:
                        CURRENT_TAG =TAG_Setting;
                        startActivity(new Intent(Home.this, Settings.class));
                        return true;
                    case R.id.nav_logout:
                        CURRENT_TAG =TAG_Logout;
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(Home.this, LoginActivity.class));
                        return true;
                    case R.id.nav_aboutUs:
                        CURRENT_TAG =TAG_AboutUs;
                        // launch new intent instead of loading fragment
                        startActivity(new Intent(Home.this, AboutUs.class));
                        drawer.closeDrawers();
                        return true;
                    case R.id.nav_contactUs:
                        CURRENT_TAG =TAG_ContactUs;
                        // launch new intent instead of loading fragment
                        startActivity(new Intent(Home.this, ContactUs.class));
                        drawer.closeDrawers();
                        return true;
                    case R.id.nav_rate:
                        CURRENT_TAG = TAG_Rate;
                        startActivity(new Intent(Home.this, RateUs.class));
                        drawer.closeDrawers();
                        return true;
                    default:
                        navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                }
                item.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };
        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        if (shouldLoadHomeFragOnBackPress) {
            // checking if user is on other navigation menu
            // rather than home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                //loadHomeFragment();
                startActivity(new Intent(this, Home.class));
                return;
            }
        }

        super.onBackPressed();
    }


    // show or hide the fab
    private void toggleFab() {
        if (navItemIndex == 0)
            fab.show();
        else
            fab.hide();
    }


}