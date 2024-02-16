
# Train Reservation System

## Overview
This project implements a train reservation system using Java, showcasing the Model-View-Controller (MVC) architectural pattern. It provides a graphical user interface for users to make train reservations, view train schedules, and manage passenger information. The system also supports administrative tasks like managing routes and discounts.

## Features
- **User Authentication**: Secure login system for both administrators and passengers.
- **Train Reservations**: Interface for booking, viewing, and canceling train reservations.
- **Passenger Management**: Allows users to enter and modify passenger details.
- **Discount Management**: Administrators can manage discount offers for passengers.
- **Route Management**: Administrators can add, update, or delete train routes.
- **Ticket Printing**: Generates PDF tickets for users after reservation.

## Technical Details
- **MVC Architecture**: Separates the application logic, user interface, and control logic, enhancing maintainability and scalability.
- **Database Integration**: Utilizes JDBC to connect to and manipulate a relational database for storing user, train, and reservation information.
- **CRUD Operations**: Supports Create, Read, Update, and Delete operations for managing data.
- **Java Swing for GUI**: Uses Swing components to build a user-friendly interface.

## Getting Started
1. **Prerequisites**: Ensure you have Java and JDBC installed on your system.
2. **Database Setup**: Initialize your database using the provided SQL scripts.
3. **Configuration**: Update `DatabaseConnection.java` with your database credentials.
4. **Running the Application**: Compile and run the `TrainReservationGUI.java` to start the application.

## Structure
- `LoginFrame.java`: Manages user login.
- `PassengerGUI.java`: Interface for passenger information management.
- `TrainReservationGUI.java`: Main interface for reservation and schedule viewing.
- `DiscountCardGUI.java`, `TrajetGUI.java`: Administrative interfaces for managing discounts and routes.
- `PDFTicketPrinter.java`: Utility for generating tickets.
- `DatabaseConnection.java`: Handles database connectivity.

## Contributing
Contributions are welcome! Please fork the repository and submit pull requests with your improvements.

## License
Distributed under the MIT License. See `LICENSE` for more information.

## Contact
For support or to report issues, please contact the project team through GitHub.
