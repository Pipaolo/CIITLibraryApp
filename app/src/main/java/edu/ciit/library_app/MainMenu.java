package edu.ciit.library_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.squareup.picasso.Picasso;

public class MainMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    public static final String GOOGLE_ACCOUNT = "google account";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        updateui();
    }

    private void updateui() {
        GoogleSignInAccount account = getIntent().getParcelableExtra(GOOGLE_ACCOUNT);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView profileName = (TextView) headerView.findViewById(R.id.nav_profile_name);
        ImageView profileImage = headerView.findViewById(R.id.nav_profile_image);
        Picasso.get().load("http://i.imgur.com/DvpvklR.png").fit().into(profileImage);
        profileName.setText(account.getDisplayName());


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_browse) {
            setTitle("Browse Books");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BrowseFragment()).commit();

        } else if (id == R.id.nav_borrow) {
            setTitle("Borrow Books");
        } else if (id == R.id.nav_return) {
            setTitle("Return Books");
        } else if (id == R.id.nav_logout) {
            logoutUser();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logoutUser() {
        Intent logout = new Intent(this, MainActivity.class);
        startActivity(logout);
        finish();
    }
}
