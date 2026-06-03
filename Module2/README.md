# Module 2: ANSI SQL Using MySQL Exercises

This directory contains solutions and schemas for all 25 ANSI SQL exercises designed for MySQL, structured around the theme **"Local Community Event Portal"**.

## Files
- [schema.sql](file:///d:/New%20folder/Module2/schema.sql): Table definitions (`CREATE TABLE`), keys, foreign key constraints, check ranges, and sample population INSERT commands.
- [queries.sql](file:///d:/New%20folder/Module2/queries.sql): Completed, commented SQL queries for all 25 exercises.

---

## Schema Overview & Entities

```text
  Users (user_id, full_name, email, city, registration_date)
    │
    ├───< Registrations (registration_id, user_id, event_id, registration_date)
    │
    ├───< Feedback (feedback_id, user_id, event_id, rating, comments, feedback_date)
    │
    └───< Events (event_id, title, description, city, start_date, end_date, status, organizer_id)
           │
           ├───< Sessions (session_id, event_id, title, speaker_name, start_time, end_time)
           │
           └───< Resources (resource_id, event_id, resource_type, resource_url, uploaded_at)
```

---

## Exercise Queries & Mapped Results

Below is the compilation of the 25 exercises with their SQL statements and outputs based on the sample dataset:

### 1. User Upcoming Events
*Objective:* Show a list of all upcoming events a user is registered for in their city, sorted by date.
```sql
SELECT u.user_id, u.full_name, e.title AS event_title, e.city, e.start_date
FROM Registrations r
JOIN Users u ON r.user_id = u.user_id
JOIN Events e ON r.event_id = e.event_id
WHERE e.status = 'upcoming' 
  AND e.city = u.city
ORDER BY e.start_date ASC;
```
*Expected Output:*
| user_id | full_name | event_title | city | start_date |
| :--- | :--- | :--- | :--- | :--- |
| 1 | Alice Johnson | Tech Innovators Meetup | New York | 2025-06-10 10:00:00 |
| 5 | Ethan Hunt | Frontend Development Bootcamp | Los Angeles | 2025-07-01 10:00:00 |

---

### 2. Top Rated Events
*Objective:* Identify events with the highest average rating, considering only those with at least 10 feedback submissions.
```sql
SELECT e.event_id, e.title AS event_title, AVG(f.rating) AS avg_rating, COUNT(f.feedback_id) AS feedback_count
FROM Events e
JOIN Feedback f ON e.event_id = f.event_id
GROUP BY e.event_id, e.title
HAVING COUNT(f.feedback_id) >= 10
ORDER BY avg_rating DESC;
```
*Expected Output:* Empty set (No events in the sample dataset have $\ge 10$ feedback entries).

---

### 3. Inactive Users
*Objective:* Retrieve users who have not registered for any events in the last 90 days.
```sql
SELECT user_id, full_name, email, registration_date
FROM Users
WHERE user_id NOT IN (
    SELECT DISTINCT user_id
    FROM Registrations
    WHERE registration_date >= DATE_SUB('2025-07-15', INTERVAL 90 DAY)
);
```
*Expected Output:*
| user_id | full_name | email | registration_date |
| :--- | :--- | :--- | :--- |
| 3 | Charlie Lee | charlie@example.com | 2024-12-10 |
| 4 | Diana King | diana@example.com | 2025-01-15 |

---

### 4. Peak Session Hours
*Objective:* Count how many sessions are scheduled between 10 AM to 12 PM for each event.
```sql
SELECT event_id, COUNT(session_id) AS sessions_10am_to_12pm_count
FROM Sessions
WHERE TIME(start_time) >= '10:00:00' 
  AND TIME(end_time) <= '12:00:00'
GROUP BY event_id;
```
*Expected Output:*
| event_id | sessions_10am_to_12pm_count |
| :--- | :--- |
| 1 | 1 |
| 3 | 1 |

---

### 5. Most Active Cities
*Objective:* List the top 5 cities with the highest number of distinct user registrations.
```sql
SELECT e.city, COUNT(DISTINCT r.user_id) AS distinct_users_count
FROM Registrations r
JOIN Events e ON r.event_id = e.event_id
GROUP BY e.city
ORDER BY distinct_users_count DESC
LIMIT 5;
```
*Expected Output:*
| city | distinct_users_count |
| :--- | :--- |
| New York | 2 |
| Chicago | 1 |
| Los Angeles | 1 |

---

### 6. Event Resource Summary
*Objective:* Generate a report showing the number of resources (PDFs, images, links) uploaded for each event.
```sql
SELECT e.event_id, e.title AS event_title,
       SUM(CASE WHEN r.resource_type = 'pdf' THEN 1 ELSE 0 END) AS pdf_count,
       SUM(CASE WHEN r.resource_type = 'image' THEN 1 ELSE 0 END) AS image_count,
       SUM(CASE WHEN r.resource_type = 'link' THEN 1 ELSE 0 END) AS link_count,
       COUNT(r.resource_id) AS total_resources
FROM Events e
LEFT JOIN Resources r ON e.event_id = r.event_id
GROUP BY e.event_id, e.title;
```
*Expected Output:*
| event_id | event_title | pdf_count | image_count | link_count | total_resources |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 1 | Tech Innovators Meetup | 1 | 0 | 0 | 1 |
| 2 | AI & ML Conference | 0 | 1 | 0 | 1 |
| 3 | Frontend Development Bootcamp | 0 | 0 | 1 | 1 |

---

### 7. Low Feedback Alerts
*Objective:* List all users who gave feedback with a rating less than 3, along with comments.
```sql
SELECT u.full_name, e.title AS event_name, f.rating, f.comments
FROM Feedback f
JOIN Users u ON f.user_id = u.user_id
JOIN Events e ON f.event_id = e.event_id
WHERE f.rating < 3;
```
*Expected Output:* Empty set (Only ratings $\ge 3$ exist in the feedback dataset).

---

### 8. Sessions per Upcoming Event
*Objective:* Display all upcoming events with the count of sessions scheduled for them.
```sql
SELECT e.event_id, e.title AS event_title, COUNT(s.session_id) AS sessions_count
FROM Events e
LEFT JOIN Sessions s ON e.event_id = s.event_id
WHERE e.status = 'upcoming'
GROUP BY e.event_id, e.title;
```
*Expected Output:*
| event_id | event_title | sessions_count |
| :--- | :--- | :--- |
| 1 | Tech Innovators Meetup | 2 |
| 3 | Frontend Development Bootcamp | 1 |

---

### 9. Organizer Event Summary
*Objective:* For each organizer, show the number of events created and their status.
```sql
SELECT u.user_id, u.full_name AS organizer_name,
       SUM(CASE WHEN e.status = 'upcoming' THEN 1 ELSE 0 END) AS upcoming_count,
       SUM(CASE WHEN e.status = 'completed' THEN 1 ELSE 0 END) AS completed_count,
       SUM(CASE WHEN e.status = 'cancelled' THEN 1 ELSE 0 END) AS cancelled_count,
       COUNT(e.event_id) AS total_events_created
FROM Users u
JOIN Events e ON u.user_id = e.organizer_id
GROUP BY u.user_id, u.full_name;
```
*Expected Output:*
| user_id | organizer_name | upcoming_count | completed_count | cancelled_count | total_events_created |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 1 | Alice Johnson | 1 | 0 | 0 | 1 |
| 2 | Bob Smith | 1 | 0 | 0 | 1 |
| 3 | Charlie Lee | 0 | 1 | 0 | 1 |

---

### 10. Feedback Gap
*Objective:* Identify events that had registrations but received no feedback at all.
```sql
SELECT DISTINCT e.event_id, e.title AS event_title
FROM Events e
JOIN Registrations r ON e.event_id = r.event_id
WHERE e.event_id NOT IN (
    SELECT DISTINCT event_id 
    FROM Feedback
);
```
*Expected Output:*
| event_id | event_title |
| :--- | :--- |
| 3 | Frontend Development Bootcamp |

---

### 11. Daily New User Count
*Objective:* Find the number of users who registered each day in the last 7 days.
```sql
SELECT registration_date, COUNT(user_id) AS new_users_count
FROM Users
WHERE registration_date >= DATE_SUB('2025-02-05', INTERVAL 7 DAY)
GROUP BY registration_date
ORDER BY registration_date DESC;
```
*Expected Output:*
| registration_date | new_users_count |
| :--- | :--- |
| 2025-02-01 | 1 |

---

### 12. Event with Maximum Sessions
*Objective:* List the event(s) with the highest number of sessions.
```sql
SELECT e.event_id, e.title AS event_title, COUNT(s.session_id) AS sessions_count
FROM Events e
JOIN Sessions s ON e.event_id = s.event_id
GROUP BY e.event_id, e.title
HAVING COUNT(s.session_id) = (
    SELECT MAX(session_cnt)
    FROM (
        SELECT COUNT(session_id) AS session_cnt
        FROM Sessions
        GROUP BY event_id
    ) AS temp
);
```
*Expected Output:*
| event_id | event_title | sessions_count |
| :--- | :--- | :--- |
| 1 | Tech Innovators Meetup | 2 |

---

### 13. Average Rating per City
*Objective:* Calculate the average feedback rating of events conducted in each city.
```sql
SELECT e.city, AVG(f.rating) AS avg_feedback_rating
FROM Events e
JOIN Feedback f ON e.event_id = f.event_id
GROUP BY e.city;
```
*Expected Output:*
| city | avg_feedback_rating |
| :--- | :--- |
| Chicago | 4.5000 |
| New York | 3.0000 |

---

### 14. Most Registered Events
*Objective:* List top 3 events based on registrations.
```sql
SELECT e.event_id, e.title AS event_title, COUNT(r.registration_id) AS registrations_count
FROM Events e
LEFT JOIN Registrations r ON e.event_id = r.event_id
GROUP BY e.event_id, e.title
ORDER BY registrations_count DESC
LIMIT 3;
```
*Expected Output:*
| event_id | event_title | registrations_count |
| :--- | :--- | :--- |
| 1 | Tech Innovators Meetup | 2 |
| 2 | AI & ML Conference | 2 |
| 3 | Frontend Development Bootcamp | 1 |

---

### 15. Event Session Time Conflict
*Objective:* Identify overlapping sessions within the same event.
```sql
SELECT s1.event_id, s1.title AS session_1, s2.title AS session_2, s1.start_time, s1.end_time
FROM Sessions s1
JOIN Sessions s2 ON s1.event_id = s2.event_id AND s1.session_id < s2.session_id
WHERE s1.start_time < s2.end_time 
  AND s2.start_time < s1.end_time;
```
*Expected Output:* Empty set (No conflicting sessions exist in the dataset).

---

### 16. Unregistered Active Users
*Objective:* Find users who registered in the last 30 days but haven't registered for any events.
```sql
SELECT user_id, full_name, email, registration_date
FROM Users
WHERE registration_date >= DATE_SUB('2025-02-05', INTERVAL 30 DAY)
  AND user_id NOT IN (
      SELECT DISTINCT user_id 
      FROM Registrations
  );
```
*Expected Output:* Empty set (Ethan registered on 2025-02-01 and registered for event 3 on 2025-06-15).

---

### 17. Multi-Session Speakers
*Objective:* Speakers handling more than one session across all events.
```sql
SELECT speaker_name, COUNT(session_id) AS session_count
FROM Sessions
GROUP BY speaker_name
HAVING COUNT(session_id) > 1;
```
*Expected Output:* Empty set (All speakers in the dataset handle exactly 1 session).

---

### 18. Resource Availability Check
*Objective:* List events without any resources.
```sql
SELECT event_id, title AS event_title
FROM Events
WHERE event_id NOT IN (
    SELECT DISTINCT event_id 
    FROM Resources
);
```
*Expected Output:* Empty set (All events in the sample dataset have 1 uploaded resource).

---

### 19. Completed Events with Feedback Summary
*Objective:* Completed events total registrations and average rating.
```sql
SELECT e.event_id, e.title AS event_title,
       COUNT(DISTINCT r.registration_id) AS total_registrations,
       AVG(f.rating) AS avg_feedback_rating
FROM Events e
LEFT JOIN Registrations r ON e.event_id = r.event_id
LEFT JOIN Feedback f ON e.event_id = f.event_id
WHERE e.status = 'completed'
GROUP BY e.event_id, e.title;
```
*Expected Output:*
| event_id | event_title | total_registrations | avg_feedback_rating |
| :--- | :--- | :--- | :--- |
| 2 | AI & ML Conference | 2 | 4.5000 |

---

### 20. User Engagement Index
*Objective:* For each user, list registrations and feedbacks submitted.
```sql
SELECT u.user_id, u.full_name,
       COUNT(DISTINCT r.event_id) AS registered_events_count,
       COUNT(DISTINCT f.feedback_id) AS feedback_submitted_count
FROM Users u
LEFT JOIN Registrations r ON u.user_id = r.user_id
LEFT JOIN Feedback f ON u.user_id = f.user_id
GROUP BY u.user_id, u.full_name;
```
*Expected Output:*
| user_id | full_name | registered_events_count | feedback_submitted_count |
| :--- | :--- | :--- | :--- |
| 1 | Alice Johnson | 1 | 0 |
| 2 | Bob Smith | 1 | 1 |
| 3 | Charlie Lee | 1 | 1 |
| 4 | Diana King | 1 | 1 |
| 5 | Ethan Hunt | 1 | 0 |

---

### 21. Top Feedback Providers
*Objective:* Top 5 users who submitted the most feedback entries.
```sql
SELECT u.user_id, u.full_name, COUNT(f.feedback_id) AS feedback_count
FROM Users u
JOIN Feedback f ON u.user_id = f.user_id
GROUP BY u.user_id, u.full_name
ORDER BY feedback_count DESC
LIMIT 5;
```
*Expected Output:*
| user_id | full_name | feedback_count |
| :--- | :--- | :--- |
| 2 | Bob Smith | 1 |
| 3 | Charlie Lee | 1 |
| 4 | Diana King | 1 |

---

### 22. Duplicate Registrations Check
*Objective:* Detect if a user is registered more than once for the same event.
```sql
SELECT user_id, event_id, COUNT(registration_id) AS registrations_count
FROM Registrations
GROUP BY user_id, event_id
HAVING COUNT(registration_id) > 1;
```
*Expected Output:* Empty set (No duplicate registrations exist).

---

### 23. Registration Trends
*Objective:* Show month-wise registration count trend over the past 12 months.
```sql
SELECT DATE_FORMAT(registration_date, '%Y-%m') AS reg_month, COUNT(registration_id) AS registrations_count
FROM Registrations
WHERE registration_date >= DATE_SUB('2025-07-01', INTERVAL 12 MONTH)
GROUP BY reg_month
ORDER BY reg_month ASC;
```
*Expected Output:*
| reg_month | registrations_count |
| :--- | :--- |
| 2025-04 | 2 |
| 2025-05 | 2 |
| 2025-06 | 1 |

---

### 24. Average Session Duration per Event
*Objective:* Compute average duration of sessions in each event in minutes.
```sql
SELECT event_id, AVG(TIMESTAMPDIFF(MINUTE, start_time, end_time)) AS avg_session_duration_minutes
FROM Sessions
GROUP BY event_id;
```
*Expected Output:*
| event_id | avg_session_duration_minutes |
| :--- | :--- |
| 1 | 67.5000 |
| 2 | 90.0000 |
| 3 | 120.0000 |

---

### 25. Events Without Sessions
*Objective:* List events with no sessions scheduled.
```sql
SELECT event_id, title AS event_title
FROM Events
WHERE event_id NOT IN (
    SELECT DISTINCT event_id 
    FROM Sessions
);
```
*Expected Output:* Empty set (All events in the sample database have at least one session).
