/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.tyagiabhinav.jokeserver;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.tyagiabhinav.JokeProvider;

/** An endpoint class we are exposing */
@Api(
  name = "myApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "jokeserver.tyagiabhinav.com",
    ownerName = "jokeserver.tyagiabhinav.com",
    packagePath=""
  )
)
public class MyEndpoint {

    @ApiMethod(name = "showMyJoke")
    public MyJoke showMyJoke() {
        MyJoke joke = new MyJoke();
        joke.setData("Joke : "+tellJoke());
        return joke;
    }

    private String tellJoke(){
        JokeProvider jokeProvider = new JokeProvider();
        return jokeProvider.getJoke();
    }

}
