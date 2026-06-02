# 3. Fundamentals of Responsive Grid Layout

## Exercise 3.1 & 3.2: Stacking and Columns

This exercise shows the implementation of a responsive 3-column layout that shifts behavior across breakpoints.

### Core Implementation
We use a standard container structure:
```html
<div class="container">
    <div class="row">
        <div class="col-12 col-md-6 col-lg-4">Column 1</div>
        <div class="col-12 col-md-6 col-lg-4">Column 2</div>
        <div class="col-12 col-md-12 col-lg-4">Column 3</div>
    </div>
</div>
```

- **Mobile View (`col-12`):** Each column takes up full width (12 grid tracks) and stacks vertically.
- **Tablet View (`col-md-6` / `col-md-12`):** First two columns split the space (6 tracks each, 50% width) and the third wraps to its own full row (12 tracks).
- **Desktop View (`col-lg-4`):** All columns sit side-by-side taking 4 tracks (33.3% width) each.
- **Interactive File:** [index.html](file:///d:/New%20folder/Module1_Bootstrap/03_Responsive_Grid/index.html)
