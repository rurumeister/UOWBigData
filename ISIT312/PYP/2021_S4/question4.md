# Question 4
Consider the following conceptual schema of a three-dimensional data cube.

``` apache
## 1) Use HBase shell command language to write the commands that create HBase table implementing a conceptual schema given above
CREATE 'TRIPTABLE'
DESCRIBE 'TRIPTABLE'

ALTER 'TRIPTABLE', {NAME => 'TRUCK', TYPE => '1'}
ALTER 'TRIPTABLE', {NAME => 'TRIP', TYPE => '1'}
ALTER 'TRIPTABLE', {NAME => 'DRIVER', TYPE => '1'}

## 2) Write the commands of HBase shell command language that insert into HBase table created in the previous step information about 2 trucks, 2 drivers, and 2 trips

PUT 'TRIPTABLE', 'TRUCK: T01', 'TRUCK: registration', 'T01'
PUT 'TRIPTABLE', 'TRUCK: T01', 'TRUCK: manufacturer', 'Bosche'
PUT 'TRIPTABLE', 'TRUCK: T02', 'TRUCK: registration', 'T02'
PUT 'TRIPTABLE', 'TRUCK: T02', 'TRUCK: manufacturer', 'Mercedes'

PUT 'TRIPTABLE', 'DRIVER: D01', 'DRIVER: license-number', 'D01'
PUT 'TRIPTABLE', 'DRIVER: D01', 'DRIVER: full-name', 'John Wick'
PUT 'TRIPTABLE', 'DRIVER: D02', 'DRIVER: license-number', 'D02'
PUT 'TRIPTABLE', 'DRIVER: D02', 'DRIVER: full-name', 'Andy Samberg'

PUT 'TRIPTABLE', 'TRIP: T01|D01', 'TRIP: fuel-consumed', '10 litres'
PUT 'TRIPTABLE', 'TRIP: T01|D01', 'TRIP: load-weight', '5 tons'
PUT 'TRIPTABLE', 'TRIP: T02|D02', 'TRIP: fuel-consumed', '500 litres'
PUT 'TRIPTABLE', 'TRIP: T02|D02', 'TRIP: load-weight', '3 tons'

## 3) Write the commands of HBase shell command language, that perform the following data retrieval and data manipulation operations on HBASE table.
## - Find all information about a truck with a registration number AL08UK.
GET 'TRIPTABLE', 'TRUCK:AL08UK'

## - Find all information about the trips where a weight of load was higher than 100.
GET 'TRIPTABLE', {FILTER =>"SingleColumnValueFilter('TRIP', 'load-weight', >, 'binary: 100')"}
## - Extend Hbase table with information about a depot of each truck. Record information about a full address of a depo and assume that each truck belongs to only one depot.
ALTER 'TRIPTABLE', {NAME => 'ADRESS', TYPE => '1'}
put 'TRIPTABLE', 'TRUCK: T01', 'DEPOT: address', '123 Main St, CityA, CountryA'
put 'TRIPTABLE', 'TRUCK: T02', 'DEPOT: address', '456 Elm St, CityB, CountryB'

## - Extend Hbase table with information about the skills of drivers. Assume, that a skill is described by a name and a driver may have many skills. of course, as kill can be possessed by many drivers.
ALTER 'TRIPTABLE', {NAME => 'SKILLS', TYPE => '1'}
put 'TRIPTABLE', 'DRIVER: D01', 'SKILLS: skill1', 'Driving License'
put 'TRIPTABLE', 'DRIVER: D01', 'SKILLS: skill2', 'Route Planning'
put 'TRIPTABLE', 'DRIVER: D02', 'SKILLS: skill1', 'Driving License'
put 'TRIPTABLE', 'DRIVER: D02', 'SKILLS: skill3', 'Vehicle Maintenance'
```