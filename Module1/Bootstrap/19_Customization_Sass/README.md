# 19. Customization with Sass

This directory demonstrates how to override Bootstrap 5's default design tokens using SCSS source files and compiling them via npm.

## Exercise 19.1 & 19.2: Step-by-Step Compilation Setup

### 1. Initialize NPM and Install Packages
Configure the build environment using the command line:
```bash
npm install bootstrap sass
```

### 2. Configure SCSS Source Files
Create a variables file (`scss/_variables.scss`) containing overrides. Variables must be declared **before** importing the core Bootstrap libraries:
```scss
// Custom variables overrides
$primary: #059669; // Emerald green
$border-radius: 1.5rem; // Rounded corners
```

Create a main loader stylesheet (`scss/custom.scss`):
```scss
@import "variables";
@import "../../node_modules/bootstrap/scss/bootstrap";
```

### 3. Compile Stylesheet via Sass CLI
Run the compiler manually or configure it as an npm build script:
```bash
npx sass scss/custom.scss dist/custom.css
```
This processes all maps, mathematical functions, variables, and generates standard CSS inside the `dist` folder.
- **Interactive File:** [index.html](file:///d:/New%20folder/Module1_Bootstrap/19_Customization_Sass/index.html)
