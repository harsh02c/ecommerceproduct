package com.axis.ecommerceproduct.service

import com.axis.ecommerceproduct.model.Category
import com.axis.ecommerceproduct.model.SubCategory
import java.util.*

interface ICategoryService {
    fun getAllCategory(): MutableList<Category?>
    fun getCategoryById(id: String): Optional<Category?>
    fun getAllSubCategory(category:String): MutableList<SubCategory?>
    fun getSubCategoryById(id: String): Optional<SubCategory?>
}