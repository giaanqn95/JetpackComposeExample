package com.example.testandroidcompose.second

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Surface
import androidx.compose.ui.platform.setContent
import com.example.testandroidcompose.ui.StateCodelabTheme

class SecondActivity : AppCompatActivity() {

    private val todoViewModel by viewModels<TodoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StateCodelabTheme(false) {
                Surface {
                    TodoScreen(
                        todoViewModel.todoItems,
                        todoViewModel.currentEditItem,
                        todoViewModel::addItem,
                        todoViewModel::removeItem,
                        todoViewModel::onEditItemSelected,
                        todoViewModel::onEditItemChange,
                        todoViewModel::onEditDone
                    )
                }
            }
        }
    }
}