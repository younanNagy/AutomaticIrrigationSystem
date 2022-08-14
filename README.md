# AutomaticIrrigationSystem
## Smart irrigation is pecomming more and more popular and we provide a simulation for the whole process.
### Main Components:
##### 1: A back end system holds all the data for the plots, thier irrigation plans and schedules.
##### 2: external service that simulates an IOT device reponds with available or not.

### Functionality
###### 1: A user can add and update a plot
###### 2: A user can configure more that Irrigation plan for a plot with different schedules.
###### 3: A user can check the history for all irrigation slots which of them succeeded 

### Data Mode:
![image](https://user-images.githubusercontent.com/32937105/184551706-9e590be9-72e2-471b-b998-88e3125baf46.png)


### APIs
#### You can find all the APIs with data samples in the PostMan collection added in the repo
##### 1: Add Plot
http://localhost:8080/plots/ -X POST

##### 2: Get All Plots
http://localhost:8080/plots/ -X GET

##### 3: Schedule IrrigationPlan for an existing plot 
http://localhost:8080/plots/{plot_id}/irrigation_plans

###### it schedules the hob using cron expresion and you can use the below link to generate one as you choose
https://www.freeformatter.com/cron-expression-generator-quartz.html
