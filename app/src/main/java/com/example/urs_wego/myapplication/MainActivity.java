package com.example.urs_wego.myapplication;

        import android.app.Activity;
        import android.content.SharedPreferences;
        import android.content.SharedPreferences.Editor;
        import android.os.Bundle;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

    EditText etText, etPass;
    TextView login;
    Button btnSave, btnLogout;

    SharedPreferences sPref;
    String savedText, savedPass;
    final String SAVED_LOGIN = "login";
    final String SAVED_PASSWORD = "pass";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sPref = getPreferences(MODE_PRIVATE);

            savedText = sPref.getString(SAVED_LOGIN, "");
            savedPass = sPref.getString(SAVED_PASSWORD, "");
            if (savedText != null && !savedText.equals("") && savedPass != null && !savedPass.equals("")) {
                setContentView(R.layout.logged);
                login = (TextView) findViewById(R.id.textView2);
                login.setText(savedText);
             /*   btnLogout = (Button) findViewById(R.id.btnlogout);
                btnLogout.setOnClickListener(this);*/
            } else {
               setContentView(R.layout.activity_main);
                etText = (EditText) findViewById(R.id.etText);
                etPass = (EditText) findViewById(R.id.etPass);
                btnSave = (Button) findViewById(R.id.btnSave);
                btnSave.setOnClickListener(this);

            }



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                saveText();
                break;

            default:
                break;
        }
    }

    void saveText() {
        sPref = getPreferences(MODE_PRIVATE);
        Editor ed = sPref.edit();
        ed.putString(SAVED_LOGIN, etText.getText().toString());
        ed.putString(SAVED_PASSWORD, etPass.getText().toString());
        ed.commit();
        setContentView(R.layout.logged);
        savedText = sPref.getString(SAVED_LOGIN, "");
        login = (TextView) findViewById(R.id.textView2);
        login.setText(savedText);
        Toast.makeText(this, "You are successfully joined our service", Toast.LENGTH_SHORT).show();
    }


    public void logout(View view) {
        sPref = getPreferences(MODE_PRIVATE);
        Editor ed = sPref.edit();
      ed.clear();
        setContentView(R.layout.activity_main);
        etText = (EditText) findViewById(R.id.etText);
        etPass = (EditText) findViewById(R.id.etPass);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        Toast.makeText(this, "Data successfully deleted", Toast.LENGTH_SHORT).show();
    }
}