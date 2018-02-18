package kanokwan.androidthai.in.th.cherryapp.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import kanokwan.androidthai.in.th.cherryapp.MainActivity;
import kanokwan.androidthai.in.th.cherryapp.R;
import kanokwan.androidthai.in.th.cherryapp.utility.MyAlert;
import kanokwan.androidthai.in.th.cherryapp.utility.MyConstance;
import kanokwan.androidthai.in.th.cherryapp.utility.PostDataToServer;

/**
 * Created by MasterUNG on 2/17/2018.
 */

public class RegisterFragment extends Fragment {
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        create Toolbar
        Toolbar toolbar = getView().findViewById(R.id.toolbarRegister);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Register");
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
//        Regsiter Controller
        Button button = getView().findViewById(R.id.buttonRegister);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Get Value From DeitText
                EditText nameEditText = getView().findViewById(R.id.editTextname);
                EditText userEditText = getView().findViewById(R.id.editTextuser);
                EditText passEditText = getView().findViewById((R.id.editTextPassword));
                String nameString = nameEditText.getText().toString().trim();
                String userStriing = userEditText.getText().toString().trim();
                String passwordString = passEditText.getText().toString().trim();

//                Check Space
                if (nameString.isEmpty()|| userStriing.isEmpty()|| passwordString.isEmpty()) {
//                    Have Space
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.dialogNormal("Have Space",
                            "Please Fill All Blank");
                }else{
//                    No Space
                    try {
                        MyConstance myConstance = new MyConstance();
                        PostDataToServer postDataToServer = new PostDataToServer(getActivity());
                        postDataToServer.execute(nameString, userStriing, passwordString,
                                myConstance.getUrlPostData());

                        if (Boolean.parseBoolean(postDataToServer.get())) {
                            getActivity().getSupportFragmentManager().popBackStack();
                        }else{
                            Toast.makeText(getActivity(), "Cannot upolad value",
                            Toast.LENGTH_SHORT).show();

                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }



                }//if
            }  //onClick
        });
    }// Main Method

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        return view;
    }
} //Main class
