package com.axis.ecommerceproduct.dao

import com.axis.ecommerceproduct.model.Category
import com.axis.ecommerceproduct.model.Product
import org.springframework.data.mongodb.repository.MongoRepository

interface ICategoryDAO: MongoRepository<Category, String> {

}