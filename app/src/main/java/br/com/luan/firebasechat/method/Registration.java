package br.com.luan.firebasechat.method;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import br.com.luan.firebasechat.view.MainActivity;

/**
 * Created by Luan on 14/06/17.
 */

public class Registration {
    Activity mActivity;
    FirebaseAuth auth;
    LinearLayout mLoginFormView;
    ProgressBar mProgressView;

    public void init(Activity activity) {
        mActivity = activity;
        auth = FirebaseAuth.getInstance();
    }


    public void register(String email, String pass, ProgressBar mProgress){
        mProgressView = mProgress;

        if(email.length() > 0 && pass.length() > 0) {
            showProgress(true);
            auth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(mActivity, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            Toast.makeText(mActivity, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                            showProgress(false);


                            if (!task.isSuccessful()) {
                                Toast.makeText(mActivity, "Registration failed." + task.getException(),
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                AuthResult authResult = task.getResult();
                                mActivity.startActivity(new Intent(mActivity, MainActivity.class));
                                mActivity.finish();
                                Toast.makeText(mActivity, "Registration Success.",
                                        Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        }else
            Toast.makeText(mActivity, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();

    }


    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

}
