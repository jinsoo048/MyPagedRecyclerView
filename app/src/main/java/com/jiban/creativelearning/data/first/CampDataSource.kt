package com.jiban.creativelearning.data.first

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jiban.creativelearning.api.first.MyApi
import com.jiban.creativelearning.model.second.camp.Item


class CampDataSource(
    private val api: MyApi
) : PagingSource<Int, Item>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        return try {
            val nextPageNumber = params.key ?: 0

            val ServiceKey =
                "oxkeiOH8uK8oW7g0PbEl5/1XxEPIdlvQtZnFkmMONh8qI3VCVBLjtyn0Q9LCS5ICwiC0oE9SmI7pEaH/2IzfDg=="
            val numOfRows = 10
            val _type = "json"
            val MobileOS = "ETC"
            val MobileApp = "AppTest"


            val response = api.getCampData(
                ServiceKey,
                nextPageNumber + 1,
                numOfRows,
                MobileOS,
                MobileApp,
                _type
            )
            LoadResult.Page(
                data = response.response.body.items.item,
                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < response.response.body.totalCount / 10) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition
    }

}