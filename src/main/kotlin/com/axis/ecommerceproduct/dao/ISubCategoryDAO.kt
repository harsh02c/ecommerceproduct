package com.axis.ecommerceproduct.dao

import com.axis.ecommerceproduct.model.Category
import com.axis.ecommerceproduct.model.SubCategory
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface ISubCategoryDAO: MongoRepository<SubCategory, String> {
    @Query("{ 'category':  ?0 }")
    fun getAllSubCategory(id: String): MutableList<SubCategory?>
}