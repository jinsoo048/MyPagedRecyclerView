package com.jiban.creativelearning.viewmodels.second

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.jiban.creativelearning.api.second.TravelApi
import com.jiban.creativelearning.data.second.TravelDataSource

class TravelViewModel(
    private val api: TravelApi
) : ViewModel() {
    val items = Pager(PagingConfig(pageSize = 10)) {
        TravelDataSource(api)
    }.flow.cachedIn(viewModelScope)
}