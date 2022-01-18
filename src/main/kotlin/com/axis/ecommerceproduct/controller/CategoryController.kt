package com.axis.ecommerceproduct.controller

import com.axis.ecommerceproduct.model.Category
import com.axis.ecommerceproduct.model.SubCategory
import com.axis.ecommerceproduct.service.ICategoryService
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/ecommerce-product/category")
class CategoryController {
    @Autowired
    private lateinit var iCategoryService: ICategoryService

    @GetMapping("/getAllCategory")
    fun getAllCategory(): ResponseEntity<MutableList<Category?>>
    {
        var categorylist = iCategoryService.getAllCategory()
        return ResponseEntity(categorylist, HttpStatus.OK)
    }

    @GetMapping("/getAllSubCategory/{id}")
    fun getAllSubCategory(@PathVariable id: String): ResponseEntity<MutableList<SubCategory?>>
    {
        var subcategorylist = iCategoryService.getAllSubCategory(id)
        return ResponseEntity(subcategorylist, HttpStatus.OK)
    }
}