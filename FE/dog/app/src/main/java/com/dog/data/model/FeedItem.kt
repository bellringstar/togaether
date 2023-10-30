package com.dog.data.model

data class FeedItem(
    val id: Int,
    val username: String,
    val userProfileImageUrl: String,
    val postImageUrl: String,
    val content: String,
    val likes: Int,
    val comments: Int
)

fun generateFeedItems(): List<FeedItem> {
    // 가상의 피드 아이템 데이터를 생성하거나 가져온다고 가정합니다.
    return listOf(
        FeedItem(
            1,
            "23423",
            "https://icons8.kr/icon/Ued8ZrxZYzBS/%EC%BB%A4%EB%AF%B8%ED%8A%B8-%EA%B0%9C%EA%B5%AC%EB%A6%AC",
            "https://img.freepik.com/premium-photo/a-cute-little-character-with-a-face-and-a-smile-on-his-face_902049-15707.jpg?w=740",
            "This is the first post.",
            10,
            5
        ),
        FeedItem(
            2,
            "4234",
            "https://img.freepik.com/premium-photo/a-cute-little-character-with-a-face-and-a-smile-on-his-face_902049-15707.jpg?w=740",
            "https://img.freepik.com/premium-photo/a-cute-little-character-with-a-face-and-a-smile-on-his-face_902049-15707.jpg?w=740",
            "Just posted a photo!",
            20,
            8
        ),
        FeedItem(
            3,
            "234",
            "url_to_user_profile_image3",
            "url_to_post_image3",
            "Feeling happy today.",
            15,
            3
        )
    )
}
