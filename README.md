# Data_Visualization_ETL_Pipeline
Visualizing country data with an ETL(Exract Transform Load) pipeline using DAO(Data Access Object) pattern. -UOI Project

## Prerequisite :wave:

- MySQL - Download [here](https://www.mysql.com)

- MySQL Workbench - Download [here](https://dev.mysql.com/downloads/file/?id=509428) 

- Jupyter Notebook - Download [here](https://jupyter.org/install)

- The Dataset that we will be using is being downloaded from [TheWorldBank](https://data.worldbank.org/) and is the '[countries.csv](https://github.com/Georgemouts/Data_Visualization_ETL_Pipeline/blob/main/countries_data.csv)'

## Step 1 : Clean Dataset & Create Database :memo: 
1. We use the [CleanData.ipynb](https://github.com/Georgemouts/Data_Visualization_ETL_Pipeline/blob/main/CleanData.ipynb) to clean the ***countries_data.csv*** dataset and extract only the ***NY***,***SP***and ***SE***  families of Code Indicators. We store the data in 3 csv files **countries.csv** , **indicators.csv** and **year_value.csv**.
2. We create a schema in MySQL Workbench and load the data (using the ***LOAD DATA INFILE*** statement).In MySQL database we will have 3 tables:
 
  - country(id, Name, Code)
  - indicator(id, Name, Code)
  - indicates(id_country, id_ind, year, value)


## Step 2 : Create packages - Start building the app :package:
We created the structure of the app and then we did the  implementation.

- View package: create the user interface with fxml files
- Controller package : take inputs from fxml files and do some process if is needed
- DAO package : create objects that interact with MYSQL database


## SQL-Schema

![alt text](https://github.com/Georgemouts/Data_Visualization_ETL_Pipeline/blob/main/img/Sql.png "Schema")




