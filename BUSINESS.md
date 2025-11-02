# MM Car Rental 

Details on the architecture and implementation for the car rental system of the book. 

## Modules and Applications

Each module contains the implementation of a service or library. 
There's the `sandbox` module which contains dummy, PoC or experimental code. 

The Reservation Service provides cars available for rent at specific dates. 
It also controls the reservations themselves. 

## Port mapping 

`localhost`
- Users -> 8080 
- Reservation -> 8081  
- Rental -> 8082 
- Inventory -> 8083 
- Billing -> 8084 

