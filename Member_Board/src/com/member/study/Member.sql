create table boardMember(
Member_id varchar(20) primary key not null,
Member_pw varchar(20),
Member_name varchar(20),
Member_age int,
Member_gender varchar(20),
Member_email varchar(30)
);

select * from boardMember;
select member_pw from boardMember where member_id='dddd';

alter table boardMember modify member_age varchar(20);
alter table boardMember change member_age member_birth varchar(20);