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

