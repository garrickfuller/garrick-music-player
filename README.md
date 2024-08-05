
**Music Player Backend**

This project is a backend for a music player application, built with Java Spring Boot, MongoDB, and Amazon S3 (DigitalOcean Spaces). It provides RESTful APIs to manage songs and user data.

Replace the placeholder values in StorageService with your actual AWS access key and secret key.


**API Endpoints**

**Get all songs:**

GET /api/songs

**Get a song by ID:**

GET /api/songs/{id}

**Create a new song:**

POST /api/songs

Content-Type: multipart/form-data

Body: song (JSON), file (MultipartFile)


**Update a song:**

PUT /api/songs/{id}

Content-Type: application/json

Body: song (JSON)


**Delete a song:**

DELETE /api/songs/{id}



