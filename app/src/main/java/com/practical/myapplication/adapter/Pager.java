package com.practical.myapplication.adapter;

import android.content.Context;

import com.practical.myapplication.R;
import com.practical.myapplication.model.Employee;
import com.practical.myapplication.view.fragments.AllFragment;
import com.practical.myapplication.view.fragments.ChicagoFragment;
import com.practical.myapplication.view.fragments.LosAngelosFragment;
import com.practical.myapplication.view.fragments.NewyorkFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class Pager extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;
    List<Employee> employeeList;
    Context context;

    //Constructor to the class
    public Pager(Context context,FragmentManager fm, int tabCount, List<Employee> employeeList) {
        super(fm);
        //Initializing tab count
        this.context=context;
        this.tabCount= tabCount;
        this.employeeList= employeeList;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                AllFragment tab1 = new AllFragment(employeeList);
                return tab1;
            case 1:
                List<Employee> chicagolist=new ArrayList<>();
                for(int i=0;i<employeeList.size();i++){
                    if(employeeList.get(i).getCity().equalsIgnoreCase(context.getResources().getString(R.string.chicago))){
                                chicagolist.add(employeeList.get(i));
                    }
                }
                ChicagoFragment tab2 = new ChicagoFragment(chicagolist);
                return tab2;
            case 2:
                List<Employee> newyorklist=new ArrayList<>();
                for(int i=0;i<employeeList.size();i++){
                    if(employeeList.get(i).getCity().equalsIgnoreCase(context.getResources().getString(R.string.newyork))){
                        newyorklist.add(employeeList.get(i));
                    }
                }
                NewyorkFragment tab3 = new NewyorkFragment(newyorklist);
                return tab3;
            case 3:
                List<Employee> losangeloslist=new ArrayList<>();
                for(int i=0;i<employeeList.size();i++){
                    if(employeeList.get(i).getCity().equalsIgnoreCase(context.getResources().getString(R.string.losangelos))){
                        losangeloslist.add(employeeList.get(i));
                    }
                }
                LosAngelosFragment tab4 = new LosAngelosFragment(losangeloslist);
                return tab4;
            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }

    public void search(int i, String query) {
    }
}

