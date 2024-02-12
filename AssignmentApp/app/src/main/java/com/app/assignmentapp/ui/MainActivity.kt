package com.app.assignmentapp.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.assignmentapp.R
import com.app.assignmentapp.data.remote.network.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class MainActivity : ComponentActivity() {

    val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.LightGray,

                    ) {
                    mainView()

                    viewModel.response.observe(LocalLifecycleOwner.current) { response ->
                        when (response) {
                            is NetworkResult.Success -> {
                                response.data?.let {
                                    Toast.makeText(
                                        baseContext,
                                        it.type,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            is NetworkResult.Error -> {
                                //show error message
                                Toast.makeText(
                                    baseContext,
                                    response.message.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            }
        }
    }


    @Preview
    @Composable
    private fun mainView() {
        var movieName by remember { mutableStateOf(TextFieldValue("")) }
        var loading by remember { mutableStateOf(false) }

        Column(modifier = Modifier
            .padding(10.dp)
            .padding(top = 100.dp)) {
            EnterMovieNameView(
                movieName,
                onValueChange = {
                    movieName = it
                    viewModel.movieName = it.text
                },
            )
            if (loading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(20.dp)
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 40.dp),
                )
            } else {
                Submit {
                    movieName = TextFieldValue("")
                    viewModel.submitMovieName()
                }
            }
        }
        viewModel.loading.observe(LocalLifecycleOwner.current) {
            loading = it
        }
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun EnterMovieNameView(userNameText: TextFieldValue, onValueChange: (TextFieldValue) -> Unit) {
        val keyboardController = LocalSoftwareKeyboardController.current
        BasicTextField(
            value = userNameText,
            singleLine = true,
            onValueChange = { onValueChange(it) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            ),
            textStyle = TextStyle(
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            ),
            decorationBox = { innerTextField ->
                Row(
                    Modifier
                        .height(50.dp)
                        .background(Color.White)
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {

                    if (userNameText.text.isEmpty()) {
                        HintText(getString(R.string.movie_name))
                    }
                    innerTextField()
                }
            },
            modifier = Modifier.padding(bottom = 10.dp)

        )
    }

    @Composable
    fun HintText(hintText: String) {
        Text(
            text = hintText,
            style = TextStyle(
                color = Color.Gray,
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
            )
        )
    }

    @Composable
    private fun Submit(onSubmit: () -> Unit) {
        Button(
            onClick = {
                onSubmit()
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Blue,
                contentColor = Color.White
            ),
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(top = 40.dp)
        ) {
            Text(getString(R.string.submit))
        }
    }
}