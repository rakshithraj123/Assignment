package com.app.assignmentapp.model

import com.google.gson.annotations.SerializedName

data class MovieNameResponse (

    @SerializedName("@meta"   ) var meta   : Meta?             = Meta(),
    @SerializedName("@type"   ) var type   : String?            = null,
    @SerializedName("query"   ) var query   : String?            = null,
    @SerializedName("results" ) var results : ArrayList<Results> = arrayListOf(),
    @SerializedName("types"   ) var types   : ArrayList<String>  = arrayListOf()

)

data class Meta (

    @SerializedName("operation"     ) var operation     : String? = null,
    @SerializedName("requestId"     ) var requestId     : String? = null,
    @SerializedName("serviceTimeMs" ) var serviceTimeMs : Double? = null

)


data class Image (

    @SerializedName("height" ) var height : Int?    = null,
    @SerializedName("id"     ) var id     : String? = null,
    @SerializedName("url"    ) var url    : String? = null,
    @SerializedName("width"  ) var width  : Int?    = null

)

data class Roles (

    @SerializedName("character"   ) var character   : String? = null,
    @SerializedName("characterId" ) var characterId : String? = null

)


data class Principals (

    @SerializedName("id"             ) var id             : String?           = null,
    @SerializedName("legacyNameText" ) var legacyNameText : String?           = null,
    @SerializedName("name"           ) var name           : String?           = null,
    @SerializedName("category"       ) var category       : String?           = null,
    @SerializedName("characters"     ) var characters     : ArrayList<String> = arrayListOf(),
    @SerializedName("endYear"        ) var endYear        : Int?              = null,
    @SerializedName("episodeCount"   ) var episodeCount   : Int?              = null,
    @SerializedName("roles"          ) var roles          : ArrayList<Roles>  = arrayListOf(),
    @SerializedName("startYear"      ) var startYear      : Int?              = null

)

data class Results (

    @SerializedName("id"                   ) var id                   : String?               = null,
    @SerializedName("image"                ) var image                : Image?                = Image(),
    @SerializedName("runningTimeInMinutes" ) var runningTimeInMinutes : Int?                  = null,
    @SerializedName("nextEpisode"          ) var nextEpisode          : String?               = null,
    @SerializedName("numberOfEpisodes"     ) var numberOfEpisodes     : Int?                  = null,
    @SerializedName("seriesEndYear"        ) var seriesEndYear        : Int?                  = null,
    @SerializedName("seriesStartYear"      ) var seriesStartYear      : Int?                  = null,
    @SerializedName("title"                ) var title                : String?               = null,
    @SerializedName("titleType"            ) var titleType            : String?               = null,
    @SerializedName("year"                 ) var year                 : Int?                  = null,
    @SerializedName("principals"           ) var principals           : ArrayList<Principals> = arrayListOf()

)

