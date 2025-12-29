package com.jslps.empvisist.presentation.components

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jslps.empvisist.presentation.viewmodel.DashboardViewmodel
import com.jslps.empvisist.ui.theme.ColorWhite
import com.jslps.empvisist.ui.theme.gradientToolBar
import com.jslps.empvisit.R

@Composable
fun MainToolbar(
    viewModel: DashboardViewmodel? = null,
    title: String,
    navController: NavHostController? = null,
    onClick: () -> Unit,
    typeActivity: String? = null
) {
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp),
                clip = true,
                spotColor = Color.Black.copy(alpha = 0.5f),
                ambientColor = Color.Black.copy(alpha = 0.1f)
            )
            .background(
                color = MaterialTheme.colorScheme.onPrimary,
                shape = RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp)
            )
            .padding(top = 25.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (typeActivity != "Dashboard") {
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .padding(start = 10.dp, top = 10.dp, bottom = 5.dp)
                    .clickable {
                        onClick.invoke()
                        if (navController?.popBackStack() != true) {
                            (context as? Activity)?.finish()
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(id = R.drawable.back_1),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryFixed
                )
            }
        }

        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 30.dp),
            text = title,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryFixed
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Image(
            modifier = Modifier
                .size(45.dp)
                .padding(top = 10.dp, end = 10.dp, bottom = 5.dp)
                .clickable { showDialog = true },
            painter = painterResource(id = R.drawable.ic_logout),
            contentDescription = null
        )
    }
}
