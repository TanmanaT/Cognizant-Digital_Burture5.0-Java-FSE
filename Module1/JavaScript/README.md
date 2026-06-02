# JavaScript Exercises: Local Community Event Portal

This directory contains solutions for all 14 JavaScript exercises under the theme **"Local Community Event Portal"**.

## Files
- [index.html](file:///d:/New%20folder/Module1/JavaScript/index.html): Portal dashboard displaying cards, search inputs, filter selectors, and jQuery triggers.
- [main.js](file:///d:/New%20folder/Module1/JavaScript/main.js): JavaScript core files compiled with ES6 variables, closures, prototypes, async data loaders, and form handlers.
- [events.json](file:///d:/New%20folder/Module1/JavaScript/events.json): Local mock JSON database representing events list.
- [styles.css](file:///d:/New%20folder/Module1/JavaScript/styles.css): Stylesheet defining card grids, layouts, badges, and loader animations.

---

## Exercise Implementations

### 1. JS Basics & Setup
- Linked [main.js](file:///d:/New%20folder/Module1/JavaScript/main.js) in HTML using `<script src="main.js"></script>`.
- Emits console logs: `console.log("Welcome to the Community Portal")`.
- Fires page-load notifications alerts: `window.onload`.

### 2. Variables and Operators
- Used `const` declarations for static parameters (name, venue, category) and `let` for fluctuating values (seat spaces).
- Manipulates available seat capacities using increment/decrement operators (`++`/`--`) on reservation checkins/cancellations.

### 3. Conditionals, Loops & Errors
- Formulates conditional checks (if-else logic) to hide past events or sold-out assemblies.
- Iterates event listings array utilizing `.forEach()`.
- Packages registration instructions inside `try-catch` segments to capture execution bugs cleanly.

### 4. Scope, Closures & Callbacks
- Utilizes closure patterns (`createRegistrationTracker`) to preserve registration tally values per category throughout active browsing sessions.
- Applies custom filter parameters inside arrays using callback filters.

### 5. Objects & Prototypes
- Created a standard class model `CommunityEvent` to structure event objects.
- Bound checking availability checks (`checkAvailability()`) directly onto the class prototype to evaluate seats availability.
- Console traces target attributes utilizing `Object.entries(eventsList[0])`.

### 6. Array Methods
- Adds objects into active collections utilizing `.push()`.
- Implements custom filters (`.filter()`) to narrow down displays (e.g. music listings).
- Formats list collections using `.map()`.

### 7 & 8. DOM Manipulation & Event Handling
- Queries target container tags using `document.querySelector()`.
- Spawns card layout blocks programmatically using `document.createElement()` and appends them to grids.
- Updates seat counters on user registers/cancellations.
- Monitors list search queries using `onkeyup`/`keydown` events.

### 9. Asynchronous Promise & Async/Await
- Resolves promises asynchronously using `async`/`await` parameters to fetch the local JSON database `events.json`.
- Displays visual loaders during data fetches.

### 10. Modern ES6 Features
- Standardizes parameter fallback configurations using default variables.
- Extracts variables easily from objects using destructuring: `const { id, title } = event;`.
- Clones array structures using the spread operator: `[...eventsList]`.

### 11 & 12. Forms & Simulated Fetch API
- Selects input elements using `form.elements` objects.
- Blocks browser reload actions on submit using `event.preventDefault()`.
- Validates strings, reporting inline warning alerts.
- Simulates server posting latency using `setTimeout()`.

### 13. Debugging Operations
- Debugged utilizing browser developer console parameters and trace checks.

### 14. jQuery and JS Frameworks
- Integrated the jQuery library CDN.
- Configured card entrance animations using `.fadeIn(400)`.

---

## Benefit of JS Frameworks (Vue/React)
1. **Declarative Rendering & State Management:** Vanilla JavaScript requires manually writing DOM manipulation code (`document.createElement`, `appendChild`) and manually updating text nodes when states shift. React and Vue manage state changes reactively, updating the DOM automatically.
2. **Component-Based Architecture:** Encourages splitting complex layouts into tiny, reusable, self-contained files containing their own markup, styles, and logic.
3. **Virtual DOM Optimization:** Avoids expensive repaint actions by evaluating layout updates off-screen first.
