package com.example.financeapp.categories.data

import com.example.financeapp.categories.domain.Category
import com.example.financeapp.categories.domain.CategoryRepository
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val dao: CategoryDao
) : CategoryRepository {
    override fun observeCategories(): Flow<List<Category>> =
        dao.observeCategories()
            .map { entities ->
                entities.map {
                    it.toDomain()
            }
        }
}

fun CategoryEntity.toDomain(): Category {
    return Category(
        id = this.id,
        label = this.label
    )
}