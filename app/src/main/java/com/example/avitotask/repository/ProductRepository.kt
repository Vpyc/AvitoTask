package com.example.avitotask.repository

import com.example.avitotask.retrofit.Product
import com.example.avitotask.retrofit.ProductList

/**
 * Интерфейс для взаимодействия с API для получения продуктов
 */
interface ProductRepository {
    /**
     * Метод для получения всех продуктов
     * @return Список товаров
     */
    suspend fun getProducts(): Result<List<ProductList>>

    /**
     * Метод для получения продукта по идентификатору
     * @param productId Идентификатор товара
     * @return Товар
     */
    suspend fun getProductById(productId: String): Result<Product>
    suspend fun getProductsByPage(
        limit: Int,
        page: Int,
        fields: String
    ): Result<List<ProductList>>

    suspend fun getProductsWithPriceSort(
        limit: Int,
        page: Int,
        fields: String,
        sort: String
    ): Result<List<ProductList>>

    suspend fun getProductsWithPriceSortAndCategory(
        limit: Int,
        page: Int,
        fields: String,
        sort: String,
        category: String
    ): Result<List<ProductList>>

    suspend fun getProductsWithCategory(
        limit: Int,
        page: Int,
        fields: String,
        category: String
    ): Result<List<ProductList>>
}