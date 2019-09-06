package matc89.exercicio2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OutraActivity extends AppCompatActivity {
    private TextView input;
    private Button btnCancel;
    private Button btnConfirm;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outra);

        input = (TextView) findViewById(R.id.editText);
        btnCancel = (Button) findViewById(R.id.btnCancelar);
        btnConfirm = (Button) findViewById(R.id.btnConfirmar);
        user = getIntent().getExtras().getString("USER");
        if (user != null) input.setText(user);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public void cancel(View v) {
        setResult(0, null);
        finish();
    }

    public void confirm(View v) {
        String typed = input.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("TYPED", typed);
        setResult(7, intent);
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("USER", user);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        String savedText = savedInstanceState.getString("USER");
        if (user != null) input.setText(user);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("texto", input.getText().toString());
        editor.commit();
    }
}
