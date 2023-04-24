# Inhouse_Navigation_Task

**INTRODUCTION**
This code aims to solve the test_task given in the below file.

[test_task_JAVA.pdf](https://github.com/vaseemr/Inhouse_Navigation_Task/files/11309284/test_task_JAVA.pdf)

**To Run Locally**

1.Clone the repo in your local.

2.Download dependencies using maven install.

3.Run the Application file.

I have used in-memory H2 Database.To access it follow the below steps

1.Run the application locally

2.From browser hit (http://localhost:8080/h2-console/)

3.Click connect with default values.

You should see a window like this
![image](https://user-images.githubusercontent.com/70680736/233970649-80c486e4-1ea9-468e-bf27-7b9d4145f102.png)


**Endpoints Exposed**

Please refer the postman collection attached here (Inhouse_Navigation_Task/inHousenavigation.postman_collection.json).

**1.POST:**

http://localhost:8080/api/addMS

**Request body**

{

"base_station_id": uuid,

"reports": [

{"mobile_station_id": uuid, "distance": float, "timestamp": timestamp},

{"mobile_station_id": uuid, "distance": float, "timestamp": timestamp},

]

}

**Result**

![image](https://user-images.githubusercontent.com/70680736/233971931-086643fc-2e4f-4ee8-920f-cc38e6c3798a.png)

![image](https://user-images.githubusercontent.com/70680736/233972152-c9c3fbf9-709f-4b4e-8adb-f767e8528b65.png)

**2.GET:**

http://localhost:8080/api/fetchMS/{UUID}

**Result**

![image](https://user-images.githubusercontent.com/70680736/233972325-68ed3c08-0629-4260-a39d-77a9f64b5828.png)




