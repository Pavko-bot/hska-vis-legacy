## These Microservices should be implemented:
CategoryService
ProductService
UserService/AdminService
(UserService / Auth nur von Monolith trennen wenn sinnvoll)
(UserManager nutzt aktuell roleManager um hardcoded nicht-admin user zu registrieren)

(API Gateway (parallelization, calling other services and serving frontend, caching, sessions, basically all this minus new microservices))

for reference:
https://www.abhinavpandey.dev/blog/domain-driven-design
https://stackoverflow.com/questions/71893113/how-to-identify-domain-in-ddd
https://concisesoftware.com/blog/domain-driven-design-discovering-domains/

ddd buch: alle begriffe auf deutsch - evtl egal weil keine domänenexperten dabei
bounded contexts sind idr umfangreicher, aufteilung hier eher erzwungen weil z.b. keine Bestellung, Lieferung
höchstens Nutzung durch Admin vs Kunde


## Data access
DAO (data access object are used currently, might be better to use microservice Manager instances) 
e.g. "new ProductManagerImpl();"
