# CreditCardsAPI
API for processing credit cards | Publicis Sapient Coding test

Two REST Endpoints are implemented

"Add" will create a new credit card for a given name, card number, and limit

  	Card numbers should be validated using Luhn 10
    New cards start with a £0 balance
    for cards not compatible with Luhn 10, returns an error
    
"GetAll" returns all cards in the system

