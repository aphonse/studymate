package com.example.studymate.GUI;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.studymate.R;


public class AboutUs extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.about_us, container, false);
        TextView v;
        v=rootView.findViewById(R.id.about);

        String a="\tDEVELOPERS\n" +
                "\tAmanda Nechesa\n" +
                "\tFrancis Mburu\n" +
                "\tMuema Stephen\n" +
                "\tKiprop Aphonse\n" +
                "\tHulda Sifuna\n\n"+
                "\tAPPLICATION USE\n" +
                "\tThe apllication can be used by students to ask\n " +
                "\tquestions and also answer them.\n" +
                "\n" +
                "\tContact us through;\n" +
                "\tWhatsapp: +254725616871\n" +
                "\tEmail: alphonsekiprop@gmail.com\n" +
                "\tPortfolio: nanotechsoftwares.co.ke";
        v.setText(a);

        return rootView;
    }

}