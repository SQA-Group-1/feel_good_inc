package com.example.feelgoodinc.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import com.example.feelgoodinc.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/***
 * Accesses the firebase database to receive and add user data
 * Used for log in, sign up and change password
 * <br>
 * Usage:
 * <pre>
 *     private final ServiceConnection serviceConnection = new ServiceConnection() {
 *         //@Override
 *         public void onServiceConnected(ComponentName name, IBinder service) {
 *             UserService.LocalBinder binder = (UserService.LocalBinder) service;
 *             userService = binder.getService();
 *             isBound = true;
 *         }
 *
 *         //@Override
 *         public void onServiceDisconnected(ComponentName name) {
 *             isBound = false;
 *         }
 *     };
 *     <br>
 *     // Bind to the service in onStart
 *     Intent intent = new Intent(this, UserService.class);
 *     bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
 * </pre>
 */
public class UserService extends Service {
    FirebaseAuth mAuth;
    private final IBinder binder = new LocalBinder();

    /***
     * Binder for the user service
     */
    public class LocalBinder extends Binder {
        public UserService getService() {
            return UserService.this;
        }
    }

    /***
     * get the current user's key without initalizing the user.
     *
     * This is needed to determine which collection to access the cloud firestore.
     * * @return
     */
    public static String getCurrentUserKey() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    /***
     * sets up firebase connection on create
     */
    @Override
    public void onCreate(){
        super.onCreate();
        mAuth = FirebaseAuth.getInstance();
    }

    /***
     * get the current signed in user from the firebase database
     * @return User model
     */
    public User getCurrentUser(){
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if(firebaseUser != null){
            firebaseUser.reload();
        }

        return mapUser(firebaseUser);
    }

    /***
     * sign in the user to firebase and return a mapped {@link User} object in the callback
     * if the sign in fails, return the exception
     * @param email the email inputted by the user
     * @param password the password inputted by the user
     * @param callback interface to track success/error and send data back to activity
     */
    public void loginUser(String email, String password, UserCallback callback){
        // TODO: encrypt password

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                User user = getCurrentUser();

                // get date to set last user login
                LocalDate ldate = LocalDate.now();
                Instant instant = Instant.from(ldate.atStartOfDay(ZoneId.of("GMT")));
                Date date = Date.from(instant);
                user.setLastLoginWhen(date);

                callback.onSuccess(user);
            } else {
                callback.onError(task.getException());
            }
        });
    }

    /***
     * register a new user account and sign them in
     * return mapped User object or exception depending on success
     * @param email the email inputted by the user
     * @param password the password inputted by the user
     * @param callback interface to track success/error and send data back to activity
     */
    public void registerUser(String email, String password, UserCallback callback){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                User user = getCurrentUser();
                callback.onSuccess(user);
            } else {
                callback.onError(task.getException());
            }
        });
    }

    /***
     * convert the {@link FirebaseUser} to our {@link User} class
     * @param firebaseUser firebaseUser object
     * @return User object
     */
    private User mapUser(FirebaseUser firebaseUser){
        User user = new User();
        if (firebaseUser != null) {
            user.setUsername(firebaseUser.getEmail());
            user.setCurrentUserKey(firebaseUser.getUid());
        }

        return user;
    }

    /***
     * returns LocalBinder to the calling activity
     * @param intent The Intent that was used to bind to this service,
     * as given to {@link android.content.Context#bindService
     * Context.bindService}.  Note that any extras that were included with
     * the Intent at that point will <em>not</em> be seen here.
     *
     * @return LocalBinder for the user service
     */
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    /***
     * callback interface for sending data back to the bound activity
     */
    public interface UserCallback {
        void onSuccess(User user);
        void onError(Exception e);
    }
}