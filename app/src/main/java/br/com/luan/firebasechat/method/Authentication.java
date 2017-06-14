package br.com.luan.firebasechat.method;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
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

public class Authentication {
    Activity mActivity;
    FirebaseAuth auth;
    LinearLayout mLoginFormView;
    ProgressBar mProgressView;

    public void init() {
        auth = FirebaseAuth.getInstance();
    }

    public boolean checkUserLogin(Activity activity){
        init();
        mActivity = activity;
        if(auth.getCurrentUser() != null){
            Intent intent = new Intent(activity.getBaseContext(), MainActivity.class);
            activity.startActivity(intent);
            activity.finish();
            return true;
        }else
            return false;
    }


    public void login(String email, String pass, LinearLayout mForm, ProgressBar mProgress){
        mLoginFormView = mForm;
        mProgressView = mProgress;

        if(email.length() > 0 && pass.length() > 0) {
            showProgress(true);
            auth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(mActivity, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(mActivity, "Authentication failed." + task.getException(),
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Intent intent = new Intent(mActivity, MainActivity.class);
                                mActivity.startActivity(intent);
                                mActivity.finish();
                                Toast.makeText(mActivity, "Authentication Success.",
                                        Toast.LENGTH_SHORT).show();
                            }
                            showProgress(false);
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
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = mActivity.getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

}
