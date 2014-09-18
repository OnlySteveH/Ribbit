package com.thetechguys.ribbit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;


public class ForgotPasswordActivity extends Activity {
    protected EditText mEmail;
    protected Button mResetEmailButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_forgot_password);

        mEmail = (EditText)findViewById(R.id.resetEmailText);
        mResetEmailButton = (Button)findViewById(R.id.resetEmail_button);

        mResetEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString();



                email = email.trim();

                if ( (email.isEmpty())){
                    AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPasswordActivity.this);
                    builder.setMessage(R.string.email_hint)
                            .setTitle(R.string.signup_error_title)
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else{
                    //TODO create new user
                    setProgressBarIndeterminateVisibility(true);
                    ParseUser.requestPasswordResetInBackground(email,
                            new RequestPasswordResetCallback() {
                                @Override
                                public void done(ParseException e) {
                                    setProgressBarIndeterminateVisibility(false);
                                    if (e == null) {
                                        //Todo success
                                        Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    } else {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPasswordActivity.this);
                                        builder.setMessage(e.getMessage())
                                                .setTitle(R.string.signup_error_title)
                                                .setPositiveButton(android.R.string.ok, null);
                                        AlertDialog dialog = builder.create();
                                        dialog.show();
                                    }
                                }
                            });
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.forgot_password, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
