package com.axis.ecommerceproduct.dao

import com.axis.ecommerceproduct.model.Product
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface IProductDAO: MongoRepository<Product,String>{
    @Query("{ 'seller':  ?0 }")
    fun getSellerProduct(id: ObjectId): MutableList<Product?>

    @Query("{ 'name':  {\$regex: ?0, \$options: 'i' }, 'category': {\$regex: ?1 },'subCategory': {\$regex: ?2 }}")
    fun getAllProduct(name: String?, category: String?, subcategory: String?): MutableList<Product?>
}