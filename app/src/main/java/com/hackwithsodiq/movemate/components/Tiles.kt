package com.hackwithsodiq.movemate.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CardGiftcard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hackwithsodiq.movemate.ui.theme.PackageColor

@Composable
fun PackageTiles(modifier: Modifier, label: String, text: String, tilesColor: Color){
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        Box(
            modifier = Modifier
                .height(36.dp)
                .width(36.dp)
                .background(
                    tilesColor,
                    shape = RoundedCornerShape(
                        corner = CornerSize(100),
                    ),
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Outlined.CardGiftcard, contentDescription = "label", tint = PackageColor)
        }

        Spacer(modifier = Modifier.width(5.dp))
        Column {
            Text(
                label,
                style = MaterialTheme.typography.subtitle1
            )
            Text(
                text,
                style = MaterialTheme.typography.h5.copy(fontSize = 14.sp)
            )
        }
    }
}