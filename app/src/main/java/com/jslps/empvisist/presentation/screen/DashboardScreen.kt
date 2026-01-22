package com.jslps.empvisist.presentation.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jslps.empvisist.presentation.components.MainToolbar
import com.jslps.empvisist.presentation.components.CustomSearchableSpinner
import com.jslps.empvisist.presentation.components.manageStatusBar
import com.jslps.empvisist.presentation.viewmodel.DashboardViewmodel
import com.jslps.empvisist.ui.theme.EmpVisistTheme
import com.jslps.empvisist.ui.theme.gradientUserDetails
import com.jslps.empvisit.R


@Composable
@Preview(showSystemUi = true, showBackground = true)
fun PreviewDashboard() {
    EmpVisistTheme {
        DashboardScreen()
    }
}

@SuppressLint("RememberReturnType", "UnrememberedGetBackStackEntry")
@Composable
fun DashboardScreen(
    navController: NavHostController? = null,
    viewModelDB: DashboardViewmodel? = null
) {
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(true) }
    var showDialog by remember { mutableStateOf(false) }
    var dialogType by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModelDB?.getPanchayatList()
        viewModelDB?.getUserDetail()
        isLoading = false
    }

    // Log.d("TAG", "User Data: $userData")
    Surface {
        //Status Bar
        manageStatusBar()

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            )
            {

                MainToolbar(
                    viewModel = viewModelDB,
                    title = stringResource(R.string.app_name),
                    navController,
                    onClick = { },
                    typeActivity = "Dashboard"

                )
                Spacer(modifier = Modifier.height(2.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 5.dp,vertical = 2.dp)
                        .background(
                            color = MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = 0.8f),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .border(
                            width = 0.5.dp,
                            color = MaterialTheme.colorScheme.onPrimaryFixedVariant,
                            shape = RoundedCornerShape(10.dp) // Same shape for border
                        )

                )
                {

                    //District
                    Spacer(modifier = Modifier.height(1.dp))
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 2.dp)
                            .background(
                                gradientUserDetails,
                                shape = RoundedCornerShape(8.dp)
                            ),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        // --- Label ---
                        Text(
                            text = "District",
                            modifier = Modifier
                                .weight(0.4f)
                                .padding(end = 5.dp, start = 5.dp, top = 10.dp, bottom = 10.dp),
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontStyle = FontStyle.Normal,
                                fontWeight = FontWeight.Bold, // <-- Make it bold
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        )

                        Box(
                            modifier = Modifier
                                .weight(0.6f)
                                .padding(vertical = 5.dp),
                            contentAlignment = Alignment.Center
                        ) {

                            Text(
                                text = "",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontStyle = FontStyle.Normal,
                                    fontWeight = FontWeight.Medium
                                ),
                                color = MaterialTheme.colorScheme.primary, // <-- Text color matches theme
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.padding(start = 10.dp)
                            )


                            /*Button(
                            onClick = { },
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    color = MaterialTheme.colorScheme.surfaceContainer,
                                    shape = RoundedCornerShape(6.dp)
                                ),
                            shape = RoundedCornerShape(6.dp),

                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = Color.Transparent
                            )
                        ) {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Text(
                                    text = "",
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        fontStyle = FontStyle.Normal,
                                        fontWeight = FontWeight.Medium
                                    ),
                                    color = MaterialTheme.colorScheme.primary, // <-- Text color matches theme
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            }
                        }*/
                        }
                    }

                    // Block
                    Spacer(modifier = Modifier.height(1.dp))
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 2.dp)
                            .background(
                                gradientUserDetails,
                                shape = RoundedCornerShape(8.dp)
                            ),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        // --- Label ---
                        Text(
                            text = "Block",
                            modifier = Modifier
                                .weight(0.4f)
                                .padding(end = 5.dp, start = 5.dp, top = 10.dp, bottom = 10.dp),
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontStyle = FontStyle.Normal,
                                fontWeight = FontWeight.Bold, // <-- Make it bold
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        )

                        Box(
                            modifier = Modifier
                                .weight(0.6f)
                                .padding(vertical = 5.dp),
                            contentAlignment = Alignment.Center
                        ) {

                            Text(
                                text = "",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontStyle = FontStyle.Normal,
                                    fontWeight = FontWeight.Medium
                                ),
                                color = MaterialTheme.colorScheme.primary, // <-- Text color matches theme
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.padding(start = 10.dp)
                            )


                            /*Button(
                            onClick = { },
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    color = MaterialTheme.colorScheme.surfaceContainer,
                                    shape = RoundedCornerShape(6.dp)
                                ),
                            shape = RoundedCornerShape(6.dp),

                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = Color.Transparent
                            )
                        ) {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Text(
                                    text = "",
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        fontStyle = FontStyle.Normal,
                                        fontWeight = FontWeight.Medium
                                    ),
                                    color = MaterialTheme.colorScheme.primary, // <-- Text color matches theme
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            }
                        }*/
                        }
                    }

                    // User name
                    Spacer(modifier = Modifier.height(1.dp))
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 2.dp)
                            .background(
                                gradientUserDetails,
                                shape = RoundedCornerShape(8.dp)
                            ),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        // --- Label ---
                        Text(
                            text = "User Name",
                            modifier = Modifier
                                .weight(0.4f)
                                .padding(end = 5.dp, start = 5.dp, top = 10.dp, bottom = 10.dp),
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontStyle = FontStyle.Normal,
                                fontWeight = FontWeight.Bold, // <-- Make it bold
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        )

                        Box(
                            modifier = Modifier
                                .weight(0.6f)
                                .padding(vertical = 5.dp),
                            contentAlignment = Alignment.Center
                        ) {

                            Text(
                                text = "",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontStyle = FontStyle.Normal,
                                    fontWeight = FontWeight.Medium
                                ),
                                color = MaterialTheme.colorScheme.primary, // <-- Text color matches theme
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.padding(start = 10.dp)
                            )


                            /*Button(
                            onClick = { },
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    color = MaterialTheme.colorScheme.surfaceContainer,
                                    shape = RoundedCornerShape(6.dp)
                                ),
                            shape = RoundedCornerShape(6.dp),

                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = Color.Transparent
                            )
                        ) {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Text(
                                    text = "",
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        fontStyle = FontStyle.Normal,
                                        fontWeight = FontWeight.Medium
                                    ),
                                    color = MaterialTheme.colorScheme.primary, // <-- Text color matches theme
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            }
                        }*/
                        }
                    }

                    Spacer(modifier = Modifier.height(1.dp))

                }


                Spacer(modifier = Modifier.height(5.dp))
                // Panchyat Dropdown

                Row(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .background(
                            gradientUserDetails,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                )
                {

                    Text(
                        text = "Panchayat",
                        modifier = Modifier
                            .weight(0.4f)
                            .padding(end = 10.dp, start = 10.dp),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontStyle = FontStyle.Normal,
                            fontWeight = MaterialTheme.typography.bodyLarge.fontWeight
                        ),
                    )

                    // OutlinedButton styled to look like an input box
                    Box(
                        modifier = Modifier
                            .weight(0.6f)
                            .padding(vertical = 5.dp)
                    ) {
                        OutlinedButton(
                            onClick = {
                                showDialog = true
                                dialogType = "panchayat"
                            },
                            modifier = Modifier
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(6.dp),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),

                            contentPadding = PaddingValues(
                                horizontal = 12.dp,
                                vertical = 12.dp
                            ),
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = Color.Transparent
                            )
                        ) {

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            )
                            {
                                // text or placeholder
                                if (viewModelDB?.arrayPanchayatList?.value?.isNotEmpty() == true) {
                                    Text(
                                        text = "Select",
                                        style = TextStyle(
                                            fontSize = 14.sp,
                                            fontStyle = FontStyle.Normal,
                                            fontWeight = MaterialTheme.typography.bodyLarge.fontWeight
                                        ),
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(start = 10.dp)
                                    )
                                } else {
                                    Text(
                                        text = "",
                                        style = TextStyle(
                                            fontSize = 14.sp,
                                            fontStyle = FontStyle.Normal,
                                            fontWeight = MaterialTheme.typography.bodyLarge.fontWeight
                                        ),
                                        color = MaterialTheme.colorScheme.onSurface,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(start = 10.dp)
                                    )
                                }
                                Icon(
                                    imageVector = Icons.Filled.ArrowDropDown,
                                    contentDescription = "Dropdown"
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(5.dp))
                // Village Dropdown
                Row(
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                        .background(
                            gradientUserDetails,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Text(
                        text = "Village",
                        modifier = Modifier
                            .weight(0.4f)
                            .padding(
                                end = 10.dp,
                                start = 10.dp,
                                top = 15.dp,
                                bottom = 15.dp
                            ),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontStyle = FontStyle.Normal,
                            fontWeight = MaterialTheme.typography.bodyLarge.fontWeight
                        ),
                    )

                    Box(
                        modifier = Modifier
                            .weight(0.6f)
                            .padding(vertical = 5.dp)
                    ) {
                        OutlinedButton(
                            onClick = {
                                showDialog = true
                                dialogType = "village"
                            },
                            modifier = Modifier
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(6.dp),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                            contentPadding = PaddingValues(
                                horizontal = 12.dp,
                                vertical = 12.dp
                            ),
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = Color.Transparent
                            )
                        ) {

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                // text or placeholder
                                if (viewModelDB?.villageList?.value?.isNotEmpty() == true) {
                                    Text(
                                        text = "Select",
                                        style = TextStyle(
                                            fontSize = 14.sp,
                                            fontStyle = FontStyle.Normal,
                                            fontWeight = MaterialTheme.typography.bodyLarge.fontWeight
                                        ),
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(start = 10.dp)
                                    )
                                } else {
                                    Text(
                                        text = "",
                                        style = TextStyle(
                                            fontSize = 14.sp,
                                            fontStyle = FontStyle.Normal,
                                            fontWeight = MaterialTheme.typography.bodyLarge.fontWeight
                                        ),
                                        color = MaterialTheme.colorScheme.onSurface,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(start = 10.dp)
                                    )
                                }
                                Icon(
                                    imageVector = Icons.Filled.ArrowDropDown,
                                    contentDescription = "Dropdown"
                                )
                            }
                        }
                    }
                }


                if (showDialog) {
                    when (dialogType) {
                        "panchayat" -> CustomSearchableSpinner(
                            showDialog = showDialog,
                            onDismiss = { showDialog = false },
                            title = "Select Panchayat",
                            items = viewModelDB?.arrayPanchayatList?.value
                                ?: emptyList(),
                            labelSelector = { panchayat ->
                                panchayat.PanchayatName
                                    ?: "Unnamed"
                            },
                            onItemSelected = { selectedPanchayat ->
                                // Handle selection
                                viewModelDB?.getVillageList(selectedPanchayat.PanchayatCode)
                                Log.d("TAG", "Selected Panchayat: $selectedPanchayat")
                                showDialog = false
                            },
                        )

                        "village" -> CustomSearchableSpinner(
                            showDialog = showDialog,
                            onDismiss = { showDialog = false },
                            title = "Select Village",
                            items = viewModelDB?.villageList?.value ?: emptyList(),
                            labelSelector = { it.VillageName ?: "" },
                            onItemSelected = { selected ->
                                showDialog = false
                                Log.d("TAG", "Selected Panchayat: $selected")
                            },
                        )
                    }
                }
            }

    }


}

