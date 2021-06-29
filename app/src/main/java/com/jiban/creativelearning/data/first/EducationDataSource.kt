package com.jiban.creativelearning.data.first

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jiban.creativelearning.api.first.EducationApi
import com.jiban.creativelearning.model.first.education.Row


class EducationDataSource(
    private val api: EducationApi
) : PagingSource<Int, Row>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Row> {
        return try {
            val ServiceKey = "76466d68766a6a733830667a784c49"
            val nextPageNumber = params.key ?:0
            val numOfRows = 10

            val response = api.getEducationData(ServiceKey,nextPageNumber+1,numOfRows)
            LoadResult.Page(
                data = response.ListPublicReservationEducation.row,
                prevKey = if (nextPageNumber > 0) nextPageNumber -1 else null,
                nextKey = if (nextPageNumber < response.ListPublicReservationEducation.list_total_count/10) nextPageNumber + 1 else null
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Row>): Int? {
        return state.anchorPosition
    }

}