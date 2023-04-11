# INGTest
A store ws

1. Short description

	The ING Store is a RESTfull web service having some functionalities of an electronic  store.
It exposes a couple of endpoints which can be used by users to manage products.

The endpoints are:

- /api/getVersion
- /api/public/listAllProducts
- /api/public/findProduct
- /api/user/addToCart
- /api/user/listCart
- /api/admin/addProduct
- /api/admin/deleteProduct
- /api/admin/updateProductPrice

2. Authentication
	
	The application contains an authentication mechanism based on 3 users and 2 roles as follows:
		- ROLE_USER
			user: user1 / password1
			user: user2 / password2
		- ROLE_ADMIN
			user: administrator / admin

3. Endpoints description

	3.1 /api/getVersion
		
		- parameters: no parameter
		- http method: GET
		- description : returns the application version
		- authentication level : public
		- role : no role	

	3.2 /api/public/listAllProducts
	
		- parameters: no parameter
		- http method: GET
		- description : returns the entire list of products as json
		- authentication level : public
		- role : no role	
		
	3.3 /api/public/findProduct
	
		- parameters: 
			- id (Long)
		- http method: GET
		- description : returns a product by its system identifier (the 'id' parameter), as json.
		- authentication level : public
		- role : no role
		
		Ex: GET /api/public/findProduct?id=7
	
	3.4 /api/user/addToCart
		
		- parameters: 
			- id (Long)
		- http method: GET
		- description : add a product in the user cart. The endpoint returns the content of the user cart as json.
		- authentication level : authenticated
		- role : ROLE_USER
	
	3.5 /api/user/listCart
		
		- parameters: 
		- http method: GET
		- description : displays all the products from the user's cart as json.
		- authentication level : authenticated
		- role : ROLE_USER
	
	3.6 /api/admin/addProduct
	
		- parameters: 
			- a product in json format
		- http method: POST
		- description : added a new product in system.
		- authentication level : authenticated
		- role : ROLE_ADMIN		
	
	3.7 /api/admin/deleteProduct
		
		- parameters:
			- id (Long)
		- http method: DELETE
		- description : delete a product given by its system id.
		- authentication level : authenticated
		- role : ROLE_ADMIN
	
	3.8 /api/admin/updateProductPrice
		
		- parameters:
			- id (Long)
			- price (Float)
		- http method: PUT
		- description : updates a product price.
		- authentication level : authenticated
		- role : ROLE_ADMIN
	

4. Notes
	
	4.1 Data model
	
	The application contains a H2 inmemmory database. Ar startup, a list of 10 products are loaded from the products.json file into h2 database. The products.json file can be found in the project classpath in the same folder with application.properties file.
	
	4.2 Tests
	
	The application contains a set of junit tests and also a Postman project which can be used to test the application endpoints.
