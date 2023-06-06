# Design and implementation of HBase Table
Create HBase script solution1.hb with HBase shell commands that create 
HBase table and load sample data into the table. Load into the table information 
about at least one department, three employees such that that one of them is a 
manager of the others and two projects the employees are working on

```apache
    disable 'SOLUTION1'
    drop 'SOLUTION1'
    create 'SOLUTION1', 'DEPARTMENT', 'EMPLOYEE', 'PROJECT'
    describe 'SOLUTION1'

    put 'SOLUTION1', 'department: TECH', 'DEPARTMENT: name', 'TECH'
    put 'SOLUTION1', 'department: TECH', 'DEPARTMENT: budget', '25000'

    put 'SOLUTION1', 'employee: E001|TECH|001', 'EMPLOYEE: enumber', 'E001'
    put 'SOLUTION1', 'employee: E001|TECH|001', 'EMPLOYEE: first_name', 'Elroy'
    put 'SOLUTION1', 'employee: E001|TECH|001', 'EMPLOYEE: last_name', 'Chua'
    put 'SOLUTION1', 'employee: E001|TECH|001', 'EMPLOYEE: salary', '5000'
    put 'SOLUTION1', 'employee: E001|TECH|001', 'EMPLOYEE: isManager', 'false'
    put 'SOLUTION1', 'employee: E001|TECH|001', 'DEPARTMENT: name', 'TECH'
    put 'SOLUTION1', 'employee: E001|TECH|001', 'PROJECT: code', '001

    put 'SOLUTION1','employee:E002|TECH|002','EMPLOYEE: enumber','E002'
    put 'SOLUTION1','employee:E002|TECH|002','EMPLOYEE: first-name','John'
    put 'SOLUTION1','employee:E002|TECH|002','EMPLOYEE: last-name','Doe'
    put 'SOLUTION1','employee:E002|TECH|002','EMPLOYEE: salary','4500'
    put 'SOLUTION1','employee:E002|TECH|002','EMPLOYEE: isDepartmentManager','false'
    put 'SOLUTION1','employee:E002|TECH|002','DEPARTMENT: name','TECH'
    put 'SOLUTION1','employee:E002|TECH|002','PROJECT: code','002'

    put 'SOLUTION1','employee:E003|TECH|002','EMPLOYEE: enumber','E003'
    put 'SOLUTION1','employee:E003|TECH|002','EMPLOYEE: first-name','Alex'
    put 'SOLUTION1','employee:E003|TECH|002','EMPLOYEE: last-name','Wasabi'
    put 'SOLUTION1','employee:E003|TECH|002','EMPLOYEE: salary','12000'
    put 'SOLUTION1','employee:E003|TECH|002','EMPLOYEE: isDepartmentManager','true'
    put 'SOLUTION1','employee:E003|TECH|002','DEPARTMENT: name','TECH'
    put 'SOLUTION1','employee:E003|TECH|002','PROJECT: code','001'
    put 'SOLUTION1','employee:E003|TECH|002','PROJECT: code','002'

    put 'SOLUTION1', 'project:001', 'PROJECT: code', '001'
    put 'SOLUTION1', 'project:001', 'PROJECT: title', 'Migration from old stack to new stack'
    put 'SOLUTION1', 'project:001', 'PROJECT: salary', '25000'

    put 'SOLUTION1', 'project:002', 'PROJECT: code', '002'
    put 'SOLUTION1', 'project:002', 'PROJECT: title', 'Web Scraper'
    put 'SOLUTION1', 'project:002', 'PROJECT: salary', '10000'

    describe 'SOLUTION1'
    scan 'SOLUTION1'
```