package com.persistent.android.sujeet.smartcityhub.presentation.components

import android.content.Context
import android.widget.Toast

/**
 * Created by SUJEET KUMAR on 7/23/2025
 */

fun showToast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}