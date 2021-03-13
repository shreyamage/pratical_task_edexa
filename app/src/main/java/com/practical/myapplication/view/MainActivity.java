package com.practical.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.view.MenuItemCompat;
import androidx.viewpager.widget.ViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.google.android.material.tabs.TabLayout;
import com.practical.myapplication.R;
import com.practical.myapplication.adapter.Pager;
import com.practical.myapplication.model.Employee;
import com.practical.myapplication.retrofit.RetrofitInstance;
import com.practical.myapplication.view.fragments.AllFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity{

    TabLayout tabLayout;
    ViewPager viewPager;
    List<Employee> employeeList;
    //Creating our pager adapter
    Pager adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing the tablayout
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);


        callApi();

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("All"));
        tabLayout.addTab(tabLayout.newTab().setText("Chicago"));
        tabLayout.addTab(tabLayout.newTab().setText("NewYork"));
        tabLayout.addTab(tabLayout.newTab().setText("Los Angeles"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));




    }

    private void callApi() {

        Call<List<Employee>> call= RetrofitInstance.getApiService().getEmployeeData();
        call.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {

                if(response.isSuccessful()){
                    //Log.e("Responce::",response.body().toString());
                    if(response.body()!=null) {
                         employeeList= response.body();
                        Log.e("Data",employeeList.get(0).getCity());

                        if(employeeList!=null) {
                            adapter= new Pager(getApplicationContext(),getSupportFragmentManager(), tabLayout.getTabCount(), employeeList);
                            //Adding adapter to pager
                            viewPager.setAdapter(adapter);
                        }
                    }
                }else {
                    Log.e("Error::",response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {

                Log.e("Error::",t.getMessage());
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem searchViewItem = menu.findItem(R.id.app_bar_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();

                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });
        return super.onCreateOptionsMenu(menu);
    }



}