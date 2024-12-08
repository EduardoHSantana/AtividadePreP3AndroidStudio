package br.edu.fatec.validamaioremenor;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private EditText etNumbers;
    private Button btnValidar, btnCalcular, btnReiniciar;
    private TextView tvResultMaior, tvResultMenor, tvMessage, tvInsertedNum, titlenumInserted;
    private final int[] numbers = new int[20];
    private int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumbers = findViewById(R.id.et_numbers);
        btnValidar = findViewById(R.id.btn_validar);
        btnCalcular = findViewById(R.id.btn_calcular);
        btnReiniciar = findViewById(R.id.btn_reiniciar);
        titlenumInserted = findViewById(R.id.titleNumIns);
        tvResultMaior = findViewById(R.id.tv_result_maior);
        tvResultMaior.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvResultMenor = findViewById(R.id.tv_result_menor);
        tvResultMenor.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvMessage = findViewById(R.id.tv_message);
        tvMessage.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvInsertedNum = findViewById(R.id.tv_numsInseridos);

    }

    public void validarNumero(View view) {

        btnReiniciar.setOnClickListener(this::reiniciar);

        String input;
        input = etNumbers.getText().toString().trim();

        if (TextUtils.isEmpty(input)) {
            etNumbers.setError(getString(R.string.alertnumValido));
            return;
        }

        int number;
        try {
            number = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            etNumbers.setError(getString(R.string.alertnumValido));
            return;
        }

        if (number < 0) {
            etNumbers.setError(getString(R.string.numero_inteiro));
            return;
        }

        if (count < 20) {
            StringBuilder numsInserted = new StringBuilder();
            numsInserted.append(number).append(" ; ");
            tvInsertedNum.append(numsInserted);
            titlenumInserted.setVisibility(View.VISIBLE);

            numbers[count++] = number;
            etNumbers.setText("");
            tvMessage.setText("");
            if (count == 20) {
                etNumbers.setEnabled(false);
                btnValidar.setEnabled(false);
                btnValidar.setVisibility(View.INVISIBLE);
                btnCalcular.setEnabled(true);

            }
        } else {

            tvMessage.setText(R.string.alertlimite);
        }
    }

    public void calcularMaiorEMenor(View view) {
        int maior = Integer.MIN_VALUE;
        int menor = Integer.MAX_VALUE;

        for (int number : numbers) {
            if (number > maior) {
                maior = number;
            }
            if (number < menor) {
                menor = number;
            }
        }

        tvResultMaior.setText(getString(R.string.maior, maior));
        tvResultMenor.setText(getString(R.string.menor, menor));
        btnCalcular.setEnabled(false);
        btnReiniciar.setEnabled(true);
        btnReiniciar.setVisibility(View.VISIBLE);
    }

    public void reiniciar(View view) {
        Arrays.fill(numbers, 0);
        count = 0;
        tvInsertedNum.setText("");
        tvResultMaior.setText("");
        tvResultMenor.setText("");
        tvMessage.setText("");
        etNumbers.setText("");
        etNumbers.setEnabled(true);
        btnValidar.setEnabled(true);

        btnValidar.setVisibility(View.VISIBLE);
        btnReiniciar.setVisibility(View.INVISIBLE);
    }


}
