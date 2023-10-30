package com.dog.ui.screen

import android.widget.ImageView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.dog.data.model.FeedItem
import com.dog.data.model.generateFeedItems
import com.dog.ui.theme.DogTheme


@Composable
fun HomeScreen(navController: NavController) {
    DogTheme {
        // 가상의 피드 아이템 데이터를 생성하거나 가져온다고 가정합니다.
        val feedItems = generateFeedItems()

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(feedItems) { item ->
                    FeedItemCard(feedItem = item)
                }
            }
        }
    }
}


@Composable
fun FeedItemCard(feedItem: FeedItem) {
    val context = LocalContext.current
    val imageView = remember { ImageView(context) }

    fun loadImage(imageView: ImageView, url: String, ) {
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // User Info Section
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                // Load user profile image using Glide
//                loadImage(postImageView, feedItem.userProfileImageUrl) // user profile
            }
            Text(text = feedItem.username, fontWeight = FontWeight.Bold)
        }

        // Content Section
        Text(text = feedItem.content)
        // Image Section
        // Load post image here
        val context = LocalContext.current
        val density = LocalDensity.current.density
        val imageWidth = (context.resources.displayMetrics.widthPixels - 32 * density).toInt()
        val imageHeight = (imageWidth * 0.7).toInt()
        val modifier = Modifier
            .fillMaxWidth()
            .height(imageHeight.dp)
        val postImageView = remember { ImageView(context) }
        loadImage(postImageView, feedItem.postImageUrl)
//        loadImage(feedItem.postImageUrl)
        AndroidView(
            factory = { postImageView },
            modifier = modifier
        )

        // Like and Comment Section
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.clickable { /* Handle like button click */ }
            ) {
                Icon(imageVector = Icons.Default.ThumbUp, contentDescription = null)
                Text(text = "${feedItem.likes} Likes")
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.clickable { /* Handle comment button click */ }
            ) {
                Icon(imageVector = Icons.Default.Send, contentDescription = null)
                Text(text = "${feedItem.comments} Comments")
            }
        }
    }
}
