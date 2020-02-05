package com.me.familybookkeepingbook;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class addFragment extends Fragment {
    private Button buttonSubmit;
    private EditText editTextType,editTextMoney;
    private AccountRecordViewModel accountRecordViewModel;
    public addFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentActivity activity = requireActivity();
        accountRecordViewModel = ViewModelProviders.of(activity).get(AccountRecordViewModel.class);
        buttonSubmit = activity.findViewById(R.id.buttonsubmit);
        editTextMoney = activity.findViewById(R.id.editTextmoney);
        editTextType = activity.findViewById(R.id.editTexttype);
        buttonSubmit.setEnabled(false);
        editTextType.requestFocus();
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editTextType,0);
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String type = editTextType.getText().toString().trim();
                String money = editTextMoney.getText().toString().trim();
                buttonSubmit.setEnabled(!type.isEmpty()&&!money.isEmpty());

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        editTextType.addTextChangedListener(textWatcher);
        editTextMoney.addTextChangedListener(textWatcher);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = editTextType.getText().toString().trim();
                String money = editTextMoney.getText().toString().trim();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");// HH:mm:ss
                //获取当前时间
                Date date = new Date(System.currentTimeMillis());
                Double money_d = Double.valueOf(money);
                AccountRecord accountRecord = new AccountRecord(1, simpleDateFormat.format(date),type,money_d);
                accountRecordViewModel.insertAccountRecord(accountRecord);
                NavController navController = Navigation.findNavController(v);
                navController.navigateUp();
                InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(),0);

            }
        });
    }
}
