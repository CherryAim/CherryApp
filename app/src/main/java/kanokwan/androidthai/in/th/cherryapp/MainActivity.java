package kanokwan.androidthai.in.th.cherryapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import kanokwan.androidthai.in.th.cherryapp.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentMainFragment, new MainFragment()).commit();

        }

    }// Main Method


}// Main class
