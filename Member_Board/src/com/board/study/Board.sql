CREATE TABLE memberBoard(
Board_num int PRIMARY KEY NOT NULL, 
Board_id VARCHAR(15), 
Board_subject VARCHAR(50), 
Board_content text, 
Board_file VARCHAR(20), 
Board_re_ref int, 
Board_re_lev int, 
Board_re_seq int, 
Board_readcount int, 
Board_date DATE ); 
--제약조건 추가
 ALTER TABLE memberBoard ADD CONSTRAINT pk_board_id FOREIGN KEY(Board_id) REFERENCES boardMember(Member_id); 
 --테이블 조회 
 SELECT * FROM memberBoard;
SELECT * FROM memberBoard WHERE board_num = 2;
select * from information_schema.table_constraints where table_name = 'memberBoard';
select * from memberBoard order by board_re_ref desc, board_re_seq asc limit 1, 10";
delete from memberBoard where board_id='aaaa';

alter table memberBoard modify board_file varchar(50);