package com.morristaedt.mirror.requests;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by jw on 29/09/15.
 */
public interface PublicTransitRequest {

    @GET("/json")
    PublicTransitResponse getTransitData(@Query("origin") String origin, @Query("destination") String destination, @Query("mode") String mode, @Query("key") String key, @Query("arrival_time") long time, @Query("language") String lang);

}
