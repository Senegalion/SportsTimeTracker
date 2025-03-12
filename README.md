### **Sports Time Tracker - Java Application**

#### **Overview**
This is a Java-based CLI application that allows users to:
- Log sports activities
- View logged activities
- Calculate total time spent on sports

The project integrates **GitHub for version control**, **Jenkins for CI/CD**, and **Docker for containerization**.

---

## **Setup and Usage (Windows)**

### **1️⃣ Prerequisites**
Ensure you have installed:  
✅ **Java (JDK 21+)** - [Download here](https://adoptopenjdk.net/)  
✅ **Maven** - [Install Maven](https://maven.apache.org/install.html)  
✅ **Git** - [Download Git](https://git-scm.com/downloads)  
✅ **Docker Desktop** - [Install Docker](https://www.docker.com/products/docker-desktop/)  
✅ **Jenkins** - [Setup Jenkins on Windows](https://www.jenkins.io/doc/book/installing/windows/)

---

### **2️⃣ Clone the Repository**
```sh
git clone https://github.com/Senegalion/SportsTimeTracker.git
cd SportsTimeTracker
```

---

### **3️⃣ Build and Run Locally**
#### **Build with Maven**
```sh
mvn clean package
```
#### **Run the Application**
```sh
java -jar target/SportsTimeTracker.jar
```

---

## **Continuous Integration with Jenkins (Windows)**
### **4️⃣ Setting Up Jenkins**
1. Install **Jenkins on Windows**
2. Install **Plugins**:
   - Git Plugin
   - Maven Integration Plugin
   - Jacoco Plugin
   - Docker Pipeline Plugin
3. Add a **New Jenkins Pipeline Job**
4. Use the following Jenkinsfile

#### **Jenkinsfile (Windows)**
```groovy
pipeline {
    agent any
    environment {
        DOCKERHUB_CREDENTIALS_ID = 'Docker_Hub'
        DOCKERHUB_REPO = 'senegalion/sports-time-tracker'
        DOCKER_IMAGE_TAG = 'latest_v1'
    }
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Senegalion/SportsTimeTracker.git'
            }
        }
        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
        stage('Code Coverage') {
            steps {
                bat 'mvn jacoco:report'
            }
        }
        stage('Publish Test Results') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }
        stage('Publish Coverage Report') {
            steps {
                jacoco()
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}")
                }
            }
        }
        stage('Push Docker Image to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKERHUB_CREDENTIALS_ID) {
                        docker.image("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}").push()
                    }
                }
            }
        }
    }
}
```

---

## **Containerization with Docker (Windows)**
### **5️⃣ Build Docker Image Locally**
```sh
docker build -t sports-time-tracker .
```
### **6️⃣ Run Docker Container Locally**
```sh
docker run -it --rm sports-time-tracker
```
### **7️⃣ Push Docker Image to Docker Hub**
```sh
docker tag sports-time-tracker your-dockerhub-username/sports-time-tracker:latest
docker push your-dockerhub-username/sports-time-tracker:latest
```

---

## **Deploy and Test in Play-with-Docker**
💻 **Go to**: [Play with Docker](https://labs.play-with-docker.com/)
### **8️⃣ Pull Image in Play-with-Docker**
```sh
docker pull your-dockerhub-username/sports-time-tracker:latest
```
### **9️⃣ Run Container in Play-with-Docker**
```sh
docker run -it --rm your-dockerhub-username/sports-time-tracker:latest
```

---