package com.me.familybookkeepingbook;


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
public class UserAddFragment extends Fragment {
    private Button buttonRegister;
    private EditText editTextUserName,editTextPassword,editTextRP;
    private UserRepository userRepository;
    public UserAddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_add, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userRepository = new UserRepository(requireActivity());
        buttonRegister = requireActivity().findViewById(R.id.buttonR);
        editTextPassword = requireActivity().findViewById(R.id.editTextP);
        editTextRP = requireActivity().findViewById(R.id.editTextRP);
        editTextUserName = requireActivity().findViewById(R.id.editTextU);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = editTextUserName.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                User user = new User(userName,password);
                userRepository.insertUser(user);
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_userAddFragment_to_loginFragment);
            }
        });
    }
}
