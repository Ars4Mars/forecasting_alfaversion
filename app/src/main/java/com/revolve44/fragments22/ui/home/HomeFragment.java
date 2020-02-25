package com.revolve44.fragments22.ui.home;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.revolve44.fragments22.MainActivity;
import com.revolve44.fragments22.R;

import java.util.Objects;


public class HomeFragment extends Fragment {

    ImageView lamp;
    Button button;

    TextView real_output;
    TextView city;
    TextView temp;





    private HomeViewModel homeViewModel;

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        //Buttons
        Button button = root.findViewById(R.id.button_home);

        //TextViews
        final TextView real_output = root.findViewById(R.id.real_output);
        final TextView city = root.findViewById(R.id.city);
        final TextView temp = root.findViewById(R.id.temp4);

        //TextView realoutput = root.findViewById(R.id.real_output);

        //imageView
        final ImageView lamp= root.findViewById(R.id.lamp);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        ((MainActivity) Objects.requireNonNull(getActivity())).runforecast();

        ((MainActivity)getActivity()).runforecast();
        MainActivity activity = (MainActivity) getActivity();
        Float myDataFromActivity = activity.getMyData();
        String myDataFromActivity2 = activity.getMyData2();
        Float myDataFromActivity3 = activity.getMyData3();

//        Float myDataFromActivity4 = activity.getMyData4();
//        Power.setText(""+myDataFromActivity);
//        City.setText(myDataFromActivity2);
//        CurrOutPut.setText(""+myDataFromActivity3);
//        CurrTemp.setText(""+ myDataFromActivity4);

        real_output.setText(""+myDataFromActivity+" Watts");
        temp.setText("temperature: "+myDataFromActivity3+ " ℃");
        //real_output.setText(""+myDataFromActivity);
        city.setText(myDataFromActivity2);

        button.setOnClickListener(new View.OnClickListener()
        {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v)
            {
                // do something
                //lamp.setVisibility(View.VISIBLE);
                ((MainActivity) Objects.requireNonNull(getActivity())).runforecast();

            }
        });



        return root;
    }

//    button.setOnClickListener(new View.OnClickListener()
//    {
//        @Override
//        public void onClick(View v)
//        {
//            // do something
//            //lamp.setVisibility(View.VISIBLE);
//            real_output.setText(""+myDataFromActivity);
//            ((MainActivity)getActivity()).runforecast();
//
//
//            //ShowMe();
//
//        }
//    });
}