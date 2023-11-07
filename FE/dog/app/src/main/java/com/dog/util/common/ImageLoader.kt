package com.dog.util.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImageLoader(imageUrl: String, modifier: Modifier = Modifier) {
    val JWT_TOKEN =
        "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDEiLCJhdXRoIjoiUk9MRV9VU0VSIiwiZXhwIjoxNzMwODU1NjU3fQ.A-LpWLT_ZjyAjB1_kFwk25SoiR1vCIIA_ikg6_600RA"

    val glideUrl = GlideUrl(
        imageUrl, LazyHeaders.Builder()
            .addHeader("Authorization", "Bearer $JWT_TOKEN")
            .build()
    )


    GlideImage(
        model = glideUrl,
        contentDescription = "이미지 설명",
        modifier = Modifier.fillMaxWidth()
    )
}