# OrderMangementSystem
Building a RESTful Order Management System where users can manage customers, products, and orders. The system should follow a multi-layer architecture and use best practices such as Spring Boot, JPA, and exception handling.

1. Customer Management:
   -> Add, update and fetch customers.
   -> A customer should have the following fields:
     ■  id (auto-generated)
     ■ name (String, required)
     ■ email (String, unique, required)
     ■ phone (String, unique, required)

2. Product Management:
  -> Add, update and fetch products.
  ->A product should have the following fields:
     ■ id (auto-generated)
     ■ name (String, required)
     ■ price (BigDecimal, required)
     ■ stock (Integer, required, greater than or equal to 0)

3. Order Management:
  ->Place a new order. An order should:
      ■ Contain customer details.
      ■ Contain one or more products with quantities.
      ■ Validate if sufficient stock is available before placing the order.
      ■ Deduct stock upon successful order placement.
  -> Fetch orders by customer ID.
  -> Calculate the total order amount.
4. Reporting:
  -> Provide an API to fetch:
      ■ Total orders placed by each customer.
      ■ Top 5 customers based on the number of orders.
