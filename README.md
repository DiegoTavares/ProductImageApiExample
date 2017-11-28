# Product Image API

This is an API to manage a products list. Each product may contain a parent product and a list of images.

## Requirements:

* Create, update and delete products
* Create, update and delete images
* Get all products excluding relationships (child products, images)
* Get all products including specified relationships (child product and/or images)
* Same as 3 using specific product identity
* Same as 4 using specific product identity
* Get set of child products for specific product
* Get set of images for specific product

## API requests:
* /products (GET) _get products without references_
	* /full _get products with references_
	* /{id} _get product without references_
	* /{id}/full _get product with references_
	* /{product_id}/images _get product images_
	* /{product_id}/children _get children products_
* /products (POST) _create product with a json_
* /products (PUT) _update product with a json_
	* /{id}/parent/{parent_id} _update product parent_
	* /{id}/child/{parent_id} _add product children_
* /products/{id} (DELETE) _delete product_

## How to

The solution was built to be depoloyed in Tomcat 8.5.
```bash
# Build
mvn build

# Execute Tests
mvn test
```
