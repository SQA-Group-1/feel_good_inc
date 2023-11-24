package com.example.feelgoodinc;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.instanceOf;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ServiceTestRule;

import com.example.feelgoodinc.models.Journal;
import com.example.feelgoodinc.services.JournalService;
import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.Instant;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * This class will handle check if this {@link Service}:
 * <ul>
 *  <li>binds correctly</li>
 *  <li>interacts with firebase</li>
 *  <li>retrieves data</li>
 *  <li>adds data</li>
 * </ul>
 */
@RunWith(AndroidJUnit4.class)
public class JournalServiceTests {

    private FirebaseFirestore firebaseFirestore;

    private Intent serviceIntent;

    @Rule
    public final ServiceTestRule serviceRule = new ServiceTestRule();

    @Before
    public void setUp() {
        firebaseFirestore = FirebaseFirestore.getInstance();
    }


    @Test
    public void testWithBoundService() throws TimeoutException {
        // Create the service Intent.
        serviceIntent =
                new Intent(ApplicationProvider.getApplicationContext(),
                        JournalService.class);

        //TODO: add a thing to add the test

        // Bind the service and grab a reference to the binder.
        IBinder binder = serviceRule.bindService(serviceIntent);

        // Get the reference to the service, or you can call
        // public methods on the binder directly.
        JournalService service =
                ((JournalService.LocalBinder) binder).getService();

        List<Journal> journalsForMonth = new LinkedList<>();

        // Verify that the service is working correctly.
        service.getJournalsForMonth(Date.from(Instant.now()), (journals) -> {

        });

        List<Journal> test = new LinkedList<>();
        assertThat(journalsForMonth, instanceOf(test.getClass()));
        assertTrue(false);
    }


    @After
    public void tearDown() {
        // Clean up any test data after the test
        // (You may want to delete the added documents in the sub-collection)

        // remove binding of service
        serviceRule.unbindService();

    }

    @Test
    public void testDataAddedToSubCollection() throws InterruptedException {

    }
}
