package com.axis.ecommerceproduct.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "category")
data class Category (
    @Id
    var _id: String?=null,
    var name:String?=null,

)