# FastSpring-Order-API
Order Processing Service using Java that allows a client to place Orders for the products via a REST API

Build From Command Line:
mvn package

Run From Command Line:
java -jar target/orderProcessingService-0.0.1-SNAPSHOT.jar

API commands:
Place an order: curl -v localhost:8080/orders/place/product id/quantity
Retrieve an Order given an Order ID: curl -v localhost:8080/orders/order id
Retrieve status of a given Order: curl -v localhost:8080/orders/status/order id
Retrieve successful Orders: curl -v localhost:8080/orders/successful
Retrieve unsuccessful Orders: curl -v localhost:8080/orders/unsuccessful
List all placed orders: curl -v localhost:8080/orders
Add new product to inventory: curl -v localhost:8080/products -H 'Content-type:application/json' -d '{"name":name, "availableQuantity":quantity, "price":price}'
Check inventory: curl -v localhost:8080/products
Retrieve a product from inventory given product id: curl -v localhost:8080/products/product id


I chose to have all commands for the api be based on a products id rather than name, because I assumed the id is something always accessable to the user also it by my design it is unique. I also worked under the assumption an order's status is either successful or not successful, and that there was no other intermediary value for this attribute.
Note: I initialized my data base at runtime with some orders and products in the inventory. This is done for general convenience when testing.

Data models:
I created two main data structures for this project being a product and a productOrder. I also created interfaces that represented repositories of these objects.
As suggested in the projects description I used H2 in memory database, Spring Boot, and Maven for project management.
