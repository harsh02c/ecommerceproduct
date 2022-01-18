package com.axis.ecommerceproduct.service

import com.axis.ecommerceproduct.dao.IProductDAO
import com.axis.ecommerceproduct.model.Product
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductServiceImpl:IProductService {
    @Autowired
    private lateinit var iProductDAO : IProductDAO
    override fun addProduct(product: Product): Any? {
        var etemp = iProductDAO.save(product)
        return "Product Added"
    }

    override fun updateProduct(id: String, product: Product): Any? {
        return if(iProductDAO.existsById(id)){
            val data =  iProductDAO.findById(id).get()

            val updatedCompany = iProductDAO.save(
                data.apply {
                    name = product.name
                    price = product.price
                    description = product.description
                    images = product.images
                }
            )
            iProductDAO.save(updatedCompany)
        } else
        {
            throw Exception("parking id not found")
        }
    }

    override fun getSellerProduct(id: ObjectId): MutableList<Product?> {
        return iProductDAO.getSellerProduct(id)
    }

    override fun getProductById(Id: String): Optional<Product?> {
        return iProductDAO.findById(Id)
    }

    override fun deleteProduct(id: String): String {
        return if(iProductDAO.existsById(id))
        {
            iProductDAO.deleteById(id)
            "Parking id deleted"
        }
        else {
            "Parking with id $id not found"
        }
    }

    override fun getAllProduct(product: Any): MutableList<Product?> {
        return iProductDAO.findAll()

//        return iProductDAO.getAllProduct(product.name, product.category?._id,product.subCategory?._id)
    }
}