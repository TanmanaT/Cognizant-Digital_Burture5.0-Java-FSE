# 14. Display and Visibility

This section shows how to dynamically hide, show, or display elements based on viewport size.

## Exercise 14.1: Responsive Displays
Bootstrap's responsive display classes follow the `d-{breakpoint}-{value}` naming system:
- **`d-none`**: Hides the component globally on all breakpoints.
- **`d-md-block`**: Displays the element as a standard block layout on screen widths of `md` (768px) and larger.
- **`d-lg-flex`**: Activates a CSS flexbox layout once screen widths cross the `lg` (992px) container threshold.

## Exercise 14.2: Tablet-Up Responsive Sidebar
Constructed a sidebar column configuration using `class="col-md-3 d-none d-md-block"`:
- On mobile devices, the element disappears (`d-none`), reserving full-screen width for content blocks.
- On tablet views and above, it expands as a side column (`d-md-block` combined with `col-md-3`).
- **Interactive File:** [index.html](file:///d:/New%20folder/Module1_Bootstrap/14_Display_Visibility/index.html)
