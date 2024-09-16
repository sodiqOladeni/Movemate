package com.hackwithsodiq.movemate.ui

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hackwithsodiq.movemate.R
import com.hackwithsodiq.movemate.navigation.Route
import com.hackwithsodiq.movemate.ui.theme.CoolGray
import com.hackwithsodiq.movemate.ui.theme.MovemateTheme
import com.hackwithsodiq.movemate.ui.theme.ProgressGreen
import com.hackwithsodiq.movemate.ui.theme.TempOrange

@Composable
fun EstimatedCalculation(navController: NavController) {

    var amountInitialAndActual by remember { mutableIntStateOf(1000) }
    val estimatedAmount by animateIntAsState(
        targetValue = amountInitialAndActual,
        animationSpec = tween(
            durationMillis = 2000,
            easing = FastOutSlowInEasing
        ), label = ""
    )
    LaunchedEffect(Unit) {
        amountInitialAndActual = 1457
    }
    Scaffold { paddingValue ->
        Column(
            modifier = Modifier
                .padding(paddingValue)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Image(painterResource(R.drawable.movemate), contentDescription = "movemate_logo")

            Spacer(modifier = Modifier.height(50.dp))
            Image(painterResource(R.drawable.box), contentDescription = "package_box")

            Spacer(modifier = Modifier.height(50.dp))
            Text(
                stringResource(R.string.total_estimated_amount),
                style = MaterialTheme.typography.h5.copy(fontSize = 25.sp),
            )
            Spacer(modifier = Modifier.height(5.dp))
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    "$${estimatedAmount}",
                    style = MaterialTheme.typography.h5.copy(fontSize = 23.sp, color = ProgressGreen)
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                    "USD",
                    style = MaterialTheme.typography.h5.copy(fontSize = 18.sp, color = ProgressGreen)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                stringResource(R.string.estimated_amount_message),
                style = MaterialTheme.typography.subtitle1.copy(fontSize = 16.sp, color = CoolGray),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = {
                    navController.popBackStack(route = Route.HOME.route, inclusive = false)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = TempOrange,
                    contentColor = Color.White
                )
            ) {
                Text(stringResource(R.string.back_to_home))
            }
        }
    }
}

@Composable
@Preview
fun EstimatedCalculation_Preview() {
    MovemateTheme {
        EstimatedCalculation(rememberNavController())
    }
}