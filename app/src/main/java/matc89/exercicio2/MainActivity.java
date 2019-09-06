package matc89.exercicio2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String user;
    private TextView displayedMessage;
    private Button btnChangeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayedMessage = (TextView) findViewById(R.id.textView);
        btnChangeUser = (Button) findViewById(R.id.btnTrocar);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public void openOutraActivity(View v) {
        Intent intent = new Intent(this, OutraActivity.class);
        intent.putExtra("USER", user);
        startActivityForResult(intent, 7);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && requestCode == 7) {
            user = data.getStringExtra("TYPED");
            if (user.length() != 0) displayedMessage.setText("Oi, " + user + '!');
            else displayedMessage.setText("Oi!");
        }
    }

    protected String getUser() {
        return user;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("savedText", displayedMessage.getText().toString());
        outState.putString("USER", user);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        String savedText = savedInstanceState.getString("savedText");
        displayedMessage.setText(savedText);
        user = savedInstanceState.getString("USER");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("texto", displayedMessage.getText().toString());
        editor.commit();
    }
}
