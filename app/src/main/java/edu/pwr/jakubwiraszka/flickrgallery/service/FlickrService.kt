package edu.pwr.jakubwiraszka.flickrgallery.service

import edu.pwr.jakubwiraszka.flickrgallery.model.FlickrResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrService {
    @GET("services/feeds/photos_public.gne")
    fun getFlickrResponse(
        @Query("format") format: String = "json",
        @Query("nojsoncallback") nojsoncallback: Int = 1
    ): Call<FlickrResponse>
}