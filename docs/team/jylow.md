---
layout: page
title: Low Jun Yu's Project Portfolio Page
---

### Overview

UNOFAS is a desktop app for Financial Advisors (FA) to manage client’s contacts, optimized for use via a Command Line
Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). The app also includes features
such as sorting, scheduling and other commands to query information quickly required by the FA.

### Summary of Contributions

Given below are my contributions to the project.

* **New Feature**: Added the ability to sort list by appointments and lexicographical order
    * What it does: allows the user to perform sorting of list by appointment time and lexicographical order of name.
    * Justification: This feature improves the product significantly because a user can more efficiently find clients by name and the proximity of their appointments to view upcoming appointments.
    * Highlights: This enhancement creates a base to implement different sorting capabilities in the future through sorting by new comparators. It required an understanding of ObservableList interface and the way the list is being tracked by JavaFX.
  
* **New Feature**: Added confirm override window for `schedule` command if person already has a current appointment
  * What it does: Caution a user if he intends to schedule a new appointment when there is already one that is not yet complete.
  * Justification: This feature reduces the likelihood of overwriting an appointment if a person already has one. Most people only arrange one appointment at a time. So the design prevents multiple appointments and also serves as a reminder of a previously set appointment that might have been forgotten.
  * Highlights: This feature causes the logic flow of the method to change if there is currently an appointment and results in breaking the execution into 2, getting a response from the user before deciding whether to continue the execution of the program.
* **Code Contributed**: [RepoSense](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=jylow&breakdown=true)

* **Enhancements Implemented**: 
    * Sort Command to enable the list to be sorted by lexicographical order of name of client
    * Override prompt to ask user for confirmation before overriding an appointment.

* **Contributions to the UG**:
    * Added documentation for the features `sort` [\#81](https://github.com/AY2324S1-CS2103T-F12-1/tp/pull/81)

* **Contributions to the DG**: to be added soon.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#125](https://github.com/AY2324S1-CS2103T-F12-1/tp/pull/125) [\#133](https://github.com/AY2324S1-CS2103T-F12-1/tp/pull/133#pullrequestreview-1699166607)

* **Contributions to team-based tasks**: 
  * Released version 1.3 of the application
