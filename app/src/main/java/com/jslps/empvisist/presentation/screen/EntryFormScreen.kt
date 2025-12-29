package com.jslps.empvisist.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.jslps.empvisist.presentation.components.AppOutlinedButton
import com.jslps.empvisist.presentation.components.AppText
import com.jslps.empvisist.presentation.components.AppTextField
import com.jslps.empvisist.presentation.components.MainToolbar
import com.jslps.empvisist.presentation.components.manageStatusBar
import com.jslps.empvisit.R


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun EntryFormScreenPreview() {

    EntryFormScreen()
}

@Composable
fun EntryFormScreen(
    navController: NavHostController? = null
) {
    Surface {
        //Status Bar
        manageStatusBar()
        Column(modifier = Modifier) {
            MainToolbar(
                viewModel = null,
                title = stringResource(R.string.app_name),
                navController,
                onClick = { },
                typeActivity = "Entry Form"
            )


            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val (lbl1, text1, lbl2, text2, lbl3, text3, btnSave, btnLogin) = createRefs()
                AppText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(lbl1) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    padding = PaddingValues(start = 10.dp, end = 10.dp, top = 10.dp),
                    textColor = MaterialTheme.colorScheme.primary,
                    backgroundColor = MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = 0.5f),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    text = "Entry Form Screen"

                )


                AppTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .constrainAs(text1) {
                            top.linkTo(lbl1.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    value = "",
                    onValueChange = { },
                    label = "Username",
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 10.dp,
                        bottomStart = 10.dp,
                        bottomEnd = 0.dp
                    ),

                    )


                AppText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(lbl2) {
                            top.linkTo(text1.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    padding = PaddingValues(start = 10.dp, end = 10.dp, top = 10.dp),
                    textColor = MaterialTheme.colorScheme.primary,
                    backgroundColor = MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = 0.5f),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    text = "Entry Form Screen 22222"

                )


                AppTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .constrainAs(text2) {
                            top.linkTo(lbl2.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    value = "",
                    onValueChange = { },
                    label = "Username",
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 10.dp,
                        bottomStart = 10.dp,
                        bottomEnd = 0.dp
                    ),
                )

                AppText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(lbl3) {
                            top.linkTo(text2.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    padding = PaddingValues(start = 10.dp, end = 10.dp, top = 10.dp),
                    textColor = MaterialTheme.colorScheme.primary,
                    backgroundColor = MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = 0.5f),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    text = "Entry Form Screen 33"

                )


                AppTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .constrainAs(text3) {
                            top.linkTo(lbl3.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    value = "",
                    onValueChange = { },
                    label = "Username",
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 10.dp,
                        bottomStart = 10.dp,
                        bottomEnd = 0.dp
                    ),
                )


                AppOutlinedButton(
                    modifier = Modifier.constrainAs(btnLogin) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
                    padding = PaddingValues(start = 10.dp, end = 10.dp, bottom = 10.dp),
                    text = "Login",
                    shape = RoundedCornerShape(
                        topStart = 40.dp,
                        topEnd = 20.dp,
                        bottomStart = 20.dp,
                        bottomEnd = 40.dp
                    ),
                    onClick = {

                    },
                )

            }
        }


    }

}