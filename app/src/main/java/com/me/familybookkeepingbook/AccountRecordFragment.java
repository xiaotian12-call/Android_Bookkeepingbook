package com.me.familybookkeepingbook;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountRecordFragment extends Fragment {
    private AccountRecordViewModel accountRecordViewModel;
    private RecyclerView recyclerView;
    private MyAdpter myAdpter;
    private FloatingActionButton floatingActionButton;
    private LiveData<List<AccountRecord>> findAccountRecord;
    private List<AccountRecord> allAccountRecord;

    public AccountRecordFragment() {
        setHasOptionsMenu(true);
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account_record, container, false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.clear:
                AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
                builder.setTitle("清空账单");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        accountRecordViewModel.deleteAllAccountRecord();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create();
                builder.show();
                break;
            case R.id.chart:
                NavController navController = Navigation.findNavController(requireActivity(),R.id.fragment4);
                navController.navigate(R.id.action_accountRecordFragment_to_chertFragment);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.show_menu,menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setMaxWidth(1000);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                String type = newText.trim();
                findAccountRecord.removeObservers(requireActivity());
                findAccountRecord = accountRecordViewModel.getAcountRecordByType(type);
                findAccountRecord.observe(requireActivity(), new Observer<List<AccountRecord>>() {
                    @Override
                    public void onChanged(List<AccountRecord> accountRecords) {
                        allAccountRecord = accountRecords;
                        myAdpter.setAllAccountRecords(accountRecords);
                        myAdpter.notifyDataSetChanged();
                    }
                });
                return true;
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        accountRecordViewModel = ViewModelProviders.of(requireActivity()).get(AccountRecordViewModel.class);
        recyclerView = requireActivity().findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        myAdpter = new MyAdpter();
        recyclerView.setAdapter(myAdpter);
        findAccountRecord =  accountRecordViewModel.getAllAcountRecord();
        findAccountRecord.observe(requireActivity(), new Observer<List<AccountRecord>>() {
            @Override
            public void onChanged(List<AccountRecord> accountRecords) {
                allAccountRecord = accountRecords;
                myAdpter.setAllAccountRecords(accountRecords);
                myAdpter.notifyDataSetChanged();
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.START | ItemTouchHelper.END) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                AccountRecord accountRecord = allAccountRecord.get(viewHolder.getLayoutPosition());
                accountRecordViewModel.deleteAccountRecord(accountRecord);
            }
        }).attachToRecyclerView(recyclerView);
        floatingActionButton = requireActivity().findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_accountRecordFragment_to_addFragment);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(),0);
    }


}
