package com.gulko.lr_4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.function.Function;

public class MainActivity extends AppCompatActivity {
    EditText etNumber;
    Button btnSend;
    TextView tvsquaredNumber, tvcubeNumber, tvnegativeNumber, tvResultSecondTask, tvThirdResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSend = findViewById(R.id.btnSend);
        etNumber = findViewById(R.id.etNumber);
        tvsquaredNumber = findViewById(R.id.tvsquaredNumber);
        tvcubeNumber = findViewById(R.id.tvcubeNumber);
        tvnegativeNumber = findViewById(R.id.tvnegativeNumber);
        tvResultSecondTask = findViewById(R.id.tvResultSecondTask);
        tvThirdResult = findViewById(R.id.tvThirdResult);
        btnSend.setOnClickListener(view -> {
            writeResult();
        });
        MainActivity.Expression func = (n) -> n % 2 == 0;
        int[] ids = {1, 2, 3, 4, 5, 6, 7, 8, 9, 12, 14};
        ArrayList<Integer> values = new ArrayList<>();
        for (int id : ids) {
            values.add(id);
        }
        tvResultSecondTask.setText(String.valueOf(sum(values, func)));

        Function<String, String> trim = String::trim;
        String str = trim
                .andThen(String::toLowerCase)
                .andThen(StringBuilder::new)
                .andThen(StringBuilder::reverse)
                .andThen(StringBuilder::toString)
                .apply(" ABCDEFG ");

        tvThirdResult.setText(str);

    }

    private static ArrayList<Integer> sum(ArrayList<Integer> numbers, MainActivity.Expression func) {
        ArrayList<Integer> newnum = new ArrayList<>();
        for (int i : numbers) {
            if (func.isEqual(i))
                newnum.add(i);
        }
        return newnum;
    }

    interface Expression {
        boolean isEqual(int n);
    }

    public void writeResult() {
        tvsquaredNumber.setText("Число в квадрате " + first((i) -> Integer.valueOf(etNumber.getText().toString()), 1));
        tvcubeNumber.setText("Число в кубе " + cubeNumber((i) -> Integer.valueOf(etNumber.getText().toString()), 1));
        tvnegativeNumber.setText("Отрицательное число " + negativeNumber((i) -> Integer.valueOf(etNumber.getText().toString()), 1));
    }

    interface NameAndIndexPredicate {
        int squaredNumber(int index);
    }

    interface NameAndIndexPredicate2 {
        int cubeNumber(int index);
    }

    interface NameAndIndexPredicate3 {
        int negativeNumber(int index);
    }

    public int first(MainActivity.NameAndIndexPredicate p, int index) {
        return p.squaredNumber(index) * p.squaredNumber(index);
    }

    public int cubeNumber(MainActivity.NameAndIndexPredicate2 p, int index) {
        return p.cubeNumber(index) * p.cubeNumber(index) * p.cubeNumber(index);
    }

    public int negativeNumber(MainActivity.NameAndIndexPredicate3 p, int index) {
        return p.negativeNumber(index) * -1;
    }
}
