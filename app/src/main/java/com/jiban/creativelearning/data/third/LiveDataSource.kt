package com.jiban.creativelearning.data.third

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jiban.creativelearning.api.third.LiveApi
import com.jiban.creativelearning.model.third.live.Row


class LiveDataSource(
    private val api: LiveApi
) : PagingSource<Int, Row>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Row> {
        return try {
            val nextPageNumber = params.key ?:0

            val ServiceKey = "56655455636a6a73333449424d4853"
            val numOfRows = 10
            val _type = "json"
            val MobileOS = "ETC"
            val MobileApp = "AppTest"


            val response = api.getLiveData(ServiceKey,nextPageNumber+1,numOfRows)
            LoadResult.Page(
                data = response.walkSaengtaeInfo.row,
                prevKey = if (nextPageNumber > 0) nextPageNumber -1 else null,
                nextKey = if (nextPageNumber < response.walkSaengtaeInfo.list_total_count/10) nextPageNumber + 1 else null
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Row>): Int? {
        TODO("Not yet implemented")
    }

}