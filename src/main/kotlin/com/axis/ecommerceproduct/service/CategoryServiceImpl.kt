package com.axis.ecommerceproduct.service

import com.axis.ecommerceproduct.dao.ICategoryDAO
import com.axis.ecommerceproduct.dao.ISubCategoryDAO
import com.axis.ecommerceproduct.model.Category
import com.axis.ecommerceproduct.model.SubCategory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CategoryServiceImpl :ICategoryService {
    @Autowired
    private lateinit var iCategoryDAO: ICategoryDAO
    @Autowired
    private lateinit var iSubCategoryDAO: ISubCategoryDAO

    override fun getAllCategory(): MutableList<Category?> {
        return iCategoryDAO.findAll()
    }

    override fun getCategoryById(Id: String): Optional<Category?> {
        return iCategoryDAO.findById(Id)
    }
    override fun getSubCategoryById(Id: String): Optional<SubCategory?> {
        return iSubCategoryDAO.findById(Id)
    }

    override fun getAllSubCategory(Id: String): MutableList<SubCategory?> {
        return iSubCategoryDAO.getAllSubCategory(Id)
    }

}