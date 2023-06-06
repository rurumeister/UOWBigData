# Question 2

```apache
## (1) Find all information about a student number 007, list one version per cell.
get 'task2', 'student|007'

## (2) Find all information about a submission of assignment 1 performed by a student 007 in a subject 312, list one version per cell.
get 'task2', 'submission|007|312|assignment|1'

## (3) Find the first and the last names of all students, list one version per cell.
scan 'task2', {COLUMNS =>['STUDENT: fname', 'STUDENT: lname']}

## (4) Find all information about a student whose last name is Potter, list one version per cell.
scan 'task2', {FILTER => "SingleColumnValueFilter('STUDENT', 'lnmame', =, 'binary:Potter')", COLUMNS =>['STUDENT:snumber', 'STUDENT:fname', 'STUDENT:lname', 'STUDENT:degree']}

## (5) Delete a column family FILES.
alter 'task2', 'delete' => 'FILES'

## (6) Add a column family ENROLMENT that contains information about dates when the subjects have been enrolled by the students and allow for 2 versions in each cell of the column family.
alter 'task2', {NAME=> 'ENROLMENT', VERSIONS =>2}

## (7) Insert information about at least two enrolments performed by the students.
put 'task2', 'enrolment|007|312', 'ENROLMENT:date', '12-Mar-2017'
put 'task2', 'enrolment|007|312', 'STUDENT:snumber', '007'
put 'task2', 'enrolment|007|312', 'SUBJECT:code', '312'

put 'task2', 'enrolment|666|312', 'ENROLMENT:date', '12-Mar-2017'
put 'task2', 'enrolment|666|312', 'STUDENT:snumber', '666'
put 'task2', 'enrolment|666|312', 'SUBJECT:code', '312'

## (8) List information about all enrolments performed by the students.
scan 'task2', {COLUMN => 'ENROLMENT', VERSIONS => 2}

## (9) Increase the total number of versions in each cell of a column family ENROLMENT.
alter 'task2', {NAME => 'ENROLMENT', VERSIONS => 3}

## (10)Delete HBase table task2.
disable 'task2'
drop 'task2'
```