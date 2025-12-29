package com.jslps.empvisist.presentation.components

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.jslps.empvisist.ui.theme.ColorBtnRed

@Composable
fun <T> customSearchableSpinner(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    title: String = "",
    items: List<T>,
    labelSelector: (T) -> String,
    onItemSelected: (T) -> Unit
) {
    if (showDialog) {
        Dialog(onDismissRequest = onDismiss) {

            var searchQuery by remember { mutableStateOf("") }
            val filteredItems = remember(searchQuery, items) {
                items.filter { labelSelector(it).contains(searchQuery, ignoreCase = true) }
            }

            Box(
                modifier = Modifier
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    Box(
                        modifier = Modifier
                            .background(Color.White, shape = RoundedCornerShape(12.dp))
                            .fillMaxWidth()
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(10.dp)
                        ) {

                            Text(
                                text = title,
                                style = TextStyle(
                                    fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    color = Color.Black
                                ),
                                modifier = Modifier.padding(all = 10.dp)
                            )

                            // 🔍 Rounded Search Input
                            OutlinedTextField(
                                value = searchQuery,
                                onValueChange = { searchQuery = it },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .padding(vertical = 8.dp, horizontal = 10.dp)
                                    .background(
                                        color = Color(0xFFF8F8F8), // light gray background
                                        shape = RoundedCornerShape(50) // pill-like round edges
                                    )
                                    .border(
                                        width = 1.dp,
                                        color = Color.Gray.copy(alpha = 0.5f),
                                        shape = RoundedCornerShape(50)
                                    ),
                                placeholder = {
                                    Text(
                                        text = "Search...",
                                        color = Color.Gray
                                    )
                                },
                                singleLine = true,
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Search,
                                        contentDescription = "Search Icon",
                                        tint = Color.Gray
                                    )
                                },
                                shape = RoundedCornerShape(50),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = Color.Transparent,
                                    unfocusedBorderColor = Color.Transparent,
                                    disabledBorderColor = Color.Transparent,
                                    focusedContainerColor = Color(0xB22196F3),
                                    unfocusedContainerColor = Color(0xFFF8F8F8)
                                )
                            )


                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(max = 300.dp)
                                    .padding(top = 8.dp)
                            ) {
                                itemsIndexed(filteredItems) { index, item ->
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable {
                                                Log.d("TAG", "Clicked on: $item")
                                                onItemSelected(item)
                                            }
                                            .padding(vertical = 12.dp, horizontal = 16.dp)
                                    ) {
                                        Text(
                                            text = labelSelector(item),
                                            style = MaterialTheme.typography.bodyLarge
                                        )
                                    }

                                    // Divider between items
                                    if (index < filteredItems.lastIndex) {
                                        HorizontalDivider(
                                            color = Color.LightGray,
                                            thickness = 1.dp,
                                            modifier = Modifier.padding(horizontal = 16.dp)
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            // Cancel Button
                            Button(
                                onClick = onDismiss,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp)
                                    .shadow(
                                        elevation = 6.dp, // shadow size
                                        shape = RoundedCornerShape(12.dp),
                                        clip = false
                                    ),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = ColorBtnRed, // background color
                                    contentColor = Color.White    // text/icon color
                                ),
                                shape = RoundedCornerShape(12.dp),
                                elevation = ButtonDefaults.buttonElevation(
                                    defaultElevation = 6.dp,
                                    pressedElevation = 10.dp,
                                    focusedElevation = 8.dp,
                                    hoveredElevation = 8.dp
                                )
                            ) {
                                Text(
                                    text = "Cancel",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }

                        }
                    }

                }
            }
        }
    }

}