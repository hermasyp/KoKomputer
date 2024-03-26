package com.catnip.kokomputer.data.repository

import com.catnip.kokomputer.data.datasource.category.CategoryDataSource
import com.catnip.kokomputer.data.model.Category

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface CategoryRepository {
    fun getCategories(): List<Category>
}

class CategoryRepositoryImpl(private val dataSource: CategoryDataSource) : CategoryRepository {
    override fun getCategories(): List<Category> = dataSource.getCategories()
}