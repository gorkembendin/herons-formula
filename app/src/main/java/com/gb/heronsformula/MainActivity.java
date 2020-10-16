/**
 * The main activity of the application.
 * Heron's Formula Calculator(Based on Babylonian square-root algorithm).
 *
 * Created by Görkem Bendin on 16/10/2020.
 */
package com.gb.heronsformula;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gb.heronsformula.util.Triangle;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final String DOUBLE_FORMAT = "~%.2f";
    private static final String REGEX_STRING = "\\.";
    private static final int MINIMUM_EDGE_LENGTH = 3;
    private static final int FIRST_INDEX = 0;
    private static final int SECOND_INDEX = 1;
    private static final int THIRD_INDEX = 2;
    private static final int ERROR_IN_RESULT = -1;

    private TextView txtOutput;
    private EditText editTextInput;
    private Button btnCalculateHeronsFormula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();

        btnCalculateHeronsFormula.setOnClickListener(v -> {
            String[] inputs = editTextInput.getText().toString().split(REGEX_STRING);
            if (inputs.length == MINIMUM_EDGE_LENGTH) {
                try {
                    int lengthOfEdgeA = Integer.parseInt(inputs[FIRST_INDEX]);
                    int lengthOfEdgeB = Integer.parseInt(inputs[SECOND_INDEX]);
                    int lengthOfEdgeC = Integer.parseInt(inputs[THIRD_INDEX]);

                    double result = Triangle.getArea(lengthOfEdgeA, lengthOfEdgeB, lengthOfEdgeC);
                    if (result > ERROR_IN_RESULT) {
                        txtOutput.setText(String.format(Locale.ENGLISH, DOUBLE_FORMAT, result));
                    } else {
                        txtOutput.setText(getResources().getString(R.string.wrong_value));
                    }
                } catch (Exception exception) {
                    txtOutput.setText(String.format(Locale.ENGLISH, "%s %s", getResources().getString(R.string.input_error), exception.getMessage()));
                }
            } else {
                txtOutput.setText(R.string.input_error);
            }
        });

        editTextInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtOutput.setText(R.string.clear_result_message);
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Not needed
            }
        });
    }

    private void setupView() {
        txtOutput = findViewById(R.id.txtOutput);
        editTextInput = findViewById(R.id.editTextInput);
        btnCalculateHeronsFormula = findViewById(R.id.btnCalculateHeronsFormula);
    }
}