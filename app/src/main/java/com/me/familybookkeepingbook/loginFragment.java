package com.me.familybookkeepingbook;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class loginFragment extends Fragment {
    private Button buttonLogin,buttonRegister;
    private EditText editTextUserName,editTextPassword;
    private UserRepository userRepository;
    public loginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userRepository = new UserRepository(requireContext());
        buttonRegister = requireActivity().findViewById(R.id.buttonregister);
        buttonLogin = requireActivity().findViewById(R.id.buttonlogin);
        editTextPassword = requireActivity().findViewById(R.id.editTextpassword);
        editTextUserName = requireActivity().findViewById(R.id.editTextusername);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = editTextUserName.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                User user = new User(userName,password);
                Boolean f = userRepository.queueUser(user);
                if(f){
                    NavController controller = Navigation.findNavController(v);
                    controller.navigate(R.id.action_loginFragment_to_accountRecordFragment);
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
                    builder.setTitle("账号或密码错误，请重新输入！");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.create();
                    builder.show();
                }

            }
        });
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_loginFragment_to_userAddFragment);
            }
        });
    }
}
