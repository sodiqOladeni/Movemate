package com.hackwithsodiq.movemate.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Archive
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Scale
import androidx.compose.material.icons.outlined.Unarchive
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
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
import com.hackwithsodiq.movemate.components.CustomChip
import com.hackwithsodiq.movemate.model.ItemCategory
import com.hackwithsodiq.movemate.navigation.CALCULATE_ESTIMATED_ROUTE
import com.hackwithsodiq.movemate.ui.theme.CoolBlack
import com.hackwithsodiq.movemate.ui.theme.CoolGray
import com.hackwithsodiq.movemate.ui.theme.DullGray
import com.hackwithsodiq.movemate.ui.theme.MovemateTheme
import com.hackwithsodiq.movemate.ui.theme.TempOrange

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CalculateScreen(navController: NavController) {

    var selectedCategoryIndex by remember { mutableIntStateOf(-1) }
    var senderLocation by remember { mutableStateOf("") }
    var receiverLocation by remember { mutableStateOf("") }
    var approxWeight by remember { mutableStateOf("") }


    Scaffold(modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing), topBar = {
            TopAppBar(title = {
                Text(
                    stringResource(R.string.calculate),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )
            },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Outlined.ArrowBackIosNew, contentDescription = "back_home", tint = Color.White)

                    }
                })
        }) { paddingValue ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(stringResource(R.string.destination), style = MaterialTheme.typography.h5)
                Spacer(modifier = Modifier.height(10.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(corner = CornerSize(12.dp)),
                    backgroundColor = Color.White,
                    elevation = 1.dp
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            value = senderLocation,
                            placeholder = {
                                Text(stringResource(R.string.sender_location), fontSize = 15.sp)
                            },
                            onValueChange = {
                                senderLocation = it
                            },
                            shape = RoundedCornerShape(12.dp),
                            leadingIcon = {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        Icons.Outlined.Unarchive,
                                        contentDescription = "sender_location"
                                    )
                                    Spacer(modifier = Modifier.width(5.dp))
                                    Divider(
                                        color = CoolGray,
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .padding(vertical = 13.dp)
                                            .width(.5.dp)
                                    )
                                }
                            },
                            maxLines = 1,
                            singleLine = true,
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = DullGray,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                            )
                        )

                        Spacer(modifier = Modifier.height(10.dp))
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            value = receiverLocation,
                            placeholder = {
                                Text(stringResource(R.string.receiver_location), fontSize = 15.sp)
                            },
                            onValueChange = {
                                receiverLocation = it
                            },
                            shape = RoundedCornerShape(12.dp),
                            leadingIcon = {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        Icons.Outlined.Archive,
                                        contentDescription = "receiver_location"
                                    )
                                    Spacer(modifier = Modifier.width(5.dp))
                                    Divider(
                                        color = CoolGray,
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .padding(vertical = 13.dp)
                                            .width(.5.dp)
                                    )
                                }
                            },
                            maxLines = 1,
                            singleLine = true,
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = DullGray,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                            )
                        )

                        Spacer(modifier = Modifier.height(10.dp))
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            value = approxWeight,
                            placeholder = {
                                Text(stringResource(R.string.approx_weight), fontSize = 15.sp)
                            },
                            onValueChange = {
                                approxWeight = it
                                            },
                            shape = RoundedCornerShape(12.dp),
                            leadingIcon = {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Outlined.Scale, contentDescription = "approx_weight")
                                    Spacer(modifier = Modifier.width(5.dp))
                                    Divider(
                                        color = CoolGray,
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .padding(vertical = 13.dp)
                                            .width(.5.dp)
                                    )
                                }
                            },
                            maxLines = 1,
                            singleLine = true,
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = DullGray,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(25.dp))
                Text(stringResource(R.string.packaging), style = MaterialTheme.typography.h5)
                Spacer(modifier = Modifier.height(1.dp))
                Text(
                    stringResource(R.string.what_are_you_sending_),
                    style = MaterialTheme.typography.subtitle1.copy(fontSize = 16.sp)
                )
                Spacer(modifier = Modifier.height(10.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(corner = CornerSize(12.dp)),
                    backgroundColor = Color.White,
                    elevation = .4.dp
                ) {
                    Column(modifier = Modifier.padding(5.dp)) {
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            value = stringResource(R.string.box),
                            onValueChange = { },
                            shape = RoundedCornerShape(12.dp),
                            leadingIcon = {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Image(
                                        painterResource(R.drawable.package2),
                                        contentDescription = "packaging_box"
                                    )
                                    Spacer(modifier = Modifier.width(5.dp))
                                    Divider(
                                        color = CoolGray,
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .padding(vertical = 13.dp)
                                            .width(.5.dp)
                                    )
                                }
                            },
                            trailingIcon = {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        Icons.Outlined.KeyboardArrowDown,
                                        contentDescription = "select_packaging_box"
                                    )
                                }
                            },
                            maxLines = 1,
                            singleLine = true,
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.White,
                                focusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent,
                                disabledTextColor = CoolBlack,
                                unfocusedIndicatorColor = Color.Transparent,
                            ),
                            readOnly = true,
                            enabled = false
                        )
                    }
                }

                Spacer(modifier = Modifier.height(25.dp))
                Text(stringResource(R.string.categories), style = MaterialTheme.typography.h5)
                Spacer(modifier = Modifier.height(1.dp))
                Text(
                    stringResource(R.string.what_are_you_sending_),
                    style = MaterialTheme.typography.subtitle1.copy(fontSize = 16.sp)
                )
                Spacer(modifier = Modifier.height(20.dp))

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    ItemCategory.categoryItems.forEachIndexed { index, category ->
                        CustomChip(category, selectedCategoryIndex == index) {
                            selectedCategoryIndex = index
                        }
                    }
                }
            }
            Button(
                onClick = {
                    navController.navigate(CALCULATE_ESTIMATED_ROUTE)
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
                Text(stringResource(R.string.calculate))
            }
        }
    }
}

@Composable
@Preview
fun CalculateScreen_Preview() {
    MovemateTheme {
        CalculateScreen(rememberNavController())
    }
}