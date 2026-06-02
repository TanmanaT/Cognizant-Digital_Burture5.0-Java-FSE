# 5. Alignment and Reordering in Grid

## Exercise 5.1: Centering Row Items

Demonstrated absolute horizontal and vertical centering inside a row:
- **CSS classes on Row:** `justify-content-center` (centers columns horizontally) and `align-items-center` (centers columns vertically).
- Requires height definition or min-height on parent container to demonstrate vertical alignment clearly.

## Exercise 5.2: Screen-size Conditional Ordering

Demonstrated rearranging structural columns without modifying the HTML structure:
- **Column A:** Has class `order-md-2`. Appears first on mobile viewports (default rendering flow), but slides to the second position on medium (`md`) screen sizes and above.
- **Column B:** Has class `order-md-1`. Appears second on mobile viewports, but leaps to the first position on medium (`md`) screens and above.
- **Interactive File:** [index.html](file:///d:/New%20folder/Module1_Bootstrap/05_Alignment_Reordering/index.html)
