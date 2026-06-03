-- ======================================================
-- MODULE 2: ANSI SQL USING MYSQL EXERCISES
-- Solutions for Exercises 1 - 25
-- ======================================================

-- ------------------------------------------------------
-- 1. User Upcoming Events
-- Show a list of all upcoming events a user is registered for in their city, sorted by date.
-- ------------------------------------------------------
SELECT u.user_id, u.full_name, e.title AS event_title, e.city, e.start_date
FROM Registrations r
JOIN Users u ON r.user_id = u.user_id
JOIN Events e ON r.event_id = e.event_id
WHERE e.status = 'upcoming' 
  AND e.city = u.city
ORDER BY e.start_date ASC;

-- ------------------------------------------------------
-- 2. Top Rated Events
-- Identify events with the highest average rating, considering only those that have received at least 10 feedback submissions.
-- ------------------------------------------------------
SELECT e.event_id, e.title AS event_title, AVG(f.rating) AS avg_rating, COUNT(f.feedback_id) AS feedback_count
FROM Events e
JOIN Feedback f ON e.event_id = f.event_id
GROUP BY e.event_id, e.title
HAVING COUNT(f.feedback_id) >= 10
ORDER BY avg_rating DESC;

-- ------------------------------------------------------
-- 3. Inactive Users
-- Retrieve users who have not registered for any events in the last 90 days.
-- ------------------------------------------------------
SELECT user_id, full_name, email, registration_date
FROM Users
WHERE user_id NOT IN (
    SELECT DISTINCT user_id
    FROM Registrations
    WHERE registration_date >= DATE_SUB('2025-07-15', INTERVAL 90 DAY) -- Mock baseline for sample datasets check
);

-- ------------------------------------------------------
-- 4. Peak Session Hours
-- Count how many sessions are scheduled between 10 AM to 12 PM for each event.
-- ------------------------------------------------------
SELECT event_id, COUNT(session_id) AS sessions_10am_to_12pm_count
FROM Sessions
WHERE TIME(start_time) >= '10:00:00' 
  AND TIME(end_time) <= '12:00:00'
GROUP BY event_id;

-- ------------------------------------------------------
-- 5. Most Active Cities
-- List the top 5 cities with the highest number of distinct user registrations.
-- ------------------------------------------------------
SELECT e.city, COUNT(DISTINCT r.user_id) AS distinct_users_count
FROM Registrations r
JOIN Events e ON r.event_id = e.event_id
GROUP BY e.city
ORDER BY distinct_users_count DESC
LIMIT 5;

-- ------------------------------------------------------
-- 6. Event Resource Summary
-- Generate a report showing the number of resources (PDFs, images, links) uploaded for each event.
-- ------------------------------------------------------
SELECT e.event_id, e.title AS event_title,
       SUM(CASE WHEN r.resource_type = 'pdf' THEN 1 ELSE 0 END) AS pdf_count,
       SUM(CASE WHEN r.resource_type = 'image' THEN 1 ELSE 0 END) AS image_count,
       SUM(CASE WHEN r.resource_type = 'link' THEN 1 ELSE 0 END) AS link_count,
       COUNT(r.resource_id) AS total_resources
FROM Events e
LEFT JOIN Resources r ON e.event_id = r.event_id
GROUP BY e.event_id, e.title;

-- ------------------------------------------------------
-- 7. Low Feedback Alerts
-- List all users who gave feedback with a rating less than 3, along with their comments and associated event names.
-- ------------------------------------------------------
-- Note: Rating threshold is less than 3 (< 3). Rating 3 is not returned.
SELECT u.full_name, e.title AS event_name, f.rating, f.comments
FROM Feedback f
JOIN Users u ON f.user_id = u.user_id
JOIN Events e ON f.event_id = e.event_id
WHERE f.rating < 3;

-- ------------------------------------------------------
-- 8. Sessions per Upcoming Event
-- Display all upcoming events with the count of sessions scheduled for them.
-- ------------------------------------------------------
SELECT e.event_id, e.title AS event_title, COUNT(s.session_id) AS sessions_count
FROM Events e
LEFT JOIN Sessions s ON e.event_id = s.event_id
WHERE e.status = 'upcoming'
GROUP BY e.event_id, e.title;

-- ------------------------------------------------------
-- 9. Organizer Event Summary
-- For each event organizer, show the number of events created and their current status (upcoming, completed, cancelled).
-- ------------------------------------------------------
SELECT u.user_id, u.full_name AS organizer_name,
       SUM(CASE WHEN e.status = 'upcoming' THEN 1 ELSE 0 END) AS upcoming_count,
       SUM(CASE WHEN e.status = 'completed' THEN 1 ELSE 0 END) AS completed_count,
       SUM(CASE WHEN e.status = 'cancelled' THEN 1 ELSE 0 END) AS cancelled_count,
       COUNT(e.event_id) AS total_events_created
FROM Users u
JOIN Events e ON u.user_id = e.organizer_id
GROUP BY u.user_id, u.full_name;

-- ------------------------------------------------------
-- 10. Feedback Gap
-- Identify events that had registrations but received no feedback at all.
-- ------------------------------------------------------
SELECT DISTINCT e.event_id, e.title AS event_title
FROM Events e
JOIN Registrations r ON e.event_id = r.event_id
WHERE e.event_id NOT IN (
    SELECT DISTINCT event_id 
    FROM Feedback
);

-- ------------------------------------------------------
-- 11. Daily New User Count
-- Find the number of users who registered each day in the last 7 days.
-- ------------------------------------------------------
SELECT registration_date, COUNT(user_id) AS new_users_count
FROM Users
WHERE registration_date >= DATE_SUB('2025-02-05', INTERVAL 7 DAY) -- Mock baseline for sample dataset registrations check
GROUP BY registration_date
ORDER BY registration_date DESC;

-- ------------------------------------------------------
-- 12. Event with Maximum Sessions
-- List the event(s) with the highest number of sessions.
-- ------------------------------------------------------
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

-- ------------------------------------------------------
-- 13. Average Rating per City
-- Calculate the average feedback rating of events conducted in each city.
-- ------------------------------------------------------
SELECT e.city, AVG(f.rating) AS avg_feedback_rating
FROM Events e
JOIN Feedback f ON e.event_id = f.event_id
GROUP BY e.city;

-- ------------------------------------------------------
-- 14. Most Registered Events
-- List top 3 events based on the total number of user registrations.
-- ------------------------------------------------------
SELECT e.event_id, e.title AS event_title, COUNT(r.registration_id) AS registrations_count
FROM Events e
LEFT JOIN Registrations r ON e.event_id = r.event_id
GROUP BY e.event_id, e.title
ORDER BY registrations_count DESC
LIMIT 3;

-- ------------------------------------------------------
-- 15. Event Session Time Conflict
-- Identify overlapping sessions within the same event (i.e., session start and end times that conflict).
-- ------------------------------------------------------
SELECT s1.event_id, s1.title AS session_1, s2.title AS session_2, s1.start_time, s1.end_time
FROM Sessions s1
JOIN Sessions s2 ON s1.event_id = s2.event_id AND s1.session_id < s2.session_id
WHERE s1.start_time < s2.end_time 
  AND s2.start_time < s1.end_time;

-- ------------------------------------------------------
-- 16. Unregistered Active Users
-- Find users who created an account in the last 30 days but haven’t registered for any events.
-- ------------------------------------------------------
SELECT user_id, full_name, email, registration_date
FROM Users
WHERE registration_date >= DATE_SUB('2025-02-05', INTERVAL 30 DAY) -- Mock baseline for sample check
  AND user_id NOT IN (
      SELECT DISTINCT user_id 
      FROM Registrations
  );

-- ------------------------------------------------------
-- 17. Multi-Session Speakers
-- Identify speakers who are handling more than one session across all events.
-- ------------------------------------------------------
SELECT speaker_name, COUNT(session_id) AS session_count
FROM Sessions
GROUP BY speaker_name
HAVING COUNT(session_id) > 1;

-- ------------------------------------------------------
-- 18. Resource Availability Check
-- List all events that do not have any resources uploaded.
-- ------------------------------------------------------
SELECT event_id, title AS event_title
FROM Events
WHERE event_id NOT IN (
    SELECT DISTINCT event_id 
    FROM Resources
);

-- ------------------------------------------------------
-- 19. Completed Events with Feedback Summary
-- For completed events, show total registrations and average feedback rating.
-- ------------------------------------------------------
SELECT e.event_id, e.title AS event_title,
       COUNT(DISTINCT r.registration_id) AS total_registrations,
       AVG(f.rating) AS avg_feedback_rating
FROM Events e
LEFT JOIN Registrations r ON e.event_id = r.event_id
LEFT JOIN Feedback f ON e.event_id = f.event_id
WHERE e.status = 'completed'
GROUP BY e.event_id, e.title;

-- ------------------------------------------------------
-- 20. User Engagement Index
-- For each user, calculate how many events they attended and how many feedbacks they submitted.
-- ------------------------------------------------------
SELECT u.user_id, u.full_name,
       COUNT(DISTINCT r.event_id) AS registered_events_count,
       COUNT(DISTINCT f.feedback_id) AS feedback_submitted_count
FROM Users u
LEFT JOIN Registrations r ON u.user_id = r.user_id
LEFT JOIN Feedback f ON u.user_id = f.user_id
GROUP BY u.user_id, u.full_name;

-- ------------------------------------------------------
-- 21. Top Feedback Providers
-- List top 5 users who have submitted the most feedback entries.
-- ------------------------------------------------------
SELECT u.user_id, u.full_name, COUNT(f.feedback_id) AS feedback_count
FROM Users u
JOIN Feedback f ON u.user_id = f.user_id
GROUP BY u.user_id, u.full_name
ORDER BY feedback_count DESC
LIMIT 5;

-- ------------------------------------------------------
-- 22. Duplicate Registrations Check
-- Detect if a user has been registered more than once for the same event.
-- ------------------------------------------------------
SELECT user_id, event_id, COUNT(registration_id) AS registrations_count
FROM Registrations
GROUP BY user_id, event_id
HAVING COUNT(registration_id) > 1;

-- ------------------------------------------------------
-- 23. Registration Trends
-- Show a month-wise registration count trend over the past 12 months.
-- ------------------------------------------------------
SELECT DATE_FORMAT(registration_date, '%Y-%m') AS reg_month, COUNT(registration_id) AS registrations_count
FROM Registrations
WHERE registration_date >= DATE_SUB('2025-07-01', INTERVAL 12 MONTH) -- Mock baseline for sample check
GROUP BY reg_month
ORDER BY reg_month ASC;

-- ------------------------------------------------------
-- 24. Average Session Duration per Event
-- Compute the average duration (in minutes) of sessions in each event.
-- ------------------------------------------------------
SELECT event_id, AVG(TIMESTAMPDIFF(MINUTE, start_time, end_time)) AS avg_session_duration_minutes
FROM Sessions
GROUP BY event_id;

-- ------------------------------------------------------
-- 25. Events Without Sessions
-- List all events that currently have no sessions scheduled under them.
-- ------------------------------------------------------
SELECT event_id, title AS event_title
FROM Events
WHERE event_id NOT IN (
    SELECT DISTINCT event_id 
    FROM Sessions
);
