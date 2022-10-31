package com.sts.investpuzzle.core.data.network.repository

import com.sts.investpuzzle.core.data.network.model.accessories.AccessoriesResponse
import io.reactivex.Single

interface AccessoriesRepository {
    fun getAccessories() : Single<AccessoriesResponse>
}