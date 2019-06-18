package com.example.studymate.GUI;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.studymate.R;


public class AnswerQuestions extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.answer, container, false);
        return rootView;
    }

}
