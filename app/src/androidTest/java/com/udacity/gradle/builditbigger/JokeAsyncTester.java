package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.util.concurrent.CountDownLatch;

/**
 * Created by abhinavtyagi on 20/02/16.
 */
public class JokeAsyncTester extends ApplicationTestCase<Application> implements JokeReceiver{
    private CountDownLatch signal;
    String joke;

    public JokeAsyncTester() {
        super(Application.class);
    }

    public void testJokeAsync() throws InterruptedException{
        signal = new CountDownLatch(1);
        JokeServerAsync jokeServerAsync = new JokeServerAsync();
        jokeServerAsync.execute(this);
        signal.await();

        assertFalse("joke is empty", joke.isEmpty());
        assertTrue("joke test pass", joke.contains("Joke : "));
    }

    @Override
    public void onReceived(String joke) {
        this.joke = joke;
        signal.countDown();
    }
}
