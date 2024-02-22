<div align="center">

![Group_2111](https://github.com/GDSC-KNU/3rd-sc-ex1-CitySavior-Backend/assets/72246411/60bc0c48-7e2a-484f-9f24-880148df77ab)
</div>

<div align="center">

<br>

# CitySavior

</div>

<div align="center">

### This app lets users report and manage issues on a map, using AI to assess damage and contribute to urban sustainability.

</div>



1️⃣ [Introduction](#👋-introduction)

2️⃣ [Repository](#📁-repository)

3️⃣ [Installation](#🧰-installation)

4️⃣ [Contributors](#🤝-contributors)

5️⃣ [Technologies](#⚙️-technologies)

6️⃣ [Images](#📷-images)

# 👋 Introduction

> This app offers users the capability to report issues via a map interface,
> which administrators can then manage and mark as completed.
> Leveraging AI technology, it assesses the extent of damage reported.
> Reports can be sorted by location, category, and severity.
> Users have the flexibility to review, modify, or delete their reports as needed,
> contributing to the continuous improvement of urban sustainability.

# 📁 Repository

[📱 Android Repository](https://github.com/GDSC-KNU/3rd-sc-ex1-CitySavior-Android)
<br>
[🌐 Backend Repository](https://github.com/GDSC-KNU/3rd-sc-ex1-CitySavior-Backend)
<br>
[🤖 AI Repository](https://github.com/GDSC-KNU/3rd-sc-ex1-CitySavior-AI)

# 🧰 Installation


## Android



- Clone project to local machine

    ```bash
    git clone https://github.com/GDSC-KNU/3rd-sc-ex1-CitySavior-Android
    ```

- Fill local.properties
    ```bash
    google_maps_key=YOUR_API_KEY
    ```
- Fill firebase file into CitySavior/app/google-services.json
- Change Backend API-URL into CitySavior/data/src/main/koilin/com/citysavior/android/dataapi/ApiClient.kt

## Backend

- Requirements
    - JDK 17
    - Redis 7.2.4
    - intelliJ IDEA
- steps
    - Open a terminal on your machine.
    - Navigate to the directory where you want to clone the code.
    - Run the following command to clone the code
        ```bash
        google_maps_key=YOUR_API_KEY
        ```
    - Refresh Gradle to fetch the necessary dependencies.
    - Run `CitySaviorApplication`

## AI
- Requirements 
  - Python = 3.8.8 
  - Anaconda 
  - tensorflow = 2.10.0 
  - flask = 3.0.0 
  - numpy =1.25.2 
  - Pillow
- RUN
  - Go to your directory where to run 
  - Clone the repository from github
    ```bash
    git git clone https://github.com/GDSC-KNU/3rd-sc-ex1-CitySavior-AI.git
    ```
  - Init conda virtual environment with python 3.8.8 version, install all the requirements mentioned above
  - Activate your environment, and Run `server.py` in directory with
    ```bash
    python server.py
    ```
- Run in background
  - If you want to run server in background, you can use this command
    ```bash
    nohup python -u server.py &
    ```
  - If you want to observe the code running, you can put this command in your prompt
    ```bash
    tail -f nohup.out
    ```



# ⚙️ Technologies

- Android
- Google Maps API
- GCP
- Google Cloud Storage
- Google Cloud Sql

# 📷 Images


|       **OnBoarding Page**               |                                                          **Home Page**                                                           |  **Map Page**   |
|:----------------------------------:|:--------------------------------------------------------------------------------------------------------------------------------:|:---------------:|
|![1_onboarding](https://github.com/GDSC-KNU/3rd-sc-ex1-CitySavior-Backend/assets/72246411/330c861a-ca18-48e1-90c3-8dae98bfc482)|    ![2_home](https://github.com/GDSC-KNU/3rd-sc-ex1-CitySavior-Backend/assets/72246411/6df71f62-f30a-4ffb-ba31-60492e44c9f5)     |       ![3_map](https://github.com/GDSC-KNU/3rd-sc-ex1-CitySavior-Backend/assets/72246411/b73d1285-c212-455f-a875-deb09a8c9a11)           |
|         **Create Repory Page**      |                                                      **Report Deail Page**                                                       | **Profil Page** |
|![5_create_report](https://github.com/GDSC-KNU/3rd-sc-ex1-CitySavior-Backend/assets/72246411/d5c6bc34-5bc3-4d2e-b733-af911778fdf9)|![6_report_detail](https://github.com/GDSC-KNU/3rd-sc-ex1-CitySavior-Backend/assets/72246411/17aae4fd-25d4-4235-9bdd-e4e333ce09db)|![7_profile](https://github.com/GDSC-KNU/3rd-sc-ex1-CitySavior-Backend/assets/72246411/4eea95af-4fca-4992-8aea-332a4dc1b27d)


# 🤝 Contributors

[<img src="https://github.com/bayy1216.png" width="100px">](https://github.com/bayy1216)
[<img src="https://github.com/jinchiim.png" width="100px">](https://github.com/jinchiim)
[<img src="https://github.com/sami355-24.png" width="100px">](https://github.com/sami355-24)
[<img src="https://github.com/Bosung-Baek" width="100px">](https://github.com/Bosung-Baek)
