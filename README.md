
Music Player Backend

This project is a backend for a music player application, built with Java Spring Boot, MongoDB, and Amazon S3 (DigitalOcean Spaces). It provides RESTful APIs to manage songs and user data.

Replace the placeholder values in StorageService with your actual AWS access key and secret key.


API Endpoints
Songs
Get all songs:

http
Copy code
GET /api/songs
Get a song by ID:

http
Copy code
GET /api/songs/{id}
Create a new song:

http
Copy code
POST /api/songs
Content-Type: multipart/form-data
Body: song (JSON), file (MultipartFile)
Update a song:

http
Copy code
PUT /api/songs/{id}
Content-Type: application/json
Body: song (JSON)
Delete a song:

http
Copy code
DELETE /api/songs/{id}
