package com.dog.data.viewmodel.feed

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dog.data.model.Comment
import com.dog.data.model.comment.AddCommentRequest
import com.dog.data.model.comment.CommentItem
import com.dog.data.model.comment.CommentResponse
import com.dog.data.model.feed.BoardItem
import com.dog.data.model.feed.BoardResponse
import com.dog.data.model.like.LikeUpRequest
import com.dog.data.repository.CommentRepository
import com.dog.data.repository.FeedRepository
import com.dog.data.repository.LikeRepository
import com.dog.util.common.DataStoreManager
import com.dog.util.common.RetrofitClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    private val interceptor = RetrofitClient.RequestInterceptor(dataStoreManager)
    private val commentApi: CommentRepository =
        RetrofitClient.getInstance(interceptor).create(CommentRepository::class.java)
    private val likeApi: LikeRepository =
        RetrofitClient.getInstance(interceptor).create(LikeRepository::class.java)
    private val feedApi: FeedRepository =
        RetrofitClient.getInstance(interceptor).create(FeedRepository::class.java)

    private val _feedList = mutableStateListOf<BoardItem>()
    val feedListState: SnapshotStateList<BoardItem> get() = _feedList

    private val _commentList = mutableStateListOf<CommentItem>()
    val commentListState: SnapshotStateList<CommentItem> get() = _commentList

    private var _isDataLoaded = mutableStateOf(false)
    val isDataLoaded: State<Boolean> get() = _isDataLoaded

    //좋아요
    private val _likes = MutableStateFlow(0L)
    val likes: MutableStateFlow<Long> get() = _likes
    private val _isLiked = MutableStateFlow(false)
    val isLiked: StateFlow<Boolean> get() = _isLiked

    init {
        viewModelScope.launch {
            val userLatitude = 127.11
            val userLongitude = 35.11
            loadBoarderNearData(userLatitude, userLongitude)
        }
    }

    init {
        viewModelScope.launch {
            val boardId = 1L
            loadCommentListData(boardId)
        }
    }

    fun addComment(
        addCommentRequest: AddCommentRequest, userName: String?,
        userProfileUrl: String?
    ) {
        viewModelScope.launch {
            Log.d("API_Response", addCommentRequest.toString())
            try {
                val response = commentApi.addCommentApiResponse(addCommentRequest)
                if (response.isSuccessful) {
                    Log.i("API_Response", "댓글 등록 : ${response.body()}")
                    val commentResponse = response.body()
                    commentResponse?.let {
                        // 성공적으로 댓글이 추가되었을 때의 처리
                        val newCommentItem = CommentItem(
                            boardId = addCommentRequest.boardId,
                            commentId = 0,
                            commentContent = addCommentRequest.commentContent,
                            userNickname = userName ?: "", // TODO: 바로 들어가게 해야함
                            commentLikes = 0,
                            userProfileUrl = userProfileUrl ?: "",  // 여기에 프로필 URL을 설정해야 합니다.
                        )
                        _commentList.add(newCommentItem)
                        _commentsCount.value = _commentList.size
                    }
                } else {
                    // 댓글 추가 실패 시의 처리
                    Log.e(
                        "API_Response",
                        "Add Comment Failed: ${response.code()}, ${response.message()}"
                    )
                }
            } catch (e: Exception) {
                // 예외 발생 시의 처리
                // 만약 빈 배열이 들어올 때 예외를 무시하고 싶다면 여기에 추가적인 로그만 남기거나 아무 작업을 하지 않으면 됩니다.
                Log.e("API_Response", "Exception: ${e.message}", e)
            }
        }
    }


    suspend fun loadCommentListData(
        boardId: Long
    ): CommentResponse? {
        val commentListApi: CommentRepository =
            RetrofitClient.getInstance(interceptor).create(CommentRepository::class.java)
        val commentListResponse = commentListApi.getCommentListApiResponse(
            boardId = boardId
        )
        if (commentListResponse.isSuccessful) {
            return commentListResponse.body()
        } else {
            _isDataLoaded.value = false
            Log.e(
                "API_Response",
                "API Call Failed: ${commentListResponse.code()}, ${commentListResponse.message()}"
            )
        }
        return null
    }

    suspend fun loadBoarderNearData(
        userLatitude: Double,
        userLongitude: Double,
    ): Response<BoardResponse> {
        val NearDateApi: FeedRepository =
            RetrofitClient.getInstance(interceptor).create(FeedRepository::class.java)
//     리포지토리를 통해 데이터를 불러옵니다.
        val response = NearDateApi.getBoarderNearApiResponse(
            userLatitude = userLatitude,
            userLongitude = userLongitude,
        )
        if (response.isSuccessful) {
            response.body()?.body?.let { boardNearApi ->
                _feedList.clear()
                _feedList.addAll(boardNearApi)
                _isDataLoaded.value = true
            }
            Log.d("API_Response", "API Call Successful: ${response.body()}")
        } else {
            _isDataLoaded.value = false
            Log.e("API_Response", "API Call Failed: ${response.code()}, ${response.message()}")
        }
        return response
    }

    private val _commentsList = mutableStateListOf<Comment>()
    val commentsList: List<Comment> get() = _commentsList

    private val _commentsCount = MutableStateFlow(0)
    val commentsCount: StateFlow<Int> get() = _commentsCount

    fun addComment(comment: Comment) {
        _commentsList.add(comment)
        _commentsCount.value = _commentsList.size
    }

    //like
    fun toggleLikeStatus(feedItem: BoardItem) {

        viewModelScope.launch {
            val likeUpRequest = LikeUpRequest(feedItem.boardId)
//             likecheck 상태에 따라 게시물 좋아요 또는 취소 호출
            if (!feedItem.likecheck) {
                likePost(likeUpRequest, feedItem)
            } else {
                likeDown(feedItem)
            }
        }
    }

    suspend fun likePost(likeUpRequest: LikeUpRequest, feedItem: BoardItem) {
        viewModelScope.launch {
            Log.d("API_Response", likeUpRequest.toString())
            try {
                val response = likeApi.likeUp(likeUpRequest)
                Log.d("likeUp", response.body()?.body.toString())
                if (response.isSuccessful) {
                    Log.i("API_Response", "게시글 좋아요 등록 : ${response.body()}")
                    val likeResponse = response.body()?.body
                    likeResponse?.let {
                        // 성공적으로 좋아요가 추가되었을 때의 처리
                        if (!feedItem.likecheck) {
                            // 좋아요 수 증가 (눌렀을 때만)
                            feedItem.boardLikes += 1
                            feedItem.likecheck = true
                            _likes.value = feedItem.boardLikes
                        }
                        // 좋아요 여부 업데이트
                        _isLiked.value = feedItem.likecheck
                    }
                } else {
                    // 좋아요 추가 실패 시의 처리
                    Log.e(
                        "API_Response",
                        "Like Post Failed: ${response.code()}, ${response.message()}"
                    )
                }
            } catch (e: Exception) {
                // 예외 발생 시의 처리
                Log.e("API_Response", "Exception: ${e.message}", e)
            }
        }
    }

    suspend fun DeleteFeed(feedItem: BoardItem) {
        viewModelScope.launch {
            try {
                val response = feedApi.deleteFeedApiResponse(boardId = feedItem.boardId)
                Log.d("delete", response.body().toString())
                if (response.isSuccessful) {
                    val deleteFeedResponse = response.body()?.body
                    deleteFeedResponse?.let {
                        feedItem.boardId
                    }
                } else {
                    // 게시글 삭제 실패 시의 처리
                    Log.e(
                        "API_Response",
                        "Delete Feed Failed: ${response.code()}, ${response.message()}"
                    )
                }
            } catch (e: Exception) {
                Log.e("API_Response", "Exception: ${e.message}", e)
            }
        }
    }

    suspend fun likeDown(feedItem: BoardItem) {
        viewModelScope.launch {
            try {
                val response = likeApi.likeDown(boardId = feedItem.boardId)
                Log.d("likeDown", response.body().toString())
                if (response.isSuccessful) {
                    Log.i("API_Response", "게시글 좋아요 감소 : ${response.body()}")
                    val likeResponse = response.body()?.body
                    likeResponse?.let {
                        // 성공적으로 좋아요가 감소되었을 때의 처리
                        if (feedItem.likecheck) {
                            // 좋아요 수 감소 (눌렀을 때만)
                            feedItem.boardLikes -= 1
                            feedItem.likecheck = false
                            _likes.value = feedItem.boardLikes
                        }
                        // 좋아요 여부 업데이트
                        _isLiked.value = feedItem.likecheck
                    }
                } else {
                    // 좋아요 감소 실패 시의 처리
                    Log.e(
                        "API_Response",
                        "Like Down Failed: ${response.code()}, ${response.message()}"
                    )
                }
            } catch (e: Exception) {
                // 예외 발생 시의 처리
                Log.e("API_Response", "Exception: ${e.message}", e)
            }
        }
    }

    fun deleteComment(comment: Long) {
        viewModelScope.launch {
            try {
                val response = commentApi.deleteCommentApiResponse(commentId = comment)
                Log.d("deleteComment", response.body().toString())
                if (response.isSuccessful) {
                    Log.i("API_Response", "댓글 삭제 성공 : ${response.body()}")
                    val deleteCommentResponse = response.body()?.body
                    deleteCommentResponse?.let {
                        // 성공적으로 댓글이 삭제되었을 때의 처리
                        // You can perform additional actions if needed
                    }
                } else {
                    // 댓글 삭제 실패 시의 처리
                    Log.e(
                        "API_Response",
                        "Delete Comment Failed: ${response.code()}, ${response.message()}"
                    )
                }
            } catch (e: Exception) {
                // 예외 발생 시의 처리
                Log.e("API_Response", "Exception: ${e.message}", e)
            }
        }
    }
}








