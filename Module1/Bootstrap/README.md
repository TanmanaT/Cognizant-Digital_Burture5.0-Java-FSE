# Module 1: Bootstrap 5 Learning Hub & Exercises

Welcome to the **Bootstrap 5 Learning Hub**. This directory hosts a structured, comprehensive playground solving all exercises from Module 1, ranging from basic setup to Sass compilation and custom theme builds.

## Directory Structure

An interactive central dashboard has been created at the root to easily browse, launch, and experiment with each exercise.

```text
d:\New folder\Module1_Bootstrap\
├── README.md                  # Main documentation
├── index.html                 # Central Navigation Dashboard
├── package.json               # Package setup & build commands
├── 01_Setting_Up/             # CDN & local setup
├── 02_Structure/              # Exploration of folders & JS bundle plugins
├── 03_Responsive_Grid/        # Basic 3-column responsive layout
├── 04_Column_Layouts/         # Sidebar layouts & 4-column equal cards
├── 05_Alignment_Reordering/   # justify-content, align-items, and order utilities
├── 06_Flexbox_Utilities/      # Responsive navbar & card footers using flex
├── 07_Typography/             # Headings, lead texts, muted, & text transforms
├── 08_Forms/                  # Registration input groups & floating labels
├── 09_Buttons/                # Color variations, button groups, and checkbox toggles
├── 10_Navbars_Navigation/     # Collapsible top menu bar, tabs, and pills
├── 11_Cards_Media_Objects/    # User cards & modern flexbox media objects
├── 12_Spacing_Utilities/      # Margin & padding multipliers and pricing section
├── 13_Colors_Backgrounds/     # Theme indicators & dark gradient overlays
├── 14_Display_Visibility/     # Breakpoint elements toggling & tablet sidebar
├── 15_Borders_Shadows/        # Circular avatar bounds, outline borders, and shadows
├── 16_Positioning/            # Fixed footers & relative overlays for badges
├── 17_Icons/                  # Footer links & icon-only buttons using Bootstrap Icons
├── 18_JS_Plugins/             # Modals & collapsible accordion menus
└── 19_Customization_Sass/     # Overriding primary variables & compiling SCSS
```

---

## Getting Started

### Prerequisites
Make sure you have [Node.js](https://nodejs.org) installed on your machine.

### Installation
From the root directory (`d:\New folder\Module1_Bootstrap`), run the following command to download Bootstrap and dependencies:
```bash
npm install
```

### Compiling custom Sass (Exercise 19)
To recompile the custom brand styles (custom primary color and border radius):
```bash
# Navigate to customization directory
cd 19_Customization_Sass

# Compile custom SCSS
npx sass scss/custom.scss dist/custom.css
```

---

## Interactive Dashboard
Double-click [index.html](file:///d:/New%20folder/Module1_Bootstrap/index.html) in your browser to run the master showcase dashboard and navigate through the completed exercises.
