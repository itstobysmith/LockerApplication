package com.example.envoyapplication.network



fun getGistService() : GistServices =
    RetrofitServiceFactory.getProvider(
        serviceClass = GistServices::class.java,
        baseUrl = base_url
    )


const val base_url = "https://api.github.com/"
