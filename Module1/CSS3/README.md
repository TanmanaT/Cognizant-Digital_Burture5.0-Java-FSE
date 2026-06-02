# CSS3 Exercises: Local Community Event Portal

This directory contains solutions and layouts for all 11 CSS3 exercises under the theme **"Local Community Event Portal"**.

## Files
- [index.html](file:///d:/New%20folder/Module1/CSS3/index.html): Interactive portal page implementing layout structures.
- [styles.css](file:///d:/New%20folder/Module1/CSS3/styles.css): External stylesheet compiling all rules, formatted with descriptive comments.

---

## Exercise Implementations

### 1. CSS Inclusion Methods
Demonstrated the three primary inclusion types:
- **Inline CSS:** Applied style parameters directly inside elements (e.g. warning text: `<h3 style="color: #ef4444;">`).
- **Internal CSS:** Embedded `<style>` rules inside the `<head>` of the html document to control background gradients.
- **External CSS:** Linked [styles.css](file:///d:/New%20folder/Module1/CSS3/styles.css) in the document header for standard styling rules.

### 2. Syntax and Comments
- Styled elements using clean, nested, indent-aligned declarations.
- Organized stylesheet sections using comments (`/* Selectors Playground */` etc.) to improve readability.

### 3. Selectors Playground
- **Universal Reset Selector (`*`):** Standardizes page layouts by resetting margin/padding boundaries.
- **Element Selector (`h2`):** Customizes section titles globally.
- **ID Selector (`#mainHeader`):** Styles the main portal header banner.
- **Class Selector (`.eventCard`):** Formats event container layout components.
- **Grouping Selector (`h3, p`):** Groups layout rules to style headings and body text uniformly.

### 4. Colors & Background
- Set backgrounds using HEX values (`#0f172a`) and alpha-transparency using RGBA values (`rgba(255, 255, 255, 0.03)`).
- Applied responsive background gradients (`radial-gradient` / `linear-gradient`) on sections.

### 5. Typography
- Linked the Google Font **"Outfit"** for a premium modern sans-serif feel.
- Defined typographic scales using `font-size`, custom weights (`font-weight: 800`), uppercase casing (`text-transform: uppercase`), and tracking text (`letter-spacing`).

### 6. Link & List Styling
- Style anchor navigation links with `:link`, `:visited`, `:hover`, and `:active` pseudo-classes to display interactive changes.
- Set `list-style-type: none` to remove bullet symbols from lists.

### 7. Table Styling
- Standardized the admin data table utilizing `border-collapse: collapse`, cell padding, and grid borders.
- Implemented zebra striping using `tr:nth-child(even)` selectors.

### 8. Box Model Layout Controls
- Configured card bounds by styling properties: `margin`, `padding`, `border`.
- Custom outline highlights on focused input components using `.form-input:focus`.
- **Interactive Playground:** Demonstrates the difference between `visibility: hidden` (reserves empty layout space) and `display: none` (fully removes the node from layout rendering).

### 9. Multiple Columns
- Standardized multi-column newspaper layouts utilizing properties: `column-count: 2`, `column-gap: 30px`, and `column-rule`.

### 10. Responsive Layout Media Queries
- Added `@media (max-width: 767.98px)` viewport parameters.
- Formats standard inline navigation bar columns into stacked block configurations.
- Dynamically scales down font sizes for readability.

### 11. Debug Guidelines
- Documents how to use browser inspect elements, simulated responsive viewports, and trace stylesheets under the network tab.
