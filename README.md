** Start the application using:

mvn spring-boot:run

The application starts on: http://localhost:8080



** API Endpoints

GET /api/products: Retrieves all products

POST /api/products: Creates a new product

Request body example:

- name: MacBook Pro

- price: 2500



**Example Demo Flow

- Start the application

- Call GET /api/products to retrieve an empty list

- Create a product using POST /api/products

- Retrieve products again using GET /api/products

- Attempt to create a duplicate product and receive HTTP 409 Conflict
