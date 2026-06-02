# HTML5 Exercises: Local Community Event Portal

This directory contains solutions and layouts for all 10 HTML5 exercises under the theme **"Local Community Event Portal"**.

## Files
- [index.html](file:///d:/New%20folder/Module1/HTML5/index.html): Interactive portal page implementing semantic structure, native media streams, form validation rules, and script handlers.
- [styles.css](file:///d:/New%20folder/Module1/HTML5/styles.css): External stylesheet compiling all rules, formatted with descriptive comments.
- [help.html](file:///d:/New%20folder/Module1/HTML5/help.html): Secondary linked help document page.

---

## Exercise Implementations

### 1. HTML5 Base Template
- Leverages standard HTML5 semantic page wrappers (`<header>`, `<nav>`, `<main>`, `<section>`, `<article>`, `<style>`, `<footer>`) to structure content layout.

### 2. Navigation and Linking
- Established anchor menu coordinates linking targeting sections internally (e.g. `<a href="#events">`).
- Links to [help.html](file:///d:/New%20folder/Module1/HTML5/help.html) using target parameters (`target="_blank"`) to open in a new tab.

### 3. Welcome Banner & Highlighting
- Designed a welcome section panel styled with internal/embedded head CSS overrides.
- Implemented highlighting classes (`.highlight`) alongside warning bold text (`style="color: #ef4444;"`).

### 4. Event Table Gallery
- Formatted past civic images into a clean `<table>` grid layout including `alt` and `title` tags.
- Configured dynamic borders on images when hovered.

### 5. Event Registration Form
- Implemented HTML5 form input types and validation tags: `required`, `placeholder`, `autofocus`, `type="date"`, and `type="email"`.
- Displays confirmation alerts inside `<output>` containers upon successful event submissions.

### 6. Events Handling Playgrounds
- Validates phone inputs on text field focus exit (`onblur`).
- Calculates category fees dynamically on change (`onchange`).
- Captures double-click events (`ondblclick`) to launch zoomed visual image overlays.
- Evaluates string lengths inside textarea inputs dynamically (`onkeyup`).

### 7. Promo Video & Page Unload Triggers
- Hosts native video invite feeds via the HTML5 `<video>` selector.
- Triggers alert text once streaming payloads are buffered (`oncanplay`).
- Alerts users if they attempt to reload/close pages with unsaved form changes (`onbeforeunload`).

### 8. Web Storage Preferences
- Saves select input choices to browser memory context (`localStorage`).
- Loads preference options dynamically on reload.
- Flushes data caches completely on button click commands (both `localStorage` and `sessionStorage`).

### 9. Geolocation satellite coordinate queries
- Solicits current coordinates via API calls: `navigator.geolocation.getCurrentPosition`.
- Includes high accuracy options and error handling for user permission denials.

### 10. Developer Tools Console
- Implemented `console.log`, `console.error`, and `console.warn` triggers inside Javascript blocks.
