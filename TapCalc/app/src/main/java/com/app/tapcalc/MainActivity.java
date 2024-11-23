package com.app.tapcalc;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.text.MessageFormat;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView resultTextView, inputTextView;
    private final List<String> ignoredOperators = List.of("+", "-", "*", "/", "(", ")", ".");

    MaterialButton btn_zero, btn_one, btn_two, btn_three, btn_four, btn_five, btn_six, btn_seven, btn_eight, btn_nine,
            btn_period, btn_addition, btn_subtraction, btn_divide, btn_multiply, btn_clear, btn_all_clear, btn_equals,
            btn_open_bracket, btn_close_bracket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        resultTextView = findViewById(R.id.result);
        inputTextView = findViewById(R.id.input);
        assignAllButtons();
    }

    private void assignAllButtons() {
        assignButtons(btn_one, R.id.btn_one_digit);
        assignButtons(btn_two, R.id.btn_two_digit);
        assignButtons(btn_three, R.id.btn_three_digit);
        assignButtons(btn_four, R.id.btn_four_digit);
        assignButtons(btn_five, R.id.btn_five_digit);
        assignButtons(btn_six, R.id.btn_six_digit);
        assignButtons(btn_seven, R.id.btn_seven_digit);
        assignButtons(btn_eight, R.id.btn_eight_digit);
        assignButtons(btn_nine, R.id.btn_nine_digit);

        assignButtons(btn_open_bracket, R.id.btn_open_bracket);
        assignButtons(btn_close_bracket, R.id.btn_close_bracket);
        assignButtons(btn_period, R.id.btn_period);
        assignButtons(btn_zero, R.id.btn_zero_digit);
        assignButtons(btn_addition, R.id.btn_addition);
        assignButtons(btn_subtraction, R.id.btn_subtraction);
        assignButtons(btn_divide, R.id.btn_divide);
        assignButtons(btn_multiply, R.id.btn_multiply);
        assignButtons(btn_clear, R.id.btn_clear);
        assignButtons(btn_all_clear, R.id.btn_all_clear);
        assignButtons(btn_equals, R.id.btn_equals);
    }

    private void assignButtons(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MaterialButton btn = (MaterialButton) v;
        if (btn.getText().equals("=")) {
            var result = calculateResult(inputTextView.getText().toString());
            resultTextView.setText(result);
            inputTextView.setText(resultTextView.getText());
        } else if (btn.getText().equals("C")) {
            if (inputTextView.getText().equals("")) {
                return;
            }
            inputTextView.setText(inputTextView.getText().subSequence(0, inputTextView.getText().length() - 1));
        } else if (btn.getText().equals("AC")) {
            if (inputTextView.getText().equals("")) {
                resultTextView.setText("");
            }
            inputTextView.setText("");
        } else {
            inputTextView.setText(MessageFormat.format("{0}{1}", inputTextView.getText().toString(), btn.getText().toString()));
            if (btn.getText().equals(")")){
                if ( inputTextView.getText().toString().contains("(")) {
                    resultTextView.setText(calculateResult(inputTextView.getText().toString()));
                    return;
                }
            }
            var result = inputTextView.getText().toString();
            resultTextView.setText(result);
        }
    }

    private String calculateResult(String string) {
        try {
            Expression exp = new ExpressionBuilder(string).build();
            double result = exp.evaluate();
            var interpretedResult = String.valueOf(result);
            if (interpretedResult.endsWith(".0")) {
                return interpretedResult.replace(".0", "");
            }
            return interpretedResult;
        } catch (Exception e) {
            return "Error";
        }
    }

}