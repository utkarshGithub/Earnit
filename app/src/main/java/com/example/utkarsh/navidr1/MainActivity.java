package com.example.utkarsh.navidr1;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import android.content.Intent;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.app.Dialog;

import android.content.DialogInterface;
import android.graphics.Bitmap;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AlertDialog;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    private android.support.v4.app.Fragment fragment;

    private RelativeLayout rl;
    private RelativeLayout view;
    private int headerLayoutHeight;
    private int headerLayoutWidth;
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    public Bundle k;

    android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mTitle = mDrawerTitle = getTitle();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.navigationdroor);
        getSupportFragmentManager().beginTransaction().add(R.id.containe2, new ProgressFragment(), "fragment").commit();
        // getSupportFragmentManager().beginTransaction().replace(R.id.containe2, new ProgressFragment()).commit();
        //   k=savedInstanceState;
        makenavigationdrawer(savedInstanceState);
        makeblur();
        calldialog();

    }

    public void makenavigationdrawer(Bundle savedInstanceState) {
        mNavigationDrawerItemTitles = getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        ObjectDrawerItem[] drawerItem = new ObjectDrawerItem[2];

      //  drawerItem[0] = new ObjectDrawerItem(R.drawable.ic_action_copy, "Login");
      //  drawerItem[1] = new ObjectDrawerItem(R.drawable.ic_action_refresh, "Read");
        drawerItem[0] = new ObjectDrawerItem(R.drawable.ic_action_share, "Home");
        drawerItem[1] = new ObjectDrawerItem(R.drawable.ic_action_share, "Add Money To Wallet");
      //  drawerItem[4] = new ObjectDrawerItem(R.drawable.ic_action_share, "Pay");
        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.listview_item_row, drawerItem);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.navigationdroor,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                toolbar.setTitle(mTitle);
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                toolbar.setTitle(mDrawerTitle);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {

        }

        //  viewPager = (ViewPager) findViewById(R.id.viewpager);
        //  setupViewPager(viewPager);

        //  tabLayout = (TabLayout) findViewById(R.id.tabs);
        //  tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

    }

    private void selectItem(int position) {

        fragment = null;

        switch (position) {
          case 0:
             //   getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new HomeFragment()).commit();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

                break;
          /*
            case 1:
                fragment = new ReadFragment();
                break;
            case 2:
                fragment = new ProgressFragment();
                break;*/
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new HomeFragment()).commit();
              //  setTitle(mNavigationDrawerItemTitles[0]);
                Intent intent1 = new Intent(this, AddMoneyActivity.class);
                startActivity(intent1);

                break;
         /*   case 4:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new HomeFragment()).commit();
                Intent intent2 = new Intent(this, UHF.class);
                startActivity(intent2);
                finish();
                break;*/
            default:
                break;
        }

        if (fragment != null) {


            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(mNavigationDrawerItemTitles[position]);
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            //     getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new HomeFragment()).commit();
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        toolbar.setTitle(mTitle);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(fragmentManager);
        adapter.addFrag(new SaloonFragment(), "Saloon");
        adapter.addFrag(new BarFragment(), "Bar");
        adapter.addFrag(new spaFragment(), "Spa");
        adapter.addFrag(new PaanFragment(), "Paan");

        viewPager.setAdapter(adapter);
    }

    private void calldialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.dialog_signin);
        builder.
                setPositiveButton("Sign Up", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                        setContentView(R.layout.activity_main);


                        //   toolbar = (Toolbar) findViewById(R.id.toolbar);
                        // setSupportActionBar(toolbar);
                        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                        viewPager = (ViewPager) findViewById(R.id.viewpager);
                        setupViewPager(viewPager);

                        tabLayout = (TabLayout) findViewById(R.id.tabs);
                        tabLayout.setupWithViewPager(viewPager);
                        toolbar = (Toolbar) findViewById(R.id.toolbar);
                        setSupportActionBar(toolbar);
                        mTitle = mDrawerTitle = getTitle();
                        getSupportActionBar().setHomeButtonEnabled(true);
                        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                        getSupportActionBar().setHomeAsUpIndicator(R.drawable.navigationdroor);

                        getSupportFragmentManager().beginTransaction().add(R.id.containe2, new ProgressFragment(), "fragment").commit();
                        makenavigationdrawer(k);
                    }
                })
                .setNegativeButton("LogIN", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        setContentView(R.layout.activity_main);


                        //   toolbar = (Toolbar) findViewById(R.id.toolbar);
                        // setSupportActionBar(toolbar);
                        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                        viewPager = (ViewPager) findViewById(R.id.viewpager);
                        setupViewPager(viewPager);

                        tabLayout = (TabLayout) findViewById(R.id.tabs);
                        tabLayout.setupWithViewPager(viewPager);
                        toolbar = (Toolbar) findViewById(R.id.toolbar);
                        setSupportActionBar(toolbar);
                        mTitle = mDrawerTitle = getTitle();
                        getSupportActionBar().setHomeButtonEnabled(true);
                        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                        getSupportActionBar().setHomeAsUpIndicator(R.drawable.navigationdroor);

                        getSupportFragmentManager().beginTransaction().add(R.id.containe2, new ProgressFragment(), "fragment").commit();
                        makenavigationdrawer(k);
                    }
                });
        Dialog d = builder.create();
        d.setCanceledOnTouchOutside(false);
        d.setCancelable(false);
        d.show();

    }

    public void makeblur() {




     /*   final View content = findViewById(android.R.id.content).getRootView();
        if (content.getWidth() > 0) {
            Bitmap image = BlurBuilder.blur(content);
            Log.d("ARPIT","arpit");
            rl.setBackground(new BitmapDrawable(getResources(), image));
        } else {
            content.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    Log.d("ARPIT1","arpit");
                    Bitmap image = BlurBuilder.blur(content);
                    rl.setBackground(new BitmapDrawable(getResources(), image));
                }
            });
        }


        Log.d("arpit", rl.getLayoutParams().width + "");
    }*/
        view = (RelativeLayout) findViewById(R.id.main);
        final RelativeLayout headerLayout = view;
        final ViewTreeObserver observer = headerLayout.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                // TODO Auto-generated method stub
                headerLayoutHeight = headerLayout.getHeight();
                headerLayoutWidth = headerLayout.getWidth();
                headerLayout.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
            }
        });
        view.setDrawingCacheEnabled(true);

        view.buildDrawingCache();
        view.post(new Runnable() {
            @Override
            public void run() {

                BlurBuilder bb = new BlurBuilder();


                Bitmap y = bb.blur(view);
                Bitmap resized = Bitmap.createScaledBitmap(y, headerLayoutWidth, headerLayoutHeight, true);

                RelativeLayout rlmain = new RelativeLayout(getApplicationContext());
                RelativeLayout.LayoutParams llp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                RelativeLayout ll1 = new RelativeLayout(getApplicationContext());

                ImageView iv = new ImageView(getApplicationContext());
                iv.setImageBitmap(resized);

                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

                iv.setLayoutParams(lp);
                ll1.addView(iv);
                rlmain.addView(ll1);
                setContentView(rlmain, llp);
            }
        });

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<android.support.v4.app.Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(android.support.v4.app.FragmentManager manager) {
            super(manager);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(android.support.v4.app.Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
