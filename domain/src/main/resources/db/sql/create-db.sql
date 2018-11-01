create table employee
(id IDENTITY, name varchar(100));

create table task
(id IDENTITY, description varchar(100));

create table skillname
(id IDENTITY, name varchar(100));


create table emp_skills(
	id IDENTITY,
	emp_id INT,
	skill_id INT,
	level varchar(10)
);

alter table emp_skills
ADD FOREIGN KEY (EMP_ID)
REFERENCES employee(ID);

alter table emp_skills
ADD FOREIGN KEY (SKILL_ID)
REFERENCES SKILLNAME(ID);

create table task_skills(
	id IDENTITY,
	task_id INT,
	skill_id INT,
	level varchar(10)
);

alter table task_skills
ADD FOREIGN KEY (TASK_ID)
REFERENCES employee(ID);

alter table task_skills
ADD FOREIGN KEY (SKILL_ID)
REFERENCES SKILLNAME(ID);
