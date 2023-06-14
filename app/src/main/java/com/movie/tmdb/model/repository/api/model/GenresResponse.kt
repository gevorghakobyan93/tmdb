package com.movie.tmdb.model.repository.api.model

import com.google.gson.annotations.SerializedName

data class GenresResponse(
    @SerializedName("genres") var genres: ArrayList<Genre> = arrayListOf()
)