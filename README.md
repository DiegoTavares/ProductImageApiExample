# Product Image API

This is an API to manage a products list. Each product may contain a parent product and a list of images.

## API requests:
* /products (GET)
	* /full
	* /{id}
	* /{id}/full
	* /{product_id}/images
	* /{product_id}/children
* /products (POST)
* /products (PUT)
	* /{id}/parent/{parent_id}
	* /{id}/child/{parent_id}
* /products (DELETE)
	* /{id}

## How to

The solution was built to be depoloyed in Tomcat 8.5.
```bash
# Build
mvn build

# Execute Tests
mvn test
```