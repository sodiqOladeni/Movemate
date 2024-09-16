package com.hackwithsodiq.movemate.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hackwithsodiq.movemate.ui.theme.DarkBlue
import com.hackwithsodiq.movemate.ui.theme.GrayTextColor

@Composable
fun CustomChip(text: String, isSelected: Boolean, onClick: () -> Unit) {
    val chipBackground = if (isSelected) DarkBlue else MaterialTheme.colors.background
    val borderStrokeColor = if (isSelected) DarkBlue else GrayTextColor
    val contentColor = if (isSelected) Color.White else DarkBlue
    val shapeCorner = RoundedCornerShape(corner = CornerSize(8.dp))

    Box(
        modifier = Modifier
            .border(
                border = BorderStroke(1.dp, color = borderStrokeColor),
                shape = shapeCorner
            ).clickable {
                onClick()
            }
            .background(chipBackground, shape = shapeCorner), contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 2.dp)
        ) {
            AnimatedVisibility(isSelected) {
                Icon(
                    Icons.Outlined.Done,
                    tint = contentColor,
                    contentDescription = "selected",
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(15.dp))
            }
            Text(text, color = contentColor, fontSize = 13.sp)
        }
    }
}

@Composable
@Preview
fun CustomChip_Preview() {
    CustomChip("Glass", false) {

    }
}