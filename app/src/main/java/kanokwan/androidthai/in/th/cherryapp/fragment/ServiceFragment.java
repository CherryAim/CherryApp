package kanokwan.androidthai.in.th.cherryapp.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import kanokwan.androidthai.in.th.cherryapp.MainActivity;
import kanokwan.androidthai.in.th.cherryapp.R;
import kanokwan.androidthai.in.th.cherryapp.utility.GetAllDatafromServer;
import kanokwan.androidthai.in.th.cherryapp.utility.MyAlert;
import kanokwan.androidthai.in.th.cherryapp.utility.MyConstance;

/**
 * Created by MasterUNG on 2/18/2018.
 */

public class ServiceFragment extends Fragment{
    public static ServiceFragment serviceInstance(String nameString) {
        ServiceFragment serviceFragment = new ServiceFragment();
        Bundle bundle = new Bundle();
        bundle.putString("Name", nameString);
        serviceFragment.setArguments(bundle);
        return serviceFragment;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        Create Toolbar
        Toolbar toolbar = getView().findViewById(R.id.toolbarService);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getArguments().getString("Name"));

//        Creat List View
        ListView listView = getView().findViewById((R.id.listViewName));
        try {

            MyConstance myConstance = new MyConstance();
            final MyAlert myAlert = new MyAlert(getActivity());
            GetAllDatafromServer getAllDatafromServer = new GetAllDatafromServer(getActivity());
            getAllDatafromServer.execute(myConstance.getUrlGetAllData());

            JSONArray jsonArray = new JSONArray(getAllDatafromServer.get());

            final String[] nameString = new String[jsonArray.length()];
            final String[] userStrings = new  String[jsonArray.length()];
            final String[] passwordString = new  String[jsonArray.length()];

            for (int i=0; i<jsonArray.length(); i+=1) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                nameString[i] = jsonObject.getString("Name");
                userStrings[i] = jsonObject.getString("User");
                passwordString[i] = jsonObject.getString("Password");
            }//for

            ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_expandable_list_item_1,nameString);
            listView.setAdapter(stringArrayAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    myAlert.dialogNormal(nameString[i]
                            , "User  ==>" + userStrings[i] +
                                    "\n" + "Paaword =>> "+ passwordString[i]);
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }


    }//Main Method

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement_service, container, false);

        return view;
    }
}
