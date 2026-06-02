/* ==========================================
   JAVASCRIPT EXERCISES: main.js
   Theme: Local Community Event Portal
   ========================================== */

// 1. JavaScript Basics & Setup
console.log("Welcome to the Community Portal");

// Array of all community events (Exercise 6)
let eventsList = [];

// Closure tracking total registration count per category (Exercise 4)
function createRegistrationTracker() {
    let registrationCounts = {};
    return {
        register(category) {
            registrationCounts[category] = (registrationCounts[category] || 0) + 1;
            return registrationCounts[category];
        },
        getCounts() {
            return registrationCounts;
        }
    };
}
const tracker = createRegistrationTracker();

// 5. Objects and Prototypes (Event Constructor/Class)
class CommunityEvent {
    constructor({ id, title, category, date, location, seats, fee }) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.date = date;
        this.location = location;
        this.seats = seats;
        this.fee = fee;
    }
}

// Add checking availability to prototype (Exercise 5)
CommunityEvent.prototype.checkAvailability = function() {
    return this.seats > 0 && new Date(this.date) >= new Date();
};

// 9. Async JS, Promises, Async/Await (Load event listings from events.json)
async function fetchEventsData() {
    const spinner = document.getElementById("loadingSpinner");
    const container = document.getElementById("eventsContainer");
    
    // Show spinner, hide grid
    spinner.style.display = "flex";
    container.innerHTML = "";

    // Local events list mockup database
    const fallbackData = [
        {
            "id": 1,
            "title": "Civic Tree Plantation Drive",
            "category": "Outreach",
            "date": "2026-06-15",
            "location": "Central Park",
            "seats": 35,
            "fee": 0
        },
        {
            "id": 2,
            "title": "Web Development Bootcamp",
            "category": "Education",
            "date": "2026-06-20",
            "location": "Town Hall",
            "seats": 5,
            "fee": 2499
        },
        {
            "id": 3,
            "title": "Local Music Festival",
            "category": "Music",
            "date": "2026-06-25",
            "location": "Sports Complex Auditorium",
            "seats": 150,
            "fee": 999
        },
        {
            "id": 4,
            "title": "Youth Art Fair and Exhibition",
            "category": "Creative",
            "date": "2026-06-28",
            "location": "Heritage Plaza Corridor",
            "seats": 80,
            "fee": 499
        }
    ];

    // Bypasses fetch on file:// links to avoid throwing CORS console exceptions
    if (window.location.protocol === "file:") {
        console.log("Local filesystem detected (file://). Bypassing remote API fetch to prevent CORS errors. Loading local fallback database directly.");
        eventsList = fallbackData.map(item => new CommunityEvent(item));
        spinner.style.display = "none";
        renderEvents(eventsList);
        return;
    }

    try {
        // Fetch from events.json mock endpoint when running on a web server
        const response = await fetch("events.json");
        if (!response.ok) throw new Error("Network response error loading database.");
        
        const rawData = await response.json();
        eventsList = rawData.map(item => new CommunityEvent(item));
        
        console.log("Mock API event objects mapped. Entries values:");
        if (eventsList.length > 0) {
            console.log(Object.entries(eventsList[0]));
        }

        spinner.style.display = "none";
        renderEvents(eventsList);

    } catch (error) {
        console.error("Fetch API error:", error);
        // Fallback loader if fetching fails on server
        eventsList = fallbackData.map(item => new CommunityEvent(item));
        spinner.style.display = "none";
        renderEvents(eventsList);
    }
}

// 7. DOM Manipulation & Rendering
function renderEvents(events) {
    const container = document.getElementById("eventsContainer");
    container.innerHTML = "";

    if (events.length === 0) {
        container.innerHTML = `<div style="text-align: center; color: rgba(255,255,255,0.6);" class="col-12">No upcoming events match search parameters.</div>`;
        return;
    }

    // Loop through events using forEach (Exercise 3)
    events.forEach(event => {
        // 3. Conditionals: Hide past events or events with no seats in visual flow
        // (Exercise 3 user story condition)
        if (!event.checkAvailability()) {
            return; // skip rendering
        }

        // 10. ES6 Destructuring (Exercise 10)
        const { id, title, category, date, location, seats, fee } = event;

        // 2. Template Literals (Exercise 2)
        const eventDetail = `${title} is category ${category} located at ${location}.`;

        // Create elements dynamically (Exercise 7)
        const card = document.createElement("div");
        card.className = "event-card";
        card.id = `event-card-${id}`;
        card.style.display = "none"; // Hide initially for jQuery fade animation

        // Append layout HTML structure
        card.innerHTML = `
            <div>
                <span class="event-badge badge-${category.toLowerCase()}">${category}</span>
                <h3 class="event-title">${title}</h3>
                <p style="font-size: 0.85rem; color: rgba(255,255,255,0.6); margin-bottom: 1rem;">
                    📅 ${date} | 📍 ${location}
                </p>
                <p style="font-size: 0.9rem; color: rgba(255,255,255,0.8); margin-bottom: 1.5rem;">
                    Seats Available: <strong id="seats-val-${id}" style="color: #38bdf8;">${seats}</strong> | Fee: <strong style="color: #10b981;">${fee === 0 ? "Free" : "₹" + fee}</strong>
                </p>
            </div>
            <div style="display: flex; gap: 0.5rem;">
                <button onclick="registerForEvent(${id})" class="btn-primary" style="flex: 1; padding: 0.5rem;">Register</button>
                <button onclick="cancelRegistration(${id})" class="btn-secondary" style="padding: 0.5rem; font-size: 0.8rem; border-color: rgba(239, 68, 68, 0.4); color: #f87171;">Cancel</button>
            </div>
        `;

        container.appendChild(card);
        
        // 14. jQuery Animate Card Fade In (Exercise 14)
        $(`#event-card-${id}`).fadeIn(400);
    });
}

// 4. Register and decrease available seats (Exercise 2 & 3 & 4 & 7)
function registerForEvent(eventId) {
    try {
        // Find matching event
        const event = eventsList.find(e => e.id === eventId);
        if (!event) throw new Error("Target event catalog parameter invalid.");

        if (event.seats <= 0) {
            alert("Sorry, no seats available for this event!");
            return;
        }

        // Decrement seat operator (Exercise 2)
        event.seats--;
        
        // Update specific UI node (Exercise 7)
        document.getElementById(`seats-val-${eventId}`).textContent = event.seats;

        // Trigger Category Tracker Closure (Exercise 4)
        const totalReg = tracker.register(event.category);
        console.log(`Registered for ${event.title}. Total registrations in category ${event.category}: ${totalReg}`);

        // Update Tracker Dashboard UI
        updateTrackerDashboard();

        alert(`Successfully registered! Seat confirmed for: ${event.title}`);

    } catch (error) {
        console.error("Registration error handled:", error.message);
        alert(`Failed to register: ${error.message}`);
    }
}

// Cancel registration and increase seats (Exercise 7)
function cancelRegistration(eventId) {
    const event = eventsList.find(e => e.id === eventId);
    if (event) {
        // Increment seats operator (Exercise 2)
        event.seats++;
        document.getElementById(`seats-val-${eventId}`).textContent = event.seats;
        console.log(`Cancelled registration for ${event.title}. seats incremented.`);
    }
}

// Update Tracker Dashboard info in UI (Exercise 4 closure)
function updateTrackerDashboard() {
    const countPanel = document.getElementById("trackerCounts");
    const counts = tracker.getCounts();
    countPanel.innerHTML = "";
    
    Object.entries(counts).forEach(([category, val]) => {
        countPanel.innerHTML += `<div style="font-size: 0.9rem; margin-bottom: 0.5rem;">🟢 Category <strong>${category}</strong> registrations: <span style="color: #38bdf8;">${val}</span></div>`;
    });
}

// 8. Filter events by category on selection dropdown (Exercise 8 & 10)
function filterEvents() {
    const categorySelect = document.getElementById("filterCategory").value;
    
    // Spread operator to clone events array before sorting (Exercise 10)
    let filtered = [...eventsList];

    if (categorySelect) {
        // Callback style filtering (Exercise 4 & 6)
        filtered = filtered.filter(event => event.category === categorySelect);
    }

    renderEvents(filtered);
}

// 6. Showcase Filter only Music events (Exercise 6)
function showOnlyMusicEvents() {
    const musicEvents = eventsList.filter(e => e.category === "Music");
    renderEvents(musicEvents);
}

// 8. Keydown Quick Search handler (Exercise 8)
function quickSearchEvents(event) {
    const searchVal = event.target.value.toLowerCase().trim();
    
    // Filter matching titles
    const filtered = eventsList.filter(e => e.title.toLowerCase().includes(searchVal));
    renderEvents(filtered);
}

// 11. Event Registration Form Sign-Up (Exercise 11 & 12)
async function submitRegistrationForm(e) {
    e.preventDefault();
    
    const form = document.getElementById("registrationForm");
    const errorBox = document.getElementById("formErrors");
    const successBox = document.getElementById("formSuccess");
    
    errorBox.textContent = "";
    successBox.textContent = "";

    // Access elements dynamically (Exercise 11)
    const name = form.elements["userName"].value.trim();
    const email = form.elements["userEmail"].value.trim();
    const eventSelect = form.elements["eventSelect"].value;

    // Validate inputs
    if (name.length < 3) {
        errorBox.textContent = "Error: Full name must be at least 3 characters long.";
        return;
    }
    if (!email.includes("@")) {
        errorBox.textContent = "Error: Please enter a valid email address.";
        return;
    }
    if (!eventSelect) {
        errorBox.textContent = "Error: Please select an event.";
        return;
    }

    // 12. AJAX & Fetch POST user registration (Simulated backend delay using setTimeout)
    successBox.textContent = "Sending registration details to server...";
    
    setTimeout(async () => {
        try {
            // POST request to mock endpoint (Exercise 12)
            const payload = { name, email, event: eventSelect };
            console.log("Mock AJAX POST payload sent:", JSON.stringify(payload));
            
            // Success response message
            successBox.textContent = `✓ Registration submitted successfully! Welcome, ${name}.`;
            form.reset();

        } catch (error) {
            errorBox.textContent = "Error sending registration details to servers.";
            console.error("AJAX error:", error);
        }
    }, 1500); // 1.5s simulated lag
}

// Alert when fully loaded (Exercise 1)
window.addEventListener("load", () => {
    alert("Welcome to the Community Event Portal!");
    fetchEventsData();
});
