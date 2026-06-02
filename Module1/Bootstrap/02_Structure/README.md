# 2. Bootstrap Structure and Files

## Exercise 2.1: Bootstrap Directory Structure Explained

When downloading the precompiled distribution files of Bootstrap 5, the directory is split into core folders:

### 1. `css/` Folder
Contains the compiled stylesheets.
- `bootstrap.css` & `bootstrap.min.css`: The core stylesheets containing all utility classes, grid rules, components, and layout utilities.
- `bootstrap-grid.css` & `bootstrap-grid.min.css`: A subset focused purely on the responsive grid system and flexbox utilities.
- `bootstrap-reboot.css` & `bootstrap-reboot.min.css`: An updated reset stylesheet to normalize default browser styling.
- `bootstrap-utilities.css` & `bootstrap-utilities.min.css`: Focuses solely on helper utility classes.

### 2. `js/` Folder
Contains the JavaScript code to enable interactive components.
- `bootstrap.js` & `bootstrap.min.js`: The Javascript plugins. Note that these do **not** include Popper.js (required for Tooltips, Popovers, and Dropdowns).
- `bootstrap.bundle.js` & `bootstrap.bundle.min.js`: Includes both the Bootstrap plugins **and** Popper.js in a single package.

### 3. `icons/` Folder (Bootstrap Icons)
- Bootstrap Icons is a separate package containing raw SVGs, SVG sprites, and web font icon sets. Web fonts (`bootstrap-icons.woff2` etc.) are used along with `bootstrap-icons.css` to render vector icons via class names (e.g., `.bi-heart`).

---

## Exercise 2.2: JavaScript Plugins via Bundle

Interactive plugins such as Tooltips and Popovers are initialized and working in [index.html](file:///d:/New%20folder/Module1_Bootstrap/02_Structure/index.html) using `bootstrap.bundle.min.js`.
