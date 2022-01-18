package com.axis.ecommerceproduct.service

import com.axis.ecommerceproduct.model.Product
import org.bson.types.ObjectId
import java.util.*

interface IProductService {
    fun addProduct(product: Product):Any?
    fun updateProduct(id: String, product:Product):Any?
    fun getSellerProduct(id: ObjectId): MutableList<Product?>
    fun getProductById(id: String): Optional<Product?>
    fun deleteProduct(id: String):String
    fun getAllProduct(product:Any): MutableList<Product?>
}