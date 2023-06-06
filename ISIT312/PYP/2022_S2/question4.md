# Question 4
Consider the following conceptual schema of a four-dimensonal data cube
```apache
## 1. Use HBase shell command language to write the commands that create HBase table implementing a conceptual schema given above.
CREATE 'MEDICALOPERATION'
DESCRIBE 'MEDICALOPERATION'

## 2. Write the commands of HBase shell command language that insert into HBase table created in the previous step information about 2 patients, 2 doctors, 1 hospital, 2 medical procedures.
ALTER 'MEDICALOPERATION', {NAME=> 'DOCTOR', TYPE => '1'}
ALTER 'MEDICALOPERATION', {NAME=> 'PATIENT', TYPE => '1'}
ALTER 'MEDICALOPERATION', {NAME=> 'HOSPITAL', TYPE => '1'}
ALTER 'MEDICALOPERATION', {NAME=> 'MEDICALPROCEDURE', TYPE => '1'}

PUT 'MEDICALOPERATION', 'PATIENT:P01', 'Patient: MedicalInsuranceID', 'P01'
PUT 'MEDICALOPERATION', 'PATIENT:P01', 'Patient: Full-name', 'John Adams'
PUT 'MEDICALOPERATION', 'PATIENT:P02', 'Patient: MedicalInsuranceID', 'P02'
PUT 'MEDICALOPERATION', 'PATIENT:P02', 'Patient: Full-name', 'Alexander Hamilton'

PUT 'MEDICALOPERATION', 'DOCTOR: D01', 'Doctor: medical-license', 'D01'
PUT 'MEDICALOPERATION', 'DOCTOR: D01', 'Doctor: full-name', 'Steven Strange'
PUT 'MEDICALOPERATION', 'DOCTOR: D02', 'Doctor: medical-license', 'D02'
PUT 'MEDICALOPERATION', 'DOCTOR: D02', 'Doctor: full-name', 'Dolittle'

PUT 'MEDICALOPERATION', 'HOSPITAL: TTSH', 'Hospital: name', 'TTSH'
PUT 'MEDICALOPERATION', 'HOSPITAL: TTSH', 'Hospital: address', 'Novena'

PUT 'MEDICALOPERATION','MEDICALPROCEDURE: MP001|PO1|D01|TTSH', 'MedicalProcedure: ProcedureID', 'MP001'
PUT 'MEDICALOPERATION','MEDICALPROCEDURE: MP001|PO1|D01|TTSH', 'Patient: MedicalInsuranceID', 'P001'
PUT 'MEDICALOPERATION','MEDICALPROCEDURE: MP001|PO1|D01|TTSH', 'Doctor: medical-license', 'D01'
PUT 'MEDICALOPERATION','MEDICALPROCEDURE: MP001|PO1|D01|TTSH', 'Hospital: name', 'TTSH'
PUT 'MEDICALOPERATION','MEDICALPROCEDURE: MP001|PO1|D01|TTSH', 'MedicalProcedure: cost-spent', '5000'
PUT 'MEDICALOPERATION','MEDICALPROCEDURE: MP001|PO1|D01|TTSH', 'MedicalProcedure: time-spent', '2days'

## 3 Write the commands of HBase shell command language, that perform the following data retrieval operations on Hbase table.
## - Find all information about a patient whose medical insurance number MI6789.\
GET 'MEDICALOPERATION', 'PATIENT:MI678', {COLUMNS => 'patient', TYPE => '1'}
## - Find all information about the medical procedures where the costs were higher than 100.
SCAN 'MEDICALOPERATION', {FILTER => "(SingleColumnValueFilter('MedicalProcedure', 'cost-spend', >, 'binary:100'))"}

## 4 Extend Hbase table with information about the consultations between patients and doctors. A description of a consultation consists of a consultation date and consultation topic. Write the commands of HBase shell command language that adds information about two consultations
ALTER 'MEDICALOPERATION', {NAME => 'CONSULTATION', TYPE => ','}
PUT 'MEDICALOPERATION', 'CONSULTATION: C001|P01|D01', 'CONSULTATION: consultationID', 'C001'
PUT 'MEDICALOPERATION', 'CONSULTATION: C001|P01|D01', 'CONSULTATION: description', '03 Mar 17, Cough'
PUT 'MEDICALOPERATION', 'CONSULTATION: C001|P01|D01', 'Patient: P01', 'P01'
PUT 'MEDICALOPERATION', 'CONSULTATION: C001|P01|D01', 'Doctor: D01', 'D01'
PUT 'MEDICALOPERATION', 'CONSULTATION: C002|P02|D02', 'CONSULTATION: consultationID', 'C002'
PUT 'MEDICALOPERATION', 'CONSULTATION: C002|P02|D02', 'CONSULTATION: description', '03 Jun 17, Cold'
PUT 'MEDICALOPERATION', 'CONSULTATION: C002|P02|D02', 'Patient: P02', 'P02'
PUT 'MEDICALOPERATION', 'CONSULTATION: C002|P02|D02', 'Doctor: D02', 'D02'

#5 Extend Hbase table with information about the specialisations of doctors. Assume, that a specialisation is described by a name and level. Write the commands of HBase shell command language that adds information about a specialisation of one doctor.
ALTER 'MEDICALOPERATION', {NAME => 'SPECIALISATION', TYPE => ','}
PUT 'MEDICALOPERATION', 'SPECIALISATION: S01|Surgeon', 'Specialisation: name', 'Surgeon'
PUT 'MEDICALOPERATION', 'SPECIALISATION: S01|Surgeon', 'Specialisation: level', '1'
PUT 'MEDICALOPERATION', 'SPECIALISATION: S01|Surgeon', 'Specialisation: medLicense', 'S01'
