# 17. Icons with Bootstrap Icons

This directory covers downloading, linking, and embedding Bootstrap Icons within user interfaces.

## Exercise 17.1: Social Media Footer
Linked Bootstrap Icons font styling:
```html
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" rel="stylesheet">
```
Embedded social media icons using native `<i>` blocks with matching class attributes:
- `bi bi-twitter-x`
- `bi bi-github`
- `bi bi-linkedin`
- `bi bi-discord`

## Exercise 17.2: Icon-Only Buttons
- Replaced standard textual button indicators with clean svg icons (e.g. `bi-play-fill`, `bi-gear-fill`, `bi-trash-fill`).
- **Best Practice:** When removing text labels from interactive components, always define descriptive `aria-label` tags (e.g. `aria-label="Settings"`) on parent buttons to preserve screen reader accessibility.
- **Interactive File:** [index.html](file:///d:/New%20folder/Module1_Bootstrap/17_Icons/index.html)
