package br.com.luan.firebasechat.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import br.com.luan.firebasechat.R;
import br.com.luan.firebasechat.method.Authentication;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    protected ProgressBar mProgressView;
    protected EditText email;
    protected EditText password;
    protected Button emailSignInButton;
    protected Button registerButton;
    protected LinearLayout mLoginFormView;
    protected ScrollView loginForm;
    Authentication authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        authentication = new Authentication();
        authentication.checkUserLogin(this);
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_login);
        initView();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.email_sign_in_button) {
            authentication.login(email.getText().toString(), password.getText().toString(), mLoginFormView,mProgressView);
        } else if (view.getId() == R.id.register_button) {
            startActivity(new Intent(this,RegisterActivity.class));
        }
    }

    private void initView() {
        mProgressView = (ProgressBar) findViewById(R.id.login_progress);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        emailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        emailSignInButton.setOnClickListener(LoginActivity.this);
        registerButton = (Button) findViewById(R.id.register_button);
        registerButton.setOnClickListener(LoginActivity.this);
        mLoginFormView = (LinearLayout) findViewById(R.id.email_login_form);
        loginForm = (ScrollView) findViewById(R.id.login_form);
    }


}
