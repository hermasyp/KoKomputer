package com.catnip.kokomputer.data.datasource.category

import com.catnip.kokomputer.data.model.Category

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface CategoryDataSource {
    fun getCategories(): List<Category>
}