# Weeb 💬

**Weeb** is a real-time, secure chatting web application built for fast, peer-to-peer communication. It supports end-to-end encrypted messaging, file sharing, and peer-to-peer video calls using cutting-edge technologies like WebSocket, WebRTC, and RSA encryption.

---

## 🔧 Tech Stack

- **Backend:** Spring Boot, WebSocket, WebRTC, OAuth, JWT, RSA Encryption, SSE (Server-Sent Events)
- **Frontend:** React.js
- **Database:** MongoDB, PostgreSQL

---

## ✨ Key Features

- 🔒 **End-to-End Encryption** for all messages
- 📞 **Peer-to-Peer Video Calls** using WebRTC
- 📁 **File Transfer** between users
- 🧑‍🤝‍🧑 **Contact Request & Connection System**
- ⚡ Real-time communication via WebSocket and SSE

---

## 🖥️ App Type

> This is a **Web Application**.

---

## 🚀 Installation

To run the app locally:

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/weeb.git
   ```

2. **Create a PostgreSQL Database**
   - Create a new database in PostgreSQL.
   - Update your database credentials in the `application.properties` file:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/your_db_name
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     ```

3. **Setup MongoDB**
   - Ensure MongoDB is installed and running.
   - Configure the MongoDB connection URI if required.

4. **Run the App in Tomcat Server**
   - Open the project in your preferred IDE (e.g., IntelliJ, Eclipse) or terminal.
   - Build and deploy the Spring Boot backend on a Tomcat server.

---

## 📲 Usage Instructions

### 🔐 Sign Up
- Register with a valid email, username, and password to create an account.

### 🔓 Login
- Log in using your registered credentials if you're already a member.

### 🤝 Connect with Contacts
- Search for other users and send a connection request to establish contact.

### 💬 Start Chatting or Video Calling
- Exchange real-time messages, share files, or initiate secure peer-to-peer video calls.

---

## 👨‍💻 Author

Developed with ❤️ by **Ashique Mohammad F**  
GitHub: [Ashique Mohammad F](https://github.com/master-vibe)

