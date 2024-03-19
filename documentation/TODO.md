## These Microservices should be implemented: 
https://www.abhinavpandey.dev/blog/domain-driven-design
https://stackoverflow.com/questions/71893113/how-to-identify-domain-in-ddd
https://concisesoftware.com/blog/domain-driven-design-discovering-domains/

Data Manager (if 1 database should be 1, otherwise 3):
    Category Data Manager
    Product Data Manager
    User Data Manager
Authentication Service
API Gateway (parallelization, calling other services and serving frontend, caching, sessions)


Contexts in theory: registration, order, delivery, bill (not applicable?)