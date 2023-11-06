package com.dog.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dog.data.model.user.MatchingUserResponse
import com.dog.data.viewmodel.MatchingViewModel
import com.google.accompanist.pager.*

@Composable
fun MatchingScreen(viewModel: MatchingViewModel) {
    val listState = rememberLazyListState()

    Column {
        LazyRow(
            state = listState,
            modifier = Modifier.fillMaxWidth()
        ) {
            items(viewModel.users.size) { index ->
                val user = viewModel.users[index]
                val isSelected = user.loginId == viewModel.selectedUserId.value
                UserThumbnail(user = user, isSelected = isSelected) {
                    viewModel.selectUser(user.loginId)
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        UserDetailsScreen(viewModel = viewModel, listState = listState)
    }
}

@Composable
fun UserThumbnail(user: MatchingUserResponse, isSelected: Boolean, onSelect: () -> Unit) {
    val thumbnailModifier = if (isSelected) {
        Modifier
            .padding(10.dp)
            .size(80.dp)
            .clip(CircleShape)
            .border(BorderStroke(2.dp, Color.Blue), CircleShape)
            .clickable(onClick = onSelect)
    } else {
        Modifier
            .padding(10.dp)
            .size(80.dp)
            .clip(CircleShape)
            .border(BorderStroke(2.dp, Color.Gray), CircleShape)
            .clickable(onClick = onSelect)
    }

    Box(modifier = thumbnailModifier) {
        // TODO: 이미지 로드 코드로 변경
        Text(text = user.nickname.first().toString(), modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun UserDetailsView(user: MatchingUserResponse) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .shadow(8.dp, RoundedCornerShape(8.dp))
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
        ) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .weight(7f)
                    .fillMaxWidth()
            ) {
                Text(text = "이미지 파일 위치")
            }
            Divider(
                color = Color.LightGray,
                thickness = 1.dp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Box(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = user.nickname,
                        style = MaterialTheme.typography.headlineMedium,
                    )
                    Text(
                        text = user.address?:"입력된 주소가 없습니다.",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserDetailsScreen(viewModel: MatchingViewModel, listState: LazyListState) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        viewModel.users.size
    }
    viewModel.pagerState = pagerState

    LaunchedEffect(pagerState.currentPage) {
        viewModel.selectUser(viewModel.users[pagerState.currentPage].loginId)
        listState.animateScrollToItem(pagerState.currentPage)
    }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
    ) { page ->
        UserDetailsView(user = viewModel.users[page])
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val viewModel = MatchingViewModel()
    MatchingScreen(viewModel = viewModel)
}