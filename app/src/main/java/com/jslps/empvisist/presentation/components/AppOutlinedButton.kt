package com.jslps.empvisist.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jslps.empvisist.ui.theme.ColorBtnGreen
import com.jslps.empvisist.ui.theme.baseColorRed
import com.jslps.empvisist.ui.theme.gradientButton

@Composable
fun AppOutlinedButton(
    text: String,
    modifier: Modifier = Modifier,
    height: Dp = 60.dp,
    enabled: Boolean = true,
    icon: (@Composable () -> Unit)? = null,
    onClick: () -> Unit,
    shape: RoundedCornerShape = RoundedCornerShape(0.dp),
    padding: PaddingValues = PaddingValues(0.dp),
    textColor: Color = Color.White,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    containerColor: Color = MaterialTheme.colorScheme.primary,
) {

    val appliedModifier = if (height == 60.dp) {
        modifier
            .wrapContentHeight()
    } else {
        modifier
            .height(height)
            .fillMaxWidth()
    }

    OutlinedButton(
        onClick = onClick,
        enabled = enabled,
        border = BorderStroke(2.dp, if (enabled) Color.Red.copy(alpha = 0.5f) else Color.Gray),
        shape = shape,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = if (enabled) Color.Blue else Color.Gray,
            disabledContentColor = Color.Gray,
            containerColor = ColorBtnGreen
        ),
        modifier = appliedModifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(padding)

    ) {
        if (icon != null) {
            icon()
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(
            text = text,
            style = TextStyle(fontSize = fontSize, color = textColor, fontWeight = fontWeight)
        )
    }
}
