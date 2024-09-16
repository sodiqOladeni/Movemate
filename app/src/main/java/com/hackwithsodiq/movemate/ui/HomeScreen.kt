package com.hackwithsodiq.movemate.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Badge
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Flip
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.CardGiftcard
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hackwithsodiq.movemate.R
import com.hackwithsodiq.movemate.components.PackageTiles
import com.hackwithsodiq.movemate.model.ItemOrder
import com.hackwithsodiq.movemate.model.Vehicle
import com.hackwithsodiq.movemate.ui.theme.MovemateTheme
import com.hackwithsodiq.movemate.ui.theme.ProgressGreen
import com.hackwithsodiq.movemate.ui.theme.TempOrange
import com.hackwithsodiq.movemate.ui.theme.TransparentGreen
import com.hackwithsodiq.movemate.ui.theme.TransparentOrange

@Composable
fun HomeScreen() {
    val focusManager = LocalFocusManager.current
    var isSearchState by remember { mutableStateOf(false) }
    var searchValue by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing),
        topBar = {
            HomeTopBar(
                isSearchState,
                searchValue = searchValue,
                isSearchStateChanged = {
                    isSearchState = it
                },
                onValueChanged = {
                    searchValue = it
                },
                onBackButtonClicked = {
                    isSearchState = false
                    searchValue = ""
                    focusManager.clearFocus()
                })
        }) { paddingValue ->
        AnimatedVisibility(isSearchState, enter = slideInVertically {
            // Slide in from 40 dp from the top.
            with(Density(1f, 1f)) { -40.dp.roundToPx() }
        } + expandVertically(
            // Expand from the top.
            expandFrom = Alignment.Top
        ) + fadeIn(
            // Fade in with the initial alpha of 0.3f.
            initialAlpha = 0.5f
        ),){
            Column(modifier = Modifier.background(MaterialTheme.colors.background)) {
                Spacer(modifier = Modifier.height(20.dp))
                OrderSearchCard(ItemOrder.getFilttedItems(searchValue))
            }
        }
        
        AnimatedVisibility(isSearchState.not()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValue)
                    .verticalScroll(
                        rememberScrollState()
                    )
                    .background(MaterialTheme.colors.background)
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = stringResource(R.string.tracking),
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(15.dp))
                Card(
                    shape = RoundedCornerShape(corner = CornerSize(12.dp)),
                    backgroundColor = Color.White,
                    elevation = 1.dp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    stringResource(R.string.shipment_number),
                                    style = MaterialTheme.typography.subtitle1,
                                )
                                Text("NEJ20089934122231")
                            }
                            Image(
                                Icons.Outlined.LocalShipping,
                                modifier = Modifier.size(50.dp),
                                contentDescription = "LocalShipping"
                            )
                        }
                        Spacer(modifier = Modifier.height(5.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {

                            PackageTiles(
                                modifier = Modifier.weight(1f),
                                label = stringResource(R.string.sender),
                                text = "Atlanta, 5243",
                                tilesColor = TransparentOrange
                            )

                            Spacer(modifier = Modifier.width(50.dp))
                            Column(
                                horizontalAlignment = Alignment.Start,
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    stringResource(R.string.time),
                                    style = MaterialTheme.typography.subtitle1
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Badge(
                                        backgroundColor = ProgressGreen,
                                        modifier = Modifier.size(6.dp)
                                    )
                                    Spacer(modifier = Modifier.width(5.dp))
                                    Text(
                                        "2 day - 3 days",
                                        fontSize = 14.sp,
                                        style = MaterialTheme.typography.h5
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(25.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            PackageTiles(
                                modifier = Modifier.weight(1f),
                                label = stringResource(R.string.receiver),
                                text = "Chicago, 6342",
                                tilesColor = TransparentGreen
                            )

                            Spacer(modifier = Modifier.width(50.dp))
                            Column(
                                horizontalAlignment = Alignment.Start,
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    stringResource(R.string.status),
                                    style = MaterialTheme.typography.subtitle1
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    "Waiting to collect",
                                    fontSize = 14.sp,
                                    style = MaterialTheme.typography.h5
                                )
                            }
                        }

                        Spacer(Modifier.height(25.dp))
                        Divider(
                            thickness = 1.dp,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(Modifier.height(10.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            Icon(
                                Icons.Filled.Add,
                                contentDescription = "add_stop",
                                tint = TempOrange,
                                modifier = Modifier.size(20.dp)
                            )
                            Text(stringResource(R.string.add_stop), color = TempOrange, fontWeight = FontWeight.SemiBold)
                        }
                        Spacer(Modifier.height(10.dp))
                    }
                }
                Spacer(modifier = Modifier.height(35.dp))
                Text(
                    stringResource(R.string.available_vehicles),
                    modifier = Modifier.padding(horizontal = 16.dp),
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(15.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    items(Vehicle.samples) {
                        VehicleCard(it)
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
            }
        }

    }
}

@Composable
fun HomeTopBar(
    isSearchState: Boolean,
    searchValue: String,
    isSearchStateChanged: (Boolean) -> Unit,
    onValueChanged: (String) -> Unit,
    onBackButtonClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)
            .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 5.dp)
    ) {

        AnimatedVisibility(isSearchState.not()) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(Modifier.size(40.dp), shape = CircleShape) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Icon(
                                Icons.Filled.Person,
                                contentDescription = "profile_image",
                                tint = Color.Black,
                                modifier = Modifier
                                    .padding(1.dp)
                                    .fillMaxSize()
                                    .clip(CircleShape),
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painterResource(R.drawable.send),
                                contentDescription = "your_location_icon",
                                tint = Color.White,
                                modifier = Modifier.size(12.dp)
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(text = stringResource(R.string.your_location), color = Color.White)
                        }
                        Spacer(Modifier.height(1.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "Wertheimer, IIIinois", color = Color.White)
                            Icon(
                                Icons.Filled.KeyboardArrowDown,
                                contentDescription = "profile",
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }

                Card(Modifier.size(40.dp), shape = CircleShape) {
                    Icon(
                        Icons.Outlined.Notifications,
                        contentDescription = "notifications",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(12.dp)
                            .padding(7.dp)
                            .clip(CircleShape),
                    )
                }
            }
        }

        Spacer(Modifier.height(20.dp))
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            AnimatedVisibility(isSearchState) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Outlined.ArrowBackIosNew,
                        contentDescription = "back_home",
                        tint = Color.White, modifier = Modifier.clickable {
                            onBackButtonClicked()
                        })
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
            TextField(
                value = searchValue,
                onValueChange = onValueChanged,
                placeholder = {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxHeight()) {
                        Text(
                            stringResource(R.string.enter_the_receipt_number_),
                            style = MaterialTheme.typography.subtitle1.copy(fontSize = 16.sp)
                        )
                    }
                },
                modifier = Modifier
                    .onFocusChanged {
                        isSearchStateChanged(it.isFocused)
                    }
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(shape = CircleShape),
                leadingIcon = {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxHeight()) {
                        Icon(
                            Icons.Outlined.Search,
                            contentDescription = "search",
                            tint = MaterialTheme.colors.primary,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                trailingIcon = {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxHeight()) {
                        Card(
                            Modifier.size(35.dp), shape = CircleShape,
                            backgroundColor = TempOrange
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Icon(
                                    Icons.Filled.Flip,
                                    contentDescription = "scan",
                                    tint = Color.White,
                                    modifier = Modifier
                                        .rotate(90f)
                                        .size(20.dp)
                                        .clip(CircleShape),
                                )
                            }
                        }
                    }
                },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    backgroundColor = Color.White
                )
            )
        }
        Spacer(Modifier.height(15.dp))
    }
}

@Composable
fun VehicleCard(vehicle: Vehicle) {
    Card(
        modifier = Modifier
            .height(220.dp)
            .width(150.dp),
        shape = RoundedCornerShape(corner = CornerSize(12.dp)),
        backgroundColor = Color.White,
        elevation = 1.dp
    ) {
        Column {
            Text(
                vehicle.name,
                modifier = Modifier.padding(top = 10.dp, start = 10.dp, end = 10.dp),
                style = MaterialTheme.typography.h5
            )
            Text(
                vehicle.route,
                modifier = Modifier.padding(horizontal = 10.dp),
                style = MaterialTheme.typography.subtitle1
            )
            Spacer(modifier = Modifier.height(10.dp))
            Image(
                painterResource(vehicle.sampleImage),
                contentDescription = "notifications",
                modifier = Modifier
                    .align(Alignment.End)
                    .size(400.dp)
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp),
            )
        }
    }
}

@Composable
@Preview
fun OrderSearchCard(list: List<ItemOrder> = ItemOrder.samples()) {
    AnimatedVisibility(list.isNotEmpty()) {
        Card(
            shape = RoundedCornerShape(corner = CornerSize(12.dp)),
            backgroundColor = Color.White,
            elevation = 1.dp,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                itemsIndexed(list) { index, item ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .height(36.dp)
                                .width(36.dp)
                                .background(
                                    MaterialTheme.colors.primary,
                                    shape = RoundedCornerShape(
                                        corner = CornerSize(100),
                                    ),
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Outlined.CardGiftcard,
                                contentDescription = "package",
                                tint = Color.White
                            )
                        }

                        Spacer(modifier = Modifier.width(5.dp))
                        Column {
                            Text(
                                item.itemName,
                                style = MaterialTheme.typography.h5
                            )
                            Spacer(modifier = Modifier.height(1.dp))
                            Text(
                                "${item.orderId} · ${item.startDestination} → ${item.ensDestination}",
                                fontSize = 14.sp,
                                style = MaterialTheme.typography.subtitle1
                            )
                        }
                    }
                    if (index < list.lastIndex) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Divider(thickness = .2.dp, modifier = Modifier.fillMaxWidth())
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun HomeScreen_Preview() {
    MovemateTheme {
        HomeScreen()
    }
}

@Composable
@Preview
fun VehicleCard_Preview() {
    VehicleCard(Vehicle("Ocean Freight", "International", R.drawable.ship))
}