package com.dmm.bootcamp.yatter2023.ui.Regist

import com.dmm.bootcamp.yatter2023.ui.login.LoginViewModel

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dmm.bootcamp.yatter2023.ui.login.LoginTemplate
import com.dmm.bootcamp.yatter2023.ui.theme.Yatter2023Theme
import com.dmm.bootcamp.yatter2023.ui.timeline.PublicTimelineActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistActivity:ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            Yatter2023Theme() {
                Surface() {
                }
            }
        }
    }
}

@Composable
fun Register(){

    var id:String = ""
    var pass:String = ""

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "新規登録") })
        }
    ) {
        Box(modifier = Modifier.padding(it)
            .padding(8.dp),) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    text = "ユーザー名"
                )
                OutlinedTextField(
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    value = id,
                    onValueChange = {},
                    placeholder = {
                        Text(text = "username")
                    },
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "パスワード"
                )
                OutlinedTextField(
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    value = pass,
                    onValueChange = { },
                    placeholder = {
                        Text(text = "password")
                    },
                )

                Button(
                    onClick = {  },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "登録")
                }

            }
        }
    }
}

@Preview
@Composable
fun RegistTemplatePreview() {
    Yatter2023Theme {
        Surface {
            Register()
        }
    }
}