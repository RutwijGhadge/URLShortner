# URLShortner
# 🔗 URL Shortener – Core Implementation

This module contains the **core backend implementation** of a URL Shortener service built using **Java & Spring Boot**.  
It focuses on **URL creation, Base62 encoding, persistence, and redirection logic**, following scalable system design principles.

---

## 🧠 Problem Overview

The goal of a URL Shortener is to:
- Convert a **long URL** into a **short, unique identifier**
- Persist the mapping efficiently
- Redirect users with **low latency** and **high availability**

---

## ✂️ URL Shortening Strategies

### 1️⃣ Hash + Collision Resolution (Conceptual)
<img width="802" height="527" alt="Hash + Collision Resolution Technique" src="https://github.com/user-attachments/assets/baf319ba-f247-4a86-a25e-479a74161ae5" />

**Approach**
- Apply a hash function on the long URL
- Detect collisions by checking the database
- Resolve collisions by rehashing with a salt

**Challenges**
- Requires DB lookup for every request
- Collision handling adds latency

**Optimization**
- Bloom Filters can reduce DB calls by probabilistically detecting collisions

---

### 2️⃣ Base-62 Conversion (Implemented)
<img width="784" height="549" alt="Base 62 Conversion Technique" src="https://github.com/user-attachments/assets/d7530cf9-5895-4db3-b8e2-8d17fa0f6c39" />

This implementation uses **Base-62 encoding**.

**Why Base-62?**
- Uses characters: `0–9`, `A–Z`, `a–z`
- URL-safe and compact
- No collisions when ID is unique

---

## 🔄 URL Creation Flow (Implemented)

1. Client sends a **long URL**
2. System checks if the URL already exists in DB
3. If exists → return existing short URL
4. If new:
   - Generate a **unique numeric ID**
   - Convert ID → Base-62 string
   - Persist `{id, shortUrl, longUrl}`

---

## 🔁 URL Redirection Flow
<img width="701" height="279" alt="URL Redirecting" src="https://github.com/user-attachments/assets/b5dc52b6-c38c-46ad-984e-f4f8688975f3" />

1. User accesses short URL
2. Request reaches load balancer
3. Web server checks cache
4. Cache miss → query database
5. Long URL returned → HTTP redirect

---
