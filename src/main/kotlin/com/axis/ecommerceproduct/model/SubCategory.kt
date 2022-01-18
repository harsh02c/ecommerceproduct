package com.axis.ecommerceproduct.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "subcategory")
data class SubCategory (
    @Id
    var _id: String?=null,
    var category: String?=null,
    var subname:String?=null,
)