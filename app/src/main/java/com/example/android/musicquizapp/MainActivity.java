package com.example.android.musicquizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int scores;
    private static final int SINGLE_CHOICE_ANSWER_POINT = 3;
    private static final int MULTI_CHOICE_ANSWER_POINT = 1;
    private static final int MAX_NUMBER_OF_POINTS = 18;
    private static final int POOR_LEVEL_THRESHOLD = 7;
    private static final int NORMAL_LEVEL_THRESHOLD = 14;

    EditText userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = (EditText) findViewById(R.id.name_input_view);
    }

    public void submitQuiz(View view) {
        updateScores();
        if (scores > NORMAL_LEVEL_THRESHOLD) {
            showResultsMessage(R.string.excellent_result);
        } else if (scores > POOR_LEVEL_THRESHOLD && scores <= NORMAL_LEVEL_THRESHOLD) {
            showResultsMessage(R.string.good_result);
        } else {
            showResultsMessage(R.string.poor_result);        }
        scores = 0;
    }

    private void showResultsMessage(int resultTextId) {
        Toast.makeText(this, getString(R.string.earned_points_message, userName.getText(), scores, MAX_NUMBER_OF_POINTS) + " " + getString(resultTextId), Toast.LENGTH_LONG).show();
    }

    private void updateScores() {
        final int[] correctSingleAnswers = {R.id.single_question_1_correct_answer,
                R.id.single_question_2_correct_answer, R.id.single_question_4_correct_answer,
                R.id.single_question_6_correct_answer};
        for (int radioButtonId : correctSingleAnswers) {
            if (((RadioButton) findViewById(radioButtonId)).isChecked()) {
                scores += SINGLE_CHOICE_ANSWER_POINT;
            }
        }
        final int[] correctMultiAnswers = {R.id.multi_question_3_correct_answer_1,
                R.id.multi_question_3_correct_answer_2, R.id.multi_question_3_correct_answer_3,
                R.id.multi_question_5_correct_answer_1, R.id.multi_question_5_correct_answer_2,
                R.id.multi_question_5_correct_answer_3};
        for (int checkBoxId : correctMultiAnswers) {
            if (((CheckBox) findViewById(checkBoxId)).isChecked()) {
                scores += MULTI_CHOICE_ANSWER_POINT;
            }
        }
    }

}
