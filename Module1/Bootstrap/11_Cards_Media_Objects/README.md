# 11. Cards and Media Objects

## Exercise 11.1: Profile Card Component
- **`card`**: The core content container layout.
- **`card-body`**: Provides consistent padded spacing inside cards.
- **`card-title`**: Configures header titles inside the container body.
- **`card-img-top`**: Stretches images across the full top-border bounds.

## Exercise 11.2: Modern Media Object (Bootstrap 5 Alternative)
> [!NOTE]
> The `.media` CSS class was officially **deprecated and removed** in Bootstrap 5.
> Bootstrap 5 developers recommend using modern CSS flexbox classes directly.

To replicate the media object structure, we use:
```html
<div class="d-flex align-items-start gap-3">
  <img src="..." class="flex-shrink-0" alt="...">
  <div>
    <h5>Media Heading</h5>
    <p>Media content...</p>
  </div>
</div>
```
- **`flex-shrink-0`**: Prevents the thumbnail image from compressing when text content expands.
- **`gap-3`**: Adds standard spacing gutter between the image and descriptive text blocks.
- **Interactive File:** [index.html](file:///d:/New%20folder/Module1_Bootstrap/11_Cards_Media_Objects/index.html)
