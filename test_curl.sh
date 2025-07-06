#!/bin/bash

# Cloud File Storage API - Test Script
# Использование: ./test_curl.sh

BASE_URL="http://localhost:8080"

echo "=== Cloud File Storage API Tests ==="
echo

# 1. Тест регистрации пользователя
echo "1. Тест регистрации пользователя:"
curl -X POST "$BASE_URL/api/v1/auth/signup" \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "password123",
    "email": "testuser@example.com"
  }'
echo -e "\n"

# 2. Тест регистрации с невалидными данными
echo "2. Тест регистрации с невалидными данными:"
curl -X POST "$BASE_URL/api/v1/auth/signup" \
  -H "Content-Type: application/json" \
  -d '{
    "username": "",
    "password": "123",
    "email": "invalid-email"
  }'
echo -e "\n"

# 3. Тест входа пользователя
echo "3. Тест входа пользователя:"
curl -X POST "$BASE_URL/api/v1/auth/signin" \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "password123",
    "email": "testuser@example.com"
  }'
echo -e "\n"

# 4. Тест доступа к защищенному эндпоинту
echo "4. Тест доступа к защищенному эндпоинту (должен вернуть 401):"
curl -X GET "$BASE_URL/api/v1/files" \
  -H "Authorization: Bearer invalid-token"
echo -e "\n"

# 5. Тест доступа к Swagger документации
echo "5. Тест доступа к Swagger документации:"
curl -X GET "$BASE_URL/v3/api-docs"
echo -e "\n"

# 6. Тест доступа к корневому эндпоинту
echo "6. Тест доступа к корневому эндпоинту (должен вернуть 401):"
curl -X GET "$BASE_URL/"
echo -e "\n"

# 7. Тест с минимально допустимым паролем
echo "7. Тест с минимально допустимым паролем:"
curl -X POST "$BASE_URL/api/v1/auth/signup" \
  -H "Content-Type: application/json" \
  -d '{
    "username": "minuser",
    "password": "12345678",
    "email": "minuser@example.com"
  }'
echo -e "\n"

# 8. Тест с коротким паролем
echo "8. Тест с коротким паролем (должен вернуть ошибку):"
curl -X POST "$BASE_URL/api/v1/auth/signup" \
  -H "Content-Type: application/json" \
  -d '{
    "username": "shortpass",
    "password": "123",
    "email": "shortpass@example.com"
  }'
echo -e "\n"

echo "=== Тестирование завершено ===" 