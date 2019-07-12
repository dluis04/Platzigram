package com.dluis.platzigram.login.repository;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.dluis.platzigram.login.presenter.LoginPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginRepositoryImpl implements LoginRepository {

    LoginPresenter presenter;


    public LoginRepositoryImpl(LoginPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void singIn(final String username, String password, final Activity activity, FirebaseAuth firebaseAuth) {
        firebaseAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    FirebaseUser user = task.getResult().getUser();

                    SharedPreferences preferences = activity.getSharedPreferences("USER", Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("email", user.getEmail());

                    editor.commit();

                    System.out.println("Usuario Logeado ->> " + username);
                    presenter.loginSuccess();
                } else {
                    System.out.println("Usuario No Logeado ->> ");
                    presenter.loginError("Ocurrió un error!");
                }
            }
        });

        boolean success = true;
        if (success) {
            presenter.loginSuccess();
        } else {
            presenter.loginError("Ocurrió un Error");
        }
    }

}
