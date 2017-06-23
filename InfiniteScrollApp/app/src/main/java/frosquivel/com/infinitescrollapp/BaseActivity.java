package frosquivel.com.infinitescrollapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.drawable.AnimationDrawable;
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

import frosquivel.com.infinitescrollapp.Classes.Utils;
import frosquivel.com.infinitescrollapp.Fragments.CountryListViewFragment;

/**
 * Created by Fabian on 02/06/2017.
 */

public class BaseActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        ImageView imageViewBackground = (ImageView) headerView.findViewById(R.id.imageViewBackground);

        AnimationDrawable animacion;
        animacion = (AnimationDrawable)getResources().getDrawable(
                R.drawable.animation_list);
        imageViewBackground.setImageDrawable(animacion);
        animacion.setEnterFadeDuration(5000);
        animacion.setExitFadeDuration(4000);
        animacion.start();

        imageViewBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.goToWebView(BaseActivity.this.getString(R.string.menu_personal_value), BaseActivity.this);
            }
        });

        chargeFirstFragment();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            finish();
        }
    }

    private void chargeFirstFragment(){
        Fragment fragment = null;
        fragment = new CountryListViewFragment();
        if(fragment != null){
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragment = null;
        int id = item.getItemId();

        if (id == R.id.nav_infinite_scroll) {
            Utils.goToWebView(this.getString(R.string.menu_infinite_scroll_value), BaseActivity.this);
        } else if (id == R.id.nav_license) {
            Utils.goToWebView(this.getString(R.string.menu_license_value), BaseActivity.this);
        } else if (id == R.id.nav_country_api) {
            Utils.goToWebView(this.getString(R.string.menu_county_api_value), BaseActivity.this);
        } else if (id == R.id.nav_share) {
            Utils.sharedApp(BaseActivity.this);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        if(fragment != null){
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        }

        return true;
    }
}