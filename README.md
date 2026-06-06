# VEDA AI — Vernacular Engine for Data & Answers

A backend AI platform that lets business users query documents and databases in plain English — built with Java 21, Spring Boot 3, PostgreSQL + pgvector, Groq API, and Ollama.

---

## Architecture Overview

```
User Question (plain English)
        │
        ▼
   Intent Router
   ┌────┴────┐
   ▼         ▼
 RAG       NL2SQL
Pipeline   Pipeline
   │         │
   └────┬────┘
        ▼
    Safety Layer
   (Audit + Confidence)
        │
        ▼
    Response
```

### Hybrid AI Stack

| Component | Role | Why |
|-----------|------|-----|
| **Groq API** (llama-3.3-70b-versatile) | LLM inference | Fast cloud inference, no GPU required |
| **Ollama** (nomic-embed-text) | Embeddings | Free, local, hardware-efficient |
| **PostgreSQL 16 + pgvector** | Vector + app data | Single database for everything |
| **Spring Boot 3** | Backend API | Production-grade Java backend |
| **Flyway** | Schema migrations | Production-style versioning from day one |

---

## Project Structure

```
src/
├── main/
│   ├── java/com/vedaai/
│   │   ├── VedaAiApplication.java
│   │   ├── api/
│   │   │   └── dto/
│   │   ├── config/
│   │   ├── rag/
│   │   │   ├── controller/
│   │   │   ├── service/
│   │   │   ├── model/
│   │   │   └── repository/
│   │   ├── nl2sql/
│   │   │   ├── controller/
│   │   │   ├── service/
│   │   │   ├── validator/
│   │   │   └── model/
│   │   ├── safety/
│   │   │   ├── audit/
│   │   │   ├── feedback/
│   │   │   └── confidence/
│   │   ├── common/
│   │   │   ├── exception/
│   │   │   ├── util/
│   │   │   └── constants/
│   │   ├── domain/
│   │   └── repository/
│   └── resources/
│       ├── application.yml
│       └── db/migration/
│           └── V1__enable_pgvector.sql
```

---

## Prerequisites

| Tool | Version | Purpose |
|------|---------|---------|
| Java | 21 | Runtime |
| Maven | 3.9+ | Build tool |
| Docker | Latest | PostgreSQL container |
| Ollama | Latest | Local embeddings |
| VS Code | Latest | IDE |

---

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/your-username/veda-ai.git
cd veda-ai
```

### 2. Set up environment variables

Create a `.env` file in the project root:

```env
GROQ_API_KEY=your_groq_api_key_here
```

### 3. Start PostgreSQL with pgvector

```bash
docker run -d \
  --name veda-postgres \
  -e POSTGRES_PASSWORD=vedaai \
  -e POSTGRES_DB=veda_ai \
  -p 5432:5432 \
  pgvector/pgvector:pg16
```

Verify pgvector is enabled (Flyway handles this automatically on startup via `V1__enable_pgvector.sql`).

### 4. Start Ollama and pull the embedding model

```bash
ollama pull nomic-embed-text
ollama serve
```

Verify Ollama is running:

```bash
curl http://localhost:11434/api/tags
```

### 5. Verify Groq API key

```bash
curl https://api.groq.com/openai/v1/chat/completions \
  -H "Authorization: Bearer $GROQ_API_KEY" \
  -H "Content-Type: application/json" \
  -d '{
    "model": "llama-3.3-70b-versatile",
    "messages": [{"role": "user", "content": "Say: VEDA AI is online"}]
  }'
```

### 6. Build and run

```bash
export GROQ_API_KEY=your_groq_key_here
mvn clean spring-boot:run
```

---

## API Endpoints

### Health Check

```
GET /api/health
```

Response:

```json
{
  "status": "ok",
  "llm": "groq-connected",
  "embedding": "ollama-connected",
  "database": "connected"
}
```

> More endpoints coming in Week 2 (document ingestion) and Week 3+ (RAG queries, NL2SQL).

---

## Configuration

Key settings in `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/{DB}
    username: {DB_USERNAME}
    password: {DB_PASSWORD}
  ai:
    openai:
      api-key: ${GROQ_API_KEY}
      base-url: https://api.groq.com/openai/v1
    ollama:
      base-url: http://localhost:11434
```

---

## Database Migrations

Managed by Flyway. Migrations live in `src/main/resources/db/migration/`.

| Migration | Description |
|-----------|-------------|
| `V1__enable_pgvector.sql` | Enables the pgvector extension |

---

## Build Status

| Week | Status | Deliverable |
|------|--------|-------------|
| Week 1 | ✅ Complete | Foundation — Spring Boot, PostgreSQL, pgvector, Groq, Ollama |
| Week 2 | 🔄 In Progress | RAG ingestion pipeline — PDF → Chunks → Embeddings → pgvector |
| Week 3 | ⏳ Upcoming | RAG query pipeline — Retrieval + LLM answer generation |
| Week 6–9 | ⏳ Upcoming | NL2SQL pipeline |
| Week 11–12 | ⏳ Upcoming | Safety layer — Audit, feedback, confidence scoring |

---

## Design Principles

- **Backend-first** — No frontend until the backend is solid
- **Modular** — RAG and NL2SQL are independent, replaceable pipelines
- **Safety-focused** — Every query is logged, scored, and auditable
- **Zero cost** — Groq free tier + Ollama local + Docker = £0/month to run
- **Production-style** — Flyway migrations, structured logging, environment variables from day one

---

## License

Private project — not licensed for public use.