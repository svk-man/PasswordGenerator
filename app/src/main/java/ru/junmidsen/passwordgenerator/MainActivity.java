package ru.junmidsen.passwordgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private CheckBox checkBoxDigits;
    private CheckBox checkBoxUppercaseLetters;
    private CheckBox checkBoxLowercaseLetters;
    private CheckBox checkboxSpecialSymbols;
    private EditText editTextLength;
    private TextView textViewResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBoxDigits = findViewById(R.id.checkbox_digits);
        checkBoxUppercaseLetters = findViewById(R.id.checkbox_uppercase_letters);
        checkBoxLowercaseLetters = findViewById(R.id.checkbox_lowercase_letters);
        checkboxSpecialSymbols = findViewById(R.id.checkbox_special_symbols);
        editTextLength = findViewById(R.id.edittext_length);
        textViewResult = findViewById(R.id.textview_result);
    }

    public void onclick_create_password(View view) {
        String result = "";
        String characters = "";
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String specialSymbols = "%*)?@#$~";
        String lengthStr = editTextLength.getText().toString();
        int passwordsCount = 10;

        if (lengthStr.isEmpty() || Integer.parseInt(lengthStr) == 0) {
            result = getString(R.string.error_input_number);
            editTextLength.setText(R.string.options_length_default);
        } else {
            int length = Integer.parseInt(lengthStr);
            if (length >= 100) {
                result = getString(R.string.error_length);
                textViewResult.setText(result);
            } else {
                if (!checkBoxDigits.isChecked() && !checkBoxUppercaseLetters.isChecked() &&
                    !checkBoxLowercaseLetters.isChecked() && !checkboxSpecialSymbols.isChecked()) {
                    checkBoxDigits.setChecked(true);
                    checkBoxUppercaseLetters.setChecked(true);
                    checkBoxLowercaseLetters.setChecked(true);
                }

                if (checkBoxDigits.isChecked()) {
                    characters += "0123456789";
                }

                if (checkBoxUppercaseLetters.isChecked()) {
                    characters += alphabet.toUpperCase();
                }

                if (checkBoxLowercaseLetters.isChecked()) {
                    characters += alphabet;
                }

                if (checkboxSpecialSymbols.isChecked()) {
                    characters += specialSymbols;
                }

                result += getString(R.string.passwords);
                int charactersCount = characters.length();
                for (int n = 1; n <= passwordsCount; n++) {
                    for (int i = 0; i < length; i++) {
                        Random random = new Random();
                        result += characters.charAt(random.nextInt(charactersCount));
                    }

                    if (n < passwordsCount) {
                        result += "\n";
                    }
                }
            }
        }

        textViewResult.setText(result);
    }
}
