drop database if exists team;
create database team;

use team;

drop table if exists board;
create table board(								-- 판
	b_no int primary key auto_increment, 
    n_no int null
    
);

drop TABLE if exists player;
create table player(
   p_no int primary key auto_increment, 
   p_name varchar(10) ,
    p_money int , 
    p_turn int,
    b_no int,				-- 플레이어의 현재 위치
    foreign key ( b_no ) references board ( b_no )
);


drop table if exists nation;
create table nation(							-- 플레이어가 소유한 나라
   n_no int primary key auto_increment, 
    n_name varchar(15) , 
    n_price int null, 
    n_toll_fee int null,
    p_no int null,
    foreign key ( p_no ) references player ( p_no )     
);






            insert into nation values ( null , "출발" , null , null , null );
            -- 주로 아시아, 가격 쌈 빨간색 구역
                              --  n_no    n_name   n_price  n_fee   p_no
            insert into nation values ( null , "타이베이" , 50000 , 20000 , null );
            insert into nation values ( null , "베이징" , 80000 , 40000 , null);
            insert into nation values ( null , "마닐라" , 80000  , 40000 , null);
            insert into nation values ( null , "제주도" , 200000 , 300000 , null );
            

            insert into nation values ( null , "황금열쇠" , null , null , null );
            -- 주로 동유럽 노란색 구역
            insert into nation values ( null , "그리스" , 140000  , 10000 , null);
            insert into nation values ( null , "코펜하겐" , 40000  , 10000 , null);
            insert into nation values ( null , "스톡홀름" , 160000  , 12000 , null);
            insert into nation values ( null , "베른" , 180000 , 14000 , null );
            

            insert into nation values ( null , "올림픽" , null , null , null );
            -- 주로 초기 대항해시대 관련 도시 초록색 구역
            insert into nation values ( null , "상파울루" , 240000  , 20000 , null);
            insert into nation values ( null , "시드니" , 240000 , 20000 , null );
            insert into nation values ( null , "부산" , 500000 , 600000 , null );
            insert into nation values ( null , "하와이" , 260000  , 22000 , null );
            
                
                insert into nation values ( null , "무인도" , null , null , null );
                
                insert into nation values ( null , "싱가포르" , 100000 , 6000 , null );
                insert into nation values ( null , "베를린" , 160000  , 12000 , null);
            insert into nation values ( null , "리스본" , 260000 , 22000, null );
            insert into nation values ( null , "서울" , 500000 , 500000 , null );

select * from nation;

drop table if exists gold_key;
create table gold_key(
   c_num   int primary key auto_increment, 
    c_name varchar(10) , 
    c_coment varchar(100) , 
    c_use boolean , 
    p_no int null, 
    foreign key ( p_no ) references player ( p_no )    
);
                  --  c_num   c_name            c_coment         c_use      p_no
insert into gold_key values( null , "정기종합소득세" , "땅 구매한것당 3만씩 지불" , false , null );      -- 왜 숫자로 나오지?
insert into gold_key values( null , "방범비" , "땅 구매한것당 1만씩 지불" , false , null );
insert into gold_key values( null , "통행권" , "1회 통행료 패스" , false , null );
insert into gold_key values( null , "뒤로 이동" , "뒤로 두칸 이동" , false , null );
insert into gold_key values( null , "고속도로" , "출발지로 이동" , false , null );
insert into gold_key values( null , "복권담청" , "20만원 당첨" , false , null );
insert into gold_key values( null , "생일축하" , "다른 플레이어에게 10만원 받기" , false , null );
insert into gold_key values( null , "해외유학" , "10만원 차감" , false , null );
insert into gold_key values( null , "기지강탈" , "상대방이 소유한 땅 무효화" , false , null );
insert into gold_key values( null , "무인도 탈출권" , "1회 사용가능" , false , null );

select * from gold_key;

alter table board add foreign key ( n_no ) references nation ( n_no );

DESCRIBE board;
DESCRIBE gold_key;