package com.example.calculadora;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText entradaEditText;
    private TextView resultadoTextView;
    private Button calcularButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, insets) -> {
            int barraSistema = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom;
            v.setPadding(0, barraSistema, 0, barraSistema);
            return insets;
        });

        entradaEditText = findViewById(R.id.editTextNumberDecimal);
        resultadoTextView = findViewById(R.id.textView2);
        calcularButton = findViewById(R.id.button16);

        calcularButton.setOnClickListener(v -> calcularResultado());
    }

    private void calcularResultado() {
        String expresion = entradaEditText.getText().toString().trim();

        if (expresion.isEmpty()) {
            resultadoTextView.setText("Ingresa una operación");
            return;
        }

        try {
            double resultado = evaluarExpresion(expresion);

            if (resultado == (long) resultado) {
                resultadoTextView.setText("Resultado: " + (long) resultado);
            } else {
                resultadoTextView.setText("Resultado: " + resultado);
            }

        } catch (Exception e) {
            resultadoTextView.setText("Error: Expresión inválida");
        }
    }

    private double evaluarExpresion(String expresion) {
        Expression expression = new ExpressionBuilder(expresion).build();
        return expression.evaluate();
    }
}
