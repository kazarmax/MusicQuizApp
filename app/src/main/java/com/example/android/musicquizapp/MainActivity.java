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
    private static final int CORRECT_ANSWER_POINT = 3;
    private static final int MAX_NUMBER_OF_POINTS = 21;
    private static final int POOR_LEVEL_THRESHOLD = 10;
    private static final int NORMAL_LEVEL_THRESHOLD = 17;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitQuiz(View view) {

        EditText userNameEditText = (EditText) findViewById(R.id.name_input_view);
        String userName = userNameEditText.getText().toString();
        if (userName.isEmpty()) {
            userName = getString(R.string.no_name_user);
        }
        updateScores();
        if (scores > NORMAL_LEVEL_THRESHOLD) {
            showResultsMessage(R.string.excellent_result, userName);
        } else if (scores > POOR_LEVEL_THRESHOLD && scores <= NORMAL_LEVEL_THRESHOLD) {
            showResultsMessage(R.string.good_result, userName);
        } else {
            showResultsMessage(R.string.poor_result, userName);
        }
        scores = 0;
    }

    private void showResultsMessage(int resultTextId, String userName) {
        Toast.makeText(this, getString(R.string.earned_points_message, userName, scores, MAX_NUMBER_OF_POINTS) + " " + getString(resultTextId), Toast.LENGTH_LONG).show();
    }

    private void updateScores() {

        EditText quizAnswerQuestion7 = (EditText) findViewById(R.id.input_answer_question_7);

        CheckBox checkBoxQ3A1Wrong = (CheckBox) findViewById(R.id.multi_question_3_incorrect_answer_1);
        CheckBox checkBoxQ3A2Wrong = (CheckBox) findViewById(R.id.multi_question_3_incorrect_answer_2);
        CheckBox checkBoxQ3A3Right = (CheckBox) findViewById(R.id.multi_question_3_correct_answer_3);
        CheckBox checkBoxQ3A4Right = (CheckBox) findViewById(R.id.multi_question_3_correct_answer_4);
        CheckBox checkBoxQ3A5Wrong = (CheckBox) findViewById(R.id.multi_question_3_incorrect_answer_5);
        CheckBox checkBoxQ3A6Right = (CheckBox) findViewById(R.id.multi_question_3_correct_answer_6);

        CheckBox checkBoxQ5A1Right = (CheckBox) findViewById(R.id.multi_question_5_correct_answer_1);
        CheckBox checkBoxQ5A2Wrong = (CheckBox) findViewById(R.id.multi_question_5_incorrect_answer_2);
        CheckBox checkBoxQ5A3Right = (CheckBox) findViewById(R.id.multi_question_5_correct_answer_3);
        CheckBox checkBoxQ5A4Wrong = (CheckBox) findViewById(R.id.multi_question_5_incorrect_answer_4);
        CheckBox checkBoxQ5A5Right = (CheckBox) findViewById(R.id.multi_question_5_correct_answer_5);

        final int[] correctSingleAnswers = {R.id.single_question_1_correct_answer,
                R.id.single_question_2_correct_answer, R.id.single_question_4_correct_answer,
                R.id.single_question_6_correct_answer};
        for (int radioButtonId : correctSingleAnswers) {
            if (((RadioButton) findViewById(radioButtonId)).isChecked()) {
                scores += CORRECT_ANSWER_POINT;
            }
        }

        if (checkBoxQ3A3Right.isChecked() && checkBoxQ3A4Right.isChecked()
                && checkBoxQ3A6Right.isChecked() && !checkBoxQ3A1Wrong.isChecked()
                && !checkBoxQ3A2Wrong.isChecked() && !checkBoxQ3A5Wrong.isChecked()) {
            scores += CORRECT_ANSWER_POINT;
        }

        if (checkBoxQ5A1Right.isChecked() && checkBoxQ5A3Right.isChecked()
                && checkBoxQ5A5Right.isChecked() && !checkBoxQ5A2Wrong.isChecked()
                && !checkBoxQ5A4Wrong.isChecked()) {
            scores += CORRECT_ANSWER_POINT;
        }

        if (quizAnswerQuestion7.getText().toString().equals(getString(R.string.question_7_answer))) {
            scores += CORRECT_ANSWER_POINT;
        }
    }

}