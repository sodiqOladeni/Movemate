package com.hackwithsodiq.movemate.ui

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Badge
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Cached
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.HistoryToggleOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hackwithsodiq.movemate.R
import com.hackwithsodiq.movemate.model.Shipment
import com.hackwithsodiq.movemate.model.ShipmentStatus
import com.hackwithsodiq.movemate.ui.theme.DullGray
import com.hackwithsodiq.movemate.ui.theme.DullGrayTransparent
import com.hackwithsodiq.movemate.ui.theme.LoadingBlue
import com.hackwithsodiq.movemate.ui.theme.MovemateTheme
import com.hackwithsodiq.movemate.ui.theme.ProgressGreen
import com.hackwithsodiq.movemate.ui.theme.TempOrange

@Composable
fun ShipmentScreen(navController: NavController) {
    val shipmentFilters = listOf("All", "Completed", "In-Progress", "Pending-Order", "Loading", "Cancelled")
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Scaffold(modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing), topBar = {
        TopAppBar(title = {
            Text(
                stringResource(R.string.shipment_history),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
        },
            navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        Icons.Outlined.ArrowBackIosNew,
                        contentDescription = "back_home",
                        tint = Color.White
                    )

                }
            }, elevation = 0.dp
        )
    }) { paddingValue ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .padding(paddingValue)
        ) {
            ScrollableTabRow(
                edgePadding = 16.dp, selectedTabIndex = selectedTabIndex, divider = {
                    Spacer(modifier = Modifier.height(15.dp))
                },
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                        height = 2.dp,
                        color = TempOrange
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                shipmentFilters.forEachIndexed { index, label ->
                    Tab(selected = selectedTabIndex == index, onClick = {
                        selectedTabIndex = index
                    }) {
                        Row(
                            modifier = Modifier.padding(vertical = 7.dp, horizontal = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val historyCount = Shipment.filterHistoryStatusSizeWithIndex(index)
                            Text(label, fontSize = 15.sp)
                            Spacer(modifier = Modifier.width(5.dp))
                            if (historyCount != 0) {
                                Badge(backgroundColor = if (selectedTabIndex != index) DullGrayTransparent else TempOrange) {
                                    Text(
                                        text = "$historyCount",
                                        fontWeight = FontWeight.SemiBold,
                                        modifier = Modifier.padding(horizontal = 2.dp),
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Box(Modifier.fillMaxSize()) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                ) {
                    item {
                        Text(
                            stringResource(R.string.shipments),
                            style = MaterialTheme.typography.h5.copy(fontSize = 20.sp)
                        )
                    }
                    itemsIndexed(Shipment.filterWithStatus(selectedTabIndex), key = {index, _ -> index}) {_, it->
                        Box(modifier = Modifier.animateItem(
                            fadeInSpec = tween(durationMillis = 250, delayMillis = 100),
                            fadeOutSpec = tween(durationMillis = 100, delayMillis = 100),
                            placementSpec = spring(stiffness = Spring.StiffnessLow, dampingRatio = Spring.DampingRatioMediumBouncy))) {
                            ShipmentCard(it)
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(30.dp))
                    }
                }
                Spacer(
                    Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    MaterialTheme.colors.background
                                )
                            )
                        )
                        .align(Alignment.BottomCenter)
                )
            }
        }
    }
}

@Composable
fun ShipmentCard(shipment: Shipment) {

    val statusIcon = when (shipment.status) {
        ShipmentStatus.InProgress -> Icons.Outlined.Cached
        ShipmentStatus.Pending -> Icons.Outlined.History
        ShipmentStatus.Loading -> Icons.Outlined.HistoryToggleOff
        ShipmentStatus.Completed -> Icons.Outlined.CheckCircle
        ShipmentStatus.Cancelled -> Icons.Outlined.Cancel
    }
    val statusColor = when (shipment.status) {
        ShipmentStatus.InProgress -> ProgressGreen
        ShipmentStatus.Pending -> TempOrange
        ShipmentStatus.Loading -> LoadingBlue
        ShipmentStatus.Completed -> ProgressGreen
        ShipmentStatus.Cancelled -> Color.Red
    }
    Card(
        shape = RoundedCornerShape(corner = CornerSize(12.dp)),
        backgroundColor = Color.White,
        elevation = 1.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(DullGray)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    ) {
                        Icon(
                            statusIcon,
                            contentDescription = "shipping_status",
                            modifier = Modifier.size(12.dp),
                            tint = statusColor,
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(shipment.status.status, color = statusColor, fontSize = 12.sp)
                    }
                }
                Spacer(modifier = Modifier.height(3.dp))
                Text(shipment.arrivingAlert, style = MaterialTheme.typography.h5)
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    shipment.deliveryStatement,
                    style = MaterialTheme.typography.subtitle1
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        shipment.amount,
                        color = MaterialTheme.colors.primary,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(shipment.date, fontSize = 13.sp)
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                painterResource(shipment.sampleImage),
                contentDescription = "package_icon",
                modifier = Modifier.size(50.dp)
            )
        }
    }
}

@Composable
@Preview
fun ShipmentScreen_Preview() {
    MovemateTheme {
        ShipmentScreen(rememberNavController())
    }
}