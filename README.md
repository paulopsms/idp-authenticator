<h1 align="center" style="font-weight: bold;">IDP-Auth 💻</h1>

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)


<p align="center">
 <a href="#started">Getting Started</a> • 
  <a href="#routes">API Endpoints</a> 
</p>

<p align="center">
  <b>This service must allow users to register, login and authenticate in external services, redirecting them once authenticated.</b>
</p>

<h2 id="started">🚀 Getting started</h2>

<h3>Prerequisites</h3>

For this project to run, you'll need to have Docker and docker-compose installed.

- [Docker](https://docs.docker.com/engine/install)
- [Docker Compose](https://docs.docker.com/compose/install)

<h3>Cloning</h3>

How to clone your project

```bash
git clone https://github.com/paulopsms/qrcode-generator.git
```

<h3> Environment Variables</h2>

For the AWS S3Client work properly, you need to create your environment file `.env` your AWS credentials on it.

```yaml
POSTGRES_DB={DATABASE_NAME}
POSTGRES_USER={DATABASE_USER}
POSTGRES_PASSWORD={DATABASE_PASSWORD}
POSTGRES_PORT=5432

PGADMIN_EMAIL={PGADMIN_ADMIN_EMAIL}
PGADMIN_PASSWORD={PGADMIN_ADMIN_PASSWORD}

EMAIL_USERNAME={COMPANY_EMAIL_ADDRESS}
EMAIL_PASSWORD={COMPANY_EMAIL_PASSWORD}
```

<h3>Starting</h3>

How to start your project.

Inside of your project folder, execute in your terminal:

```bash
docker compose up -d --build
``````

<h2 id="routes">📍 API Endpoints</h2>

Here you can list the main routes of your API, and what are their expected request bodies.
​
| route               | description                                          
|----------------------|-----------------------------------------------------
| <kbd>POST /users</kbd>     | Create a new user. See [request details](#post-create-user)
| <kbd>GET /users/forgot-password</kbd>     | Request password recovery. See [request details](#get-forgot-password)
| <kbd>POST /users/password-recovery</kbd>     | Update password to recover account. See [request details](#post-qrcode-detail)
| <kbd>POST /users/verify-account</kbd>     | Validate registered email on account creation. See [request details](#post-qrcode-detail)
| <kbd>POST /login</kbd>     | Authenticates user and generate a new Token. See [request details](#post-qrcode-detail)
| <kbd>POST /refresh-token</kbd>     | Refresh token duration. See [request details](#post-qrcode-detail)
| <kbd>POST /get-logged-user</kbd>     | Get info about current logged in user. See [request details](#post-qrcode-detail)

<h3 id="post-create-user">POST /users</h3>

**REQUEST**
```json
{
    "name": "John Doe",
    "email": "johndoe@email.com",
    "password": "123"
}
```

**RESPONSE**
```json
{
    "id": "ecd08c09-5a97-4325-bd0f-0de026c941a6",
    "name": "John Doe",
    "email": "johndoe@email.com",
    "roles": [
        {
            "id": 1,
            "role": "FRESH_USER"
        }
    ]
}

* And an email will be sent to the email address of the request.
```

<h3 id="get-forgot-password">GET /users/forgot-password</h3>

**REQUEST**
```json

```

**RESPONSE**
```json


```

<h3 id="get-forgot-password">POST /users/verify-account</h3>

**REQUEST**
```json
{
    "name": "John Doe",
    "email": "johndoe@email.com",
    "password": "123"
}
```

**RESPONSE**
```json
{
    "id": "ecd08c09-5a97-4325-bd0f-0de026c941a6",
    "name": "John Doe",
    "email": "johndoe@email.com",
    "roles": [
        {
            "id": 1,
            "role": "FRESH_USER"
        }
    ]
}

* And an email will be sent to the email address of the request.
```

http://localhost:8091/users/verify-account?token=0e4893f7-b43d-40d0-b707-6596cbd02c3e

