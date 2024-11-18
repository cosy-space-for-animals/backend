# **Memopet Project**

## **Overview**  
The Memopet Project is a service designed to record a pet's entire lifetime, allowing memories to be preserved not only during their lifetime but also as a memorial after they have passed away, built using Spring Boot v3.1.2, JPA, and MySQL.

---

## **Development Environment**  
- **Framework**: Spring Boot v3.1.2  
- **Database**: MySQL  
- **ORM**: JPA  
- **Build Tool**: Gradle  

---

## **Role**  
**Full-stack Developer**  
- System Design and Implementation  

---

## **Key Features & Achievements**  

### **1. Enhancing Service Stability**  
- Implemented features such as **image upload**, **email sending**, and **SMS services** using **Message Queue** to ensure high service stability.

### **2. Integrated Login Development**  
- Developed a **social login integration system** using **Spring Security**, including support for **Naver login**.  
- Designed and implemented a process for **automatic reissuance of access tokens** using refresh tokens (Naver access token validity: 3 hours).  

### **3. Database Design and API Development**  
- Designed a **report system inspired by Memopet**, handling **DB schema design** and **API development** directly.  
- Developed a well-structured **layered architecture** comprising **XX database tables** and **50+ services** for efficient project organization.  

### **4. Optimizing Data Logging**  
- Designed a **data logging feature** to log access data into the database using **Filter**.  
- Implemented a **BatchUpdate process** capable of inserting up to **50,000 records per second** for optimized performance.  

### **5. Automated Deployment**  
- Developed **automation scripts** to streamline deployment from the local development environment to the test server.  

---

## **How to Run**  
1. Clone the repository:  
   ```bash
   git clone https://github.com/memopet-project/memopet-backend.git
