package com.jslps.empvisist.presentation.screen

import android.annotation.SuppressLint
import android.graphics.Paint
import android.graphics.Rect
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.jslps.empvisist.api.ApiResponse
import com.jslps.empvisist.presentation.components.AppImage
import com.jslps.empvisist.presentation.components.AppOutlinedButton
import com.jslps.empvisist.presentation.components.AppText
import com.jslps.empvisist.presentation.components.AppTextField
import com.jslps.empvisist.presentation.components.LoadingView
import com.jslps.empvisist.presentation.navigation.Screens
import com.jslps.empvisist.ui.theme.AppGradientBackground
import com.jslps.empvisist.presentation.viewmodel.LoginViewModel
import com.jslps.empvisist.ui.theme.EmpVisistTheme
import com.jslps.empvisist.utils.AutoRequestPermissions
import com.jslps.empvisit.R
import kotlin.random.Random


@SuppressLint("ViewModelConstructorInComposable")
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LoginScreenPreview() {
    EmpVisistTheme {
        LoginScreen()
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun LoginScreen(
    navController: NavHostController? = null,
    loginVM: LoginViewModel? = null
) {
    val context = LocalContext.current
    var captchaText by remember { mutableStateOf(generateCaptchaText()) }
    var isCaptchaVerified by remember { mutableStateOf(false) }

    val loginState = loginVM?.loginState?.collectAsStateWithLifecycle()
    val isLoading = loginState?.value is ApiResponse.Loading
    val username by loginVM?.username?.collectAsStateWithLifecycle()
        ?: remember { mutableStateOf("") }
    val password by loginVM?.password?.collectAsStateWithLifecycle()
        ?: remember { mutableStateOf("") }


    LaunchedEffect(loginState?.value) {
        when (loginState?.value) {
            is ApiResponse.Success -> {
                val dataSuccess = loginState.value as ApiResponse.Success
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                Log.d("TAG", "LoginScreen: Success")
                navController?.navigate(Screens.DashboardScreen.route) {
                    popUpTo(Screens.LoginScreen.route) { inclusive = true }
                }
            }

            is ApiResponse.Error -> {
                val dataError = loginState.value as ApiResponse.Error
                loginVM.clearLoginState()
                Toast.makeText(context, dataError.message, Toast.LENGTH_SHORT).show()
            }

            else -> {}
        }
    }

    if (!LocalInspectionMode.current) {
        AutoRequestPermissions()
    }
    Box(modifier = Modifier.fillMaxSize()) {
        AppGradientBackground(modifier = Modifier.fillMaxSize()) {
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val (imgLogo, textLogin, edtUserName, edtPassword, captchaView, btnLogin) = createRefs()

                AppImage(
                    modifier = Modifier.constrainAs(imgLogo) {
                        top.linkTo(parent.top, margin = 50.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                    url = "https://picsum.photos/300",
                    height = 120.dp,
                    width = 120.dp,
                    cornerRadius = 12.dp,
                    drawable = painterResource(id = R.drawable.ic_launcher_foreground)
                )

                AppText(
                    modifier = Modifier.constrainAs(textLogin) {
                        top.linkTo(imgLogo.bottom, margin = 24.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                    text = "Login To Your Account",
                    underline = true,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    backgroundColor = Color.Transparent,
                    textColor = Color.Black
                )

                AppTextField(
                    modifier = Modifier.constrainAs(edtUserName) {
                        top.linkTo(textLogin.bottom, margin = 24.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                    padding = PaddingValues(horizontal = 10.dp),
                    value = username,
                    onValueChange = {
                        loginVM?.updateUsername(it)
                    },
                    label = "Username",
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 10.dp,
                        bottomStart = 10.dp,
                        bottomEnd = 0.dp
                    ),
                )

                AppTextField(
                    modifier = Modifier.constrainAs(edtPassword) {
                        top.linkTo(edtUserName.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                    padding = PaddingValues(horizontal = 10.dp),
                    value = password,
                    onValueChange = {
                        loginVM?.updatePassword(it)
                    },
                    label = "Password",
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 10.dp,
                        bottomStart = 10.dp,
                        bottomEnd = 0.dp
                    ),
                )
                CaptchaView(
                    modifier = Modifier.constrainAs(captchaView) {
                        top.linkTo(edtPassword.bottom, margin = 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                    captchaText = captchaText,
                    onVerificationResult = { isCaptchaVerified = it },
                    onRefreshRequest = { captchaText = generateCaptchaText() }
                )

                AppOutlinedButton(
                    modifier = Modifier.constrainAs(btnLogin) {
                        top.linkTo(captchaView.bottom, margin = 24.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                    padding = PaddingValues(horizontal = 10.dp),
                    text = "Login",
                    shape = RoundedCornerShape(
                        topStart = 40.dp,
                        topEnd = 20.dp,
                        bottomStart = 20.dp,
                        bottomEnd = 40.dp
                    ),
                    onClick = {
                        loginVM?.onLoginClick(
                            loginVM.username.value,
                            loginVM.password.value
                        )
                    },
                    // enabled = isCaptchaVerified
                )
                if (isLoading) {
                    LoadingView("Loading...")
                }
            }
        }
    }

}


@Composable
fun CaptchaView(
    modifier: Modifier = Modifier,
    captchaText: String,
    onVerificationResult: (Boolean) -> Unit,
    onRefreshRequest: () -> Unit
) {
    var userInput by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // CAPTCHA Display Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // CAPTCHA Canvas
            Canvas(
                modifier = Modifier
                    .height(50.dp)
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
            ) {
                // Background
                drawRect(Color.LightGray.copy(alpha = 0.2f))

                // Add noise (3000 random elements)
                repeat(3000) {
                    when (Random.nextInt(3)) {
                        0 -> drawCircle(
                            color = Color.Black.copy(alpha = 0.5f),
                            radius = Random.nextFloat() * 3.dp.toPx(),
                            center = Offset(
                                Random.nextFloat() * size.width,
                                Random.nextFloat() * size.height
                            )
                        )

                        1 -> drawLine(
                            color = Color.Black.copy(alpha = 0.7f),
                            start = Offset(
                                Random.nextFloat() * size.width,
                                Random.nextFloat() * size.height
                            ),
                            end = Offset(
                                Random.nextFloat() * size.width,
                                Random.nextFloat() * size.height
                            ),
                            strokeWidth = 1.dp.toPx()
                        )

                        2 -> drawRect(
                            color = Color.DarkGray.copy(alpha = 0.6f),
                            topLeft = Offset(
                                Random.nextFloat() * size.width,
                                Random.nextFloat() * size.height
                            ),
                            size = Size(4.dp.toPx(), 4.dp.toPx())
                        )
                    }
                }

                // Draw CAPTCHA text with distortions
                captchaText.forEachIndexed { index, char ->
                    val x = size.width * (index + 1) / (captchaText.length + 1)
                    val y = size.height / 2

                    val paint = Paint().apply {
                        color = android.graphics.Color.rgb(
                            Random.nextInt(100, 200),
                            Random.nextInt(100, 200),
                            Random.nextInt(100, 200)
                        )
                        textSize = 24.sp.toPx()
                        isFakeBoldText = true
                    }

                    // Get text bounds for proper vertical centering
                    val textBounds = Rect()
                    paint.getTextBounds(char.toString(), 0, 1, textBounds)

                    rotate(degrees = Random.nextFloat() * 30 - 15, pivot = Offset(x, y)) {
                        drawContext.canvas.nativeCanvas.drawText(
                            char.toString(),
                            x,
                            y + (textBounds.height() / 2),
                            paint
                        )
                    }
                }
            }

            // Refresh Button
            IconButton(
                onClick = onRefreshRequest,
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = "Refresh CAPTCHA",
                    tint = Color.Blue,
                    modifier = Modifier.size(35.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(5.dp))

        // CAPTCHA Input Field

        AppTextField(
            value = userInput,
            onValueChange = {
                userInput = it
                onVerificationResult(it.equals(captchaText, ignoreCase = true))
            },
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 10.dp,
                bottomStart = 10.dp,
                bottomEnd = 0.dp
            ),
            label = "Enter captcha",

            isError = userInput.isNotEmpty() && !userInput.equals(captchaText, ignoreCase = true),
            modifier = Modifier.fillMaxWidth()
        )

        // Verification Status
        if (userInput.isNotEmpty()) {
            Text(
                text = if (userInput.equals(captchaText, ignoreCase = true))
                    "✓ Verified" else "✗ Incorrect CAPTCHA",
                color = if (userInput.equals(captchaText, ignoreCase = true))
                    Color.Green else Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

private fun generateCaptchaText(length: Int = 6): String {
    val chars = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789"
    return (1..length)
        .map { Random.nextInt(0, chars.length) }
        .map(chars::get)
        .joinToString("")
}

