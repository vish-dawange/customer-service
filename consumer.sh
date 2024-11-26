#!/bin/bash

BASE_URL="http://localhost:8080/api/v1/customers"

function list_customers() {
    echo "Fetching all customers..."
    curl -s -X GET "${BASE_URL}" | jq .
}

function get_customer_by_id() {
    echo "Enter Customer ID:"
    read customer_id
    echo "Fetching customer with ID: $customer_id..."
    curl -s -X GET "${BASE_URL}/${customer_id}" | jq .
}

function create_customer() {
    echo "Enter First Name:"
    read first_name
    echo "Enter Last Name:"
    read last_name
    echo "Enter Email:"
    read email
    echo "Enter Phone Number:"
    read phone_number

    echo "Creating customer..."
    curl -s -X POST "${BASE_URL}" \
        -H "Content-Type: application/json" \
        -d '{
            "firstName": "'"$first_name"'",
            "lastName": "'"$last_name"'",
            "email": "'"$email"'",
            "phoneNumber": "'"$phone_number"'"
        }' | jq .
}

function update_customer() {
    echo "Enter Customer ID to Update:"
    read customer_id
    echo "Enter New First Name:"
    read first_name
    echo "Enter New Last Name:"
    read last_name
    echo "Enter New Email:"
    read email
    echo "Enter New Phone Number:"
    read phone_number

    echo "Updating customer with ID: $customer_id..."
    curl -s -X PUT "${BASE_URL}/${customer_id}" \
        -H "Content-Type: application/json" \
        -d '{
            "firstName": "'"$first_name"'",
            "lastName": "'"$last_name"'",
            "email": "'"$email"'",
            "phoneNumber": "'"$phone_number"'"
        }' | jq .
}

function delete_customer() {
    echo "Enter Customer ID to Delete:"
    read customer_id

    echo "Deleting customer with ID: $customer_id..."
    curl -s -X DELETE "${BASE_URL}/${customer_id}" | jq .
}

while true; do
    echo
    echo "Customer API Client"
    echo "1. List All Customers"
    echo "2. Get Customer by ID"
    echo "3. Create Customer"
    echo "4. Update Customer"
    echo "5. Delete Customer"
    echo "6. Exit"
    echo "Enter your choice:"
    read choice

    case $choice in
        1) list_customers ;;
        2) get_customer_by_id ;;
        3) create_customer ;;
        4) update_customer ;;
        5) delete_customer ;;
        6) echo "Exiting..."; break ;;
        *) echo "Invalid choice. Please try again." ;;
    esac
done