package com.example.avitotask.utils.impl

import android.content.Context
import androidx.annotation.StringRes
import com.example.avitotask.utils.ResourceProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ResourceProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ResourceProvider {
    override fun getStringById(@StringRes resId: Int): String {
        return context.getString(resId)
    }
}
