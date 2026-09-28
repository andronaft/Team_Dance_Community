# Team Dance Community — REST API

[![build](https://github.com/andronaft/Team_Dance_Community/actions/workflows/build.yml/badge.svg)](https://github.com/andronaft/Team_Dance_Community/actions/workflows/build.yml)
![Java 17](https://img.shields.io/badge/Java-17-blue)
![Spring Boot 2.7](https://img.shields.io/badge/Spring%20Boot-2.7-brightgreen)

Back-end for a dance school's web and mobile apps. Visitors browse branches, halls, the group-training
schedule, trainers and news. Registered users manage their profile, trainers get their own role, and
admins run the whole catalogue and activate new accounts.

## Tech stack

- **Java 17, Spring Boot 2.7**: Web, Data JPA, Security, Validation
- **JWT** authentication (jjwt, HS256), stateless sessions, BCrypt password hashing
- **PostgreSQL** (H2 in tests), Lombok
- **JUnit 5, MockMvc, AssertJ**; GitHub Actions CI

## Architecture

```
HTTP ─► JwtTokenFilter ─► Controller (rest/) ─► Service (service/) ─► Repository (repository/) ─► PostgreSQL
              │                  │
     validates the token,    DTOs (dto/) keep entities
     sets SecurityContext    and passwords out of responses
```

| Package | Contents |
|---|---|
| `rest/` | REST controllers: public, user, trainer and admin (`/api/v1/admin/**`) APIs, plus a global error handler |
| `service/` | Business logic behind interfaces (`service/impl/`) |
| `repository/` | Spring Data JPA repositories |
| `model/` | JPA entities: `User`, `Role`, `UserProfile`, `Branch`, `Hall`, `GroupTraining`, `Training`, `News`, `Award`, … |
| `security/` | JWT creation and validation, the request filter, `UserDetailsService` |
| `config/` | Security rules, CORS, password encoder, startup data (roles and first admin) |

## API overview

| Access | Endpoints |
|---|---|
| Public | `POST /api/v1/auth/register`, `POST /api/v1/auth/login`, `GET /api/v1/branch/`, `/api/v1/hallPublic/`, `/api/v1/training/`, `/api/v1/training/groupTraining/`, `/api/v1/news/`, `/api/v1/userPublic/…`, `POST /api/v1/feedback/apply` |
| User (`ROLE_USER`) | `/api/v1/users/getAllUserInfo`, `/api/v1/users/updatePassword`, `/api/v1/users/profile/…`, `POST /api/v1/users/upload/upload/` (profile photo) |
| Admin (`ROLE_ADMIN`) | `/api/v1/admin/**`: users, trainers, branches, halls, trainings, news, applications; `/api/deep/getall` |

Authenticated requests send `Authorization: Bearer_<token>` (the format the front-end uses); the standard
`Bearer <token>` works too.

**Account flow:** register → the account is `NOT_ACTIVE` → an admin calls
`GET /api/v1/admin/users/activateUser/?id=…` → the user can log in and gets a JWT.

## Running locally

Requires JDK 17 and Docker.

```bash
cp .env.example .env                  # fill in DB_PASSWORD and JWT_SECRET (32+ chars)
docker compose up -d postgres
set -a && . ./.env && set +a
ADMIN_USERNAME=admin ADMIN_PASSWORD=change-me ./mvnw spring-boot:run
```

On startup the app creates the `tdcbd` schema, the `ROLE_USER` / `ROLE_TRAINER` / `ROLE_ADMIN` roles, and,
when `ADMIN_USERNAME` and `ADMIN_PASSWORD` are set, a first active admin.

### Configuration

| Variable | Default | Purpose |
|---|---|---|
| `DB_URL` | `jdbc:postgresql://localhost:5432/tdc` | JDBC URL |
| `DB_USERNAME` / `DB_PASSWORD` | `tdc` / — | Database credentials |
| `JWT_SECRET` | — (required, 32+ bytes) | HS256 signing key; the app refuses to start with a weak one |
| `JWT_EXPIRATION_MS` | `36000000` (10 h) | Token lifetime |
| `CORS_ALLOWED_ORIGINS` | `http://localhost:3000` | Comma-separated front-end origins |
| `UPLOAD_DIR` | `uploads` | Where profile photos are stored |
| `ADMIN_USERNAME` / `ADMIN_PASSWORD` / `ADMIN_EMAIL` | — | Optional first admin account |

## Tests

```bash
./mvnw verify
```

Integration tests run the whole application against in-memory H2 through MockMvc and cover: registration
and activation, login, role-based access, anonymous vs admin deep-link access, profile updates, upload
safety, token validation, and CORS. Unit tests cover JWT creation, expiry, and forged-signature rejection.

## Security fixes (2026 revision)

I wrote this project in 2020 and came back to it to review and modernize it. The review found:

- **Database credentials and the JWT secret were committed** in `application.properties`, and the JWT
  secret was a three-letter word, so anyone could forge an admin token. Both are now environment
  variables, and the app refuses to start with a secret shorter than 32 bytes. *The old credentials remain
  in git history and must be treated as leaked and rotated.*
- **Path traversal in photo upload**: the client's file name was used as is, and the folder was hard-coded
  to a Windows desktop path. The server now generates the name, accepts only JPEG, PNG and WebP, and
  limits the size.
- **Users could set their own rating and level** through `updateProfile`. Now only user-owned fields
  are taken from the request.
- **Plain-text passwords in logs**: the registration DTO, including the password, was printed to stdout.
- **CORS** allowed any origin together with credentials. Origins are now a whitelist from configuration.
- **Anyone could read all deep-link records**; reading them is now admin-only.
- **Registration always answered with an error**: it tried to log the new, still inactive, user in.
- `Hall.groupTraining` was mapped by the wrong field (`branch` instead of `hall`), `@Data` on entities with
  two-way relations broke `equals` (duplicate roles were possible) and could loop forever in `toString`,
  and several services used `getOne()` proxies that failed outside a web request.
- Exceptions now become JSON errors with the right status (400/401/403/404) instead of a 500 with a stack trace.

## Roadmap

- [ ] Liquibase baseline for the current schema (it is managed by Hibernate `ddl-auto` for now)
- [ ] Bean Validation on DTOs, typed responses instead of `Map` / raw `ResponseEntity`
- [ ] Pagination for list endpoints
- [ ] OpenAPI / Swagger UI
- [ ] Upgrade to Spring Boot 3 (`jakarta.*`, Spring Security 6)
