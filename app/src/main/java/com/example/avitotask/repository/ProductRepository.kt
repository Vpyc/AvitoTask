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

    /**
     * Метод для получения продуктов по странице
     * @param limit Количество запрашиваемых продуктов
     * @param page Номер страницы
     * @param fields Поля продуктов, которые необходимо получить
     * @return Список продуктов
     */
    suspend fun getProductsByPage(
        limit: Int,
        page: Int,
        fields: String
    ): Result<List<ProductList>>

    /**
     * Метод для получения продуктов по странице с сортировкой по цене
     * @param limit Количество запрашиваемых продуктов
     * @param page Номер страницы
     * @param fields Поля продуктов, которые необходимо получить
     * @param sort Сортировка по цене
     * @return Список продуктов
     */
    suspend fun getProductsWithPriceSort(
        limit: Int,
        page: Int,
        fields: String,
        sort: String
    ): Result<List<ProductList>>

    /**
     * Метод для получения продуктов по странице с сортировкой по цене и категории
     * @param limit Количество запрашиваемых продуктов
     * @param page Номер страницы
     * @param fields Поля продуктов, которые необходимо получить
     * @param sort Сортировка по цене
     * @param category Категория продуктов
     * @return Список продуктов
     */
    suspend fun getProductsWithPriceSortAndCategory(
        limit: Int,
        page: Int,
        fields: String,
        sort: String,
        category: String
    ): Result<List<ProductList>>

    /**
     * Метод для получения продуктов по странице с сортировкой по цене и категории
     * @param limit Количество запрашиваемых продуктов
     * @param page Номер страницы
     * @param fields Поля продуктов, которые необходимо получить
     * @param category Категория продуктов
     * @return Список продуктов
     */
    suspend fun getProductsWithCategory(
        limit: Int,
        page: Int,
        fields: String,
        category: String
    ): Result<List<ProductList>>
}