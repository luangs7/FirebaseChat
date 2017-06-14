package br.com.luan.firebasechat.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import br.com.luan.firebasechat.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected TextView textView;
    protected Button out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.out) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this,LoginActivity.class));
        }
    }

    private void initView() {
        textView = (TextView) findViewById(R.id.textView);
        out = (Button) findViewById(R.id.out);
        out.setOnClickListener(MainActivity.this);
    }
}
