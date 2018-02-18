package kanokwan.androidthai.in.th.cherryapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import kanokwan.androidthai.in.th.cherryapp.R;
import kanokwan.androidthai.in.th.cherryapp.utility.GetAllDatafromServer;
import kanokwan.androidthai.in.th.cherryapp.utility.MyAlert;
import kanokwan.androidthai.in.th.cherryapp.utility.MyConstance;

/**
 * Created by MasterUNG on 2/16/2018.
 */

public class
MainFragment extends Fragment{
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        register controller
        TextView textView= getView().findViewById(R.id.textViewRegister);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Replace Fragment
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentMainFragment, new RegisterFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });


//        Login Controller

        Button button = getView().findViewById((R.id.buttonLogin));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText useEditText = getView().findViewById(R.id.editTextuser);
                EditText PasswordEditText = getView().findViewById(R.id.editTextPassword);

                String userString = useEditText.getText().toString().trim();
                String passwordString = PasswordEditText.getText().toString().trim();

                if (userString.isEmpty()|| passwordString.isEmpty()) {
//                    Have Stpace
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.dialogNormal("Have Space","Please fill user and password");
                } else {
//                    No Space
                    try {
                        String tag = "18FebV1";
                        MyConstance myConstance = new MyConstance();
                        boolean statusBoolen = true;  // true == User False
                        String resultJSon, nameString = null, passwordTrueString = null;
                        MyAlert myAlert = new MyAlert(getActivity());


                        GetAllDatafromServer getAllDatafromServer = new GetAllDatafromServer(getActivity());
                        getAllDatafromServer.execute(myConstance.getUrlGetAllData());

                        resultJSon = getAllDatafromServer.get();
                        Log.d(tag, "JSON==>" + resultJSon);
                        JSONArray jsonArray = new JSONArray(resultJSon);
                        for (int i=0; i< jsonArray.length(); i+=1){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            if (userString.equals(jsonObject.getString("User"))) {
                                statusBoolen = false;
                                nameString = jsonObject.getString("Name");
                                passwordTrueString = jsonObject.getString("Password");


                            }// if
                        }//for
                        if (statusBoolen) {
                            myAlert.dialogNormal("User False",
                                    "No"  + userString + "in my Database");

                        } else if (passwordString.equals((passwordTrueString))) {

                            Toast.makeText(getActivity(),"Welcom"+ nameString,
                                    Toast.LENGTH_SHORT).show();
                            getActivity().getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.contentMainFragment, ServiceFragment.serviceInstance(nameString))
                                    .commit();

                        } else {
                            myAlert.dialogNormal("Password Falas",
                                    "Please Tray Again Passwrod False");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }// if


            }// onClick
        });


    } // Main Method

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }
}   //Main Class




