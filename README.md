# Cellular Usage Demo Application

### Overview
This Android demo application showcases a user's **cellular usage summary** — including data, call minutes, and SMS usage — with **promotional offers** and **available plans**.  
It is built entirely using **Jetpack Compose** and follows the **MVVM architecture pattern**.

The goal is to demonstrate clean architecture, reactive state management using **StateFlow**, and a modern declarative UI approach.

---

### Features
- **Splash Screen** → Navigates automatically to Dashboard after delay  
- **Dashboard Screen**
  - Displays usage summary (Data, Minutes, SMS)
  - Shows renewal date and remaining balance
  - Displays promotional banners (dynamic list)
- **Plans Screen**
  - Lists available packages with price, limits, and subscribe button
- **Settings Screen**
  - Toggle notifications
- **Footer**
  - Displays company logo and name
- **Navigation**
  - Implemented via `NavHost` with sealed class routes
- **Mock Data**
  - static mock used for usage and plan details
- **Notification**
  - Data usage notification    
---

### Architecture
- **MVVM (Model–View–ViewModel)** pattern
- **Model:** `UsageState.kt`, `PromoBanner.kt`, `Plan.kt`
- **ViewModel:** Handles state and business logic
- **View:** Compose UI screens (Dashboard, Plans, Settings)
- **Navigation:** Managed via sealed `Screen` class
- **Dependency Injection:** Optional Hilt for scalability
- **Testing:** Unit tests for ViewModels using `runTest`




