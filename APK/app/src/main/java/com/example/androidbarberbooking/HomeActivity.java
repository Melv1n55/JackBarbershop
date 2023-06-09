package com.example.androidbarberbooking;

import com.example.androidbarberbooking.Fragments.HomeFragment;
import com.example.androidbarberbooking.Fragments.ShopingFragment;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import butterknife.BindView;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidbarberbooking.Model.User;
import com.google.android.gms.common.internal.service.Common;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.nullness.qual.NonNull;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    BottomSheetDialog bottomSheetDialog;

    CollectionReference userRef;

    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        userRef = FirebaseFirestore.getInstance().collection("User");
        dialog = new SpotsDialog.Builder().setContext(this).setCancelable(false).build();

        if(getIntent() != null)
        {
            boolean isLogin = getIntent().getBooleanExtra(Common.IS_LOGIN,false);
            if(isLogin)
            {
                dialog.show();

                AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
                    @Override
                    public void onSuccess(Account account) {
                        if (account !=null)
                        {
                            DocumentReference currentUser = userRef.document(account.getPhoneNumber().toString());
                        }      currentUser.get()
                                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        if(task.isSuccessful())
                                        {
                                            DocumentSnapshot userSnapShot = task.getResult();
                                            if(!userSnapShot.exists())
                                            {

                                                showUpdateDialog(account.getPhoneNumber().toString());
                                            }
                                            else
                                            {
                                                Common.currentUser = userSnapShot.toObject(User.class);
                                                bottomNavigationView.setSelectedItemId(R.id.action_home);
                                            }

                                            if(dialog.isShowing())
                                                dialog.dismiss();
                                        }
                                    }
                                })
                    }

                    @Override
                    public void onError(AccountKitError accountKitError) {
                        Toast.makeText(HomeActivity.this,""+accountKitError.getErrorType().getMessage(), Toast.LENGTH_SHORT).show();
                    }
            }

            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                Fragment fragment = null;
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    if (menuItem.getItemId() == R.id.action_home)
                        fragment = new HomeFragment();
                    else if(MenuItem.getItemId() == R.id.action_shopping)
                        fragment = new ShopingFragment();

                    return loadFragment(fragment);
                }
            });
        }
    };


}

private boolean loadFragment(Fragment fragment) {
    if(fragment != null)
    {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment)
                .commit();
        return true;
    }
    return false;
}

// Dari codingan lama

    private Button mBtn_login, mBtn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtn_login = (Button)findViewById(R.id.btn_login);
        mBtn_register=(Button)findViewById(R.id.btn_register);

        mBtn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iLogin = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(iLogin);
            }
        });

        mBtn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iRegister = new Intent(getApplicationContext(), register.class);
                startActivity(iRegister);
            }
        });
    }
}

private void showUpdateDialog(String phoneNumber) {



        bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setTitle("One More Step");
        bottomSheetDialog.setCanceledOnTouchOutside(false);
        bottomSheetDialog.setCancelable(false);
    View sheetView = getLayoutInflater().inflate(R.layout.layout_update_information,null);

    Button btn_update = (Button) sheetView.findViewById(R.id.btn_update);
    final TextInputEditText edt_name = (TextInputEditText) sheetView.findViewById(R.id.edt_name);
    final TextInputEditText edt_address = (TextInputEditText) sheetView.findViewById(R.id.edt_address);

    btn_update.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if(!dialog.isShowing())
                dialog.show();

            User user = new User(edt_name.getText().toString(),
                    edt_address.getText().toString(),
                    phoneNumber);
            userRef.document(phoneNumber)
                    .set(user)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(Task<Void> task) {
                            bottomSheetDialog.dismiss();
                            if(dialog.isShowing())
                                dialog.dismiss();

                            Common.currentUser = user;
                            bottomNavigationView.setSelectedItemId(R.id.action_home);

                            Toast.makeText(HomeActivity.this, "Thank You", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(@NonNull Exception e) {
                bottomSheetDialog.dismiss();
                if(dialog.isShowing())
                    dialog.dismiss();
                Toast.makeText(HomeActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    });

        bottomSheetDialog.setContentView(sheetView);
        bottomSheetDialog.show();
    }
}