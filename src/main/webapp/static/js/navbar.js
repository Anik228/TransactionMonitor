/**
 * navbar.js
 * Handles the live clock displayed in the navbar.
 * Called once on DOMContentLoaded.
 */
(function () {
    "use strict";

    function updateClock() {
        const el = document.getElementById("navbar-clock");
        if (el) {
            el.textContent = new Date().toLocaleTimeString("en-GB"); // HH:MM:SS
        }
    }

    document.addEventListener("DOMContentLoaded", function () {
        updateClock();
        setInterval(updateClock, 1000);
    });
})();
