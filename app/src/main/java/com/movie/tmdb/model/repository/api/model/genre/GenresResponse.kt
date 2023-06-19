package com.movie.tmdb.model.repository.api.model.genre

import com.google.gson.annotations.SerializedName

data class GenresResponse(
    @SerializedName("genres") var genres: ArrayList<Genre> = arrayListOf()
)