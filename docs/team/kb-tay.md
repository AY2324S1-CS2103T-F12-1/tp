---
layout: page
title: Aaron Tay's Project Portfolio Page
---

### Overview

UNOFAS is a desktop app for **Financial Advisors (FA)** to manage client’s contacts, optimized for use via a **Command Line Interface (CLI)** while still having the benefits of a **Graphical User Interface (GUI)**. The app also includes features such as sorting, scheduling and other commands to query information quickly required by the FA.

### Summary of Contributions

Given below are my contributions to the project.

* **New Feature**: Added the ScheduleItem class as a new field to Person
  * What it does: Ensures that a Person has a 1-to-1 relationship with a ScheduleItem object.
  * Justification: This feature provides users the ability to associate appointments with their clients. 
  * Highlights: An abstract class is used to allow methods to be executed on the person's appointment field without needing to know if it is a null appointment or appointment. 
  * Pull Request [#70](https://github.com/AY2324S1-CS2103T-F12-1/tp/pull/70)
  
  <br>
  
* **New Feature** Added the ability to schedule appointments. 
  * What it does: Allows the user to create an appointment with the specified client.
  * Justification: This feature provides users the ability to schedule and keep track of appointments with their clients.
  * Pull Request [#70](https://github.com/AY2324S1-CS2103T-F12-1/tp/pull/70)

  <br>

* **New Feature** Added the ability to complete appointments. 
  * What it does: Allows users to clear appointments completed from the contact book. 
  * Justification: With this feature, user can keep track of appointments completed.
  * Highlights: This command allows for more flexibility, giving users the choice to clear appointments by user's index or by a date. 
  * Pull Request [#133](https://github.com/AY2324S1-CS2103T-F12-1/tp/pull/133)
  

* **Code Contributed**: [RepoSense](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=kb-tay&breakdown=true)

* **Enhancements Implemented**: 
  * Added testing for implemented features 


* **Contributions to the UG**:
  * Added documentation for `schedule` and `complete` command.
  

* **Contributions to the DG**:
  * Added implementation details of the `schedule` command.
  

* **Contributions to team-based tasks**: to be added soon.
