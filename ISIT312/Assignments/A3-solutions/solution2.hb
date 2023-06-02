print 'Solution for Assignment 3, Task 2'
print 'Student number: 7431673'
print 'Student name: Elroy Chua Ming Xuan'

get 'task2', 'student|007'

get 'task2', 'submission|007|312|assignment|1'

scan 'task2', {COLUMNS => ['STUDENT:fname', 'STUDENT:lname']}

scan 'task2', {FILTER=> "SingleColumnValueFilter('STUDENT', 'lname', =, 'binary:Potter')", COLUMNS => ['STUDENT:snumber', 'STUDENT:fname', 'STUDENT:lname', 'STUDENT:degree']}

alter 'task2', 'delete' => 'FILES'

alter 'task2', {NAME=> 'ENROLMENT',VERSIONS=>2}

put 'task2', 'enrolment|007|312', 'ENROLMENT:date', '12-Mar-2017'
put 'task2', 'enrolment|007|312', 'STUDENT:snumber', '007'
put 'task2', 'enrolment|007|312', 'SUBJECT:code', '312'

put 'task2', 'enrolment|666|312', 'ENROLMENT:date', '12-Mar-2017'
put 'task2', 'enrolment|666|312', 'STUDENT:snumber', '666'
put 'task2', 'enrolment|666|312', 'SUBJECT:code', '312'

scan 'task2', {COLUMN=>'ENROLMENT', VERSIONS=>2}

alter 'task2', {NAME=> 'ENROLMENT', VERSIONS=>3}

disable 'task2'
drop 'task2'
