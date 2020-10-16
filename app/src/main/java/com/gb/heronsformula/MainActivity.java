package com.gb.heronsformula;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gb.heronsformula.util.CustomMath;
import com.gb.heronsformula.util.CustomTriangle;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final String DOUBLE_FORMAT = "~%.2f";
    private static final String REGEX_STRING = "\\.";
    private static final int MINIMUM_EDGE_LENGTH = 3;
    private static final int FIRST_INDEX = 0;
    private static final int SECOND_INDEX = 1;
    private static final int THIRD_INDEX = 2;

    private TextView txtOutput;
    private EditText editTextInput;
    private Button btnCalculateHeronsFormula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();
        CustomMath.sqrt(125348);

        btnCalculateHeronsFormula.setOnClickListener(v -> {
            String[] inputs = editTextInput.getText().toString().split(REGEX_STRING);
            if (inputs.length == MINIMUM_EDGE_LENGTH) {
                try {
                    int lengthOfEdgeA = Integer.parseInt(inputs[FIRST_INDEX]);
                    int lengthOfEdgeB = Integer.parseInt(inputs[SECOND_INDEX]);
                    int lengthOfEdgeC = Integer.parseInt(inputs[THIRD_INDEX]);

                    txtOutput.setText(String.format(Locale.ENGLISH, DOUBLE_FORMAT, CustomTriangle.getArea(lengthOfEdgeA, lengthOfEdgeB, lengthOfEdgeC)));
                } catch (Exception exception) {
                    txtOutput.setText(String.format(Locale.ENGLISH, "%s %s", getResources().getString(R.string.input_error), exception.getMessage()));
                }
            } else {
                txtOutput.setText(R.string.input_error);
            }
        });
    }

    private void setupView() {
        txtOutput = findViewById(R.id.txtOutput);
        editTextInput = findViewById(R.id.editTextInput);
        btnCalculateHeronsFormula = findViewById(R.id.btnCalculateHeronsFormula);
    }
}