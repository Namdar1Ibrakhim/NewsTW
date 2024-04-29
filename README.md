# News Api Documentation

The news site consists of parts: user and moderator

* The moderator can delete, add, change data about users, categories, news and comments on these news.

* The user can view profile, categories by ID or all, news using a filter, like a post, and also leave comments.

The added news is saved with the Waiting status and waits until the moderator changes the status to Active and only after
This news will be displayed in the list visible to users.

Postman Collections: https://restless-star-481705.postman.co/workspace/My-Workspace~fd54e6dd-2f64-4cce-8fb6-4ab377c3c47c/collection/25164846-a8f31521-8764-4e0a-b775-d902f03b61c6?action=share&creator=25164846

Example:

POST  http://24.144.90.170:8080/api/auth/authenticate

Request:
{
"login": "useruser@gmai.com",
"password": "Useruser2024"
}

Response:
{
"accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VydXNlckBnbWFpLmNvbSIsImlhdCI6MTcxNDE2NjI0NSwiZXhwIjoxNzE0MTY3Njg1fQ.HN4-u4nMFmQPZ7_JBWnLRNgVyWYygt_Mh1fCsfmhpW4"
}

-
If you have any additional questions, write to Telegram: @abservd
