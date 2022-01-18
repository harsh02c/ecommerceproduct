package com.axis.ecommerceproduct.controller

import com.axis.ecommerceproduct.dao.ISellerDAO
import com.axis.ecommerceproduct.model.Category
import com.axis.ecommerceproduct.model.Product
import com.axis.ecommerceproduct.model.Seller
import com.axis.ecommerceproduct.model.SubCategory
import com.axis.ecommerceproduct.service.AmazonClient
import com.axis.ecommerceproduct.service.ICategoryService
import com.axis.ecommerceproduct.service.IProductService
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*


@CrossOrigin
@RestController
@RequestMapping("/ecommerce-product/product")
class ProductController {
    @Autowired
    private lateinit var iProductService: IProductService
    @Autowired
    private lateinit var iCategoryService: ICategoryService
    @Autowired
    private lateinit var iSellerDAO: ISellerDAO
    @Autowired
    private lateinit var amazonClient: AmazonClient
    @Autowired
    fun BucketController(amazonClient: AmazonClient?) {
        if (amazonClient != null) {
            this.amazonClient = amazonClient
        }
    }

    @PostMapping("/getAllProduct")
    fun getAllProduct(@RequestBody product: Any):ResponseEntity<MutableList<Product?>>
    {
        var productlist = iProductService.getAllProduct(product)
        return ResponseEntity(productlist,HttpStatus.OK)
    }

    @PostMapping("/addProduct")
    fun addProduct(
        @RequestParam("name")  name: String,
        @RequestParam("description")  description: String,
        @RequestParam("category")  category: String,
        @RequestParam("subCategory")  subCategory: String,
        @RequestParam("price")  price: Float,
        @RequestParam("seller")  seller: String,
        @RequestParam("images")  file: MultipartFile): ResponseEntity<Any?> {

        var category = iCategoryService.getCategoryById(category) //findById get Optional<>
        val roomCategory: Category = category.get()               // So convert to Entity Category

        var subcategory = iCategoryService.getSubCategoryById(subCategory) //findById get Optional<>
        val roomSubCategory: SubCategory = subcategory.get()               // So convert to Entity Sub Category

        var user = iSellerDAO.findById(seller) //findById get Optional<>
        val roomSeller: Seller = user.get()    // So convert to Entity Sub Category

        val product = Product()
        product.name = name
        product.description = description
        product.category = roomCategory
        product.subCategory = roomSubCategory
        product.price = price
//        product.images = amazonClient.uploadFile(file!!)
        product.images = "test"
        product.seller = roomSeller

        var addProduct = iProductService.addProduct(product)
        return ResponseEntity(addProduct, HttpStatus.OK)
    }

    @PostMapping("/uploadFile")
    fun uploadFile(@RequestPart(value = "file") file: MultipartFile?): String? {
        return amazonClient.uploadFile(file!!)
    }

    @DeleteMapping("/deleteFile")
    fun deleteFile(@RequestPart(value = "url") fileUrl: String?): String? {
        return amazonClient.deleteFileFromS3Bucket(fileUrl!!)
    }

    @PutMapping("/updateProduct/{id}")
    fun updateProduct(@PathVariable id: String,
        @RequestParam("name")  name: String,
        @RequestParam("description")  description: String,
        @RequestParam("category")  category: String,
        @RequestParam("subCategory")  subCategory: String,
        @RequestParam("price")  price: Float,
        @RequestParam("seller")  seller: String,
        @RequestParam("images")  file: MultipartFile): ResponseEntity<Any?> {

        var category = iCategoryService.getCategoryById(category) //findById get Optional<>
        val roomCategory: Category = category.get()               // So convert to Entity Category

        var subcategory = iCategoryService.getSubCategoryById(subCategory) //findById get Optional<>
        val roomSubCategory: SubCategory = subcategory.get()               // So convert to Entity Sub Category

        var user = iSellerDAO.findById(seller) //findById get Optional<>
        val roomSeller: Seller = user.get()    // So convert to Entity Sub Category

        val product = Product()
        product.name = name
        product.description = description
        product.category = roomCategory
        product.subCategory = roomSubCategory
        product.price = price
        product.images = "images"
        product.seller = roomSeller

        var addProduct = iProductService.updateProduct(id,product)
        return ResponseEntity(addProduct, HttpStatus.OK)
    }

    @GetMapping("/getSellerProduct/{id}")
    fun getSellerProduct(@PathVariable id: ObjectId): ResponseEntity<MutableList<Product?>>
    {
        var productlist = iProductService.getSellerProduct(id)
        return ResponseEntity(productlist,HttpStatus.OK)
    }

    @GetMapping("/getProductById/{id}")
    fun getProductById(@PathVariable id:String):ResponseEntity<Optional<Product?>>{

        var productDetails = iProductService.getProductById(id)
        return ResponseEntity(productDetails,HttpStatus.OK)
    }

    @DeleteMapping("/deleteById/{id}")
    fun deleteProductById(@PathVariable id: String):ResponseEntity<String?>
    {
        var deleteProduct = iProductService.deleteProduct(id)
        return ResponseEntity(deleteProduct,HttpStatus.OK)
    }
}