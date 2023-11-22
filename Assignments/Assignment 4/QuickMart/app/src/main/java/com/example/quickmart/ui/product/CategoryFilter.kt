package com.example.quickmart.ui.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.quickmart.R
import com.example.quickmart.model.Category

@Composable
fun CategoryFilter(categories: List<Category>, onFilterByCategory: (Category) -> Unit) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    Row {
        IconButton(onClick = { isExpanded = !isExpanded }) {
            Icon(
                painterResource(R.drawable.baseline_tune_24),
                null,
                modifier = Modifier.size(32.dp),
                tint = Color.hsl(144F, 0.70F, 0.45F)
            )
        }
        DropdownMenu(
            expanded = isExpanded, onDismissRequest = { isExpanded = false },
            modifier = Modifier.background(MaterialTheme.colorScheme.surface)
        ) {
            categories.forEach {
                DropdownMenuItem({ Text(it.category) }, {
                    onFilterByCategory(it)
                    isExpanded = false
                })
                if (it != categories[categories.size - 1]) Divider()
            }
        }
    }
}