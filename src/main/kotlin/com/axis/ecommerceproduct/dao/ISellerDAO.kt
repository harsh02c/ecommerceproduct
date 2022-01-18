package com.axis.ecommerceproduct.dao

import com.axis.ecommerceproduct.model.Seller
import org.springframework.data.mongodb.repository.MongoRepository

interface ISellerDAO: MongoRepository<Seller, String> {

}