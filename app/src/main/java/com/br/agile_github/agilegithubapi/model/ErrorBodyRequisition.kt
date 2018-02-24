package com.br.agile_github.agilegithubapi.model

import com.google.gson.annotations.SerializedName

data class ErrorBodyRequisition(
        val message: String,
        @SerializedName("documentation_url")
        val documentationUrl: String)