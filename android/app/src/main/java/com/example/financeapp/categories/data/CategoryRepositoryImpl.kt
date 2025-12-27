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

    // TODO: we need to make sure that the viewmodel isn't responsible for generating ids
    // But that the update uses the correct id
    override suspend fun addCategory(category: Category) {
        dao.insert(category.toEntity())
    }

    override suspend fun updateCategory(category: Category) {
        dao.update(category.toEntity())
    }
}

fun CategoryEntity.toDomain(): Category {
    return Category(
        id = this.id,
        label = this.label
    )
}

fun Category.toEntity(): CategoryEntity {
    return CategoryEntity(
        id = this.id,
        label = this.label
    )
}