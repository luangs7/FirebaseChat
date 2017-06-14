package br.com.luan.firebasechat.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import br.com.luan.firebasechat.R;
import br.com.luan.firebasechat.method.Registration;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    protected EditText email;
    protected EditText password;
    protected Button signUpButton;
    protected ProgressBar progressBar;
    Registration registration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        registration = new Registration();
        registration.init(this);

        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_register);
        initView();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.sign_up_button) {
            registration.register(email.getText().toString(), password.getText().toString(), progressBar);
        }
    }

    private void initView() {
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        signUpButton = (Button) findViewById(R.id.sign_up_button);
        signUpButton.setOnClickListener(RegisterActivity.this);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }
}
