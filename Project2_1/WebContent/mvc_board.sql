--테이블 생성
create table mvc_board (
    bId number(4) primary key,
    bName varchar2(20),
    bTitle varchar2(100),
    bContent varchar2(300),
    bDate date default sysdate,
    bHit number(4) default 0,
    bGroup number(4),
    bStep number(4),
    bIndent number(4)
);

--시퀀스 생성

drop table mvc_board;

select * from mvc_board;

create sequence mvc_board_seq;

drop sequence mvc_board_seq;

commit;